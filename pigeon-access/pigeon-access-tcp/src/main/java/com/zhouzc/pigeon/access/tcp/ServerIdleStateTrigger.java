package com.zhouzc.pigeon.access.tcp;

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
public class ServerIdleStateTrigger extends SimpleUserEventChannelHandler<IdleStateEvent> {

    @Override
    protected void eventReceived(ChannelHandlerContext ctx, IdleStateEvent idleStateEvent) throws Exception {
        IdleState state = idleStateEvent.state();
        if (state == IdleState.READER_IDLE) {
            // 在规定时间内没有收到客户端的上行数据, 主动断开连接
            System.out.println("read timeout " + ctx.channel().id());
            ctx.disconnect().addListener(ChannelFutureListener.CLOSE);
        }
    }

}
