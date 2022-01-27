package ch.bbw.zork;

import java.io.IOException;
import java.util.Objects;

public class Navigator {
    private int x;
    private int y;
    private final Room[][] rooms;

    public Navigator() throws IOException {
        rooms = new Room[4][3];
        x = 0;
        y = 1;

        Room reception = new Room("the reception");
        Room lab       = new Room("lab, where some experiments had taken place");
        Room office    = new Room("big shared office");
        Room ceoOffice = new Room("fancy office belonging to the manager");
        Room lobby     = new Room("the lobby");
        Room cafeteria = new Room("the cafeteria");
        Room storage   = new Room("the storage room");
        Room hallway   = new Room("an eerie partially lit hallway");

        Door receptionHallway = new Door(null);
        Door hallwayOffice    = new Door(null);
        Door hallwayLobby     = new Door(null);
        Door hallwayLab       = new Door(null);
        Door lobbyStorage     = new Door(null);
        Door lobbyCafeteria   = new Door(null);
        Door lobbyCEOOffice   = new Door(null);

        reception.setExits(null, receptionHallway, null, null);
        hallway.setExits(hallwayOffice, receptionHallway, hallwayLobby, hallwayLab);
        office.setExits(null, null, hallwayOffice, null);
        lab.setExits(null, null, null, hallwayLab);
        lobby.setExits(hallwayLobby, lobbyCEOOffice, lobbyCafeteria, lobbyStorage);
        ceoOffice.setExits(null, null, null, lobbyCEOOffice);
        storage.setExits(null, lobbyStorage, null, null);
        cafeteria.setExits(lobbyCafeteria, null, null, null);
        ceoOffice.setExits(null, null, null, lobbyCEOOffice);

        rooms[0] = new Room[]{null, reception, storage};
        rooms[1] = new Room[]{office, hallway, lobby, cafeteria};
        rooms[2] = new Room[]{null, lab, ceoOffice};

        Image monaLisaASCII = new Image("img/monaLisa.txt");
        Image waveASCII     = new Image("img/wave.txt");

        PointOfInterest monaLisa = new PointOfInterest(monaLisaASCII.getImage(), "north wall");
        PointOfInterest wave     = new PointOfInterest(waveASCII.getImage(), "north wall");

        hallway.addPointOfInterest("vertical_painting", monaLisa);
        hallway.addPointOfInterest("horizontal_painting", wave);

    }

    public void look() {
        rooms[this.x][this.y].look();
    }

    public void inspect(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Inspect what?");
        } else {
            String target = command.getSecondWord();
            // Try to leave current room.
            String detailedView = this.rooms[this.x][this.y].inspect(target);
            System.out.println(Objects.requireNonNullElse(detailedView, "Nothing to inspect"));
        }
    }

    public String getLongDescription(){
        return this.rooms[this.x][this.y].getLongDescription();
    }

    public void go(Command command){

        String  direction = command.getSecondWord();

        // Try to leave current room.
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
                this.x = newX;
                this.y = newY;
                System.out.println(rooms[this.x][this.y].getLongDescription());
            }
        }
    }
}