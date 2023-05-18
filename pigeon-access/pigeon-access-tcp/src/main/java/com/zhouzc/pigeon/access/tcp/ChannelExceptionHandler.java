package com.zhouzc.pigeon.access.tcp;

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
public class ChannelExceptionHandler implements ChannelHandler {
    private final Logger logger = LoggerFactory.getLogger(ChannelExceptionHandler.class);

    public static final ChannelExceptionHandler INSTANCE = new ChannelExceptionHandler();

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
        logger.error("catch exception: ", throwable);
        // 处理异常
//        FullHttpResponse response = new DefaultFullHttpResponse(
//                HttpVersion.HTTP_1_1,
//                HttpResponseStatus.INTERNAL_SERVER_ERROR);
//        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
//        ctx.writeAndFlush(response);
    }
}
