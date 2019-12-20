import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    private static Consumer<Path> zipAll; // Consumer for lambda recursion
    private static ZipOutputStream zos;
    private static String firstDirName = null;

    public static void main(String[] args) {
        final String version = "v1.0";
        File source = null;
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Welcome to Compressor %s\n" +
                        "If selected path is directory program will use zip compression\n" +
                        "otherwise gzip compression\n",
                version);

        do {
            if (source == null) {
                System.out.println("Input the source path:");
                String src = scanner.next();
                source = new File(src).exists() ? new File(src) : null;
                if (source == null) {
                    System.out.println("File not exists, please try again!");
                }
            }

        } while (source == null);

        // GZip compressor strategy
        CompressorStrategy gzip = (path) -> {
            File f = new File(path.toString());
            try (GZIPOutputStream gzos = new GZIPOutputStream(new FileOutputStream(path + ".gz")); FileInputStream fis = new FileInputStream(f)) {
                byte[] buff = new byte[fis.available()];
                fis.read(buff);
                gzos.write(buff);
                gzos.finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        //Zip compressor strategy
        CompressorStrategy zip = (path) -> {
            File[] files = new File(path.toString()).listFiles();

            Stream.of(files)
                .filter(File::isFile)
                .forEach(file -> {
                    try {
                        byte[] buffer = new byte[1024];
                        FileInputStream fis = new FileInputStream(file);
                        String dirName = file.getAbsolutePath().substring(file.getAbsolutePath().indexOf(firstDirName));
                        zos.putNextEntry(new ZipEntry( dirName ));
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        };

        // Strategy condition
        if (source.isDirectory()) { // Use zip strategy if path is directory
            try {
                zos = new ZipOutputStream(new FileOutputStream(source.getPath() + ".zip"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // ZipAll - lambda with recursion, compress all dirs, sub dirs, and files
            zipAll = (path) -> {
                File[] files = new File(path.toString()).listFiles();
                boolean fileCaptured = false;
                for (File single : files) {
                    if (single.isDirectory()) {
                        zipAll.accept(single.toPath());
                    } else {
                        if (!fileCaptured && single.isFile()) {
                            fileCaptured = true;
                            zip.compress(Paths.get(single.getParent()));
                        }
                    }
                }
            };
            firstDirName = source.getName();
            zipAll.accept(source.toPath());
            try{
                System.out.println("Zip compression complete");
                zos.finish();
                zos.close(); // Close Output stream
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // Use GZip strategy
            new Compression(gzip).compress(source.toPath());
            System.out.println("GZip compression complete");
        }
    }
}
