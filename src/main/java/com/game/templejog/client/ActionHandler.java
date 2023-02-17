package com.game.templejog.client;

import com.game.templejog.animation.Animation;

import com.game.templejog.Game;
import com.game.templejog.Sound;

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
                    GameUI.getSettings().setVisible(false);
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
            case "ball o yarn":
                GameUI.pickUpItem(userChoice);
                GameUI.getSettings().setVisible(false);
                break;
            case "use desert eagle":
            case "use grenade":
            case "use machete":
            case "use flamethrower":
            case "use key":
            case "use crystal femur":
            case "use ball o yarn":
                GameUI.useItem(userChoice);
                GameUI.getSettings().setVisible(false);
                break;
            case "close":
                GameUI.eventPanelClose();
                break;
            case "settings":
                if (GameUI.getSettings().isVisible()) {
                    GameUI.getSettings().setVisible(false);
                } else {
                    GameUI.getSettings().setVisible(true);
                    break;

                }
            case "volume up":
                Sound.volumeUp();
                break;
            case "volume down":
                Sound.volumeDown();
                break;
            case "toggle sound":
                if (GameUI.getMusicStatus().getSelectedItem().equals("ON")) {
                    if (Game.getPlaySound().equals(false)) {
                        Game.setPlaySound(true);
                        String currentRoomSound = Game.getCurrentRoom().getSound();
                        Sound.themeSound(currentRoomSound);
                    }
                } else if (GameUI.getMusicStatus().getSelectedItem().equals("OFF")) {
                    Sound.stopSound();
                    Game.setPlaySound(false);
                }

                break;

        }
    }
}
