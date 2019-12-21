package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;

public class Test {
    @Benchmark
    public void measureName() {
        int a = 1;
        int b = 2;
        int sum = a + b;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(Test.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}