package io.pigeon.delivery.api;

import io.pigeon.common.entity.Message;

import java.util.Set;

/**
 * 消息投递器，server -> client
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/7
 **/
public interface MessageDeliverer {
    boolean deliver(Message message, String receiver);

    boolean deliver(Message message, Set<String> receivers);
}
