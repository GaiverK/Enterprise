import java.nio.file.Path;

@FunctionalInterface
public interface CompressorStrategy {
    void compress(Path source);
}
