import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

// ENTRY
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println(TitleScreen.displaySetup());
        scanner.nextLine();

        clearScreen();
        System.out.println(TitleScreen.displayTitle());
        System.out.println("Start Game? y/n");
        String playerInput = scanner.nextLine();
        playerInput = playerInput.toLowerCase().substring(0, 1);
        clearScreen();

        HashMap<String, Room> roomsMap = new HashMap<>();
        HashMap<String, Encounter> encountersMap = new HashMap<>();

// PARSE JSON -> CLASS
        File jsonFile = new File("src/maps.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jsonFile);
        for (JsonNode rm : root.get("easymap")) {
            Room roomObj = objectMapper.treeToValue(rm, Room.class);
            roomsMap.put(roomObj.number >= 10 ? ("room" + roomObj.number) : ("room0" + roomObj.number), roomObj);
        }
        for (JsonNode encounter : root.get("encounters")) {
            Encounter encounterObj = objectMapper.treeToValue(encounter, Encounter.class);
            encountersMap.put(encounterObj.name, encounterObj);
        }

// LOAD GAME
        if (playerInput.equals("y")) {
            // LOAD GAME
            Game game = new Game(new Player(), roomsMap, encountersMap);
            //TEMP new TitleScreen(game)
            clearScreen();
            TitleScreen.displayIntro();
            scanner.nextLine();
            clearScreen();
            // WELCOME
            do {
                //TEMP TitleScreen.displayScene()
                // - items , player info, monster info
                System.out.println("*" + game.getCurrentRoom().name + "    Items:"+ game.getPlayer().inventory.toString());
                System.out.println("What do you want to do? go,look,get,use,quit,help");
                //
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                System.out.println(game.processChoice(choice));
                System.out.println("Press any key when ready...");
                scanner.nextLine();
            } while ( !game.quitGame );
        }
        else System.out.println("Good Bye");

    }
    private static void clearScreen () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}