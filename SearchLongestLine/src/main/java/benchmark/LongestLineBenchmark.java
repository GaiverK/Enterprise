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
import java.util.concurrent.TimeUnit;

public class LongestLineBenchmark {

    @State(Scope.Thread)
    public static class Config{
        String fileName = "/test2.txt";
        String firstLongestLine = "";
        String secondLongestLine = "";
    }


    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 15, time = 1)
    @Measurement(iterations = 15, time = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(10)
    @Threads(1)
    @Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
    public void searchLongestWordByBufferedReader(Blackhole blackhole, Config conf) throws IOException {
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(conf.fileName), Charset.defaultCharset()))) {
            String line = bFileReader.readLine();
            while (line != null){
                if( line.length() > conf.firstLongestLine.length() ){
                    conf.firstLongestLine = line;
                }
                line = bFileReader.readLine();
            }
        }
        blackhole.consume(conf.firstLongestLine);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Warmup(iterations = 15, time = 1)
    @Measurement(iterations = 15, time = 1)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(10)
    @Threads(1)
    @Timeout(time = 5, timeUnit = TimeUnit.MINUTES)
    public void searchLongestWordByFilesWithFilter(Blackhole blackhole, Config conf) throws IOException {
        Files.lines(Paths.get(conf.fileName))
                .filter(line -> line.length() > conf.secondLongestLine.length())
                .forEach(line -> {
                    conf.secondLongestLine = (line.length() > conf.secondLongestLine.length()) ? line : conf.secondLongestLine;
                });
        blackhole.consume(conf.secondLongestLine);
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