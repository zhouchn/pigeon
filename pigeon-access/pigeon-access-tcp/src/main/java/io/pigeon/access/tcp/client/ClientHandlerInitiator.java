package io.pigeon.access.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.pigeon.message.codec.PigeonMessageDecoder;
import io.pigeon.message.codec.PigeonMessageEncoder;
import io.pigeon.access.tcp.handler.ChannelAutoFlushHandler;
import io.pigeon.access.tcp.handler.HeartbeatHandler;
import io.pigeon.message.codec.MessageCodecFactory;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class ClientHandlerInitiator extends ChannelInitializer<SocketChannel> {
    private final TcpClient tcpClient;
    private final ReconnectHandler reconnectHandler;

    public ClientHandlerInitiator(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        this.reconnectHandler = new ReconnectHandler(tcpClient);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        MessageCodecFactory messageCodecFactory = new MessageCodecFactory();
        socketChannel.pipeline()
//                .addLast(reconnectHandler)
                .addLast(new ChannelAutoFlushHandler())
                .addLast(new PigeonMessageDecoder(messageCodecFactory))
                .addLast(new PigeonMessageEncoder(messageCodecFactory))
                .addLast(new IdleStateHandler(300, 800, 0))
                .addLast(new HeartbeatHandler())
                .addLast(new ClientEmulator());
    }
}
