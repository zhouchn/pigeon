package io.pigeon.access.tcp.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Auto-flush data on channel after a write timeout.
 * It's inspired by IdleStateHandler but it's specialized version, just flushing data after write is done on the channel after a period.
 * It's used to avoid aggressively flushing from the ProtocolProcessor.
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/12
 **/
public class ChannelAutoFlushHandler extends ChannelDuplexHandler {
    private static final long MIN_WRITE_TIMEOUT = TimeUnit.SECONDS.toNanos(3);

    private long timeoutNanos = 3000;
    private boolean reading = false;
    private ScheduledFuture<?> flushTimeout;

    public ChannelAutoFlushHandler() {
    }

    public ChannelAutoFlushHandler(int timeout, TimeUnit timeUnit) {
        this.timeoutNanos = Math.max(MIN_WRITE_TIMEOUT, timeUnit.toNanos(timeout));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        flushTimeout = ctx.executor().schedule(() -> {}, timeoutNanos, TimeUnit.NANOSECONDS);
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.reading = true;
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        this.reading = false;
        super.channelReadComplete(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        if (reading) {
            System.out.println("skip");
        } else if (flushTimeout.isDone() || flushTimeout.isCancelled()) {
            System.out.println("start ticks...");
            flushTimeout = ctx.executor().schedule(() -> doFlush(ctx.channel()), timeoutNanos, TimeUnit.NANOSECONDS);
        }
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        if (!flushTimeout.isDone() && !flushTimeout.isCancelled()) {
            flushTimeout.cancel(false);
        }
        super.flush(ctx);
    }

    private void doFlush(Channel channel) {
        if (channel.isOpen() && channel.isActive()) {
            System.out.println("start send...");
            channel.flush();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        this.destroy();
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.destroy();
        super.channelInactive(ctx);
    }

    private void destroy() {
        if (this.flushTimeout != null) {
            this.flushTimeout.cancel(false);
            this.flushTimeout = null;
        }
    }
}
