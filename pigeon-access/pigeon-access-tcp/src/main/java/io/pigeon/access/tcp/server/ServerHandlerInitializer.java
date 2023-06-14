package io.pigeon.access.tcp.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.pigeon.access.codec.MessageCodecFactory;
import io.pigeon.access.codec.PigeonMessageDecoder;
import io.pigeon.access.codec.PigeonMessageEncoder;
import io.pigeon.access.tcp.handler.BlackHoleHandler;
import io.pigeon.access.tcp.handler.GlobalAutoFlushHandler;
import io.pigeon.access.tcp.handler.GlobalExceptionHandler;
import io.pigeon.access.tcp.handler.HeartbeatHandler;
import io.pigeon.auth.api.AuthProvider;
import io.pigeon.delivery.api.MessageDispatcher;
import io.pigeon.registry.api.RegistryService;

import java.util.List;

import static io.pigeon.access.tcp.utils.ChannelConst.PROTOCOL_DECODER;
import static io.pigeon.access.tcp.utils.ChannelConst.PROTOCOL_ENCODER;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/11
 **/
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    private final GlobalAutoFlushHandler globalAutoFlushHandler;
    private final List<AuthProvider> authProviders;
    private final MessageCodecFactory messageCodecFactory;
    private final MessageDispatchHandler messageDispatchHandler;

    public ServerHandlerInitializer(RegistryService registryService, MessageDispatcher messageDispatcher, List<AuthProvider> authProviders) {
        this.authProviders = authProviders;
        this.globalAutoFlushHandler = new GlobalAutoFlushHandler(GlobalEventExecutor.INSTANCE);
        this.messageCodecFactory = new MessageCodecFactory();
        this.messageDispatchHandler = new MessageDispatchHandler(registryService, messageDispatcher);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(globalAutoFlushHandler)
                .addLast(PROTOCOL_DECODER, new PigeonMessageDecoder(messageCodecFactory))
                .addLast(PROTOCOL_ENCODER, new PigeonMessageEncoder(messageCodecFactory))
                .addLast(new IdleStateHandler(60, 180, 0))
                .addLast(new HeartbeatHandler())
                .addLast(new AuthenticationHandler(authProviders))
                .addLast(new BlackHoleHandler())
                .addLast(this.messageDispatchHandler)
                .addLast(GlobalExceptionHandler.INSTANCE);
    }
}
