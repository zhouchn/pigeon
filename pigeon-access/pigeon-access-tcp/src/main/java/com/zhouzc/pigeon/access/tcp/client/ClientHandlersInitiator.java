package com.zhouzc.pigeon.access.tcp.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class ClientHandlersInitiator extends ChannelInitializer<SocketChannel> {
    private final TcpClient tcpClient;
    private final ReconnectHandler reconnectHandler;

    public ClientHandlersInitiator(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        this.reconnectHandler = new ReconnectHandler(tcpClient);
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(reconnectHandler)
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(new IdleStateHandler(0, 8, 0))
                .addLast(new ClientIdleStateTrigger())
                .addLast(new Pinger());
    }
}
