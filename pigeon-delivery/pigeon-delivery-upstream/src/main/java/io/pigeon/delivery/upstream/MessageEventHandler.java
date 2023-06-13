package io.pigeon.delivery.upstream;

import com.lmax.disruptor.EventHandler;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/20
 **/
public class MessageEventHandler implements EventHandler<MessageEvent> {
    private final long ordinal;
    private final long numberOfConsumers;

    public MessageEventHandler(long ordinal, long numberOfConsumers) {
        this.ordinal = ordinal;
        this.numberOfConsumers = numberOfConsumers;
    }

    @Override
    public void onEvent(MessageEvent messageEvent, long sequence, boolean endOfBatch) throws Exception {
//        Thread.sleep(1000);
        if (sequence % numberOfConsumers == ordinal) {
            if (sequence % 10 == 5) {
                Thread.sleep(3000);
            }
            System.out.println(Thread.currentThread().getName() + " " + ordinal + " handle log event " + messageEvent + " " + sequence);
        }
    }
}
