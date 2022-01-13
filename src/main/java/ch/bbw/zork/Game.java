package ch.bbw.zork;

import java.util.*;
import java.util.HashMap;

/**
 * Class Game - the main class of the "Zork" game.
 *
 * Author:  Michael Kolling, 1.1, March 2000
 * refactoring: Rinaldo Lanza, September 2020
 */

public class Game {
	
	private Parser parser;
	private Room currentRoom;
	private Room bedroom, lab, apartment1, apartment2, hallway;
	private Inventory inventory;

	HashMap<String, Item> allItems = new HashMap<String, Item>();

	public Game() {

		parser = new Parser(System.in);

		inventory = new Inventory();

		bedroom = new Room("your apartment");
		lab = new Room("lab, where some experiments had taken place");
		apartment1 = new Room("the Seahorse Tavern (the campus pub)");
		apartment2 = new Room("the G Block");
		hallway = new Room("an eerie partially lit hallway");

		PointOfInterest painting = new PointOfInterest("A painting of the mona lisa", "north wall");

		bedroom.setExits(null, hallway, null, null);
		lab.setExits(null, null, null, bedroom);
		apartment1.setExits(null, bedroom, null, null);
		apartment2.setExits(bedroom, hallway, null, null);
		hallway.setExits(null, lab, null, apartment2);
		hallway.addPointsOfInterest("painting", painting);
		hallway.addPointsOfInterest("photo", painting);

		currentRoom = bedroom; // start game outside

		Item key = new Item("Key", "Just an old rusty key");
		Item coin = new Item("Coin", "An ancient looking coin");
		Item knife = new Item("Knife", "An old rusty blade");
		Item picture = new Item("Picture", "Polaroid of someone from your past");

		allItems.put("key", key);
		allItems.put("coin", coin);
		allItems.put("knife", knife);
		allItems.put("picture", picture);

	}


	/**
	 *  Main play routine.  Loops until end of play.
	 */
	public void play() {
		printWelcome();

		// Enter the main command loop.  Here we repeatedly read commands and
		// execute them until the game is over.
		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
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
		System.out.println(currentRoom.longDescription());
	}

	private boolean processCommand(Command command) {
		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();

		} else if (commandWord.equals("go")) {
			goRoom(command);

		} else if (commandWord.equals("check")) {
			checkSomething(command);

		} else if (commandWord.equals("take")){
			takeItem(command);

		} else if (commandWord.equals("look")) {
			currentRoom.look();

		} else if (commandWord.equals("inspect")) {
			inspect(command);

		} else if (commandWord.equals("quit")) {
			if (command.hasSecondWord()) {
				System.out.println("Quit what?");
			} else {
				return true; // signal that we want to quit
			}
		}
		return false;
	}

	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at Monash Uni, Peninsula Campus.");
		System.out.println();
		System.out.println("Your command words are:");
		System.out.println(parser.showCommands());
	}

	private void inventory(){
		inventory.showInventory();
	}

	private void takeItem(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Take what?");
		} else {
			String takenItem = command.getSecondWord();
			if(allItems.containsKey(takenItem)){
				System.out.println("\nPicked up" + takenItem +"\n");
				inventory.backpack.put(takenItem, allItems.get(takenItem));
				allItems.remove(takenItem);
			} else {
				System.out.println("I can't take that");
			}
		}
	}

	private void checkSomething(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Check what?");
		} else {
			String checking = command.getSecondWord();
			inventory.showInventory(); //TODO: remove default showing inventory after below TODO is done
			//TODO: make check what input is checked and make it work
			/*if (checking == "backpack"){
				inventory.showInventory();
			} else {
				System.out.println("I don't know how to check that");
			}*/
		}
	}

	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Go where?");
		} else {
			
			String direction = command.getSecondWord();
	
			// Try to leave current room.
			Room nextRoom = currentRoom.nextRoom(direction);
	
			if (nextRoom == null)
				System.out.println("There is no door!");
			else {
				currentRoom = nextRoom;
				System.out.println(currentRoom.longDescription());
			}
		}
	}
	private void inspect(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Inspect what?");
		} else {
			String target = command.getSecondWord();
			// Try to leave current room.
			String detailedView = currentRoom.inspect(target);
			if (detailedView == null)
				System.out.println("Nothing to inspect");
			else {
				System.out.println(detailedView);
			}
		}
	}
}
