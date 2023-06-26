package io.pigeon.access.tcp.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.pigeon.access.tcp.internal.ClientAuthedEvent;
import io.pigeon.access.tcp.utils.ChannelConst;
import io.pigeon.auth.api.AuthParam;
import io.pigeon.auth.api.AuthProvider;
import io.pigeon.auth.api.AuthProviderFactory;
import io.pigeon.auth.api.AuthResult;
import io.pigeon.common.entity.AuthMessage;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/18
 **/
public class AuthenticationHandler extends ChannelInboundHandlerAdapter {
    private final AuthProviderFactory authProviderFactory;


    public AuthenticationHandler(AuthProviderFactory authProviderFactory) {
        this.authProviderFactory = authProviderFactory;
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
        return authProviderFactory.getInstance(authType);
    }

    private void notSupportedAuthType(ChannelHandlerContext ctx, AuthMessage authMessage) {
        System.out.println(ctx.channel().id().asShortText() + " unsupported authType. " + authMessage.getAuthType());
    }

    private void onClientAuthSucceeded(ChannelHandlerContext ctx, AuthResult authResult) {
        System.out.println(ctx.channel().id() + " login success. clientId: " + authResult);

        ctx.channel().attr(ChannelConst.CLIENT_ID).set(authResult.getClientId());

        ctx.fireUserEventTriggered(ClientAuthedEvent.INSTANCE);

        // 认证通过，移除认证处理器
        ctx.pipeline().remove(this);
    }

    private void onClientAuthFailed(ChannelHandlerContext ctx, AuthMessage authMessage, Throwable throwable) {
        System.out.println(ctx.channel().id() + " login failed. " + authMessage);
        if (throwable != null) throwable.printStackTrace();
    }
}
