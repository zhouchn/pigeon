package io.piegon.tlv;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/29
 **/
public class TlvBuilder {
    private Map<Class<?>, TypeEncoder> encoderMap = new HashMap<>();

    public TlvBuilder() {
        encoderMap.put(boolean.class, this::encodeForBool);
        encoderMap.put(Boolean.class, this::encodeForBoolean);
        encoderMap.put(char.class, this::encodeForChar);
        encoderMap.put(Character.class, this::encodeForCharacter);
        encoderMap.put(byte.class, this::encodeForByte);
        encoderMap.put(Byte.class, this::encodeForByte2);
        encoderMap.put(short.class, this::encodeForShort);
        encoderMap.put(Short.class, this::encodeForShort2);
        encoderMap.put(int.class, this::encodeForInt);
        encoderMap.put(Integer.class, this::encodeForInteger);
        encoderMap.put(long.class, this::encodeForLong);
        encoderMap.put(Long.class, this::encodeForLong2);
        encoderMap.put(Date.class, this::encodeForDate);
        encoderMap.put(LocalDateTime.class, this::encodeForLocalDateTime);
        encoderMap.put(int[].class, this::encodeForArray);
        encoderMap.put(byte[].class, this::encodeForArray);
        encoderMap.put(List.class, this::encodeForArray);
    }

    public void build(Object bean, ByteBuf buffer) throws Exception {
        if (bean == null || buffer == null) {
            throw new NullPointerException();
        }
        Field[] fields = getTlvBindFields(bean);
        for (Field field : fields) {
            TlvBind tag = field.getAnnotation(TlvBind.class);
            field.setAccessible(true);
            Class<?> type = field.getType();
            System.out.printf("field number: %d type: %s %n", tag.number(), type);
            TypeEncoder typeEncoder = encoderMap.get(type);
            if (typeEncoder != null) {
                typeEncoder.encode(tag, field, bean, buffer);
            }
//            if (type == boolean.class || type.equals(Boolean.class)) {
//                System.out.println("bool");
//                appendVarInt(buffer, tag.number() << 3 ^ 0);
//                appendVarInt(buffer, ((Boolean) value) ? 1 : 0);
//            } else if (type == short.class || type == Short.class) {
//                appendVarInt(buffer, tag.number() << 3 ^ 0);
//                if (tag.signed()) {
//                    appendVarSignedInt(buffer, (Short) value);
//                } else {
//                    appendVarInt(buffer, (Short) value);
//                }
//            } else if (type == int.class || type == Integer.class) {
//                appendVarInt(buffer, tag.number() << 3 ^ 0);
//                if (tag.signed()) {
//                    appendVarSignedInt(buffer, (Integer) value);
//                } else {
//                    appendVarInt(buffer, (Integer) value);
//                }
//            } else if (type == String.class) {
//                appendVarInt(buffer, tag.number() << 3 ^ 1);
//                appendVarInt(buffer, ((String) value).length());
//                buffer.writeBytes(((String) value).getBytes());
//            } else if (type == long.class) {
//                appendVarInt(buffer, tag.number() << 3 ^ 0);
//                appendVarInt(buffer, (long) value);
//            } else if (type.isArray()) {
//                Class<?> componentType = type.getComponentType();
//                System.out.println("array[" + componentType + "]");
//            }
        }
//        byte[] res = new byte[buffer.position()];
//        buffer.flip();
//        buffer.get(res);
    }

    private void encodeForBool(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        boolean value = field.getBoolean(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value ? 1 : 0, buf);
    }

    private void encodeForBoolean(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Boolean value = (Boolean) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value ? 1 : 0, buf);
    }

    private void encodeForChar(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        char value = field.getChar(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForCharacter(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Character value = (Character) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForByte(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        byte value = field.getByte(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForByte2(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Byte value = (Byte) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForShort(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        short value = field.getShort(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }


    private void encodeForShort2(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Short value = (Short) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForInt(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        int value = field.getInt(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForInteger(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Integer value = (Integer) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForLong(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        long value = field.getLong(bean);
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForLong2(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Long value = (Long) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value, buf);
    }

    private void encodeForDate(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        Date value = (Date) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value.getTime(), buf);
    }

    private void encodeForLocalDateTime(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        LocalDateTime value = (LocalDateTime) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), buf);
    }

    private void encodeForArray(TlvBind bind, Field field, Object bean, ByteBuf buf) throws IllegalAccessException {
        LocalDateTime value = (LocalDateTime) field.get(bean);
        if (value == null) return;
        TlvUtils.writeVarInt(bind.number() << 3 ^ 0, buf);
        TlvUtils.writeVarInt(value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(), buf);
    }







    private Field[] getTlvBindFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(TlvBind.class))
                .peek(field -> field.setAccessible(true))
                .toArray(Field[]::new);
    }

    private void appendVarSignedInt(ByteBuf buffer, int value) {
        appendVarInt(buffer, (value << 1) ^ (value >> 31));
    }

    private void appendVarInt(ByteBuf buffer, long value) {
        int count = 10;
        while (count-- >= 0) {
            long i = value & 127;
            value >>>= 7;
            if (value == 0) {
                System.out.println("write: " + i);
                buffer.writeByte((byte) i);
                break;
            } else {
                i ^= (1 << 7);
                System.out.println("write: " + i);
                buffer.writeByte((byte) i);
            }
        }
    }

    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer(10);
    }
}
