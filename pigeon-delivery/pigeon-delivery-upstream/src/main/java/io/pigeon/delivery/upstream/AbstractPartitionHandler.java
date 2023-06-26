package io.pigeon.delivery.upstream;

import com.lmax.disruptor.EventHandler;

/**
 * 分片处理
 **/
abstract class AbstractPartitionHandler implements EventHandler<MessageEvent> {
    private final long ordinal;
    private final long numberOfConsumers;

    AbstractPartitionHandler(long ordinal, long numberOfConsumers) {
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
    }

    @Override
    public void onEvent(MessageEvent messageEvent, long sequence, boolean endOfBatch) throws Exception {
        if (sequence % numberOfConsumers == ordinal) {
            handleEvent(messageEvent, sequence, endOfBatch);
            System.out.println(Thread.currentThread().getName() + " " + ordinal + " handle log event " + messageEvent + " " + sequence);
        }
    }

    abstract void handleEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception;
}
