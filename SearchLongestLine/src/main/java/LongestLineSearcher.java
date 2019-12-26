import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LongestLineSearcher {
    private String longestLine = "";

    public String getLongestLine() {
        return longestLine;
    }

    public void bufferedReaderSearcher(String fileName) throws IOException {
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = bFileReader.readLine();
            while (line != null){
                if( line.length() > longestLine.length() ){
                    longestLine = line;
                }
                line = bFileReader.readLine();
            }
        }
    }

    public void fileLinesSearcher(String fileName) throws IOException {
        Files.lines(Paths.get(fileName))
                .filter(line -> line.length() > longestLine.length())
                .forEach(line -> {
                    longestLine = line;
                });
    }
}
