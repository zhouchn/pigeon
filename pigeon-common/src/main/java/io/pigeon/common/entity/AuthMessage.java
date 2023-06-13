package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/22
 **/
public class AuthMessage implements Message {
    private int authType;
    private String authorization;

    public int getAuthType() {
        return authType;
    }

    public AuthMessage setAuthType(int authType) {
        this.authType = authType;
        return this;
    }

    public String getAuthorization() {
        return authorization;
    }

    public AuthMessage setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }

    @Override
    public String id() {
        return null;
    }

    public String sender() {
        return null;
    }

    @Override
    public String receiver() {
        return null;
    }

    @Override
    public Command command() {
        return Command.AUTH;
    }

    @Override
    public long timestamp() {
        return 0;
    }

    @Override
    public Object payload() {
        return authorization;
    }
}
