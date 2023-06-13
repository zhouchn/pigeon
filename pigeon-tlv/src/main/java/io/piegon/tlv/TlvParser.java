package io.piegon.tlv;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/29
 **/
public class TlvParser {
    public <T> T parse(ByteBuf buffer, Object bean) {
        while (buffer.readableBytes() > 0) {
            byte b = buffer.readByte();
            int wareType = b & 7;
            int fieldNumber = b >> 3;
            if (wareType == 0) {
                long varInt = readVarInt(buffer);
                System.out.printf("fn: %s wt: %s val: %s\n", fieldNumber, wareType, varInt);
            } else if (wareType == 1) {
                long len = readVarInt(buffer);
                byte[] bytes = new byte[(int) len];
                buffer.readBytes(bytes);
                System.out.printf("fn: %s wt: %s val: %s\n", fieldNumber, wareType, new String(bytes));
            }
        }
        return (T) bean;
    }

    private long readVarInt(ByteBuf buffer) {
        byte b;
        int tmp;
        long res = 0;
        int count = 0;
        do {
            b = buffer.readByte();
            tmp = b & 0xFF;
            if ((tmp & (1 << 7)) != 0) {
                tmp &= 127;
            }
            res ^= ((long) tmp << count * 7L);
            count++;
        } while (((b & 0xFF) & (1 << 7)) != 0);
        return res;
    }
}
