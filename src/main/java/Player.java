import java.util.ArrayList;
import java.util.List;

public class Player {
    Integer health, steps;
    List<Item> inventory = new ArrayList<>();

    /*              CONSTRUCTORS                    */
    public Player() {
        health = 5;
        steps = 0;
    }

    public Integer inventoryHasItem(String itemName){
        for( Item item : getInventory() ){
            if( item.getName().equals(itemName) ) return getInventory().indexOf(item);
        }
        return -1;
    }

    public Item useItemFromInventory( String itemName ){
        Integer itemIndex = inventoryHasItem(itemName);
        return getInventory().get(itemIndex);
    }

    public Boolean removeFromInventory(String itemName){
        Integer itemIndex = inventoryHasItem(itemName);
        System.out.println("IDX"+itemIndex);
        return getInventory().remove(itemIndex);
    }

    public List<Item> getInventory() { return inventory; }
    public void setInventory(List<Item> inventory) {this.inventory = inventory;}
}