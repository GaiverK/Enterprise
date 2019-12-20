import java.nio.file.Path;

public class Compression {
    CompressorStrategy compressorStrategy;

    public Compression(CompressorStrategy compressorStrategy) {
        this.compressorStrategy = compressorStrategy;
    }


    public void compress(Path source){
        this.compressorStrategy.compress(source);
    }
}
