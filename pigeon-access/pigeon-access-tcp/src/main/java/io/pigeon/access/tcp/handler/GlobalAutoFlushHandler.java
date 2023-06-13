package io.pigeon.access.tcp.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.Attribute;
import io.pigeon.access.tcp.utils.ChannelUtil;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static io.pigeon.access.tcp.utils.ChannelConst.FLUSH;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/12
 **/
@ChannelHandler.Sharable
public class GlobalAutoFlushHandler extends ChannelDuplexHandler implements AutoCloseable {
    private final long timeoutNanos;
    private final ScheduledFuture<?> flushScheduler;
    private final ConcurrentLinkedQueue<Channel> channelQueue;

    public GlobalAutoFlushHandler(ScheduledExecutorService scheduledExecutorService) {
        this(scheduledExecutorService, 5, TimeUnit.SECONDS);
    }

    public GlobalAutoFlushHandler(ScheduledExecutorService scheduledExecutorService, int delay, TimeUnit timeUnit) {
        this.timeoutNanos = timeUnit.toNanos(delay);
        this.channelQueue = new ConcurrentLinkedQueue<>();
        this.flushScheduler = scheduledExecutorService.scheduleWithFixedDelay(this::doFlush, timeoutNanos, timeoutNanos, TimeUnit.NANOSECONDS);
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        // Channel 去重
        Attribute<Boolean> flushAttr = ctx.channel().attr(FLUSH);
        if (!Boolean.TRUE.equals(flushAttr.getAndSet(Boolean.TRUE))) {
            this.channelQueue.offer(ctx.channel());
            System.out.println(ctx.channel().id() + " start ticks...");
        }
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().attr(FLUSH).set(null);
        super.flush(ctx);
    }

    private void doFlush() {
        Channel channel;
        while ((channel = channelQueue.poll()) != null) {
            if (ChannelUtil.isInvalid(channel)) {
                continue;
            }
            if (channel.attr(FLUSH).compareAndSet(Boolean.TRUE, Boolean.FALSE)) {
                System.out.println(channel.id().asShortText() + " start send...");
                channel.flush();
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 等到下一次定时任务到达时，遍历队列中的Channel 再移除
        // this.channelQueue.remove(ctx.channel());
        ctx.channel().attr(FLUSH).set(null);
        super.channelInactive(ctx);
    }

    @Override
    public void close() throws Exception {
        if (flushScheduler.isCancelled() || flushScheduler.isDone()) {
            return;
        }
        this.doFlush();
        this.flushScheduler.cancel(true);
    }

}
