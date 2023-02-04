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
        if(verb.equals("quit") || noun.equals("quit")) return processQuitting( verb );
        if(verb.equals("go")) return processNavigating( noun );
        if(verb.equals("get")) return processGetting( noun );
        if(verb.equals("look")) return processLooking( noun );
        if(verb.equals("use")) return processUsing( noun );
        if(verb.equals("help")) return processHelping( noun );
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
        if(noun == ""){
            return "Invalid command. Please provide direction or type 'help'.";
        };
        System.out.println("going "+ noun + " ....");
        String accessableRoom = "";
        String directionValue = getCurrentRoom().checkDirection(noun);
        if( directionValue.length() > 1  ) {
            accessableRoom = directionValue;
            Room validRoom = getRooms().get(accessableRoom);
            validRoom.setHasBeenVisited(!validRoom.getHasBeenVisited());
            setCurrentRoom( validRoom );
            getCurrentRoom().setHasBeenVisited(true);
            player.steps++;
            return accessableRoom;
        } else {
            System.out.println("Cannot go in that direction");
            return accessableRoom;
        }
    }
    private String processLooking(String noun){
        // if room has item
        // description of item
        if(noun == ""){
            return "Invalid command. Please provide item name to look for or type 'help'.";
        };
        Integer itemIndex = getPlayer().inventoryHasItem(noun);
        if( itemIndex >= 0 ){
            return getPlayer().getInventory().get(itemIndex).getDescription();
        }
        if( getCurrentRoom().getItems().contains(noun) ) {
            return getItems().get(noun).getDescription();
        }
        else return " not found " + noun;
    }
    private String processGetting(String noun){
        if(noun == ""){
            return "Invalid command. Please provide the item name trying to get or type 'help'.";
        };
        // if present in room
        // add to inventory, pop from map -> inventory
        // remove item from room items and map of items
        if(getCurrentRoom().getItems().contains(noun)){
            Item poppedItem = popItemFromMap(noun); // removes from games' itemsMap
            Boolean addedItemInventory = getPlayer().getInventory().add(poppedItem);
            Boolean removedRoomItem = getCurrentRoom().getItems().remove(noun);
            return "added "+noun+" to inventory";
        };
        return noun+" not found in current room";
    }
    private String processUsing(String noun){
        if(noun == "") return "Invalid command. Please provide the item name to use or type 'help'.";
        // if in inventory
        // use item , then dispose of empty
        Integer itemIndex = getPlayer().inventoryHasItem(noun);
        if( itemIndex >= 0 ){
            // pop if single use
            // update number of uses
            Item item = getPlayer().useItemFromInventory(noun);
            if( item.getReuse() == 1 ) System.out.println("last chance, make it count");
            if( item.getReuse() == 0 ) {
                Item itemRemoved = getPlayer().getInventory().remove(0);
                return noun+" removed from your inventory ";
            }
            else item.setReuse( item.getReuse() - 1 );
            return "Using " + noun;
        }
        return noun + " not in your inventory";
    }
    private String processHelping(String noun){
        return "listing commands";
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
            return itemsMap.remove(targetName);
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

    public HashMap<String, Item> getItems() { return items; }

    public void setItems(HashMap<String, Item> items) { this.items = items; }
}