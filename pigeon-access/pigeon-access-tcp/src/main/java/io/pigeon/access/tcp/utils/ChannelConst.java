package io.pigeon.access.tcp.utils;

import io.netty.util.AttributeKey;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/10
 **/
public class ChannelConst {
    public static final String PROTOCOL_ENCODER = "ENCODER";
    public static final String PROTOCOL_DECODER = "DECODER";
    public static final AttributeKey<String> CLIENT_ID = AttributeKey.valueOf("clientId");
    public static final AttributeKey<Boolean> FLUSH = AttributeKey.valueOf("flush");
}
