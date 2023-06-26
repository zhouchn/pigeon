package io.pigeon.delivery.upstream;

/**
 * 消息分发处理器
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/20
 **/
public class MessageEventHandler extends AbstractPartitionHandler {

    public MessageEventHandler(long ordinal, long numberOfConsumers) {
        super(ordinal, numberOfConsumers);
    }

    @Override
    public void handleEvent(MessageEvent messageEvent, long sequence, boolean endOfBatch) throws Exception {
    }
}
