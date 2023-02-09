package com.game.templejog;

import java.util.*;

public class Game {
    // MODEL
    private Boolean quitGame;
    private String scannerString;
    private HashMap<String, Room> rooms;
    private HashMap<String, Encounter> encounters;
    private HashMap<String, Item> items;
    private Player player;
    private Room currentRoom;
    private Boolean communicatorOff;
    private static Boolean playSound;

    public Game( Player player, HashMap<String, Room> rooms, HashMap<String, Encounter> encounters, HashMap<String, Item> items){
        this.quitGame = false;
        this.player = player;
        this.rooms = rooms;
        this.items = items;
        this.encounters = encounters;
        setCurrentRoom(getRooms().get("room01"));
        setCommunicatorOff(false);
    }

// CONTROLLERS
    public String processChoice(String[] choice){
        String verb = choice[0];
        String noun = "";
        if( choice.length > 1 ) noun = choice[1];
        if(verb.equals("quit")) return processQuitting();
        if(verb.equals("go")) return processNavigating( noun );
        if(verb.equals("get")) return processGetting( noun );
        if(verb.equals("look")) return processLooking( noun );
        if(verb.equals("use")) return processUsing( noun );
        if(verb.equals("help")) return processHelping();
        if(verb.equals("invalid")) return processInvalid();
        if(verb.equals("sound")) return Sound.turningSound(noun, this);
        return "";
    }
    private String processQuitting(){
        System.out.println("Are you sure you want to quit? [Type 'y' or 'n']");
        updateScannerString();
        String playerResponse  = getScannerString().toLowerCase().substring(0, 1);
        if( playerResponse.equals("y") ) setQuitGame(!getQuitGame());
        else return "Returning to game...";

        return "Thanks for playing!";
    }
    private String processNavigating(String noun){

        List<String> standardDirections = Arrays.asList("north", "south", "east", "west");
        if( noun.isEmpty() || !standardDirections.contains(noun.toLowerCase()) ) return InvalidNounInput.BAD_NAV.getWarning();
        String directionValue = getCurrentRoom().checkDirection(noun);
        String accessibleRoom;
        // CHECK IF DIR BLOCKED
        if( !directionIsLocked(noun,directionValue).isEmpty() ) return directionIsLocked(noun, directionValue);
        // GO IN THAT DIR
        if( directionValue.length() > 1  ) {
            String outputMessage;

            outputMessage = cowardiceDamage();

            accessibleRoom = directionValue;
            Room validRoom = getRooms().get(accessibleRoom);
            validRoom.setHasBeenVisited(!validRoom.getHasBeenVisited());
            setCurrentRoom(validRoom);
            getCurrentRoom().setHasBeenVisited(true);
            getPlayer().setSteps(getPlayer().getSteps()+1);
            currentRoomSound();
            return String.format("Traveling to %s... %s",getCurrentRoom().getName(), outputMessage);
        }

        return "Cannot go in that direction...";
    }

    private void currentRoomSound() {
        String currentRoomSound = getCurrentRoom().getSound();
        if (getPlaySound()) {
            if(!currentRoomSound.isEmpty()) {
                Sound.stopSound();
                Sound.themeSound(currentRoomSound);
            }
        }
    }

    private String processLooking(String noun){
        if(noun.isEmpty()) return InvalidNounInput.BAD_LOOK.getWarning();

        Integer itemIndex = getPlayer().inventoryHasItem(noun);
        if( itemIndex >= 0 ) return getPlayer().getInventory().get(itemIndex).getDescription();
        for(Item item : getItems().values()) {
            if(item.getName().toLowerCase().equals(noun)) return item.getDescription();
        }
        return " not found " + noun;

    }
    private String processGetting(String noun){
        if(noun.isEmpty()) return InvalidNounInput.BAD_GET.getWarning();
        for(String item: getCurrentRoom().getItems()){
            if(item.toLowerCase().equals(noun)){
                Item poppedItem = popItemFromMap(noun);
                getPlayer().getInventory().add(poppedItem);
                getCurrentRoom().getItems().remove(noun);
                return String.format("You added %s to your inventory...", noun);
            }
        }
        if( getPlayer().inventoryHasItem(noun) >= 0 ){
            return String.format("%s already in your inventory",noun);
        }
        return String.format(" %s was not found in current room...",noun);
    }
    private String processUsing(String noun){
//      TODO: DECOMPOSE INTO METHODS
        if(noun.isEmpty()) return InvalidNounInput.BAD_USE.getWarning();
        Integer inventoryIndex = getPlayer().inventoryHasItem(noun); // HAS ITEM
        // DONE: HAS ITEMS and ENCOUNTERS
        if( inventoryIndex >= 0 && getCurrentRoom().getEncounters_to().size() > 0 ){
            List<String> activeEncounters = getCurrentRoom().getEncounters_to();
            String currentEncounterName = activeEncounters.get(0); // 1st Encounter Name
            Encounter encounter = null;
            if( getEncounters().get(currentEncounterName) != null ) encounter = getEncounters().get(currentEncounterName);// get Encounter Obj
            if( encounter != null ){
        // DONE: ENCOUNTER HAS WEAKNESS and ENEMY TYPE
//                String decrementItemsNumberOfReuses = usePlayerItem(inventoryIndex,noun);
                if( !encounter.getWeakness().contains(noun) ) return " Failed to use "+noun+" on "+ currentEncounterName;

                String decrementItemsNumberOfReuses = usePlayerItem(inventoryIndex,noun);
                if( encounter.getType().equals("enemy") ){
                    Boolean encounterRemovedFromCurrRoom = getCurrentRoom().removeEncounter(currentEncounterName); // room's with enc
                    // DONE: USE ITEM, DESTROY ENCOUNTER
                    if( (encounterRemovedFromCurrRoom || (getCurrentRoom().getEncounters_to().size() == 0) ) ) {
                        System.out.println(decrementItemsNumberOfReuses);
                        System.out.println(noun+" is EFFECTIVE against " + currentEncounterName);
                        return "You destroyed " + currentEncounterName;
                    }
                    else return noun+" is EFFECTIVE against " + currentEncounterName+ ", but is not destroyed";
                }
        // DONE: ENV TYPE
                if( encounter.getType().equals("environment") ){
//                    WIP: REMOVE ENCOUNTER FROM ROOM List of encounters_to/from
//                    Boolean encounterRemovedFromCurrRoom = getCurrentRoom().removeEncounter(currentEncounterName);  // Never used
                    // DONE: REMOVE FROM ENCOUNTERS MAP

                    if( currentEncounterName.equals("communicator") ){
                        setActiveEncounters();
//                        communicatorOff = true;
                        setCommunicatorOff(!getCommunicatorOff());
                    }
                    getCurrentRoom().getEncounters_to().remove(currentEncounterName);
                    return "Success, you have opened the locked door!";
                }
            }
            else return "Not EFFECTIVE against "+currentEncounterName;
        }
        // DONE: HAS ITEMS and NO ENCOUNTERS
        if( inventoryIndex >= 0 && getCurrentRoom().getEncounters_to().size() == 0 ) {
            return "no active encounter in this room";
        }
        // DONE: NO ITEMS
        return noun + " not in your inventory";
    }
//    private String processHelping(String noun){
    private String processHelping(){
        return "Go - Use 'go [direction]' command to move to designated direction \n" +
                "Look - Use 'look [item]' for item description \n" +
                "Get  - Use 'get [item]' command to obtain the item \n" +
                "Use - Use 'use [item]' command to fight or kill enemy \n" +
                "Quit - Use 'quit' command to exit out of the game \n" +
                "Sound - Use 'sound [on/off]' to turn on or off sound";
    }
    private String processInvalid(){
        return "Invalid Input, Type 'Help' for more information.";
    }

//  Helper Methods
    private String cowardiceDamage(){
        String outputMessage = "";
        if(!getCurrentRoom().getEncounters_to().isEmpty()){
            Integer enemyDamage = 1;
            StringBuilder enemy = new StringBuilder();
            for (String name : getCurrentRoom().getEncounters_to()) {
                Encounter encounter = getEncounters().get(name);
                if (encounter.getType().equals("enemy")){
                    enemy.append(encounter.getName());
                    getPlayer().setHealth(getPlayer().getHealth() - enemyDamage );
                    System.out.println();
                }
            }
            outputMessage = (enemy.toString().isEmpty())?"": String.format("You chose to jog away and took damage from %s", enemy);

        }
        return outputMessage;
    }
    private void setActiveEncounters(){
        for(Room rm : getRooms().values()){
            ArrayList<String> list = new ArrayList<>();
            list.addAll(rm.getEncounters_to());
            list.addAll(rm.getEncounters_from());
            rm.getEncounters_from().clear();
            rm.setEncounters_to(list);
        }
    }
    private String directionIsLocked(String noun, String directionValue) {
        String checkDirection = "";
        if( getCurrentRoom().directionBlockedByDoor() && !directionValue.isEmpty() ) {
            Boolean hasLockedDoor = getCurrentRoom().directionBlockedByDoor();
            System.out.println();
            Boolean targetRoomIsLocked = getRooms().get(directionValue).getIsLocked();
            if(hasLockedDoor && targetRoomIsLocked) {
                String roomName = getRooms().get(directionValue).getName();
                checkDirection = String.format("%s is a locked door, cannot get to %s",noun,roomName);
            }
        }
        System.out.println();
        return checkDirection;
    }
    private String usePlayerItem( Integer inventoryIndex, String noun ){
        if(inventoryIndex < 0) return String.format("%s not in your inventory",noun);
        // TODO: Move prints to Main or ConsoleInterface and just return strings in Game
        Item inventoryItem = getPlayer().getInventory().get(inventoryIndex);
        Integer reuse = inventoryItem.getReuse();
        if( reuse == 0 ){
            getPlayer().getInventory().remove(inventoryItem);
            System.out.println("Removed "+noun+" from inventory");
            if(noun.equals("key") || noun.equals("crystal femur")) System.out.printf("looks like %s fits perfectly%n",noun);
            else System.out.println("Last chance make it count!!!");
        }
        if( reuse > 0 )inventoryItem.setReuse( inventoryItem.getReuse() - 1 );
        return "Using "+ noun;

    }
    public void updateScannerString(){
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        setScannerString(scannerString);
    }
    public Item popItemFromMap(String targetName){
        HashMap<String, Item> itemsMap = getItems();
        Item targetItem = new Item();
        for(String key : itemsMap.keySet()) {
            if(key.toLowerCase().equals(targetName)) {
                targetItem = itemsMap.remove(key);
                break;
            }
        }
        return targetItem;
    }

//  ACCESSOR METHODS
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

    public Boolean getCommunicatorOff() { return communicatorOff; }
    public void setCommunicatorOff(Boolean communicatorOff) { this.communicatorOff = communicatorOff; }

    public Boolean getPlaySound() {
        return playSound;
    }

    public void setPlaySound(Boolean playSound) {
        this.playSound = playSound;
    }
}