package io.pigeon.common.enums;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/23
 **/
public enum MessageType {
    NULL(0),
    TEXT(1),
    ;

    private final int value;

    MessageType(int value) {
        this.value= value;
    }

    public int getValue() {
        return value;
    }
}
