package test;

import ch.bbw.zork.Item;
import ch.bbw.zork.Lock;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockTest {
    private Item key;
    private String code;
    private Lock stringLock;
    private Lock objectLock;

    @BeforeEach
    public void init(){
        this.key = new Item("key", "a key", 12);
        this.code = "1234";
        this.stringLock = new Lock(this.code, "a lock that needs a code to open");
        this.objectLock = new Lock(this.key, "a lock that needs a key to open");
    }

    @Test
    public void testUnlockStringCode() {
        assertTrue(this.stringLock.unlock(this.code));
        assertTrue(this.stringLock.isUnlocked());
    }

    @Test
    public void testUnlockObjectKey() {
        assertTrue(this.objectLock.unlock(this.key));
        assertTrue(this.objectLock.isUnlocked());
    }

    @Test
    public void testUnlockStringCodeInvalid() {
        assertFalse(this.stringLock.unlock("8594"));
        assertFalse(this.stringLock.isUnlocked());
    }

    @Test
    public void testUnlockObjectKeyInvalid() {
        assertFalse(this.objectLock.unlock(new Item("key", "invalid key", 12)));
        assertFalse(this.objectLock.isUnlocked());
    }
}