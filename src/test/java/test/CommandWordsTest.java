package test;
import ch.bbw.zork.CommandWords;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandWordsTest {
    private CommandWords commandWords;
    @Test
    public void testIsCommand() {
        commandWords = new CommandWords();
        assertTrue(commandWords.isCommand("go"));
    }

    @Test
    public void testIsCommandInvalid() {
        commandWords = new CommandWords();
        assertFalse(commandWords.isCommand("invalidCommand"));
    }

    @Test
    public void testShowAll(){
        commandWords = new CommandWords();
        String commandWordsString = commandWords.showAll();
        assertInstanceOf(String.class, commandWordsString);
    }
}
