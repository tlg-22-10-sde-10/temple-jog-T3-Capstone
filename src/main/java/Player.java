import java.util.ArrayList;
import java.util.List;

public class Player {
    Room currentRoom;
    Integer health, steps;
    List<String> inventory = new ArrayList<>();

    /*              CONSTRUCTORS                    */
    public Player() {
        health = 5;
        steps = 0;
    }
}