import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    static String longestWord1 = "";
    static String longestWord2 = "";
    public static void main(String[] args) {
        String fileName = "/utf.txt";
        try(BufferedReader bFileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line = bFileReader.readLine();
            while (line != null){
                String[] wordsArr = line.replaceAll("[^a-zA-Zа-яА-Я]", "").split(" ");
                Stream.of(wordsArr)
                        .forEach(word ->{
                           if( word.length() > longestWord1.length() ){
                               longestWord1 = word;
                           }
                        });
                line = bFileReader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.lines(Paths.get(fileName))
                    .forEach(line->{
                        System.out.println("L " + line);
                        Stream.of(line.replaceAll("[^a-zA-Zа-яА-Я]", "").split(" "))
                                .filter(word-> word.length() > longestWord2.length())
                                .forEach(resWord->{
                                    System.out.println("W " + resWord);
                                    if( resWord.length() > longestWord2.length() ){
                                        longestWord2 = resWord;
                                    }
                                });
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(longestWord1);
        System.out.println(longestWord2);
    }
}
