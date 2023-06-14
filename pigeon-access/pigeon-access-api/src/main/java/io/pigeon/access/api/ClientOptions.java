package io.pigeon.access.api;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/14
 **/
public class ClientOptions {
    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public ClientOptions setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public ClientOptions setPort(Integer port) {
        this.port = port;
        return this;
    }
}
