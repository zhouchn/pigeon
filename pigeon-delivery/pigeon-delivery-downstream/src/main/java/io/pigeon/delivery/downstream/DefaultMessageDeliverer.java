package io.pigeon.delivery.downstream;

import io.pigeon.common.entity.Message;
import io.pigeon.delivery.api.MessageDeliverer;
import io.pigeon.registry.api.RegistryService;

import java.util.Set;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/10
 **/
public class DefaultMessageDeliverer implements MessageDeliverer {
    private final RegistryService registryService;

    public DefaultMessageDeliverer(RegistryService registryService) {
        this.registryService = registryService;
    }

    @Override
    public boolean deliver(Message message, String receiver) {
        return false;
    }

    @Override
    public boolean deliver(Message message, Set<String> receivers) {
        return false;
    }
}
