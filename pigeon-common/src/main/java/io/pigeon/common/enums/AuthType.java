package io.pigeon.common.enums;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/5
 **/
public enum AuthType {
    BASIC(0),
    OAUTH(1),
    JWT(2),
    OTP(3),
    ;

    private final int order;

    AuthType(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public static AuthType of(Integer val) {
        if (val == null) return null;
        for (AuthType value : AuthType.values()) {
            if (value.order == val) {
                return value;
            }
        }
        return null;
    }
}
