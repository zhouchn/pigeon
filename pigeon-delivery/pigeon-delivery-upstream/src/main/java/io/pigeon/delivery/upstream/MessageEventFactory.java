package io.pigeon.delivery.upstream;

import com.lmax.disruptor.EventFactory;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/20
 **/
public class MessageEventFactory implements EventFactory<MessageEvent> {
    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}
