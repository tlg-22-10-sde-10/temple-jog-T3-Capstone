import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        clearScreen();
// ENTRY
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(TitleScreen.displaySetup());
//        scanner.nextLine();
//        clearScreen();
//        System.out.println(TitleScreen.displayTitle());
//        System.out.println("Start Game? y/n");
//        String playerInput = scanner.nextLine();
//        playerInput = playerInput.toLowerCase().substring(0,1);

//        if( playerInput.equals("y") ){
//
            HashMap<Object, Object> roomsMap = new HashMap<>();
            HashMap<Object, Object> encountersMap = new HashMap<>();
// PARSE JSON -> CLASS
            File jsonFile = new File("src/maps.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonFile);
            for( JsonNode rm : root.get("easymap") ) {
                Room roomObj = objectMapper.treeToValue(rm, Room.class);
                roomsMap.put(roomObj.number >= 10 ?  ("room" + roomObj.number) : ("room0" + roomObj.number), roomObj);
            }
            for( JsonNode encounter : root.get("encounters") ){
                Encounter encounterObj = objectMapper.treeToValue(encounter, Encounter.class);
                encountersMap.put(encounterObj.name,encounterObj);
            }


// LOAD GAME
            Game game = new Game( new Player(), roomsMap, encountersMap );

            do{
                System.out.println("*"+game.getCurrentRoom().name);
                System.out.println("What do you want to do");
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                game.processChoice(choice);

            }while(!game.quitGame);
//        }
//        else System.out.println("Good Bye");
//        System.out.println("Game End");
//        clearScreen();
    }

    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}