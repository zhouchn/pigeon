package io.pigeon.access.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufUtil;
import io.pigeon.common.entity.Message;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/30
 **/
public class ProtostuffMessageCodec<T extends Message> implements PigeonMessageCodec<T> {
    private static final DefaultIdStrategy STRATEGY = new DefaultIdStrategy(
            IdStrategy.DEFAULT_FLAGS
            | IdStrategy.MORPH_COLLECTION_INTERFACES
            | IdStrategy.MORPH_NON_FINAL_POJOS);

    private final ThreadLocal<LinkedBuffer> bufferThreadLocal = ThreadLocal.withInitial(() -> LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

    @Override
    public int encodeTo(T source, ByteBuf buf) throws IOException {
        if (source == null || buf == null) {
            return 0;
        }
        Schema<T> schema = getSchema((Class<T>) source.getClass());
        LinkedBuffer buffer = bufferThreadLocal.get();
        try {
            OutputStream dataOutput = new ByteBufOutputStream(buf);
            return ProtostuffIOUtil.writeTo(dataOutput, source, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public void decodeFrom(ByteBuf buf, int length, T target) {
        if (buf.readableBytes() < length || target == null) {
            return;
        }
        Schema<T> schema = getSchema((Class<T>) target.getClass());

        int offset;
        byte[] array;
        // 检查 ByteBuf 是否有支持数组，ByteBuf 将数据存储在 JVM 的堆空间时，是将数据存储在数组的实现
        if (buf.hasArray()) {
            // 如果有的话，得到引用数组
            array = buf.array();
            // 计算第一字节的偏移量
            offset = buf.arrayOffset() + buf.readerIndex();
        } else {
            array = ByteBufUtil.getBytes(buf, buf.readerIndex(), length, false);
            System.out.println(Arrays.toString(array));
            for (byte b : array) {
                System.out.println(b + " " + Integer.toBinaryString(b));
            }
            offset = 0;
        }

        ProtostuffIOUtil.mergeFrom(array, offset, length, target, schema);
    }

    private <I> Schema<I> getSchema(Class<I> clazz) {
        return RuntimeSchema.getSchema(clazz, STRATEGY);
    }

}
