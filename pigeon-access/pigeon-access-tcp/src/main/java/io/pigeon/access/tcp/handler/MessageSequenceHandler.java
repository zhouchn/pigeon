package io.pigeon.access.tcp.handler;

import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.List;

/**
 * 消息序列处理器，记录收到的最后一个有序的id，如果不连续则缓存起来待后续消息到来后处理
 *
 * 处理重复消息、乱序消息
 *
 * 消息不丢失通过客户端的发送缓冲队列保证，在指定时间内未收到ack则重复发送消息
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/18
 **/
public class MessageSequenceHandler extends ChannelInboundHandlerAdapter {
    // 当前收到的最大的有序ID
    private long curSequence;
    // 不连续消息缓冲队列
    private List<Object> queue;
}
