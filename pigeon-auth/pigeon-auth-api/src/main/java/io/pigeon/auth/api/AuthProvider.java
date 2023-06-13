package io.pigeon.auth.api;

import io.pigeon.common.enums.AuthType;

import java.util.concurrent.CompletableFuture;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/5
 **/
public interface AuthProvider {
    AuthType support();

    CompletableFuture<AuthResult> authenticate(AuthParam authParam);
}
