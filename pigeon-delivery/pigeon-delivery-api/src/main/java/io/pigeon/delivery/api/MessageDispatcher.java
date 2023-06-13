package io.pigeon.delivery.api;

import io.pigeon.common.entity.Message;

/**
 * 消息分发器，client -> server
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/7
 **/
public interface MessageDispatcher {
    double remainingRate();

    boolean tryDispatch(Message message);
}