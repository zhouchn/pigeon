package io.pigeon.access.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.pigeon.access.tcp.codec.PigeonMessageDecoder;
import io.pigeon.access.tcp.codec.PigeonMessageEncoder;
import io.pigeon.access.tcp.handler.ChannelAutoFlushHandler;
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
//                .addLast(new IdleStateHandler(3, 8, 0))
                .addLast(new ClientIdleStateTrigger())
                .addLast(new ClientEmulator());
    }
}
