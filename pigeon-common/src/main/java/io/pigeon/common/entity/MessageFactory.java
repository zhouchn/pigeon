package io.pigeon.common.entity;

import io.pigeon.common.enums.Command;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/1
 **/
public class MessageFactory {
    public static Message newInstance(Command cmd) {
        if (Command.AUTH.equals(cmd)) {
            return new AuthMessage();
        } else if (Command.DM.equals(cmd)) {
            return new DirectMessage();
        }
        return null;
    }
}
