package io.pigeon.access.api;

import io.pigeon.auth.api.AuthProviderFactory;
import io.pigeon.delivery.api.MessageDispatcher;
import io.pigeon.registry.api.RegistryService;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/14
 **/
public class ServerOptions {
    private Integer port;
    private RegistryService registryService;
    private AuthProviderFactory authProviderFactory;
    private MessageDispatcher messageDispatcher;

    public Integer getPort() {
        return port;
    }

    public ServerOptions setPort(Integer port) {
        this.port = port;
        return this;
    }

    public RegistryService getRegistryService() {
        return registryService;
    }

    public ServerOptions setRegistryService(RegistryService registryService) {
        this.registryService = registryService;
        return this;
    }

    public AuthProviderFactory getAuthProviderFactory() {
        return authProviderFactory;
    }

    public ServerOptions setAuthProviderFactory(AuthProviderFactory authProviderFactory) {
        this.authProviderFactory = authProviderFactory;
        return this;
    }

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public ServerOptions setMessageDispatcher(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
        return this;
    }
}
