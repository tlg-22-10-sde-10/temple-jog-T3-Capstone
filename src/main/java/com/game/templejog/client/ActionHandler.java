package com.game.templejog.client;

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
        List<String> navList = Arrays.asList("north", "east", "south", "west");

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
                try {
                    GameUI.mediumGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                break;
            case "c3":
                try {
                    GameUI.hardGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
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
                    GameUI.getHelpeventPanel().setVisible(false);
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
            case "close settings":
            case "close help":
            case "close getMap":
                GameUI.eventPanelClose(userChoice);
                GameUI.getAreaItemPanel().setVisible(true);
                GameUI.getPlayerInventoryPanel().setVisible(true);
                GameUI.getDirectionalPanel().setVisible(true);
                GameUI.getMainGamePanel().setVisible(true);
                break;
            case "settings":
                if (GameUI.getSettings().isVisible()) {
                    GameUI.getSettings().setVisible(false);
                    GameUI.getAreaItemPanel().setVisible(true);
                    GameUI.getPlayerInventoryPanel().setVisible(true);
                    GameUI.getDirectionalPanel().setVisible(true);
                    GameUI.getMainGamePanel().setVisible(true);
                } else {
                    GameUI.getSettings().setVisible(true);
                    GameUI.getAreaItemPanel().setVisible(false);
                    GameUI.getPlayerInventoryPanel().setVisible(false);
                    GameUI.getDirectionalPanel().setVisible(false);
                    GameUI.getMainGamePanel().setVisible(false);
                }
                break;
            case "help":
                if (GameUI.getHelpeventPanel().isVisible()) {
                    GameUI.getHelpeventPanel().setVisible(false);
                    GameUI.getAreaItemPanel().setVisible(true);
                    GameUI.getPlayerInventoryPanel().setVisible(true);
                    GameUI.getDirectionalPanel().setVisible(true);
                    GameUI.getMainGamePanel().setVisible(true);
                } else {
                    GameUI.getHelpeventPanel().setVisible(true);
                    GameUI.getAreaItemPanel().setVisible(false);
                    GameUI.getPlayerInventoryPanel().setVisible(false);
                    GameUI.getDirectionalPanel().setVisible(false);
                    GameUI.getMainGamePanel().setVisible(false);

                }
                break;
            case "getMap":
                if (GameUI.getMapPanel().isVisible()) {
                    GameUI.getMapPanel().setVisible(false);
                    GameUI.getAreaItemPanel().setVisible(true);
                    GameUI.getPlayerInventoryPanel().setVisible(true);
                    GameUI.getDirectionalPanel().setVisible(true);
                    GameUI.getMainGamePanel().setVisible(true);
                } else {
                    GameUI.getMapPanel().setVisible(true);
                    GameUI.getAreaItemPanel().setVisible(false);
                    GameUI.getPlayerInventoryPanel().setVisible(false);
                    GameUI.getDirectionalPanel().setVisible(false);
                    GameUI.getMainGamePanel().setVisible(false);
                }
                break;
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
            case "toggle fx":
                Sound.setSoundFx(GameUI.getSoundFXStatus().getSelectedItem().equals("ON"));
                break;
        }
    }
}

