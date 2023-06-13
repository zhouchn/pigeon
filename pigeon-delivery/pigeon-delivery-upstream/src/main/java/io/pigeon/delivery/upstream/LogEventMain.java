package io.pigeon.delivery.upstream;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/20
 **/
public class LogEventMain {
    public static void main(String[] args) {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 8;
        Disruptor<MessageEvent> disruptor = new Disruptor<>(
                MessageEvent::new,
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler(0, 2), new MessageEventHandler(1, 2))
                .then(new ClearingEventHandler());
        disruptor.start();

//        new SequenceBarrier();
        RingBuffer<MessageEvent> ringBuffer = disruptor.getRingBuffer();
        LogEventProducer logEventProducer = new LogEventProducer(ringBuffer);
        for (int i = 0; i < 21; i++) {
            logEventProducer.onData("identity" + i);
        }

        disruptor.shutdown();
    }
}
