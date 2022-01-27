package ch.bbw.zork;

public class Player {
    private double weight;
    private Inventory inventory;

    public Player() {
        this.weight = 0;
        this.inventory = new Inventory();
    }

    public void showInventory() {
        this.inventory.showInventory();
    }

    public void takeItem(String itemName, Item item) {
        this.inventory.addItem(itemName, item);
    }

    public void dropItem(String itemName) {
        this.weight -= this.inventory.getItem(itemName).getWeight();
        this.inventory.dropItem(itemName);
    }

    public boolean hasItem(String itemName) {
        return this.inventory.hasItem(itemName);
    }

    public Item getItem(String chosenItem){
        return this.inventory.getItem(chosenItem);
    }

}
