package io.pigeon.access.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.pigeon.access.tcp.handler.AuthenticationHandler;
import io.pigeon.access.tcp.handler.MessageDispatchHandler;
import io.pigeon.auth.api.AuthProviderFactory;
import io.pigeon.protocol.codec.ProtocolInitHandler;
import io.pigeon.access.tcp.handler.BlackHoleHandler;
import io.pigeon.access.tcp.handler.GlobalAutoFlushHandler;
import io.pigeon.access.tcp.handler.GlobalExceptionHandler;
import io.pigeon.access.tcp.handler.HeartbeatHandler;
import io.pigeon.delivery.api.MessageDispatcher;
import io.pigeon.registry.api.RegistryService;

import java.util.Set;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/11
 **/
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    private final GlobalAutoFlushHandler globalAutoFlushHandler;
    private final AuthProviderFactory authProviderFactory;
    private final MessageDispatchHandler messageDispatchHandler;

    public ServerHandlerInitializer(RegistryService registryService, MessageDispatcher messageDispatcher, AuthProviderFactory authProviderFactory) {
        this.authProviderFactory = authProviderFactory;
        this.globalAutoFlushHandler = new GlobalAutoFlushHandler(GlobalEventExecutor.INSTANCE);
        this.messageDispatchHandler = new MessageDispatchHandler(registryService, messageDispatcher);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new ProtocolInitHandler(false, Set.of()))
                .addLast(globalAutoFlushHandler)
                .addLast(new IdleStateHandler(60, 180, 0))
                .addLast(new HeartbeatHandler())
                .addLast(new AuthenticationHandler(authProviderFactory))
                .addLast(new BlackHoleHandler())
                .addLast(this.messageDispatchHandler)
                .addLast(GlobalExceptionHandler.INSTANCE);
    }
}
