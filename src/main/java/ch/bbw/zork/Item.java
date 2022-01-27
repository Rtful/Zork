package ch.bbw.zork;

/*
 * author: Loris Hütter, Januar 2022
 */

public class Item {

    private String name;
    private String description;
    private double weight;

    public Item(String name, String description, double weight){
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
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
