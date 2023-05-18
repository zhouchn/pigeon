package com.zhouzc.pigeon.access.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/11
 **/
public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    private final TcpMessageHandler httpRequestHandler;

    public ServerHandlerInitializer() {
        this.httpRequestHandler = new TcpMessageHandler();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new IdleStateHandler(10, 0, 0))
                .addLast(new ServerIdleStateTrigger())
                .addLast(new StringDecoder())
                .addLast(new StringEncoder())
                .addLast(ChannelExceptionHandler.INSTANCE)
                .addLast(new EchoHandler());
    }
}
