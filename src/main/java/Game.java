import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {
// MODEL
    Boolean quitGame;
    String scannerString;
    Map<String, Room> rooms;
    Map<String, Encounter> encounters;
    Player player;
    Room currentRoom;

    public Game(Player player, Map rooms, Map encounters){
        this.quitGame = false;
        this.player = player;
        this.rooms = rooms;
        this.encounters = encounters;
        setCurrentRoom(getRooms().get("room01"));
    }

    public String processChoice(String[] choice){
        String verb = choice[0];
        String noun = verb;
        if( choice.length > 1 ) noun = choice[1];
        if(verb.equals("quit") || noun.equals("quit")) return processQuitting( verb );
        if(verb.equals("go")) return processNavigating( noun );
        if(verb.equals("get")) return processGetting( noun );
        if(verb.equals("look")) return processLooking( noun );
        if(verb.equals("use")) return processUsing( noun );
        if(verb.equals("help")) return processHelping( noun );
        return "";
    }

// CONTROLLERS
//    perhaps 1 for state change 0 for no change
    private String processQuitting(String noun){
    System.out.println("Are you sure you want to quit?");
    updateScannerString();
    String playerResponse  = getScannerString().toLowerCase().substring(0, 1);
    if( playerResponse.equals("y") ){
        setQuitGame(!getQuitGame());
    }
    return noun;
}
    private String processNavigating(String noun){
        System.out.println("going "+ noun);
        String accessableRoom = "";
        String directionValue = getCurrentRoom().checkDirection(noun);
        if( directionValue.length() > 1  ) {
            accessableRoom = directionValue;
            Room validRoom = getRooms().get(accessableRoom);
            validRoom.setHasBeenVisited(!validRoom.getHasBeenVisited());
            setCurrentRoom( validRoom );
            System.out.println("Going to"+ getCurrentRoom().name);
            return accessableRoom;
        };
        System.out.println("cannot go in that direction");
        return accessableRoom;
    }
    private String processLooking(String noun){
        // if room has item
        // description of item
        if( roomHasNoun(noun) ) return "description of " + noun;
        else return " not found " + noun;
    }
    private String processGetting(String noun){
        // if present in room
        // add to inventory
        // remove item from room
        if( roomHasNoun( noun )) {
            Boolean isAdded = getPlayer().addToInventory(noun);
            if( isAdded ) {
                getCurrentRoom().getItems().remove(noun);
                return noun + "added to inventory";
            }

        };
        return noun+" not found";
    }
    private String processUsing(String noun){
        // if in inventory
        // use item , then dispose of empty

        if( getPlayer().getInventory().contains( noun ) ){
            // pop if single use
            // update number of uses

            getPlayer().removeFromInventory(noun);
            // some action
            return "Using " + noun;
        }
        return noun + "not used";
    }
    private String processHelping(String noun){
        System.out.println("listing commands...");
        return "";
    }

//  Helper Methods
    public void updateScannerString(){
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        setScannerString(scannerString);
    }
    public Boolean roomHasNoun(String noun){
        Boolean foundNoun = false;
        List<String> tempItems = getCurrentRoom().getItems();
        tempItems.addAll(getCurrentRoom().getEncounters_to());
        tempItems.addAll(getCurrentRoom().getEncounters_from());
        return  ( tempItems.contains(noun) )? !foundNoun: foundNoun;
    }

//    ACCESSOR METHODS
    public Room getCurrentRoom() { return currentRoom;}
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom;}
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public Map<String, Room> getRooms() { return rooms; }
    public void setRooms(Map<String, Room> rooms) { this.rooms = rooms; }

    public Boolean getQuitGame() { return quitGame; }
    public void setQuitGame(Boolean quitGame) { this.quitGame = quitGame; }
    public String getScannerString() {return scannerString;}
    public void setScannerString(String scannerString) { this.scannerString = scannerString;}
}