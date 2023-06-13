package io.pigeon.common.enums;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/1
 **/
public enum CodecType {
    TLV(0),
    PROTOBUF(1),
    FLAT_BUFFERS(2),
    CBOR(3),
    JSON(4),
    ;

    private final int value;

    CodecType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CodecType of(int value) {
        CodecType[] values = CodecType.values();
        for (CodecType codecType : values) {
            if (codecType.value == value) {
                return codecType;
            }
        }
        return null;
    }
}
