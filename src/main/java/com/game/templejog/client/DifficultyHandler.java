package com.game.templejog.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DifficultyHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String userChoice = e.getActionCommand();

        switch (userChoice) {
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
        }
    }
}
