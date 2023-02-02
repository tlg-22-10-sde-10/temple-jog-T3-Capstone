import java.util.Map;
import java.util.Scanner;

public class Game {
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

    public void updateScannerString(){
        Scanner scanner = new Scanner(System.in);
        String scannerString = scanner.nextLine();
        setScannerString(scannerString);
    }

    public Boolean isNewRoom(){
        Boolean hasBeenVisited = getCurrentRoom().hasBeenVisited;

        if( hasBeenVisited && )

        return false;
    }

    public void processChoice(String[] choice){
        String verb = choice[0];
        String noun = "";
        if( choice.length > 1 ) noun = choice[1];
        if(verb.equals("quit") || noun.equals("quit")) processQuitting(verb);
        if(verb.equals("go")) processNavigating(noun);
        if(verb.equals("get")) System.out.println("getting "+ noun);
        if(verb.equals("look")) processLooking(noun);
        if(verb.equals("use")) System.out.println("using "+ noun);
        if(verb.equals("help")) System.out.println("helping "+ noun);

    }

// Engines
    private String processQuitting(String noun){
    System.out.println("Are you sure you want to quit?");
    updateScannerString();
    String playerResponse  = getScannerString().toLowerCase().substring(0, 1);
    if( playerResponse.equals("y") ){
        setQuitGame(!getQuitGame());
    }
    return "quitting";
}
    private String processNavigating(String noun){
        System.out.println("going "+ noun);
        String accessableRoom = "";
        String directionValue = getCurrentRoom().checkDirection(noun);
        if( directionValue.length() > 1  ) {
            accessableRoom = directionValue;
            Room validRoom = getRooms().get(accessableRoom);
            setCurrentRoom( validRoom );
            System.out.println("Going to"+ getCurrentRoom().name);
            return accessableRoom;
        };
        System.out.println("cannot go in that direction");
        return accessableRoom;
    }
    private String processLooking(String noun){

        return "";
    }
    private String processGetting(String noun){return "";}
    private String processUsing(String noun){return "";}
    private String processHelping(String noun){return "";}



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