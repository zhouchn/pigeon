package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;
import io.pigeon.common.enums.MessageType;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/23
 **/
public class DirectMessage implements Message {
    private String id;
    private long sequence;

    public String getId() {
        return id;
    }

    public DirectMessage setId(String id) {
        this.id = id;
        return this;
    }

    public long getSequence() {
        return sequence;
    }

    public DirectMessage setSequence(long sequence) {
        this.sequence = sequence;
        return this;
    }

    @Override
    public String id() {
        return null;
    }

    public long sequence() {
        return 0;
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
        return Command.DM;
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
