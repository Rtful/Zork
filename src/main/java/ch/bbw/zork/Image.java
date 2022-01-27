package ch.bbw.zork;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Image {
    private final Path fileName;

    public Image(String path) {
        fileName = Path.of(path);
    }
    public String getImage() throws IOException {
        return Files.readString(fileName);
    }
}