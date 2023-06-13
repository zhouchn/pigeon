package io.pigeon.access.tcp.client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.pigeon.common.entity.AuthMessage;
import io.pigeon.common.entity.DirectMessage;
import io.pigeon.common.entity.Message;

import java.util.UUID;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class ClientEmulator extends SimpleChannelInboundHandler<Message> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active");
        AuthMessage authMessage = new AuthMessage();
        authMessage.setAuthorization("password");
        ctx.writeAndFlush(authMessage).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("send result:" + channelFuture.isSuccess());
                for (int i = 0; i < 1; i++) {
                    DirectMessage directMessage = new DirectMessage();
                    directMessage.setId(UUID.randomUUID().toString());
                    directMessage.setSequence(i);
                    ctx.write(directMessage);
                }
            }
        });
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println("client receive data: " + msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client inactive");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client unregistered");
    }
}
