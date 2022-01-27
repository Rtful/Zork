package test;

import ch.bbw.zork.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImageTest {
    Image image;

    @Test
    public void testGetImage() throws IOException {
        String expected = "test";
        image = new Image("img/test.txt");
        assertEquals(expected, image.getImage());
    }

    @Test
    public void testGetImageInvalid() {
        image = new Image("invalid");
        assertThrows(IOException.class, () -> {
            image.getImage();
        });
    }
}
