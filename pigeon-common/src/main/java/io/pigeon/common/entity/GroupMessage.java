package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;
import io.pigeon.common.enums.MessageType;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/23
 **/
public class GroupMessage implements Message {
    @Override
    public String id() {
        return null;
    }

    @Override
    public String sender() {
        return null;
    }

    @Override
    public String receiver() {
        return null;
    }

    @Override
    public Command command() {
        return null;
    }

    @Override
    public long timestamp() {
        return 0;
    }

    @Override
    public MessageType messageType() {
        return MessageType.TEXT;
    }

    @Override
    public boolean hasPayload() {
        return true;
    }

    @Override
    public Object payload() {
        return null;
    }
}
