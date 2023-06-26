package io.pigeon.auth.api;

import io.pigeon.common.enums.AuthType;

/**
 * Auth Provider factory
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/26
 **/
public interface AuthProviderFactory {
    AuthProvider getInstance(int authType);
    AuthProvider getInstance(AuthType authType);
}
