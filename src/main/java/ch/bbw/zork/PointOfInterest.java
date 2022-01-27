package ch.bbw.zork;

import java.util.HashMap;

public class PointOfInterest {
    private String description;
    private String location;
    private final Riddle riddle;

    public PointOfInterest(String description, String location) {
        this.description = description;
        this.location = location;
        this.riddle = null;
    }

    public PointOfInterest(String description, String location, Riddle riddle) {
        this.description = description;
        this.location = location;
        this.riddle = riddle;
    }

    public String solveRiddle(String solution) {
        if (this.riddle == null) {
            return "There is nothing to solve";
        } else if (this.riddle.unlock(solution)) {
            return "You have entered the correct code.\n" + this.riddle.getSecret();
        }
        return "Wrong answer";
    }

    public String interact() {
        return this.riddle == null ? null : this.riddle.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }
}
