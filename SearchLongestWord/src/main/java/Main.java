import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String fileName = "/test.txt";
        LongestWordSearcher lws = new LongestWordSearcher();
        try {
            lws.bufferedReaderSearcher(fileName);
            System.out.println(lws.getLongestWord());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            lws.fileLinesSearcher(fileName);
            System.out.println(lws.getLongestWord());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
