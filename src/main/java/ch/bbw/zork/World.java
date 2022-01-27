package ch.bbw.zork;

/*
 * author: Aaron Holenstein, Januar 2022
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class World {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private final Room[][] rooms;
    private final Scanner scanner;
    private final Player player;
    HashMap<String, Item> allItems = new HashMap<>();

    public World(Scanner scanner, Player player) throws IOException {
        this.scanner = scanner;
        this.player = player;
        Item key        = new Item("key", "Just an old rusty key", 0.2);
        Item knife      = new Item("knife", "An old rusty blade", 0.1);
        Item keycard    = new Item("keycard", "A keycard. It probably opens up the exit", 1);
        Item boltCutter = new Item("boltCutter", "A boltcutter. Very useful for cutting wire and fences", 5);
        allItems = new HashMap<String, Item>(Map.of(
                "key", key,
                "knife", knife
        ));

        rooms = new Room[4][3];
        x = 0;
        y = 1;

        Room  reception     = new Room("reception", "the reception", false);
        Room  lab           = new Room("lab", "lab, where some experiments had taken place", false);
        Room  office        = new Room("office", "big shared office", false);
        Room  ceoOffice     = new Room("ceoOffice", "fancy office belonging to the manager", false);
        Room  lobby         = new Room("lobby", "the lobby", false);
        Room  cafeteria     = new Room("cafeteria", "the cafeteria", false);
        Room  storage       = new Room("storage", "the storage room", false);
        Room  hallway       = new Room("hallway", "an eerie partially lit hallway", false);
        Image monaLisaASCII = new Image("img/monaLisa.txt");
        Image waveASCII     = new Image("img/wave.txt");
        Image dogASCII      = new Image("img/dog.txt");

        Riddle pcRiddle = new Riddle("KISHA", "This PC is locked. A 5-digit password is required\nEnter password:", "Oth the computer screen there is an open document.\nIt says:\nIf your are searching for information,\nthe smiling woman will tell you the answer.");

        PointOfInterest monaLisa = new PointOfInterest(monaLisaASCII.getImage(), "on the north wall");
        PointOfInterest wave     = new PointOfInterest(waveASCII.getImage(), "on the north wall");
        PointOfInterest dog      = new PointOfInterest(dogASCII.getImage(), "next to the PC");
        PointOfInterest pc       = new PointOfInterest("The PC is running but a password is required\nYou can interact with this object", "on one of the desks", pcRiddle);
        PointOfInterest sign     = new PointOfInterest("The smiling woman will tell you the answer", "next to the east door");

        Lock officeLock    = new Lock(knife, "A code based lock. It doesn't seem to be working though. Some of the wires are exposed.");
        Lock storageLock   = new Lock("146", "A code based lock. A 3-digit code is required."); //TODO
        Lock ceoOfficeLock = new Lock(boltCutter, "This door is closed with a padlock.");
        Lock labLock       = new Lock(keycard, "A keycard is required to open this door.");

        Door receptionHallway = new Door(null);
        Door hallwayOffice    = new Door(officeLock);
        Door hallwayLobby     = new Door(null);
        Door hallwayLab       = new Door(labLock);
        Door lobbyStorage     = new Door(storageLock);
        Door lobbyCafeteria   = new Door(null);
        Door lobbyCEOOffice   = new Door(ceoOfficeLock);

        reception.setExits(null, receptionHallway, null, null);
        hallway.setExits(hallwayOffice, hallwayLab, hallwayLobby, receptionHallway);
        office.setExits(null, null, hallwayOffice, null);
        lab.setExits(null, null, null, hallwayLab);
        lobby.setExits(hallwayLobby, lobbyCEOOffice, lobbyCafeteria, lobbyStorage);
        ceoOffice.setExits(null, null, null, lobbyCEOOffice);
        storage.setExits(null, lobbyStorage, null, null);
        cafeteria.setExits(lobbyCafeteria, null, null, null);
        ceoOffice.setExits(null, null, null, lobbyCEOOffice);

//        reception.addContainer("key", key);
//
//        reception.addContainer("knife", knife);
//        reception.addContainer("boltCutter", boltCutter);
//        hallway.addContainer("coin", coin);
//        hallway.addContainer("picture", picture);

        /* TODO: give each room the needed Items
        office.addContainer();
        ceoOffice.addContainer();
        lobby.addContainer();
        cafeteria.addContainer();
        storage.addContainer();
        lab.addContainer();
        */

        cafeteria.addContainer("knife", knife);
        ceoOffice.addContainer("keycard", keycard); //TODO put inside safe
        storage.addContainer("boltcutter", boltCutter);

        rooms[0] = new Room[]{null, reception, storage};
        rooms[1] = new Room[]{office, hallway, lobby, cafeteria};
        rooms[2] = new Room[]{null, lab, ceoOffice};

        lobby.addPointOfInterest("MonaLisa", monaLisa);
        lobby.addPointOfInterest("Wave", wave);
        lobby.addPointOfInterest("Sign", sign);
        office.addPointOfInterest("Picture", dog);
        office.addPointOfInterest("PC", pc);
    }

    public Item takeItem(String requestedItem) {
        if (this.allItems.containsKey(requestedItem)) {
            Item foundItem = this.allItems.get(requestedItem);
            this.allItems.remove(requestedItem);
            return foundItem;
        } else {
            return null;
        }
    }

    public int[] getCoordinates() {
        return new int[]{this.x, this.y};
    }

    public void look() {
        rooms[this.x][this.y].look();
    }

    public Room getCurrentRoom() {
        return rooms[this.x][this.y];
    }

    public void inspect(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Inspect what?");
        } else {
            String target       = command.getSecondWord();
            String detailedView = this.rooms[this.x][this.y].inspect(target);
            System.out.println(Objects.requireNonNullElse(detailedView, "Nothing to inspect"));
        }
    }

    public String getLongDescription() {
        return this.rooms[this.x][this.y].getLongDescription();
    }

//    public void unlock(String exit, Scanner scanner){
//        Door requestedDoor = this.rooms[this.x][this.y].getExit(exit);
//        if (Objects.equals(requestedDoor.getLockType(), "code")){
//
//        }
//    }

    public void back() {
        this.x = this.oldX;
        this.y = this.oldY;
        System.out.println(this.rooms[this.x][this.y].getLongDescription());
    }

    public void use(String target) {
        String riddleDescription = this.rooms[this.x][this.y].interact(target);
        if (riddleDescription != null) {
            System.out.println(riddleDescription);
            String attemptedSolution = scanner.nextLine();
            System.out.println(this.rooms[this.x][this.y].getPointOfInterest(target).solveRiddle(attemptedSolution));
        } else {
            System.out.println("You can't interact with that");
        }
    }

    public void go(String direction) {

        Door exit = this.rooms[this.x][this.y].getExit(direction);
        int  newX = this.x;
        int  newY = this.y;

        switch (direction) {
            case "north":
                newY = this.y - 1;
                break;
            case "east":
                newX = this.x + 1;
                break;
            case "south":
                newY = this.y + 1;
                break;
            case "west":
                newX = this.x - 1;
                break;
            default:
                break;
        }

        if (exit == null)
            System.out.println("There is no door!");
        else {
            try {
                if (rooms[newX][newY] == null) {
                    System.out.println("The room you are trying to access does not exist");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There has been an internal error with the map");
                throw e;
            }
            if (exit.isOpen()) {
                this.oldX = this.x;
                this.oldY = this.y;
                this.x = newX;
                this.y = newY;
                System.out.println(rooms[this.x][this.y].getLongDescription());
            } else {
                if (Objects.equals(this.rooms[this.x][this.y].getExit(direction).getLockType(), "code")) {
                    System.out.println("Please enter a code");
                    String code = scanner.nextLine();
                    if (this.rooms[this.x][this.y].unlock(direction, code)) {
                        System.out.println("You entered the correct combination and the door opened.");
                        this.oldX = this.x;
                        this.oldY = this.y;
                        this.x = newX;
                        this.y = newY;
                        System.out.println(rooms[this.x][this.y].getLongDescription());
                    } else {
                        System.out.println("That didn't work.\nYou will have to try something else");
                    }
                } else {
                    System.out.println(this.rooms[this.x][this.y].getExit(direction).getDescription() + "\nPlease select the item you want to use to try and open the door");
                    this.player.showInventory();
                    String chosenItem = scanner.nextLine();
                    Item   key        = this.player.getItem(chosenItem);
                    if (this.rooms[this.x][this.y].unlock(direction, key)) {
                        System.out.println("You used the " + chosenItem + " to open the door.");
                        this.oldX = this.x;
                        this.oldY = this.y;
                        this.x = newX;
                        this.y = newY;
                        System.out.println(rooms[this.x][this.y].getLongDescription());
                    } else {
                        System.out.println("That didn't work.\nYou will have to try something else");
                    }
                }
            }
        }
    }
}
