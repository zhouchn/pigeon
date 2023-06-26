package io.pigeon.protocol.codec;

import com.google.common.collect.ImmutableMap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import io.pigeon.common.entity.PingMessage;
import io.pigeon.common.proto.Protocol;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * 协议初始化处理器，连接建立后客户端发送握手消息到服务端，指定使用的协议及版本
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/15
 **/
public class ProtocolInitHandler extends ChannelInboundHandlerAdapter {
    public static final String MESSAGE_CODEC = "CODEC";
    private static final byte[] MAGIC = "🕊".getBytes();
    private static final AttributeKey<Boolean> INIT = AttributeKey.valueOf("init");
    private static Map<Protocol, Supplier<ByteToMessageCodec>> payloadCodecMap;

    static {
        PayloadCodecFactory factory = PayloadCodecFactory.INSTANCE;
        payloadCodecMap = ImmutableMap.<Protocol, Supplier<ByteToMessageCodec>>builder()
                .put(new Protocol("pigeon", "1.0.0"), () -> new PigeonMessageCodec(factory))
                .build();
    }

    private final boolean clientMode;
    private final Set<Protocol> protocols;
    private AtomicInteger counter = new AtomicInteger(0);

    public ProtocolInitHandler(boolean clientMode, Set<Protocol> protocols) {
        this.clientMode = clientMode;
        this.protocols = protocols;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().attr(INIT).set(Boolean.FALSE);
        if (this.clientMode) {
            // send handshake message to server
            protocols.stream().findAny().ifPresent(protocol -> {
                ctx.write(encodeToByteBuf(ctx.alloc().ioBuffer(20), protocol));
                ctx.write(new PingMessage());
//                ctx.write(new PingMessage());
//                ctx.write(new PingMessage());
                ctx.flush();
                System.out.println("client send handshake");
            });
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof ByteBuf buf) {
                System.out.println(buf.readableBytes());
                while (buf.readableBytes() > 0 && !ctx.isRemoved() && ctx.channel().isOpen()) {
                    if (this.clientMode) {
                        handleClientHandShake(ctx, buf);
                    } else {
                        handleServerHandShake(ctx, buf);
                    }
                }
            } else {
                ctx.disconnect();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handleServerHandShake(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        byteBuf.markReaderIndex();
        if (startWith(byteBuf, MAGIC)) {
            System.out.println("client receive handshake");
            // init protocol codec
            Protocol protocol = decodeFromByteBuf(byteBuf);
            if (ctx.channel().attr(INIT).compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
                Supplier<ByteToMessageCodec> factory = payloadCodecMap.get(protocol);
                ctx.pipeline().addAfter(ctx.name(), MESSAGE_CODEC, factory.get());
                // handshake complete, remove self
                System.out.println("client remove init handler");
                ctx.pipeline().remove(this);
                ctx.fireChannelActive();
            }
        } else {
            byteBuf.release();
        }
    }

    private void handleClientHandShake(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        byteBuf.markReaderIndex();
        Attribute<Boolean> initAttr = ctx.channel().attr(INIT);
        if (startWith(byteBuf, MAGIC)) {
            if (Boolean.TRUE.equals(initAttr.get())) {
                ctx.close();
                return;
            }
            // decode payload
            Protocol protocol = decodeFromByteBuf(byteBuf);
            System.out.println("server receive handshake " + counter.incrementAndGet() + " " + protocol);
            if (protocol == null) {
                byteBuf.resetReaderIndex();
                return;
            }
            Supplier<ByteToMessageCodec> factory = payloadCodecMap.get(protocol);
            if (factory == null) {
                System.out.println("nnnnnnn");
                ctx.close();
                return;
            }
            if (initAttr.compareAndSet(Boolean.FALSE, Boolean.TRUE)) {
                ctx.pipeline().addAfter(ctx.name(), MESSAGE_CODEC, factory.get());
                ctx.pipeline().remove(this);
            }
            ctx.writeAndFlush(encodeToByteBuf(ctx.alloc().ioBuffer(), protocol), ctx.voidPromise());
            ctx.fireChannelActive();
        } else {
            byteBuf.resetReaderIndex();
            if (Boolean.TRUE.equals(initAttr.get())) {
                super.channelRead(ctx, byteBuf);
                System.out.println("server remove init handler");
                ctx.pipeline().remove(this);
            } else {
                System.out.println("oops");
                ctx.close();
            }
        }
    }

    private boolean startWith(ByteBuf buf, byte[] bytes) {
        if (buf.readableBytes() < bytes.length) {
            return false;
        }
        byte[] head = new byte[bytes.length];
        buf.readBytes(head);
        return Arrays.equals(head, bytes);
    }

    private ByteBuf encodeToByteBuf(ByteBuf buf, Protocol protocol) {
        byte flag = 0;
        flag |= (3 << 6);
        buf.writeBytes(MAGIC);
        buf.writeByte(flag);
        buf.writeByte(protocol.getName().length());
        buf.writeBytes(protocol.getName().getBytes());
        buf.writeByte(protocol.getVersion().length());
        buf.writeBytes(protocol.getVersion().getBytes());
        return buf;
    }

    private Protocol decodeFromByteBuf(ByteBuf buf) {
        if (buf.readableBytes() < 1) {
            return null;
        }
        byte flag = buf.readByte();
        if (flag == 0) {
            return null;
        }
        if (buf.readableBytes() < 1) {
            return null;
        }
        int len = buf.readByte();
        if (buf.readableBytes() < len) {
            return null;
        }
        byte[] name = new byte[len];
        buf.readBytes(name);
        if (buf.readableBytes() < 1) {
            return null;
        }
        len = buf.readByte();
        if (buf.readableBytes() < len) {
            return null;
        }
        byte[] ver = new byte[len];
        buf.readBytes(ver);
        return new Protocol(new String(name), new String(ver));
    }
}
