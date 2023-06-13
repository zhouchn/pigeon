package io.pigeon.access.tcp.codec;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TLV
 * Tag: 基础类型or结构体，
 *      var type
 *      seq
 *
 * Length (optional)
 *      2 bytes
 *
 * Value
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/24
 **/
public class TLVDecoder {

    public void decode(ByteBuffer buffer, User user) throws IllegalAccessException {
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            int wareType = b & 7;
            int fieldNumber = b >> 3;
            if (wareType == 0) {
                long varInt = readVarInt(buffer);
                System.out.printf("fn: %s wt: %s val: %s\n", fieldNumber, wareType, varInt);
            } else if (wareType == 1) {
                long len = readVarInt(buffer);
                byte[] bytes = new byte[(int) len];
                buffer.get(bytes);
                System.out.printf("fn: %s wt: %s val: %s\n", fieldNumber, wareType, new String(bytes));
            }
        }
    }

    private long readVarInt(ByteBuffer buffer) {
        byte b;
        int tmp;
        long res = 0;
        int count = 0;
        do {
            b = buffer.get();
            tmp = b & 0xFF;
            if ((tmp & (1 << 7)) != 0) {
                tmp &= 127;
            }
            res ^= ((long) tmp << count * 7L);
            count++;
        } while (((b & 0xFF) & (1 << 7)) != 0);
        return res;
    }

    static class User {
        @Tag(value = 1)
        public int id;
        @Tag(value = 2)
        private String name;
        @Tag(value = 3)
        private int age;
        @Tag(value = 4, signed = true)
        private Integer abc;
        @Tag(value = 5)
        private long timestamp;
    }

    static enum Type {
        INT4,
        INT8,
        INT16,
        INT32,
        ;
    }

    @Target(FIELD)
    @Retention(RUNTIME)
    @Documented
    static @interface Tag {
        int value();

        boolean signed() default false;
    }

    public static void main(String[] args) throws IllegalAccessException {
        TLVDecoder decoder = new TLVDecoder();
        User user = new User();
        user.id = 123;
        user.name = "zs";
        user.age = 2048;
        user.abc = -111;
        user.timestamp = System.currentTimeMillis();
        byte[] bytes = new byte[]{8, 123, 17, 2, 122, 115, 24, -128, 16, 40, -59, -122, -59, -111, -123, 49};
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.put(bytes);
        buffer.flip();
        decoder.decode(buffer, null);

//        byte[] byteArray = encoder.toByteArray(user);
//        System.out.println(Arrays.toString(byteArray));
//        for (byte b : byteArray) {
//            // 保持低位不变，高位用0填充
//            System.out.println(b + "   " + Integer.toBinaryString(b & 0xFF));
//        }
//        System.out.println(Integer.toBinaryString(user.age));

//        System.out.println(Integer.toBinaryString(1138 >>> 7));

//        byte b = (byte) (-14);
//        System.out.println(b);
//        System.out.println(b & 0xFF);
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println(Integer.toUnsignedString(b & 0xFF, 2));
//        System.out.println(((b & 0xFF) & (1 << 7)) == 0);

//        int ii = -2;
//        System.out.println(Integer.toBinaryString(ii));
//        System.out.println(Integer.toUnsignedString(ii, 2));
//        System.out.println((ii << 1) ^ (ii >> 31));
//        System.out.println(ii << 1);
//        System.out.println(Integer.toBinaryString(ii << 1));
//        System.out.println(ii >> 31);
//        System.out.println(Integer.toBinaryString(ii >> 31));
    }
}
