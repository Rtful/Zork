package ch.bbw.zork;

/*
 * author: Loris Hütter, Januar 2022
 */

import java.util.HashMap;

public class Inventory {

    private final HashMap<String, Item> backpack = new HashMap<String, Item>();

    public Item getItem(String itemName){
        return backpack.get(itemName);
    }
    public boolean hasItem(String itemName){
        return this.backpack.containsKey(itemName);
    }

    public void addItem(String itemName, Item item) {
        this.backpack.put(itemName, item);
    }

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

    public void dropItem(String itemName) {
        this.backpack.remove(itemName);
    }
}
