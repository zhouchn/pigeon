package io.piegon.tlv;

import io.netty.buffer.ByteBuf;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/29
 **/
public class TlvUtils {
    public static void writeVarInt(long val, ByteBuf buf) {
        int count = 10;
        while (count-- >= 0) {
            long i = val & 127;
            val >>>= 7;
            if (val == 0) {
                System.out.printf("write:%s -> %s%n", i, Long.toBinaryString(i));
                buf.writeByte((byte) i);
                break;
            } else {
                i ^= (1 << 7);
                System.out.printf("write:%d -> %s%n", i, Long.toBinaryString(i));
                buf.writeByte((byte) i);
            }
        }
    }

    public static void writeSignedVarInt(int val, ByteBuf buf) {
        writeVarInt((val << 1) ^ (val >> 31), buf);
    }

    public void writeSignedVarInt(long val, ByteBuf buf) {
        writeVarInt((val << 1) ^ (val >> 63), buf);
    }



    public long readVarInt(ByteBuf buffer) {
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
