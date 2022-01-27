package ch.bbw.zork;

import java.util.HashMap;

public class PointOfInterest {
    private String description;
    private String location;

    public PointOfInterest(String description, String location) {
        this.description = description;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
