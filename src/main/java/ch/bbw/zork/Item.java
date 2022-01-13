package ch.bbw.zork;

public class Item {

    private int id;
    private String name;
    private String description;

    public Item(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void itemInfo(){
        System.out.println("ID:" + id + "\nName:" + name + "\nDescription:" + description);
    }

    public void description(){
        System.out.println(description);
    }
}
