package io.pigeon.access;

import com.google.protobuf.InvalidProtocolBufferException;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/30
 **/
public class ProtostuffTest {
    static final DefaultIdStrategy STRATEGY = new DefaultIdStrategy(IdStrategy.DEFAULT_FLAGS
            | IdStrategy.MORPH_COLLECTION_INTERFACES
//            | IdStrategy.MORPH_MAP_INTERFACES
            | IdStrategy.MORPH_NON_FINAL_POJOS);

    static Schema<User> schema = RuntimeSchema.getSchema(User.class, STRATEGY);

    static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    static User user;
    static Person person;

    static {
        long now = System.currentTimeMillis();

        user = new User();
        user.setId(15L);
        user.setName("xhangseser");
        user.setAge(63);
        user.setNums(new int[]{123, 234, 345});
        user.setBirthday(now);

        person = Person.newBuilder()
                .setId(15)
                .setName("xhangseser")
                .setAge(63)
                .addNums( 123)
                .addNums( 234)
                .addNums( 345)
                .setTimestamp(now)
                .build();
    }

    private Schema<User> getSchema() {
        return schema;
    }

    public byte[] serialize(User object) {
        Schema<User> schema = getSchema();
        byte[] bytes;
        try {
            bytes = ProtostuffIOUtil.toByteArray(object, schema, buffer);
        } finally {
            buffer.clear();
        }
        return bytes;
    }

    public User deserialize(byte[] bytes) {
        Schema<User> schema = getSchema();
        User user = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, user, schema);
        return user;
    }


    public byte[] serialize1(Person object) {
        return object.toByteArray();
    }

    public Person deserialize1(byte[] bytes) throws InvalidProtocolBufferException {
        return Person.parseFrom(bytes);
    }

    @Benchmark
    public void test_protobuf_serialize(Blackhole blackhole) {
        blackhole.consume(person.toByteArray());
    }

    @Benchmark
    public void test_protostuff_serialize(Blackhole blackhole) {
        blackhole.consume(serialize(user));
    }

//    @Benchmark
//    public void test_protobuf_deserialize(Blackhole blackhole) {
//        blackhole.consume(Person.parseFrom());
//    }

//    @Benchmark
//    public void test_protostuff_deserialize(Blackhole blackhole) {
//        blackhole.consume(serialize(user));
//    }

    //    @Test
    public void testSerialize() {
        User user = new User();
        user.setId(1L);
        user.setName("zh");
        user.setAge(15);
        user.setAttrs(Collections.singletonMap("a", "a"));
        byte[] bytes = serialize(user);
        System.out.println(bytes.length);
        System.out.println(Arrays.toString(bytes));
        for (byte b : bytes) {
            System.out.printf("byte: %d\t %s%n", b, Integer.toBinaryString(b));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtostuffTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
