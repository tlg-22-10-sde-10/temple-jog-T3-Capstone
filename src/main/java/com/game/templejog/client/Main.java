package com.game.templejog.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.templejog.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {

// ENTRY
        ConsoleInterface.clearScreen();
        Scanner scanner = new Scanner(System.in);
        ConsoleInterface.displaySetup();
        scanner.nextLine();
        ConsoleInterface console = new ConsoleInterface();

        ConsoleInterface.clearScreen();
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
            InputStream jsonFile =  Main.class.getClassLoader().getResourceAsStream("JSON/maps.json");
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
            Sound.gameSound(scanner, game); //extracted method
            console.setGame(game);
            ConsoleInterface.clearScreen();

            console.displayIntro();
            scanner.nextLine();
            ConsoleInterface.clearScreen();

/* Stop the background music when entering landing zone */
            if(game.getPlaySound()){
                Sound.stopSound();
                Sound.themeSound("sounds/landing_zone.wav");
            }
// GAME LOOP
            do {
                ConsoleInterface.clearScreen();
                console.displayScene();
                System.out.print("What do you want to do?\n>");
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                ConsoleInterface.clearScreen();
                console.displayResult(game.processChoice(choice));
            } while ( !game.getQuitGame()
                    && game.getPlayer().getSteps() < 24
                    && game.getPlayer().getHealth() > 0);
        }
    }

//    private static void gameSound(Scanner scanner, Game game) {
//        System.out.println("Do you want the music on? [y/n]"); //Music on or off functionality
//        String musicOn = scanner.nextLine();
//        if (musicOn.equalsIgnoreCase("y")){
//            game.setPlaySound(true);
//            Sound.themeSound("sounds/background_music.wav");
//        } else {
//            game.setPlaySound(false);
//        }
//    }

}