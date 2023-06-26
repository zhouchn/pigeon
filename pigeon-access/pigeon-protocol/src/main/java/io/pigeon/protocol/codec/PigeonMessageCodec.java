package io.pigeon.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.pigeon.common.entity.Message;

import java.util.List;

/**
 * pigeon protocol codec
 *
 * @author chaoxi
 * @since 3.0.0 2023/6/15
 **/
public class PigeonMessageCodec extends ByteToMessageCodec<Message> {
    private final PigeonMessageEncoder messageEncoder;
    private final PigeonMessageDecoder messageDecoder;

    public PigeonMessageCodec(PayloadCodecFactory payloadCodecFactory) {
        this.messageEncoder = new PigeonMessageEncoder(payloadCodecFactory);
        this.messageDecoder = new PigeonMessageDecoder(payloadCodecFactory);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        this.messageEncoder.encode(ctx, msg, out);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        this.messageDecoder.decode(ctx, in, out);
    }
}
