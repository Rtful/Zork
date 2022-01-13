package ch.bbw.zork;

import java.util.HashMap;

public class Inventory {

    public HashMap<Integer, Object> backpack = new HashMap<Integer, Object>();


    public void showInventory(){
        System.out.println("+----+--------------------+");
        System.out.println("| ID |     Item Name      |");
        System.out.println("+----+--------------------+");
        System.out.format("| %-2d |%-20s|%n", 25, "backpack3");
        System.out.format("| %-2d |%-20s|%n", 25, "backpack3");
        System.out.format("| %-2d |%-20s|%n", 25, "backpack3");
        System.out.println("+----+--------------------+");
    }

}
