package com.game.templejog.client;

import com.game.templejog.*;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

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
            System.out.println(UserInput.START_GAME.getUserPrompt());
            playerInput = scanner.nextLine();
        }
        playerInput = playerInput.toLowerCase().substring(0, 1);

// LOAD GAME
        if (playerInput.equals("y")) {
            Temple gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
            Game game = new Game(gameFiles);
            console.setGame(game);
            playerInput = "";
            do {
                System.out.println(UserInput.DIFFICULTY_LEVEL.getUserPrompt());
                playerInput = scanner.nextLine();

                if(TextParser.parseText(playerInput)[0].equals("quit")) {
                    System.out.println("Quitting... ");
                    exit(0);
                }
                playerInput = TextParser.parseDifficulty(playerInput);
            } while(playerInput.equals(""));
            game.processDifficulty(playerInput);
            //Sound
            Sound.gameSound(scanner, game);
// Play intro
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
                System.out.print(UserInput.USER_ACTION.getUserPrompt());
                game.updateScannerString();
                String[] choice = TextParser.parseText(game.getScannerString());
                ConsoleInterface.clearScreen();
                console.displayResult(game.processChoice(choice),0);
            } while ( !game.getQuitGame()
                    && game.getPlayer().getSteps() < 24
                    && game.getPlayer().getHealth() > 0
                    && !(game.getCommunicatorOff() && game.getCurrentRoom().getName().equalsIgnoreCase("landing zone")));
            ConsoleInterface.clearScreen();
            if(!game.getQuitGame()) {
                console.displayEnding();
            } else {
                Sound.stopSound();
                ConsoleInterface.clearScreen();
                console.displayResult("Thank you for playing!",1);
            }
        }
    }

}