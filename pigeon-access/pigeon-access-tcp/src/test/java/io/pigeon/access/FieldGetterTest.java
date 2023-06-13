package io.pigeon.access;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

/**
 * <description>
 *
 * @author chaoxi
 * @since 3.0.0 2023/5/30
 **/
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 5)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FieldGetterTest {
//    @Param(value = {"10", "50", "100"})
//    private int length;
    static private User user;
    static private Field ageField;

    static private ToIntFunction<User> ageFunction = User::getAge;

    static {
        user = new User();
        user.setAge(14);
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("age")) {
                field.setAccessible(true);
                ageField = field;
                break;
            }
        }
    }

    @Benchmark
    public void testFieldGet(Blackhole blackhole) throws IllegalAccessException {
        blackhole.consume(ageField.get(user));
    }

    @Benchmark
    public void testFieldGetInt(Blackhole blackhole) throws IllegalAccessException {
        blackhole.consume(ageField.getInt(user));
    }

    @Benchmark
    public void testFunctionGet(Blackhole blackhole) throws IllegalAccessException {
        blackhole.consume(ageFunction.applyAsInt(user));
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(FieldGetterTest.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}