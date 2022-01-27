package ch.bbw.zork;

/*
 * author: Loris Hütter, Januar 2022
 */

import java.util.HashMap;

public class Inventory {

    public static HashMap<String, Item> backpack = new HashMap<String, Item>();

    public static Item getItem(String itemName){
        return backpack.get(itemName);
    }

    public static void showInventory(){
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
