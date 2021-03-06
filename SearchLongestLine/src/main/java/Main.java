import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if( args.length >= 1 ){
            File lookForFile = new File(args[0]);
            if( lookForFile.exists() && lookForFile.canRead() && lookForFile.isFile() ){
                String fileName = lookForFile.getAbsolutePath();
                LongestLineSearcher lws = new LongestLineSearcher();
                int longSize;
                try {
                    longSize = lws.bufferedReaderSearcher(fileName);
                    System.out.println(longSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    longSize = lws.fileLinesSearcher(fileName);
                    System.out.println(longSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                throw new IllegalArgumentException("Передан некорректный путь к файлу!");
            }
        }else{
            throw new IllegalArgumentException("Передайте путь к файлу для поиска!");
        }

    }
}
