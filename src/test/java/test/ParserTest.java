package test;

import ch.bbw.zork.Command;
import ch.bbw.zork.CommandWords;
import ch.bbw.zork.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser;

    @Test
    public void testGetCommandSingleWord() {
        byte[]      data  = "look".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        parser = new Parser(input);
        Command command = parser.getCommand();
        assertEquals(command.getCommandWord(), "look");
        assertFalse(command.hasSecondWord());
    }

    @Test
    public void testGetCommandTwoWords() {
        byte[]      data  = "go east".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        parser = new Parser(input);
        Command command = parser.getCommand();
        assertEquals(command.getCommandWord(), "go");
        assertEquals(command.getSecondWord(), "east");
    }

    @Test
    public void testGetCommandMultiWords() {
        byte[]      data  = "go too long".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        parser = new Parser(input);
        assertNull(parser.getCommand());
    }

    @Test
    public void testGetCommandNull() {
        byte[]      data  = "".getBytes();
        InputStream input = new ByteArrayInputStream(data);
        parser = new Parser(input);

        assertThrows(NullPointerException.class, () -> parser.getCommand());
    }

    @Test
    public void testShowCommands() {
        byte[]       data         = "".getBytes();
        CommandWords commandWords = new CommandWords();
        InputStream  input        = new ByteArrayInputStream(data);
        parser = new Parser(input);
        String expected = commandWords.showAll();

        assertEquals(expected, parser.showCommands());
    }
}
