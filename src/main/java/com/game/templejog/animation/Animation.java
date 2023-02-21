package com.game.templejog.animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Animation extends JPanel implements ActionListener {

    Image icon;
    Image background;
    Timer timer;
    int timeDelay = 100;
    static int xVelocity = 1;
    static int yVelocity = 0;
    int x = 0;
    int y = 0;


    public Animation() {
        this.setBackground(Color.BLACK);
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("car.jpg")){
            //noinspection ConstantConditions
            icon = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        icon = new ImageIcon("src/main/resources/car.jpg").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    public Animation(Image image) {
        this.setBackground(Color.BLACK);
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
        if (x > 200) {
            setxVelocity(0);
            setyVelocity(1);
        }
        if (y > 200) {
            setxVelocity(-1);
            setyVelocity(0);
        }
        if (x < 0) {
            setxVelocity(0);
            setyVelocity(-1);
        }
        if (y < 0 && x < 100 ) {
            setxVelocity(1);
            setyVelocity(0);
        }
        x = x + xVelocity;
        y = y + yVelocity;
        repaint();
    }
    public void visibility() {
        this.setVisible(false);
    }





    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getTimeDelay() {
        return timeDelay;
    }

    public void setTimeDelay(int timeDelay) {
        this.timeDelay = timeDelay;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }





}
