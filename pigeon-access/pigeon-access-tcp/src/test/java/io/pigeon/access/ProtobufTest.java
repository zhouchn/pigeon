package io.pigeon.access;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/30
 **/
public class ProtobufTest {
    public byte[] serialize(Person object) {
        return object.toByteArray();
    }

    public Person deserialize(byte[] bytes) throws InvalidProtocolBufferException {
        return Person.parseFrom(bytes);
    }

    @Test
    public void testSerialize() {
        Person person = Person.newBuilder().setId(1).setName("zh").setAge(15).build();
        byte[] bytes = serialize(person);
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));
        for (byte b : bytes) {
            System.out.printf("byte: %d\t %s%n", b, Integer.toBinaryString(b));
        }
    }
}
