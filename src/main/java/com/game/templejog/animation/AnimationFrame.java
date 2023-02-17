package com.game.templejog.animation;

import com.game.templejog.client.Window;

import javax.swing.*;
import java.awt.*;

public class AnimationFrame extends JFrame {

    static Animation panel;
    static JButton exitButton;
    static JPanel j;
    int xVelocity;
    int yVelocity;

    public AnimationFrame() {
        panel = new Animation();
        exitButton = new JButton("exit");
        exitButton.setBackground(Color.BLUE);
        exitButton.setForeground(Color.WHITE);
        this.setSize(1000,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(exitButton);
        this.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);
    }
   public AnimationFrame(int xVelocity, int yVelocity) {
        panel = new Animation();
        panel.setxVelocity(xVelocity);
        panel.setyVelocity(yVelocity);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        AnimationFrame test = new AnimationFrame(1,0);
        Thread.sleep(5000);

        panel.setyVelocity(1);
        panel.setxVelocity(0);
        Thread.sleep(5000);

        panel.setyVelocity(0);
        panel.setxVelocity(-1);
        Thread.sleep(5000);

        panel.setyVelocity(-1);
        panel.setxVelocity(0);


    }
}
