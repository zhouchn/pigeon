package io.pigeon.delivery.api;

import io.pigeon.common.entity.Message;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/8
 **/
public interface MessageSubscriber {
    boolean isWritable(String clientId);

    void onMessage(Message message);
}
