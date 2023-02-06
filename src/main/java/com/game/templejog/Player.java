package com.game.templejog;

import java.util.ArrayList;
import java.util.List;

public class Player {
    Integer health, steps;
    List<Item> inventory = new ArrayList<>();

    /*              CONSTRUCTORS                    */
    public Player(){
        this.health = 5;
        this.steps = 0;
    }

    public Player(List<Item> inventory) {
        super();
        this.inventory = inventory;
    }

    public Integer inventoryHasItem(String itemName){
        for( Item item : getInventory() ){
            if( item.getName().toLowerCase().equals(itemName) ) {
                Integer itemIndex = getInventory().indexOf(item);
                return itemIndex;
            }
        }
        return -1;
    }

    public List<Item> getInventory() { return inventory; }
    public void setInventory(List<Item> inventory) {this.inventory = inventory;}
    public Integer getHealth() {return health;}
    public void setHealth(Integer health) {this.health = health;}
    public Integer getSteps() {return steps;}
    public void setSteps(Integer steps) {this.steps = steps;}
}