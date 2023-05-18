package com.zhouzc.pigeon.access.tcp;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/11
 **/
@ChannelHandler.Sharable
public class TcpMessageHandler extends SimpleChannelInboundHandler<TcpMessage> {
    private final Logger logger = LoggerFactory.getLogger(TcpMessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TcpMessage tcpMessage) throws Exception {
        logRequest(tcpMessage);
    }

    private void logRequest(TcpMessage tcpMessage) {
        System.out.println();
        System.out.println(tcpMessage);
        if (logger.isInfoEnabled()) {
            logger.info("receive http request: {} {}", tcpMessage);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("close conn");
        super.channelInactive(ctx);
    }

}
