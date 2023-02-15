package com.game.templejog.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreenHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        GameUI.difficultyScreen();
    }
}

