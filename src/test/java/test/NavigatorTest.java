package test;

import ch.bbw.zork.Navigator;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NavigatorTest {
    Navigator navigator;

    @Test
    public void testGetLongDescription() throws IOException {
        this.navigator = new Navigator();
        assertEquals("You are in the reception.\nExits:east", navigator.getLongDescription());
    }

    @Test
    public void testGoValid() throws IOException {
        this.navigator = new Navigator();
        navigator.go("east");
        assertArrayEquals(new int[]{1, 1}, navigator.getCoordinates());
    }

    @Test
    public void testGoInvalid() throws IOException {
        this.navigator = new Navigator();
        navigator.go("invalid");
        assertArrayEquals(new int[]{0, 1}, navigator.getCoordinates());
    }

    @Test
    public void testGoNonExistingRoom() throws IOException {
        this.navigator = new Navigator();
        navigator.go("north");
        assertArrayEquals(new int[]{0, 1}, navigator.getCoordinates());
    }
}
