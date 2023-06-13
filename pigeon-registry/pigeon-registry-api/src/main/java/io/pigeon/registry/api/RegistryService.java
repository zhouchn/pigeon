package io.pigeon.registry.api;

import java.util.List;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/10
 **/
public interface RegistryService {
    void register(RegistryInfo registryInfo);

    void unregister(RegistryInfo registryInfo);

    RegistryInfo getRegistered(String id);
    List<RegistryInfo> getRegistered(List<String> id);

    List<RegistryInfo> getRegisteredClients();

    List<RegistryInfo> getRegisteredServers();
}
