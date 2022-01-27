package test;
import ch.bbw.zork.Command;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {
    Command command;
    @BeforeEach
    protected void beforeEach() {
    }

    @Test
    public void testTwoWordCommand() {
        String firstWord = "go";
        String secondWord = "east";
        command = new Command(firstWord, secondWord);
        assertTrue(command.hasSecondWord());
        assertEquals(command.getCommandWord(), firstWord);
        assertEquals(command.getSecondWord(), secondWord);
    }

    @Test
    public void testSingleWordCommand() {
        command = new Command("look");
        Assertions.assertFalse(command.hasSecondWord());
        assertEquals(command.getCommandWord(), "look");
    }

    @Test
    public void testNoCommand() {
        command = new Command(null);
        Assertions.assertTrue(command.isUnknown());
    }
}
