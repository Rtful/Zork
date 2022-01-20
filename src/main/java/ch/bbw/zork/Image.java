package ch.bbw.zork;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Image {
    private Path fileName;
    private String content;

    public Image(String path) throws IOException {
        fileName = Path.of(path);
        content = Files.readString(fileName, StandardCharsets.US_ASCII);
    }
    public String getImage() throws IOException {
        Files.writeString(fileName, content);
        return Files.readString(fileName);    }
}
