import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LongestWordSearcher {
    private String longestWord = "";

    public String getLongestWord() {
        return longestWord;
    }

    public void bufferedReaderSearcher(String fileName) throws IOException {
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = bFileReader.readLine();
            while (line != null){
                String[] wordsArr = line.replaceAll("[^a-zA-Zа-яА-Я ]", "").split(" ");
                Stream.of(wordsArr)
                        .forEach(word ->{
                            if( word.length() > longestWord.length() ){
                                longestWord = word;
                            }
                        });
                line = bFileReader.readLine();
            }
        }
    }

    public void fileLinesSearcher(String fileName) throws IOException {
        Files.lines(Paths.get(fileName))
                .forEach(line-> Stream.of(line.replaceAll("[^a-zA-Zа-яА-Я ]", "").split(" "))
                        .filter(word-> word.length() > longestWord.length())
                        .forEach(resWord->{
                            if( resWord.length() > longestWord.length() ){
                                longestWord = resWord;
                            }
                        }));
    }
}
