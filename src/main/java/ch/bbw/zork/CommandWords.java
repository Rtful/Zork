package ch.bbw.zork;

/*
 * author:  Michael Kolling, Version: 1.0, Date: July 1999
 * refactoring: Rinaldo Lanza, September 2020
 */

import java.util.*;

public class CommandWords {

    //	private final List<String> validCommands = Arrays.asList("go", "quit", "help", "take", "check", "look", "inspect");
    private final Map<String, String> validCommands;

    public CommandWords() {
        this.validCommands = new HashMap<>(Map.of(
                "go", "leave the current room in the desired direction (north, east, south, west)",
                "quit", "quit the game",
                "help", "get useful tips",
                "take", "pick up items",
                "check", "",
                "look", "get information about the current room",
                "inspect", "get information about an item or feature of the room"
        ));
    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    }

    public String showAll() {
        StringBuilder commandsWithDescription = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = validCommands.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            boolean hasNext = iterator.hasNext();
            commandsWithDescription.append("'").append(entry.getKey()).append("' to ").append(entry.getValue()).append(hasNext ? "\n": "");
        }
        return commandsWithDescription.toString();
    }

}
