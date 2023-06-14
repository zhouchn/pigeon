package io.pigeon.access.tcp.server;

import com.google.common.util.concurrent.Futures;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.WriteBufferWaterMark;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.PlatformDependent;
import io.pigeon.access.api.PigeonServer;
import io.pigeon.access.api.ServerOptions;
import io.pigeon.access.tcp.internal.ConnectionManager;
import io.pigeon.access.tcp.internal.LocalMessageSubscriber;
import io.pigeon.delivery.api.MessageSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/15
 **/
public class TcpServer implements PigeonServer {
    private final Logger logger = LoggerFactory.getLogger(TcpServer.class);
    private int port;
    private Channel channel;
    /**
     * boss 线程组，用于服务端接受客户端的连接
     */
    private EventLoopGroup bossGroup;
    /**
     * worker 线程组，用于服务端接受客户端的数据读写
     */
    private EventLoopGroup workerGroup;

    private ServerOptions options = new ServerOptions();
    private MessageSubscriber messageSubscriber;


    @Override
    public PigeonServer options(ServerOptions options) {
        this.options = options;
        return this;
    }

    @Override
    public MessageSubscriber subscriber() {
        return this.messageSubscriber;
    }

    public Future<Void> listenAndServe() throws InterruptedException {
        return this.listenAndServe(this.port);
    }

    public Future<Void> listenAndServe(int port) throws InterruptedException {
        this.bossGroup = newEventLoopGroup(true);
        this.workerGroup = newEventLoopGroup(false);
        ConnectionManager connManager = ConnectionManager.INSTANCE;
        this.messageSubscriber = new LocalMessageSubscriber(connManager);
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(getServerSocketChannel())
                .option(ChannelOption.SO_BACKLOG, 128) // 设置链接缓存池的大小
                .option(ChannelOption.WRITE_BUFFER_WATER_MARK, WriteBufferWaterMark.DEFAULT) // 设置写缓冲区高低水位，配合后面的 isWritable 使用
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 维持链接的活跃，清理僵尸链接
                .childOption(ChannelOption.TCP_NODELAY, true) // 关闭延迟发送功能
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ServerHandlerInitializer(options.getRegistryService(), options.getMessageDispatcher(), options.getAuthProviderList()));
        ChannelFuture channelFuture = bootstrap.bind(port).sync();
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                channel = future.channel();
                System.out.println("start server at port " + port);
            }
        });
        logger.info("start http server on port[{}] {}", port, channelFuture.isSuccess());
        return channelFuture;
    }

    private EventLoopGroup newEventLoopGroup(boolean boss) {
        int nThreads = boss ? 1 : 0;
        if (PlatformDependent.isOsx()) {
            return new KQueueEventLoopGroup(nThreads);
        }
        if (Epoll.isAvailable()) {
            return new EpollEventLoopGroup(nThreads);
        }
        return new NioEventLoopGroup(nThreads);
    }

    private Class<? extends ServerChannel> getServerSocketChannel() {
        if (PlatformDependent.isOsx()) {
            logger.info("enable kqueue");
            return KQueueServerSocketChannel.class;
        }
        if (Epoll.isAvailable()) {
            // 边缘触发，更少的GC
            logger.info("enable epoll");
            return EpollServerSocketChannel.class;
        }
        // 水平触发
        return NioServerSocketChannel.class;
    }

    @Override
    public Future<Void> shutdownNow() {
        logger.info("stop http server");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        return channel != null ? channel.close() : Futures.immediateVoidFuture();
    }

    @Override
    public Future<Void> shutdown(long timeout, TimeUnit unit) {
        logger.info("stop http server");
        long quietPeriod = Math.max(0, timeout - 1);
        bossGroup.shutdownGracefully(quietPeriod, timeout, unit);
        workerGroup.shutdownGracefully(quietPeriod, timeout, unit);
        return channel != null ? channel.close() : Futures.immediateVoidFuture();
    }

}
