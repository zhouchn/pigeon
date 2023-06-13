package io.pigeon.delivery.upstream;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import io.pigeon.common.entity.Message;
import io.pigeon.delivery.api.MessageDispatcher;
import io.pigeon.delivery.api.MessageSubscriber;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public class LocalMessageDispatcher implements MessageDispatcher {
    private MessageSubscriber messageSubscriber;
    private final RingBuffer<MessageEvent> ringBuffer;

    public LocalMessageDispatcher() {
        this.ringBuffer = initRingBuffer();
    }

    public LocalMessageDispatcher(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private RingBuffer<MessageEvent> initRingBuffer() {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 8;
        Disruptor<MessageEvent> disruptor = new Disruptor<>(
                new MessageEventFactory(),
                bufferSize,
                DaemonThreadFactory.INSTANCE,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        int consumers = 2;
        EventHandler<MessageEvent>[] eventHandlers = new MessageEventHandler[consumers];
        for (int i = 0; i < eventHandlers.length; i++) {
            eventHandlers[i] = new MessageEventHandler(i, consumers);
        }
        disruptor.handleEventsWith(eventHandlers)
                .then(new ClearingEventHandler());
        disruptor.start();

        return disruptor.getRingBuffer();
    }

    @Override
    public double remainingRate() {
        return ringBuffer.remainingCapacity() * 0.1 / ringBuffer.getBufferSize();
    }

    @Override
    public boolean tryDispatch(Message message) {
        return ringBuffer.tryPublishEvent((event, sequence, data) -> event.setData(data.sender()), message);
    }

}
