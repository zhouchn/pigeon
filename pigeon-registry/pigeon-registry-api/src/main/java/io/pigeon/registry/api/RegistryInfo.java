package io.pigeon.registry.api;

import java.util.Map;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/10
 **/
public class RegistryInfo {
    private String clientId;
    private String streamId;
    private String ip;
    private Integer port;
    private RegistryType registryType;
    private Map<String, Object> attachments;

    public String getClientId() {
        return clientId;
    }

    public RegistryInfo setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getStreamId() {
        return streamId;
    }

    public RegistryInfo setStreamId(String streamId) {
        this.streamId = streamId;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public RegistryInfo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public RegistryInfo setPort(Integer port) {
        this.port = port;
        return this;
    }

    public RegistryType getRegistryType() {
        return registryType;
    }

    public RegistryInfo setRegistryType(RegistryType registryType) {
        this.registryType = registryType;
        return this;
    }

    public Map<String, Object> getAttachments() {
        return attachments;
    }

    public RegistryInfo setAttachments(Map<String, Object> attachments) {
        this.attachments = attachments;
        return this;
    }
}
