package io.pigeon.auth;

import io.pigeon.auth.api.AuthProvider;
import io.pigeon.auth.api.AuthProviderFactory;
import io.pigeon.common.enums.AuthType;

import java.util.List;
import java.util.Objects;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/26
 **/
public class DefaultAuthProviderFactory implements AuthProviderFactory {
    private final int length;
    private final AuthProvider[] authProviders;

    public DefaultAuthProviderFactory(List<AuthProvider> authProviders) {
        this.authProviders = buildAuthProviders(authProviders);
        this.length = this.authProviders.length;
    }

    private AuthProvider[] buildAuthProviders(List<AuthProvider> authProviders) {
        int len = authProviders.stream()
                .filter(item -> Objects.nonNull(item.support()))
                .mapToInt(item -> item.support().getOrder())
                .max()
                .orElse(0);
        AuthProvider[] result = new AuthProvider[len];
        for (AuthProvider authProvider : authProviders) {
            AuthType type = authProvider.support();
            if (Objects.isNull(type)) continue;
            result[type.getOrder()] = authProvider;
        }
        return result;
    }

    @Override
    public AuthProvider getInstance(int authType) {
        if (authType < 0 || authType >= length) {
            return null;
        }
        return this.authProviders[authType];
    }

    @Override
    public AuthProvider getInstance(AuthType authType) {
        return getInstance(authType.getOrder());
    }
}
