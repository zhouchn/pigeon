package io.pigeon.common.proto;

import java.util.Objects;

/**
 * 协议实例
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/15
 **/
public class Protocol {
    private final String name;
    private final String version;

    public Protocol(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Protocol protocol = (Protocol) o;
        return Objects.equals(name, protocol.name) && Objects.equals(version, protocol.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version);
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
