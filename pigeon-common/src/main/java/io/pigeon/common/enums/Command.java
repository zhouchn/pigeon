package io.pigeon.common.enums;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/21
 **/
public enum Command {
    // 按照使用频率由高到低依次展示
    ACK(0),
    PING(1),
    PONG(2),
    DM(3),
    GM(4),
    REQ(5),
    RESP(6),
    NOTIFY(7),

    SYNC(8),
    RECEIPT(9),
//    PUBLISH(6),
//    SUBSCRIBE(7),
//    UNSUBSCRIBE(8),
    AUTH(10),
    INFO(11),
    QUIT(12),

    // -------- server ---------
    MOVED(13),
    KICK_OUT(14),
    ;

    private final int value;

    Command(int order) {
        this.value = order;
    }

    public int getValue() {
        return value;
    }

    public static Command of(int value) {
        Command[] values = Command.values();
        for (Command command : values) {
            if (command.value == value) {
                return command;
            }
        }
        return null;
    }
}
