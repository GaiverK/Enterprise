import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;

public class LongestLineSearcher {
    private int longestLineLength = 0;

    public int bufferedReaderSearcher(String fileName) throws IOException {
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = bFileReader.readLine();
            while (line != null){
                if( line.length() > longestLineLength ){
                    longestLineLength = line.length();
                }
                line = bFileReader.readLine();
            }
        }
        return  longestLineLength;
    }

    public int fileLinesSearcher(String fileName) throws IOException {
        Optional<String> longestWord = Files.lines(Paths.get(fileName))
                .max(Comparator.comparingInt(String::length));

        return longestWord.map(String::length).orElse(0);
    }
}
