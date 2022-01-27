package ch.bbw.zork;

import java.io.IOException;

public class WorldMap {

    String savedMap;

    // TODO: show player current location with string replace

    public void showMap() throws IOException {


        Image asciiMap = new Image("img/asciiMap.txt");

        savedMap = asciiMap.getImage();

        System.out.println(savedMap);
    }

}
