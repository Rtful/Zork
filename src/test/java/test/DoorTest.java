package test;

import ch.bbw.zork.Door;
import ch.bbw.zork.Item;
import ch.bbw.zork.Lock;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    private Door door;
    private Item key;
    private String code;
    private Lock stringLock;
    private Lock objectLock;

    @Before
    public void init(){
        this.key = new Item("key", "a key", 12);
        this.code = "1234";
        this.stringLock = new Lock(this.code, "a lock that needs a code to open");
        this.objectLock = new Lock(this.key, "a lock that needs a key to open");
    }

    @Test
    public void testUnlockStringCode() {
        this.door = new Door(this.stringLock);
        assertTrue(this.door.unlock(this.code));
    }

    @Test
    public void testLockIsOpenStringCode(){
        this.door = new Door(this.stringLock);
        this.door.unlock(code);
        assertTrue(this.door.isOpen());
    }

    @Test
    public void testLockIsNotOpenStringCode(){
        this.door = new Door(stringLock);
        assertFalse(this.door.isOpen());
    }

    @Test
    public void testUnlockObjectKey() {
        this.door = new Door(objectLock);
        assertTrue(this.door.unlock(this.key));
    }

    @Test
    public void testLockIsOpenObjectKey(){
        this.door = new Door(objectLock);
        this.door.unlock(this.key);
        assertTrue(this.door.isOpen());
    }

    @Test
    public void testLockIsNotOpenObjectKey(){
        this.door = new Door(objectLock);
        assertFalse(this.door.isOpen());
    }

    @Test
    public void testHasNoLock(){
        this.door = new Door(null);
        assertTrue(this.door.isOpen());
    }
}
