package com.game.templejog.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ActionHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = e.getActionCommand();

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
                break;
            case "east":
                break;
            case "south":
                break;
            case "west":
                break;
        }
    }

    }
