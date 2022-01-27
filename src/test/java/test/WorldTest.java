package test;

import ch.bbw.zork.World;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
    World world;
    Scanner scanner;

    @Before
    public void init(){
        this.scanner = new Scanner(System.in);
    }

    @Test
    public void testGetLongDescription() throws IOException {
        this.world = new World(this.scanner);
        assertEquals("You are in the reception.\nExits:east", world.getLongDescription());
    }

    @Test
    public void testGoValid() throws IOException {
        this.world = new World(this.scanner);
        world.go("east");
        assertArrayEquals(new int[]{1, 1}, world.getCoordinates());
    }

    @Test
    public void testGoInvalid() throws IOException {
        this.world = new World(this.scanner);
        world.go("invalid");
        assertArrayEquals(new int[]{0, 1}, world.getCoordinates());
    }

    @Test
    public void testGoNonExistingRoom() throws IOException {
        this.world = new World(this.scanner);
        world.go("north");
        assertArrayEquals(new int[]{0, 1}, world.getCoordinates());
    }
}
