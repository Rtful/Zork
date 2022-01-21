package test;
import ch.bbw.zork.PointOfInterest;
import ch.bbw.zork.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;
    Room north = new Room("northDescription");
    Room south = new Room("southDescription");
    Room east = new Room("eastDescription");
    Room west = new Room("westDescription");

    @Test
    public void testAddPointsOfInterest(){
        room = new Room("testDescription");
        PointOfInterest pointOfInterest = new PointOfInterest("testName", "testLocation");
        room.addPointsOfInterest("testName", pointOfInterest);
        assertTrue(room.getPointsOfInterest().containsKey("testName"));
    }
    @Test
    public void testSetExits(){
        room = new Room("testDescription");
        room.setExits(north, east, south, west);
        assertSame(room.nextRoom("north"), north);
        assertSame(room.nextRoom("east"), east);
        assertSame(room.nextRoom("south"), south);
        assertSame(room.nextRoom("west"), west);
    }

    @Test
    public void testInspect() {
        room = new Room("testDescription");
        PointOfInterest pointOfInterest = new PointOfInterest("testDescription", "testLocation");
        room.addPointsOfInterest("testName", pointOfInterest);
        String description = room.inspect("testName");
        assertInstanceOf(String.class, description);
        assertEquals("testDescription", description);
    }

    @Test
    public void testInspectNull() {
        room = new Room("testDescription");
        PointOfInterest pointOfInterest = new PointOfInterest("testDescription", "testLocation");
        String description = room.inspect("testName");
        assertNull(description);
    }

    @Test
    public void testgetLongDescription(){
        room = new Room("testDescription");
        room.setExits(north, null, null, west);
        String description = room.getLongDescription();
        assertTrue(description.contains("testDescription"));
        assertTrue(description.contains("north"));
        assertTrue(description.contains("west"));
    }

    @Test
    public void testNextRoom(){
        room = new Room("testDescription");
        room.setExits(north, east, south, west);
        assertEquals(north, room.nextRoom("north"));
        assertEquals(east, room.nextRoom("east"));
        assertEquals(south, room.nextRoom("south"));
        assertEquals(west, room.nextRoom("west"));
    }

    @Test
    public void testNextRoomNull(){
        room = new Room("testDescription");
        assertNull(room.nextRoom("north"));
        assertNull(room.nextRoom("east"));
        assertNull(room.nextRoom("south"));
        assertNull(room.nextRoom("west"));
    }
}
