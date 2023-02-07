package com.game.templejog;

import java.util.ArrayList;
import java.util.List;

public class Player {
    Integer health, steps;
    List<Item> inventory;

    /*              CONSTRUCTORS                    */
    public Player(){
        this.health = 5;
        this.steps = 0;
        this.inventory = new ArrayList<>();
    }

    //  DELETE ME
    public Player(List<Item> inventory) {
        super();
        this.inventory = inventory;
    }

    /*              HELPERS                    */
    public Integer inventoryHasItem(String itemName){
        for( Item item : getInventory() ){
//            if( item.getName().equals(itemName) ) return getInventory().indexOf(item);
            if( item.getName().toLowerCase().equals(itemName) ) {
                Integer itemIndex = getInventory().indexOf(item);
                return itemIndex;
            }
        }
        return -1;
    }

    /*              ACCESSOR METHODS                    */
    public List<Item> getInventory() { return inventory; }
    public void setInventory(List<Item> inventory) {this.inventory = inventory;}
    public Integer getHealth() {return health;}
    public void setHealth(Integer health) {this.health = health;}
    public Integer getSteps() {return steps;}
    public void setSteps(Integer steps) {this.steps = steps;}
}