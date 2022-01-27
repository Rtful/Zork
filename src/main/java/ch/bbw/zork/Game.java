package ch.bbw.zork;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

	HashMap<String, Item> allItems = new HashMap<>();
	HashMap<String, Item> container = new HashMap<>();

	public Game() throws IOException {
		parser = new Parser(System.in);

		inventory = new Inventory();

		bedroom = new Room("your apartment", false);
		lab = new Room("lab, where some experiments had taken place", false);
		apartment1 = new Room("the Seahorse Tavern (the campus pub)", false);
		apartment2 = new Room("the G Block", true);
		hallway = new Room("an eerie partially lit hallway", false);

		Image monaLisaASCII = new Image("img/monaLisa.txt");
		Image waveASCII = new Image("img/wave.txt");

		PointOfInterest monaLisa = new PointOfInterest(monaLisaASCII.getImage(), "north wall");
		PointOfInterest wave = new PointOfInterest(waveASCII.getImage(), "north wall");

		bedroom.setExits(null, hallway, null, null);
		lab.setExits(null, null, null, hallway);
		apartment1.setExits(null, null, hallway, null);
		apartment2.setExits(hallway, null, null, null);
		hallway.setExits(apartment1, lab, apartment2, apartment2);
		hallway.addPointsOfInterest("vertical_painting", monaLisa);
		hallway.addPointsOfInterest("horizontal_painting", wave);

		currentRoom = bedroom; // start game in bedroom

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

		} else if (commandWord.equals("map")){
			map();

		}else if (commandWord.equals("check")) {
			checkSomething(command);

		} else if (commandWord.equals("take")){
			takeItem(command);

		} else if (commandWord.equals("look")) {
			currentRoom.look();

		} else if (commandWord.equals("inspect")) {
			inspect(command);

		} else if(commandWord.equals("drop")){
			dropItem(command);
		}else if (commandWord.equals("quit")) {
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

	private void map(){
		System.out.println("Map");
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
	}

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
