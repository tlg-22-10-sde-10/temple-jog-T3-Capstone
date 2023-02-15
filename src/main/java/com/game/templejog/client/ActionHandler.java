package com.game.templejog.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ActionHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = e.getActionCommand();
        List<String> navList = Arrays.asList(new String[]{"north", "east", "south", "west"});
        boolean direction = navList.contains(userChoice);

        switch (userChoice) {
            case "start":
                GameUI.difficultyScreen();
                break;
            case "c1":
                try {
                    GameUI.easyGame();
                } catch (InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "c2":
                GameUI.normalGame();
                break;
            case "c3":
                GameUI.hardGame();
                break;
            case "play":
                GameUI.mainGameDisplay();
                break;
            case "north":
            case "east":
            case "south":
            case "west":
                try {
                    GameUI.updateGameScreen(userChoice);
                } catch (Exception event) {
                    throw new RuntimeException(event);
                }
                break;
            case "desert eagle":
            case "grenade":
            case "machete":
            case "flamethrower":
            case "key":
            case "crystal femur":
            case "ball O yarn":
                GameUI.pickUpItem(userChoice);
                break;
            case "close":
                GameUI.eventPanelClose();
                break;
            case "settings":
                GameUI.getSettings().setVisible(true);
                break;

        }

    }}
