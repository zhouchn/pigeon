package io.pigeon.access.tcp.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutor;
import io.pigeon.access.tcp.internal.ClientAuthedEvent;
import io.pigeon.access.tcp.internal.ConnectionManager;
import io.pigeon.access.tcp.utils.ChannelConst;
import io.pigeon.common.entity.Message;
import io.pigeon.delivery.api.MessageDispatcher;
import io.pigeon.registry.api.RegistryInfo;
import io.pigeon.registry.api.RegistryService;
import io.pigeon.registry.api.RegistryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 消息分发处理器
 *
 * @author chaoxi
 * @since 3.0.0 2023/2/11
 **/
@ChannelHandler.Sharable
public class MessageDispatchHandler extends SimpleChannelInboundHandler<Message> {
    private final Logger logger = LoggerFactory.getLogger(MessageDispatchHandler.class);

    private static final AtomicBoolean DISPATCH_FLAG = new AtomicBoolean(true);
    private final ConcurrentLinkedQueue<Channel> queue = new ConcurrentLinkedQueue<>();

    private final RegistryService registryService;
    private final MessageDispatcher messageDispatcher;
    private final ConnectionManager connectionManager;

    public MessageDispatchHandler(RegistryService registryService, MessageDispatcher messageDispatcher) {
        this.connectionManager = ConnectionManager.INSTANCE;
        this.registryService = registryService;
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        connectionManager.add(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof ClientAuthedEvent) {
            RegistryInfo registryInfo = new RegistryInfo();
            String clientId = ctx.channel().attr(ChannelConst.CLIENT_ID).get();
            registryInfo.setClientId(clientId);
            registryInfo.setStreamId(ctx.channel().id().asShortText());
            registryInfo.setIp(getLocalIpAddress());
            registryInfo.setPort(8000);
            registryInfo.setRegistryType(RegistryType.CLIENT);
            registryService.register(registryInfo);
        }
    }

    // from https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
    private String getLocalIpAddress() throws SocketException {
        return NetworkInterface.networkInterfaces()
                .flatMap(NetworkInterface::inetAddresses)
                .filter(item -> !item.isLoopbackAddress())
                .filter(Inet4Address.class::isInstance)
                .findAny()
                .map(InetAddress::getHostAddress)
                .orElse(null);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        loggingReceivedMessage(message);
        if (!isAllowDispatchToBackend()) {
            logger.warn("dispatch stop");
            queue.add(ctx.channel());
            ctx.channel().config().setAutoRead(false);
            return;
        }
        boolean result = messageDispatcher.tryDispatch(message);
        if (!result && ctx.channel().isActive()) {
            queue.add(ctx.channel());
            messageDispatcher.forceDispatch(message);
            ctx.channel().config().setAutoRead(false);
            if (DISPATCH_FLAG.compareAndSet(true, false)) {
                checkCapacityAndAutoRead(ctx.executor(), 50);
            }
        }
    }

    private void loggingReceivedMessage(Message message) {
        System.out.println();
        System.out.println(message);
        if (logger.isInfoEnabled()) {
            logger.info("receive tcp request: {} ", message);
        }
    }

    private boolean isAllowDispatchToBackend() {
        return DISPATCH_FLAG.get();
    }

    private void checkCapacityAndAutoRead(EventExecutor executor, int timeout) {
        double rate = messageDispatcher.remainingRate();
        if (rate >= 0.2 && DISPATCH_FLAG.compareAndSet(false, true)) {
            for (Channel channel : queue) {
                if (channel.isOpen()) {
                    channel.config().setAutoRead(true);
                } else {
                    channel.close();
                }
            }
        } else {
            int delay = Math.min(timeout * 2, 10000);
            executor.schedule(() -> checkCapacityAndAutoRead(executor, delay), delay, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 此时未必有下发
        // ctx.flush();
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        ChannelConfig config = ch.config();

        if (!ch.isWritable()) {
            if (logger.isWarnEnabled()) {
                logger.warn("{} is not writable, over high water level : {}", ch.id(), config.getWriteBufferHighWaterMark());
            }

            config.setAutoRead(false);
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("{} is writable, to low water : {}", ch.id(), config.getWriteBufferLowWaterMark());
            }

            config.setAutoRead(true);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("close conn");
        connectionManager.remove(ctx.channel());

        RegistryInfo registryInfo = new RegistryInfo();
        registryInfo.setClientId(ctx.channel().attr(ChannelConst.CLIENT_ID).get());
        registryInfo.setStreamId(ctx.channel().id().asShortText());
        registryInfo.setRegistryType(RegistryType.CLIENT);
//        registryService.unregister(registryInfo);
    }

}
