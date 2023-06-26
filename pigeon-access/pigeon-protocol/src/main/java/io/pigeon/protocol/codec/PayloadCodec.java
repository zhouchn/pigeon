package io.pigeon.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.pigeon.common.entity.Message;

import java.io.IOException;

/**
 * payload codec
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/30
 **/
public interface PayloadCodec<T extends Message> {
    int encodeTo(T source, ByteBuf buf) throws IOException;

    void decodeFrom(ByteBuf buf, int length, T target);
}
