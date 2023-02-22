package com.game.templejog.client;

import javax.swing.*;

public class MainUI {
    public static void main(String[] args) throws InterruptedException {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }
        new GameUI();
    }
}
