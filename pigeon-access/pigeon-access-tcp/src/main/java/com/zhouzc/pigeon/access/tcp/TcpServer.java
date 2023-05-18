package com.zhouzc.pigeon.access.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/15
 **/
public class TcpServer {
    private final Logger logger = LoggerFactory.getLogger(TcpServer.class);
    private int port;
    private Channel channel;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;


    public void listen() throws InterruptedException {
        this.bossGroup = newEventLoopGroup(true);
        this.workerGroup = newEventLoopGroup(false);
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(getServerSocketChannel())
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ServerHandlerInitializer());
        ChannelFuture future = bootstrap.bind(port).sync();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                logger.info("start...");
            }
        });
        logger.info("start http server on port[{}] {}", port, future.isSuccess());
        System.out.println("start server at port " + port);
        this.channel = future.channel();
        //future.channel().closeFuture().sync();
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

    public void close() throws Exception {
        logger.info("stop http server");
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        TcpServer tcpServer = new TcpServer();
        tcpServer.port = 8888;
        tcpServer.listen();
    }
}
