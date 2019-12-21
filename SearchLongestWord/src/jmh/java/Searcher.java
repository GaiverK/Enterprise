import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Searcher {
    @Benchmark
    public void measureName() {
        int a = 1;
        int b = 2;
        int sum = a + b;
    }

    public static void main(String[] args) throws IOException, RunnerException {
        org.openjdk.jmh.Main.main(args);
//        org.openjdk.jmh.Sear
    }
}