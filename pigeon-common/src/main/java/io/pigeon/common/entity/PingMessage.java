package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/22
 **/
public class PingMessage implements Message {
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
        return Command.PING;
    }

    @Override
    public long timestamp() {
        return 0;
    }

    @Override
    public boolean hasPayload() {
        return false;
    }

    @Override
    public Object payload() {
        return null;
    }
}
