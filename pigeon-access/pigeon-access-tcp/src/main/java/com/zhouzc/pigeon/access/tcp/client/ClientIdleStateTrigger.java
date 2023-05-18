package com.zhouzc.pigeon.access.tcp.client;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class ClientIdleStateTrigger extends SimpleUserEventChannelHandler<IdleStateEvent> {

    @Override
    protected void eventReceived(ChannelHandlerContext ctx, IdleStateEvent idleStateEvent) throws Exception {
        IdleState state = idleStateEvent.state();
        if (state == IdleState.WRITER_IDLE) {
            // 在规定时间内没有收到客户端的上行数据, 主动断开连接
            System.out.println("write timeout " + ctx.channel().id());
            ctx.writeAndFlush("ping").addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        }
    }

}
