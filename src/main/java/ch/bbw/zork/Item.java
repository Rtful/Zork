package ch.bbw.zork;

/*
 * author: Loris Hütter, Januar 2022
 */

public class Item {

    private String name;
    private String description;

    public Item(String name, String description){
        this.name = name;
        this.description = description;
    }

    public void itemInfo(){
        System.out.println("Name:" + name + "\nDescription:" + description);
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
}
