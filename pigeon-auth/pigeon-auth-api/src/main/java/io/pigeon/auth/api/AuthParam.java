package io.pigeon.auth.api;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/5
 **/
public class AuthParam {
    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public AuthParam setAuthorization(String authorization) {
        this.authorization = authorization;
        return this;
    }
}
