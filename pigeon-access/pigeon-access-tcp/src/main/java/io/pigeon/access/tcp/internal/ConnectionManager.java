package io.pigeon.access.tcp.internal;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/12
 **/
public class ConnectionManager {
    private final ConcurrentMap<String, Channel> channelMap = new ConcurrentSkipListMap<>();
    private final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void add(Channel channel) {
        channels.add(channel);
        channelMap.put(channel.id().asShortText(), channel);
    }

    public void remove(Channel channel) {
        channels.remove(channel);
        channelMap.remove(channel.id().asShortText(), channel);
    }

    public Channel get(String channelId) {
        return channelMap.get(channelId);
    }
}
