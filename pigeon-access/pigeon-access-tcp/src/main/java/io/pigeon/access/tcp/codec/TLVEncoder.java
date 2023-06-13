package io.pigeon.access.tcp.codec;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class TLVEncoder {

    public byte[] toByteArray(User user) throws IllegalAccessException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Field[] fields = user.getClass().getDeclaredFields();
        for (Field field : fields) {
            Tag tag = field.getAnnotation(Tag.class);
            if (tag == null) {
                continue;
            }
            field.setAccessible(true);
            Class<?> type = field.getType();
            Object value = field.get(user);
            if (value == null) {
                continue;
            }
            System.out.println(value.getClass());
            System.out.printf("field number: %d type: %s value: %s%n", tag.value(), type, value);
            if (type.equals(Boolean.class)) {
                System.out.println("bool");
                appendVarInt(buffer, tag.value() << 3 ^ 0);
                appendVarInt(buffer, ((Boolean) value) ? 1 : 0);
            } else if (type == Integer.class) {
                System.out.println("Integer");
                appendVarInt(buffer, tag.value() << 3 ^ 0);
                if (tag.signed()) {
                    appendVarSignedInt(buffer, (Integer) value);
                } else {
                    appendVarInt(buffer, (Integer) value);
                }
            } else if (type == int.class) {
                System.out.println("int");
                appendVarInt(buffer, tag.value() << 3 ^ 0);
                if (tag.signed()) {
                    appendVarSignedInt(buffer, (int) value);
                } else {
                    appendVarInt(buffer, (int) value);
                }
            } else if (type == String.class) {
                appendVarInt(buffer, tag.value() << 3 ^ 1);
                appendVarInt(buffer, ((String) value).length());
                buffer.put(((String) value).getBytes());
            } else if (type == long.class) {
                appendVarInt(buffer, tag.value() << 3 ^ 0);
                appendVarInt(buffer, (long) value);
            } else if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                System.out.println("array[" + componentType + "]");
                System.out.println(type == int[].class);
                System.out.println(type == byte[].class);
                System.out.println(type == char[].class);
                System.out.println(type == Integer[].class);
            } else if (type == List.class) {
                System.out.println("List");
                java.lang.reflect.Type type1 = field.getGenericType();
                System.out.println(((ParameterizedType) type1).getActualTypeArguments()[0]);
            }
        }
        byte[] res = new byte[buffer.position()];
        buffer.flip();
        buffer.get(res);
        return res;
    }

    private void appendVarInt(ByteBuffer buffer, long value) {
        int count = 10;
        while (count-- >= 0) {
            long i = value & 127;
            value >>>= 7;
            if (value == 0) {
                System.out.println("write: " + i);
                buffer.put((byte) i);
                break;
            } else {
                i ^= (1 << 7);
                System.out.println("write: " + i);
                buffer.put((byte) i);
            }
        }
    }

    private void appendVarSignedInt(ByteBuffer buffer, int value) {
        appendVarInt(buffer, (value << 1) ^ (value >> 31));
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
        @Tag(value = 6)
        private boolean male;

        @Tag(value = 7)
        private List<Integer> integers;
        @Tag(value = 8)
        private Integer[] ints;
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
        TLVEncoder encoder = new TLVEncoder();
        User user = new User();
        user.id = 123;
        user.name = "zs";
        user.age = 2048;
        user.male = true;
        user.ints = new Integer[1];
        user.integers = new ArrayList<>();
//        user.abc = -111;
        user.timestamp = System.currentTimeMillis();
        byte[] byteArray = encoder.toByteArray(user);
        System.out.println(Arrays.toString(byteArray));
        for (byte b : byteArray) {
            // 保持低位不变，高位用0填充
            System.out.println(b + "\t" + Integer.toBinaryString(b & 0xFF));
        }
//        System.out.println(Long.toBinaryString(user.age));

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
