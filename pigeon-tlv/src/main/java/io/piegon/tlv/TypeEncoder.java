package io.piegon.tlv;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.Field;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/29
 **/
public interface TypeEncoder {
    void encode(TlvBind bind, Field field, Object bean, ByteBuf buf) throws Exception;
}
