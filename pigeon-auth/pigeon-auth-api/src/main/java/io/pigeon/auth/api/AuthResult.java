package io.pigeon.auth.api;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/5
 **/
public class AuthResult {
    private boolean success;
    private String clientId;

    public boolean isSuccess() {
        return success;
    }

    public String getClientId() {
        return clientId;
    }
}
