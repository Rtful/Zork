package ch.bbw.zork;
/*
 * Author:  Michael Kolling, Version: 1.1, Date: August 2000
 * refactoring: Rinaldo Lanza, September 2020
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room {

    private final String roomName;
	private final String description;
	private final HashMap<String, PointOfInterest> pointsOfInterest;
	private final HashMap<String, Item> items;
	private final HashMap<String, Door> exits;

	public Room(String roomName, String description, Boolean container) {
		this.roomName = roomName;
	    this.description = description;
		this.exits = new HashMap<>();
		this.pointsOfInterest = new HashMap<>();
		this.items = new HashMap<>();
	}


	public String getRoomName(){
	    return this.roomName;
    }

    public void addPointOfInterest(String name, PointOfInterest pointOfInterest) {
        pointsOfInterest.put(name, pointOfInterest);
    }

	public void addContainer(String name, Item item) {
		items.put(name, item);
	}

	public PointOfInterest getPointOfInterest(String name) {
		return pointsOfInterest.getOrDefault(name, null);
	}

    public String interact(String target) {
        PointOfInterest pointOfInterest = this.getPointOfInterest(target);
        if (pointOfInterest != null) {
            return pointOfInterest.interact();
        }
        return null;
    }

    public void setExits(Door north, Door east, Door south, Door west) {
        exits.put("north", north);
        exits.put("east", east);
        exits.put("south", south);
        exits.put("west", west);
        exits.values().removeIf(Objects::isNull);
    }

    public Door getExit(String direction) {
        return exits.get(direction);
    }

    public String getLongDescription() {
        return "You are in " + description + ".\n" + "Exits:" + String.join(" ", exits.keySet());
    }

	public HashMap<String, Item> getContainer() {
		return items;
	}

    public void look() {
        System.out.println("You look around. You see:\n" + exits.size() + " exits: " + String.join(", ", exits.keySet()));
        for (Map.Entry<String, PointOfInterest> entry : pointsOfInterest.entrySet()) {
            String location = entry.getValue().getLocation();
            System.out.println("A " + entry.getKey() + " " + location);
        }
        if (!items.isEmpty()){
            System.out.print("You also see this/these items: ");
            items.forEach((k, v) -> System.out.print(" | " + k + " | "));
            System.out.println();
        } else {
            System.out.println("There are no items here");
        }
    }

    public boolean unlock(String direction, Item key) {
		return this.exits.get(direction).unlock(key);
    }

    public boolean unlock(String direction, String code) {
		return this.exits.get(direction).unlock(code);
    }

    public String inspect(String target) {
        if (pointsOfInterest.containsKey(target)) {
            return pointsOfInterest.get(target).getDescription();
        }
        return null;
    }

}

