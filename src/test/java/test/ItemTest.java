package test;
import ch.bbw.zork.Item;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;
    @Test
    public void testItem() {
        item = new Item("testName", "testDescription", 12);
        assertEquals("testName", item.getName());
        assertEquals("testDescription", item.getDescription());
    }
}
