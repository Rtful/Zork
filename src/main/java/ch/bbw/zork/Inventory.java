package ch.bbw.zork;

import java.util.HashMap;

public class Inventory {

    public HashMap<String, Item> backpack = new HashMap<String, Item>();


    public void showInventory(){
        System.out.println("+---------------+----------------------------------------------------+");
        System.out.println("|   Item Name   |                 Item Description                   |");
        System.out.println("+---------------+----------------------------------------------------+");

        for (Item item : backpack.values()){
            System.out.format("| %-13s | %-50s |%n", item.getName(), item.getDescription());
        }
        System.out.println("+---------------+----------------------------------------------------+");
        System.out.println("Entries:" + backpack.size());
    }

}
