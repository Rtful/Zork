package ch.bbw.zork;

import java.io.IOException;
import java.util.HashMap;

public class WorldMap {

    HashMap<String, String> roomLocation = new HashMap<>();


    String savedMap;

    public WorldMap(){
        roomLocation.put("reception", "{1");
        roomLocation.put("hallway", "{2");
        roomLocation.put("office", "{3");
        roomLocation.put("lab", "{4");
        roomLocation.put("storage", "{5");
        roomLocation.put("lobby", "{6");
        roomLocation.put("ceoOffice", "{7");
        roomLocation.put("cafeteria", "{8");
    }

    public void showMap(String roomName) throws IOException {

        Image asciiMap = new Image("img/asciiMap.txt");

        savedMap = asciiMap.getImage();

        System.out.println("Currently in: " + roomName);
        savedMap = savedMap.replace(roomLocation.get(roomName), " ▲");

        // roomLocation.remove(roomName);

        roomLocation.forEach((k, v) -> savedMap = savedMap.replace(v, "  "));



        System.out.println(savedMap);
    }

}
