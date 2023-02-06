package com.game.templejog;

import java.util.*;

public class Game {
// MODEL
    Boolean quitGame;
    String scannerString;
    HashMap<String, Room> rooms;
    HashMap<String, Encounter> encounters;
    HashMap<String, Item> items;
    Player player;
    Room currentRoom;

    public Game( Player player, HashMap<String, Room> rooms, HashMap<String, Encounter> encounters, HashMap<String, Item> items){
        this.quitGame = false;
        this.player = player;
        this.rooms = rooms;
        this.items = items;
        this.encounters = encounters;
        setCurrentRoom(getRooms().get("room01"));
    }

// CONTROLLERS
    public String processChoice(String[] choice){
        String verb = choice[0];
//        String noun = verb; // why assigning?
        String noun = "";
        if( choice.length > 1 ) noun = choice[1];
        if(verb.equals("quit")) return processQuitting( verb );
        if(verb.equals("go")) return processNavigating( noun );
        if(verb.equals("get")) return processGetting( noun );
        if(verb.equals("look")) return processLooking( noun );
        if(verb.equals("use")) return processUsing( noun );
        if(verb.equals("help")) return processHelping();
        if(verb.equals("invalid")) return processInvalid();
        return "";
    }
    private String processQuitting(String noun){
        System.out.println("Are you sure you want to quit? [Type 'y' or 'n']");
        updateScannerString();
        String playerResponse  = getScannerString().toLowerCase().substring(0, 1);
        if( playerResponse.equals("y") ) setQuitGame(!getQuitGame());
        else { return "Invalid input"; }

        return noun;
    }
    private String processNavigating(String noun){
        List<String> standardDirections = Arrays.asList("north", "south", "east", "west");
        if( noun.isEmpty() || !standardDirections.contains(noun.toLowerCase()) ) return EnumInvalidNounInput.BAD_NAV.getWarning();
        String accessableRoom = "";
        String directionValue = getCurrentRoom().checkDirection(noun);
        if( directionValue.length() > 1  ) {
            System.out.println("going "+ noun + " ....");
            accessableRoom = directionValue;
            Room validRoom = getRooms().get(accessableRoom);
            validRoom.setHasBeenVisited(!validRoom.getHasBeenVisited());
            setCurrentRoom(validRoom);
            getCurrentRoom().setHasBeenVisited(true);
            player.steps++;
        }
        else System.out.println("Cannot go in that direction");

        return accessableRoom;
    }
    private String processLooking(String noun){
        if(noun.isEmpty()) return EnumInvalidNounInput.BAD_LOOK.getWarning();

        Integer itemIndex = getPlayer().inventoryHasItem(noun);
        if( itemIndex >= 0 ) return getPlayer().getInventory().get(itemIndex).getDescription();
        if( getCurrentRoom().getItems().contains(noun) ) return getItems().get(noun).getDescription();
        else return " not found " + noun;

    }
    private String processGetting(String noun){
        if(noun.equals("")) return EnumInvalidNounInput.BAD_GET.getWarning();
        if(getCurrentRoom().getItems().contains(noun)){
            Item poppedItem = popItemFromMap(noun); // removes from games' itemsMap
            Boolean addedItemInventory = getPlayer().getInventory().add(poppedItem);
            Boolean removedRoomItem = getCurrentRoom().getItems().remove(noun);
            return "added "+noun+" to inventory";
        };
        if( getPlayer().inventoryHasItem(noun) >= 0 ){
            return noun + "already in your inventory";
        }
        return noun+" not found in current room";
    }
    private String processUsing(String noun){
        if(noun == "") return EnumInvalidNounInput.BAD_USE.getWarning();

        Integer inventoryIndex = getPlayer().inventoryHasItem(noun);
        if( inventoryIndex >= 0 ){
            Item inventoryItem = getPlayer().getInventory().get(inventoryIndex);
            Integer reuse = inventoryItem.getReuse();

            if( reuse == 0 ){
                getPlayer().getInventory().remove(inventoryItem);
                System.out.println("Removed "+" from inventory");
            }
            if( reuse > 0 )inventoryItem.setReuse( inventoryItem.getReuse() - 1 );
            return "Using "+ noun;
        }

        return noun + " not in your inventory";
    }
//    private String processHelping(String noun){
    private String processHelping(){
        String helpInfo = "Go - Use 'go [direction]' command to move to designated direction \n" +
                "Look - Use 'look [item]' for item description \n" +
                "Get  - Use 'get [item]' command to obtain the item \n" +
                "Use - Use 'use [item]' command to fight or kill enemy \n" +
                "Quit - Use 'quit' command to exit out of the game";
        return helpInfo;
    }
    private String processInvalid(){
        return "Invalid Input, Type \'Help\' for more information.";
    }

//  Helper Methods
    public void updateScannerString(){
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        setScannerString(scannerString);
    }
    public Item popItemFromMap(String targetName){
        HashMap<String, Item> itemsMap = getItems();
        Item targetItem = new Item();
        if( itemsMap.containsKey(targetName) ){
            Item removed = itemsMap.remove(targetName);
            return removed;
        }
        return targetItem;
    }

//    ACCESSOR METHODS
    public Room getCurrentRoom() { return currentRoom;}
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom;}
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public Map<String, Room> getRooms() { return rooms; }
    public void setRooms(HashMap<String, Room> rooms) { this.rooms = rooms; }
    public Boolean getQuitGame() { return quitGame; }
    public void setQuitGame(Boolean quitGame) { this.quitGame = quitGame; }
    public String getScannerString() {return scannerString;}
    public void setScannerString(String scannerString) { this.scannerString = scannerString;}
    public HashMap<String, Encounter> getEncounters() { return encounters; }
    public void setEncounters(HashMap<String, Encounter> encounters) {this.encounters = encounters;}
    public HashMap<String, Item> getItems() { return items; }
    public void setItems(HashMap<String, Item> items) { this.items = items; }
}