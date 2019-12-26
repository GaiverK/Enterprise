package com.gaiver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compressor {
    CompressionStrategy strategy;

    public Compressor(CompressionStrategy strategy) {
        this.strategy = strategy;
    }

    public void compress(Path inFile, File output) throws IOException {
        try(OutputStream ous = new FileOutputStream(output);OutputStream zs = strategy.compress(ous);) {
            if( output.getName().contains(".zip") ){ // If file contains zip extension then add zip entry
                ZipOutputStream zos = (ZipOutputStream) zs;
                zos.putNextEntry(new ZipEntry(inFile.getFileName().toString()));
            }
            Files.copy(inFile, zs);
        }
    }
}
