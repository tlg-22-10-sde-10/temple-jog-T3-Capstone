package com.game.templejog.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.templejog.*;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

// ENTRY
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface.displaySetup();
        scanner.nextLine();
        ConsoleInterface console = new ConsoleInterface();

        clearScreen();
        ConsoleInterface.displayTitle();
        String playerInput = "";
        while(playerInput.isEmpty()){
            System.out.println("Start a new Game? y/n");
            playerInput = scanner.nextLine();
        }
        playerInput = playerInput.toLowerCase().substring(0, 1);



// LOAD GAME
        if (playerInput.equals("y")) {
            HashMap<String, Room> roomsMap = new HashMap<>();
            HashMap<String, Encounter> encountersMap = new HashMap<>();
            HashMap<String, Item> itemsMap = new HashMap<>();
// PARSE JSON -> CLASS
            InputStream jsonFile =  Main.class.getResourceAsStream("/JSON/maps.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonFile);
            for (JsonNode rm : root.get("easymap")) {
                Room roomObj = objectMapper.treeToValue(rm, Room.class);
                roomsMap.put(roomObj.getNumber() >= 10 ? ("room" + roomObj.getNumber()) : ("room0" + roomObj.getNumber()), roomObj);
            }
            for (JsonNode encounter : root.get("encounters")) {
                Encounter encounterObj = objectMapper.treeToValue(encounter, Encounter.class);
                encountersMap.put(encounterObj.getName(), encounterObj);
            }
            for (JsonNode item : root.get("items")){
                Item itemObj = objectMapper.treeToValue(item, Item.class);
                itemsMap.put(itemObj.getName(),itemObj);
            }

            Game game = new Game(new Player(), roomsMap, encountersMap, itemsMap);
            console.setGame(game);
            clearScreen();

            console.displayIntro();
            scanner.nextLine();
            clearScreen();
// GAME LOOP
            do {
                clearScreen();
                console.displayScene();
                System.out.print("What do you want to do? go,look,get,use,quit,help\n>");
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                System.out.println(game.processChoice(choice));
                System.out.println("Press <ENTER> key when ready...");
                scanner.nextLine();
            } while ( !game.getQuitGame() );
        }
        System.out.println("Good Bye");

    }

    private static void clearScreen () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}