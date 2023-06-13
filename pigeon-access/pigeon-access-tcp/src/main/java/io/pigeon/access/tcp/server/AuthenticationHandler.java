package io.pigeon.access.tcp.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.pigeon.access.tcp.internal.ClientAuthedEvent;
import io.pigeon.access.tcp.utils.ChannelConst;
import io.pigeon.auth.api.AuthParam;
import io.pigeon.auth.api.AuthProvider;
import io.pigeon.auth.api.AuthResult;
import io.pigeon.common.entity.AuthMessage;
import io.pigeon.common.enums.AuthType;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/18
 **/
public class AuthenticationHandler extends ChannelInboundHandlerAdapter {
    private final AuthProvider[] authProviders;


    public AuthenticationHandler(List<AuthProvider> authProviders) {
        if (authProviders == null || authProviders.isEmpty()) {
            this.authProviders = new AuthProvider[0];
        } else {
            this.authProviders = buildAuthProviders(authProviders);
        }
    }

    private AuthProvider[] buildAuthProviders(List<AuthProvider> authProviders) {
        int length = authProviders.stream()
                .filter(item -> Objects.nonNull(item.support()))
                .mapToInt(item -> item.support().getOrder())
                .max().orElse(0);
        AuthProvider[] result = new AuthProvider[length];
        for (AuthProvider authProvider : authProviders) {
            AuthType type = authProvider.support();
            if (Objects.isNull(type)) continue;
            result[type.getOrder()] = authProvider;
        }
        return result;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if ((msg instanceof AuthMessage authMessage)) {
            int authType = authMessage.getAuthType();
            AuthProvider authProvider = getAuthProvider(authType);
            if (Objects.isNull(authProvider)) {
                notSupportedAuthType(ctx, authMessage);
                return;
            }
            AuthParam authParam = new AuthParam();
            authParam.setAuthorization(authMessage.getAuthorization());
            var authResultFuture = authProvider.authenticate(authParam);
            authResultFuture.orTimeout(3, TimeUnit.SECONDS)
                    .whenCompleteAsync(((authResult, throwable) -> {
                        if (authResult != null && authResult.isSuccess()) {
                            onClientAuthSucceeded(ctx, authResult);
                        } else {
                            onClientAuthFailed(ctx, authMessage, throwable);
                        }
                    }), ctx.executor());
        } else {
            // drop upstream message when not login
            System.out.println("drop message " + msg);
        }
    }

    private AuthProvider getAuthProvider(int authType) {
        if (authType < 0 || authType >= authProviders.length) {
            return null;
        }
        return this.authProviders[authType];
    }

    private void notSupportedAuthType(ChannelHandlerContext ctx, AuthMessage authMessage) {
        System.out.println(ctx.channel().id().asShortText() + " unsupported authType. " + authMessage.getAuthType());
    }

    private void onClientAuthSucceeded(ChannelHandlerContext ctx, AuthResult authResult) {
        System.out.println(ctx.channel().id() + " login success. clientId: " + authResult);

        ctx.channel().attr(ChannelConst.CLIENT_ID).set(authResult.getClientId());

//        ClientAuthedEvent event = new ClientAuthedEvent();
//        event.setClientId(authResult.getClientId());
//        event.setStreamId(ctx.channel().id().asLongText());
        ctx.fireUserEventTriggered(ClientAuthedEvent.INSTANCE);

        // 认证通过，移除认证处理器
        ctx.pipeline().remove(this);
    }

    private void onClientAuthFailed(ChannelHandlerContext ctx, AuthMessage authMessage, Throwable throwable) {
        System.out.println(ctx.channel().id() + " login failed. " + authMessage);
        if (throwable != null) throwable.printStackTrace();
    }
}
