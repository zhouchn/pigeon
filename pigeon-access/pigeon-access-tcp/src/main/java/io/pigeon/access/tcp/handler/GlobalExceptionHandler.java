package io.pigeon.access.tcp.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception handler
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/13
 **/
@ChannelHandler.Sharable
public class GlobalExceptionHandler implements ChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static final GlobalExceptionHandler INSTANCE = new GlobalExceptionHandler();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added " + ctx.channel().id());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler removed " + ctx.channel().id());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        logger.error("catch exception: ", throwable);
    }
}
