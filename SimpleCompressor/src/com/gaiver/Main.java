package com.gaiver;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Path destination = Paths.get(scanner.nextLine()); // File source
        File output = new File(scanner.nextLine()); // Output like path_file.zip or path_file.extension.gz

        Compressor compressor;
        if( output.getName().contains(".zip") ){
            compressor = new Compressor(ZipOutputStream::new);
        }else{
            compressor = new Compressor(GZIPOutputStream::new);
        }

        try {
            compressor.compress(destination, output);
        }catch(AccessDeniedException e){
            System.out.println("Укажите путь к файлу");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
