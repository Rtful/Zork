package test;
import ch.bbw.zork.Door;
import ch.bbw.zork.PointOfInterest;
import ch.bbw.zork.Room;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;
    Door north = new Door(null);
    Door south = new Door(null);
    Door east = new Door(null);
    Door west = new Door(null);

    @Test
    public void testAddPointsOfInterest(){
        room = new Room("testName", "testDescription", false);
        PointOfInterest pointOfInterest = new PointOfInterest("testName", "testLocation");
        room.addPointOfInterest("testName", pointOfInterest);
        assertTrue(room.getPointsOfInterest().containsKey("testName"));
    }
    @Test
    public void testSetExits(){
        room = new Room("testName", "testDescription", false);
        room.setExits(north, east, south, west);
        assertSame(room.getExit("north"), north);
        assertSame(room.getExit("east"), east);
        assertSame(room.getExit("south"), south);
        assertSame(room.getExit("west"), west);
    }

    @Test
    public void testInspect() {
        room = new Room("testName", "testDescription", false);
        PointOfInterest pointOfInterest = new PointOfInterest("testDescription", "testLocation");
        room.addPointOfInterest("testName", pointOfInterest);
        String description = room.inspect("testName");
        assertInstanceOf(String.class, description);
        assertEquals("testDescription", description);
    }

    @Test
    public void testInspectNull() {
        room = new Room("testName", "testDescription", false);
        PointOfInterest pointOfInterest = new PointOfInterest("testDescription", "testLocation");
        String description = room.inspect("testName");
        assertNull(description);
    }

    @Test
    public void testgetLongDescription(){
        room = new Room("testName", "testDescription", false);
        room.setExits(north, null, null, west);
        String description = room.getLongDescription();
        assertTrue(description.contains("testDescription"));
        assertTrue(description.contains("north"));
        assertTrue(description.contains("west"));
    }

    @Test
    public void testPointOfInterest(){
        room = new Room("testName", "testDescription", false);
        PointOfInterest pointOfInterest = new PointOfInterest("testDescription", "testLocation");
        room.addPointOfInterest("testName", pointOfInterest);
        HashMap<String, PointOfInterest> pointsOfInterest = room.getPointsOfInterest();
        assertTrue(pointsOfInterest.containsKey("testName"));
        assertEquals("testDescription", room.inspect("testName"));
    }

    @Test
    public void testNextRoom(){
        room = new Room("testName", "testDescription", false);
        room.setExits(north, east, south, west);
        assertEquals(north, room.getExit("north"));
        assertEquals(east, room.getExit("east"));
        assertEquals(south, room.getExit("south"));
        assertEquals(west, room.getExit("west"));
    }

    @Test
    public void testNextRoomNull(){
        room = new Room("testName", "testDescription", false);
        assertNull(room.getExit("north"));
        assertNull(room.getExit("east"));
        assertNull(room.getExit("south"));
        assertNull(room.getExit("west"));
    }
}
