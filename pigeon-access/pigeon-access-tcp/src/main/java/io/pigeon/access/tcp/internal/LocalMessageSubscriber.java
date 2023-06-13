package io.pigeon.access.tcp.internal;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.pigeon.common.entity.Message;
import io.pigeon.delivery.api.MessageSubscriber;

import static io.pigeon.access.tcp.utils.ChannelConst.PROTOCOL_ENCODER;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/12
 **/
public class LocalMessageSubscriber implements MessageSubscriber {
    private final ConnectionManager connManager;

    public LocalMessageSubscriber(ConnectionManager connManager) {
        this.connManager = connManager;
    }

    @Override
    public boolean isWritable(String clientId) {
        Channel channel = connManager.get(clientId);
        return channel != null && channel.isWritable();
    }

    @Override
    public void onMessage(Message message) {
        String receiver = message.receiver();
        Channel channel = connManager.get(receiver);
        if (channel == null || !channel.isActive()) {
            System.out.println("channel is not active");
            return;
        }
        if (channel.isWritable()) {
            if (channel.eventLoop().inEventLoop()) {
                writeToChannel(channel, message);
            } else {
                channel.eventLoop().submit(() -> writeToChannel(channel, message));
            }
        } else {
            System.out.println("channel is not writable");
        }
    }

    private void writeToChannel(Channel channel, Message message) {
        try {
            ChannelHandlerContext ctx = channel.pipeline().context(PROTOCOL_ENCODER);
            ChannelOutboundHandler outboundHandler = (ChannelOutboundHandler) ctx.handler();
            outboundHandler.write(ctx, message, channel.voidPromise());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
