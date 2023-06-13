package io.pigeon.delivery.upstream;

import com.lmax.disruptor.InsufficientCapacityException;
import com.lmax.disruptor.RingBuffer;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public class LogEventProducer {
    private final RingBuffer<MessageEvent> ringBuffer;

    public LogEventProducer(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String event) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor for that sequence
            MessageEvent logEvent = ringBuffer.get(sequence);
            logEvent.setData(event);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public boolean tryPublish(String data) {
        try {
            long sequence = ringBuffer.tryNext();
            MessageEvent logEvent = ringBuffer.get(sequence);
            logEvent.setData(data);
            ringBuffer.publish(sequence);
            return true;
        } catch (InsufficientCapacityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
