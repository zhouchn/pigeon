package io.pigeon.registry;

import io.pigeon.registry.api.RegistryInfo;
import io.pigeon.registry.api.RegistryService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/10
 **/
public class LocalRegistryService implements RegistryService {
    private ConcurrentMap<String, RegistryInfo> registryMap = new ConcurrentHashMap<>();

    @Override
    public void register(RegistryInfo registryInfo) {

    }

    @Override
    public void unregister(RegistryInfo registryInfo) {

    }

    @Override
    public RegistryInfo getRegistered(String id) {
        return null;
    }

    @Override
    public List<RegistryInfo> getRegistered(List<String> id) {
        return null;
    }

    @Override
    public List<RegistryInfo> getRegisteredClients() {
        return null;
    }

    @Override
    public List<RegistryInfo> getRegisteredServers() {
        return null;
    }
}
