package ch.bbw.zork;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling, 1.1, March 2000
 * refactoring: Rinaldo Lanza, September 2020
 * refactoring: Aaron Holenstein, Januar 2022
 */

public class Game {

    private final Parser parser;
    private Inventory inventory;
    private final Navigator navigator;

    HashMap<String, Item> allItems = new HashMap<>();

    public Game() throws IOException {

        Date      date      = new Date();
        this.inventory = new Inventory();
        this.parser = new Parser(System.in);
        this.navigator = new Navigator();

        Item key     = new Item("Key", "Just an old rusty key");
        Item coin    = new Item("Coin", "An ancient looking coin");
        Item knife   = new Item("Knife", "An old rusty blade");
        Item picture = new Item("Picture", "Polaroid of someone from your past");

        allItems.put("key", key);
        allItems.put("coin", coin);
        allItems.put("knife", knife);
        allItems.put("picture", picture);   
    } 
    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {

        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;

        while (!finished) {
            Command command = parser.getCommand();
            if (command == null) {
                System.out.println("Please enter a valid command");
                continue;
            }
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a simple adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(this.navigator.getLongDescription());
    }

        private boolean processCommand(Command command) {
        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                navigator.go(command.getSecondWord());
                break;
            case "check":
                checkSomething(command);
                break;
            case "take":
                takeItem(command);
                break;
            case "look":
                this.navigator.look();
                break;
            case "map":
                map();
            case "inspect":
                navigator.inspect(command);
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                } else {
                    return true; // signal that we want to quit
                }
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are one of few survivors of a zombie apocalypse.");
        System.out.println("You have been bitten by a zombie and are in great need of a remedy.");
        System.out.println("You currently are in the Umbrella.inc headquarters");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    private void inventory() {
        inventory.showInventory();
    }

	private void map(){
		System.out.println("Map");
	}

	private void takeItem(Command command) {

		if (!command.hasSecondWord()) {
			System.out.println("Take what?");
		} else {
			String takenItem = command.getSecondWord();
			if (allItems.containsKey(takenItem)) {
				System.out.println("\nPicked up" + takenItem + "\n");
				inventory.backpack.put(takenItem, allItems.get(takenItem));
				allItems.remove(takenItem);
			} else {
				System.out.println("I can't take that");
			}
		}
	}

	/* TODO: make item drop in a room in a container
	private void dropItem(Command command){
		if (!command.hasSecondWord()) {
			System.out.println("Drop what?");
		} else {
			String droppedItem = command.getSecondWord();
			if (currentRoom.getContainer()){
				if(inventory.backpack.containsKey(droppedItem)){
					container.put(droppedItem, inventory.backpack.get(droppedItem));
					inventory.backpack.remove(droppedItem);
				} else {
					System.out.println("I don't have that");
				}
			} else {
				System.out.println("You can't drop any Items here, go to the room with the container to drop Items");
			}
		}
	}*/

	private void checkSomething(Command command) {

		if (!command.hasSecondWord()) {
			System.out.println("Check what?");
		} else {
			String checking = command.getSecondWord();
			if (checking.equals("backpack")) {
				inventory.showInventory();
			} else {
				System.out.println("I don't know how to check that");
			}
		}
	}
}
