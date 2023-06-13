package io.pigeon.access.tcp.client;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.pigeon.common.entity.PingMessage;

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
            System.out.println("write timeout " + ctx.channel().id());
            PingMessage message = new PingMessage();
            ctx.writeAndFlush(message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
        } else if (state == IdleState.READER_IDLE) {
            System.out.println("read timeout, reconnect");
            // 在规定时间内没有收到服务端的下行数据, 主动断开连接，重连
//            ctx.disconnect();
        }
    }

}
