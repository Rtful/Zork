package ch.bbw.zork;

import java.io.IOException;
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
    private final World world;
    private final Player player;
    private final Scanner scanner;

    WorldMap worldMap = new WorldMap();

    public Game() throws IOException {
        this.scanner = new Scanner(System.in);
        Date date = new Date();
        this.player = new Player();
        this.parser = new Parser(this.scanner);
        this.world = new World(this.scanner, this.player);
    }
    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() throws IOException {

        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        boolean won = false;

        while (!finished && !won) {
            Command command = parser.getCommand();
            if (command == null) {
                System.out.println("Please enter a valid command");
                continue;
            }
            finished = processCommand(command);
            if (Arrays.equals(this.world.getCoordinates(), new int[]{2, 1})) {
                won = true;
            } else if(Objects.equals(command.getCommandWord(), "go") && Arrays.equals(this.world.getCoordinates(), new int[]{0, 2})) {
                System.out.println("The window is open, brrrrr");
            }
        }
        if (won){
            System.out.println("Congratulations! You have won the game");
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to Zork!");
        System.out.println("Zork is a simple adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(this.world.getLongDescription());
    }

        private boolean processCommand(Command command) throws IOException {
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
                this.world.go(command.getSecondWord());
                break;
            case "back":
                this.world.back();
                break;
            case "check":
                checkSomething(command);
                break;
            case "take":
                takeItem(command);
                break;
            case "look": // TODO: show room items
                this.world.look();
                break;
            case "drop":
                dropItem(command);
                break;
            case "use":
                if (command.hasSecondWord()) {
                    this.world.use(command.getSecondWord());
                } else {
                    System.out.println("Please specify what you want to use");
                }
                break;
            case "inspect":
                this.world.inspect(command);
                break;
            case "unlock":
                if (command.hasSecondWord()) {
                    this.world.go(command.getSecondWord());
                } else {
                    System.out.println("Please specify what you want to unlock");
                }
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
        System.out.println(this.parser.showCommands());
    }

	private void takeItem(Command command) {

		if (!command.hasSecondWord()) {
			System.out.println("Take what?");
		} else {
			String takenItem = command.getSecondWord();
			if (this.world.getCurrentRoom().getContainer().containsKey(takenItem)) {
				System.out.println("\nPicked up " + takenItem + "\n");
				this.player.takeItem(takenItem, this.world.getCurrentRoom().getContainer().get(takenItem));
				this.world.getCurrentRoom().getContainer().remove(takenItem);
			} else {
				System.out.println("I can't take that");
			}
		}
	}

	private void dropItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else {
            String droppedItem = command.getSecondWord();
            if (this.player.hasItem(droppedItem)) {
                this.world.getCurrentRoom().addContainer(droppedItem, this.player.getItem(droppedItem));
                this.player.dropItem(droppedItem);
            } else {
                System.out.println("I don't have that");
            }
        }
    }

	private void checkSomething(Command command) throws IOException {

		if (!command.hasSecondWord()) {
			System.out.println("Check what?");
		} else {
			String checking = command.getSecondWord();
			if (checking.equals("backpack")) {
                this.player.showInventory();
            } else if (checking.equals("map")) {
                worldMap.showMap(world.getCurrentRoom().getRoomName());
			} else {
				System.out.println("I don't know how to check that");
			}
		}
	}
}
