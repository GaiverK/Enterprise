package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class LongestLineBenchmark {

    @State(Scope.Thread)
    public static class Config{
        String fileName = "/book.txt";
        int longestLineLength = 0;
    }


    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(5)
    @Threads(1)
    @Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
    public void searchLongestWordByBufferedReader(Blackhole blackhole, Config conf) throws IOException {
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(conf.fileName), Charset.defaultCharset()))) {
            String line = bFileReader.readLine();
            while (line != null){
                conf.longestLineLength = Math.max(line.length(), conf.longestLineLength);
                line = bFileReader.readLine();
            }
        }
        blackhole.consume(conf.longestLineLength);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 10, time = 1)
    @Measurement(iterations = 10, time = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(5)
    @Threads(1)
    @Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
    public void searchLongestWordByFilesWithFilter(Blackhole blackhole, Config conf) throws IOException {
        Optional<String> longestLine = Files.lines(Paths.get(conf.fileName))
                .max(Comparator.comparingInt(String::length));

        blackhole.consume(longestLine.map(String::length).orElse(0));
    }

    public static void main(String[] args) throws IOException, RunnerException {
        // Set options through annotations
        Options opt = new OptionsBuilder()
                .include(LongestLineBenchmark.class.getSimpleName())
                .syncIterations(true)
                .result("./benchmark.txt")
                .build();

        new Runner(opt).run();

    }
}