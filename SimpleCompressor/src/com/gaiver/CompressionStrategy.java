package com.gaiver;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

public interface CompressionStrategy {
    DeflaterOutputStream compress(OutputStream data) throws IOException;
}
