package io.pigeon.delivery.upstream;

import com.lmax.disruptor.EventHandler;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public class ClearingEventHandler implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent event, long l, boolean b) throws Exception {
        event.clear();
    }
}
