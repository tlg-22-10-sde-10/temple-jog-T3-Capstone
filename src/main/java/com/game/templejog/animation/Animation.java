package com.game.templejog.animation;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Animation extends JPanel implements ActionListener {

    Image icon;
    Timer timer;
    static int xVelocity = 0;
    static int yVelocity = 1;
    int x = 0;
    int y = 200;



    public Animation(Image image) {
        this.setBackground(Color.BLACK);
        this.setOpaque(false);
        this.icon = image;
        timer = new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(icon, x, y, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (y > 200) {
            setxVelocity(-1);
            setyVelocity(0);
        }
        if (x < 0) {
            setxVelocity(0);
            setyVelocity(-1);
        }
        if (y < 100 && x < 100 ) {
            setxVelocity(1);
            setyVelocity(0);
        }
        x = x + xVelocity;
        y = y + yVelocity;
        repaint();
    }


    public void setxVelocity(int xVelocity) {
        Animation.xVelocity = xVelocity;
    }


    public void setyVelocity(int yVelocity) {
        Animation.yVelocity = yVelocity;
    }

    @Override
    public int getX() {
        return x;
    }



    @Override
    public int getY() {
        return y;
    }


}
