package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class LongestWordBenchmark {
//    @BenchmarkMode(Mode.All) // Test all modes
//    @OutputTimeUnit(TimeUnit.MILLISECONDS) // Get res in miliseconds
//    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
//    @Measurement(iterations = 3, time = 55, timeUnit = TimeUnit.MILLISECONDS)
//    @Fork(10)

    @State(Scope.Thread)
    public static class Config{
        String fileName = "/test.txt";
        String firstLongestWord = "";
        String secondLongestWord = "";
    }


    @Benchmark
    public void searchLongestWordByBufferedReader(Blackhole blackhole, Config conf){

        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(conf.fileName)))) {
            String line = bFileReader.readLine();
            while (line != null){
                String[] wordsArr = line.replaceAll("[^a-zA-Zа-яА-Я ]", "").split(" ");
                Stream.of(wordsArr)
                        .forEach(word ->{
                            if( word.length() > conf.firstLongestWord.length() ){
                                conf.firstLongestWord = word;
                            }
                        });
                line = bFileReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        blackhole.consume(conf.firstLongestWord);
    }

    @Benchmark
    public void searchLongestWordByFilesWithFilter(Blackhole blackhole, Config conf){
        try {
            Files.lines(Paths.get(conf.fileName))
                    .forEach(line-> Stream.of(line.replaceAll("[^a-zA-Zа-яА-Я ]", "").split(" "))
                            .filter(word-> word.length() > conf.secondLongestWord.length())
                            .forEach(resWord->{
                                if( resWord.length() > conf.secondLongestWord.length() ){
                                    conf.secondLongestWord = resWord;
                                }
                            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blackhole.consume(conf.secondLongestWord);
    }

    public static void main(String[] args) throws IOException, RunnerException {
        // Set options through annotations - does not work, always default values
        // org.openjdk.jmh.Main.main(args);

        // Set options through OptionsBuilder - work perfectly
        Options opt = new OptionsBuilder()
                .include(LongestWordBenchmark.class.getSimpleName())
                .mode(Mode.All)
                .timeUnit(TimeUnit.SECONDS)
                .warmupIterations(20)
                .warmupTime(TimeValue.seconds(1))
                .measurementIterations(20)
                .measurementTime(TimeValue.seconds(1))
                .timeout(TimeValue.minutes(5))
                .threads(1)
                .forks(10)
                .syncIterations(true)
                .result("./result.log")
                .build();

        new Runner(opt).run();

    }
}