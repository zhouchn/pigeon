package io.pigeon.common.handler;

import io.pigeon.common.entity.Message;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public interface CommandHandler<T extends Message> {
    void handle(T message);
}
