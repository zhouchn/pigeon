package io.pigeon.access.tcp.utils;

import io.netty.channel.Channel;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/13
 **/
public class ChannelUtil {
    private ChannelUtil() {
    }

    public static boolean isValid(Channel channel) {
        return channel.isOpen() && channel.isActive();
    }

    public static boolean isInvalid(Channel channel) {
        return !channel.isOpen() || !channel.isActive();
    }
}
