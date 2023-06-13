package io.pigeon.access.tcp.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleUserEventChannelHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.pigeon.common.entity.Message;
import io.pigeon.common.entity.PingMessage;

/**
 * 业务心跳超时触发器
 *
 * 为什么要有业务心跳
 * - 网络运营商NAT超时删除，无法检测
 * - 对端TCP网络正常，但是CPU被占满，无法正常通讯
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class HeartbeatHandler extends SimpleUserEventChannelHandler<IdleStateEvent> {
    private static final Message PING_MESSAGE = new PingMessage();

    @Override
    protected void eventReceived(ChannelHandlerContext ctx, IdleStateEvent idleStateEvent) throws Exception {
        IdleState state = idleStateEvent.state();
        if (state == IdleState.READER_IDLE) {
            // 在规定时间内没有收到客户端的上行数据, 主动断开连接
            System.out.println("read timeout " + ctx.channel().id());
//            ctx.disconnect().addListener(ChannelFutureListener.CLOSE);
        } else if (state == IdleState.WRITER_IDLE) {
            ctx.write(PING_MESSAGE);
        }
    }

}
