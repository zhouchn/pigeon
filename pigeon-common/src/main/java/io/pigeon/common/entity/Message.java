package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;
import io.pigeon.common.enums.MessageType;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public interface Message {
    String id();
    String sender();
    String receiver();
    Command command();
    long timestamp();

    default String partitionBy() {
        return receiver();
    }

    default MessageType messageType() {
        return MessageType.NULL;
    }

    default boolean hasPayload() {
        return payload() != null;
    }

    Object payload();
}
