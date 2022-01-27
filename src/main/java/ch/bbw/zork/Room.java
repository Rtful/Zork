package ch.bbw.zork;
/*
 * Author:  Michael Kolling, Version: 1.1, Date: August 2000
 * refactoring: Rinaldo Lanza, September 2020
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room {

	private final String description;
	private final HashMap<String, PointOfInterest> pointsOfInterest;
	private final HashMap<String, Door> exits;
	private Boolean container;

	public Room(String description, Boolean container) {
		this.description = description;
		this.exits = new HashMap<>();
		this.pointsOfInterest = new HashMap<>();
		this.container = container;

	}

	public void addPointOfInterest(String name, PointOfInterest pointOfInterest) {
		pointsOfInterest.put(name, pointOfInterest);
	}

	public HashMap<String, PointOfInterest> getPointsOfInterest() {
		return pointsOfInterest;
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

	public Boolean getContainer() {
		return container;
	}

	public void look() {
		System.out.println("You look around. You see:\n" + exits.size() + " exits: " + String.join(", ", exits.keySet()));
		for(Map.Entry<String, PointOfInterest> entry : pointsOfInterest.entrySet()) {
			String location = entry.getValue().getLocation();
			System.out.println("A " + entry.getKey() + " in " + location);
		}
	}

    public String inspect(String target){
        if (pointsOfInterest.containsKey(target)) {
            return pointsOfInterest.get(target).getDescription();
        }
        return null;
    }

}

