package io.pigeon.access.tcp.client;

import io.pigeon.common.retry.ExponentialBackOffRetry;
import io.pigeon.common.retry.RetryPolicy;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * TCP 客户端
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/17
 **/
public class TcpClient {
    private final String host;
    private final int port;
    private Bootstrap bootstrap;
    /** 将<code>Channel</code>保存起来, 可用于在其他非handler的地方发送数据 */
    private Channel channel;
    /** 重连策略 */
    private RetryPolicy retryPolicy;

    public TcpClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.init();
    }

    private void init() {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientHandlerInitiator(this));
        this.retryPolicy = new ExponentialBackOffRetry(1000, 3, 60 * 1000);
    }

    public void connect() {
        synchronized (bootstrap) {
            ChannelFuture channelFuture = bootstrap.connect(host, port);
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        // 连接失败，触发通道的inactive
                        channelFuture.channel().pipeline().fireChannelInactive();
                    }
                }
            });
            this.channel = channelFuture.channel();
        }
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

}
