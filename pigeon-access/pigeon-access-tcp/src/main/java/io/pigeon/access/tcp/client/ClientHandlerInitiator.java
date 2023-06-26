package io.pigeon.access.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.pigeon.protocol.codec.PayloadCodecFactory;
import io.pigeon.protocol.codec.ProtocolInitHandler;
import io.pigeon.access.tcp.handler.ChannelAutoFlushHandler;
import io.pigeon.access.tcp.handler.HeartbeatHandler;
import io.pigeon.common.proto.Protocol;

import java.util.Set;

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
        PayloadCodecFactory payloadCodecFactory = new PayloadCodecFactory();
        socketChannel.pipeline()
//                .addLast(reconnectHandler)
                .addLast(new ProtocolInitHandler(true, Set.of(new Protocol("pigeon", "1.0.0"))))
                .addLast(new ChannelAutoFlushHandler())
                .addLast(new IdleStateHandler(300, 800, 0))
                .addLast(new HeartbeatHandler())
                .addLast(new ClientEmulator());
    }
}
