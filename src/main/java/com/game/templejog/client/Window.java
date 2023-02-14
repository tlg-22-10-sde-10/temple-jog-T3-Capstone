package com.game.templejog.client;

import com.game.templejog.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
    private int windowSize = 600;
    private JFrame window;
    private Container container;
    private JPanel titlePanel, startButtonPanel, introPanel;
    private JLabel titleText;
    private JPanel playerPanel, mainGamePanel, directionalPanel;
    private JLabel healthLabel, healthValue, timeLabel, timeValue;
    private JButton startButton, northButton, eastButton, southButton, westButton;
    private JTextArea introTextArea, mainTextArea;
    private IntroHandler introHandler = new IntroHandler();
    private Player playerOne = new Player();




    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 75);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font introFont = new Font("Times New Roman", Font.PLAIN, 70);


    public Window() {

        window = new JFrame();
        window.setSize(windowSize,windowSize);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        titlePanel = new JPanel();
        titlePanel.setBounds(50,150,500,150);
        titlePanel.setBackground(Color.BLACK);
        titleText = new JLabel("TEMPLE JOG");
        titleText.setForeground(Color.GREEN);
        titleText.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(250, 400, 100,50);
        startButtonPanel.setBackground(Color.BLACK);

        startButton = new JButton("START");
        startButton.setForeground(Color.GREEN);
        startButton.setBackground(Color.BLACK);
        startButton.setFont(normalFont);
        startButton.addActionListener(introHandler);

        startButtonPanel.add(startButton);
        titlePanel.add(titleText);
        container.add(titlePanel);
        container.add(startButtonPanel);
    }

    public void createIntroScreen() throws InterruptedException {
        titlePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        introPanel = new JPanel();
        introPanel.setBounds(150,150,300,300);
        introPanel.setBackground(Color.GREEN);
        container.add(introPanel);
        introTextArea = new JTextArea("Welcome To\nTemple Jog\n\n\n\n\n the game will begin shortly...");
        introTextArea.setBounds(150,150,300,150);
        introTextArea.setBackground(Color.BLACK);
        introTextArea.setForeground(Color.GREEN);
        introTextArea.setFont(normalFont);
        introTextArea.setLineWrap(true);
        introPanel.add(introTextArea);
//        Thread.sleep(5000);
        mainGameDisplay();
    }


    public void mainGameDisplay()  {

        introPanel.setVisible(false);
        mainGamePanel = new JPanel();
        mainGamePanel.setBounds(1,50,598,150);
        mainGamePanel.setBackground(Color.GREEN);
        container.add(mainGamePanel);

        mainTextArea = new JTextArea("Welcome To\nTemple Jog\n the game will begin shortly...");
        mainTextArea.setBounds(1,1,598,150);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.GREEN);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainGamePanel.add(mainTextArea);

        directionalPanel = new JPanel();
        directionalPanel.setBounds(375,350,150,150);
        directionalPanel.setBackground(Color.BLACK);
        directionalPanel.setLayout(new GridLayout(4,1));
        container.add(directionalPanel);

        playerPanel = new JPanel();
        playerPanel.setBounds(1,10, 598,40);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1,4));
        container.add(playerPanel);

        healthLabel = new JLabel("HP: ");
        healthLabel.setForeground(Color.GREEN);
        healthLabel.setFont(normalFont);
        healthValue = new JLabel(String.valueOf(playerOne.getHealth()));
        healthValue.setForeground(Color.GREEN);
        healthValue.setFont(normalFont);

        timeLabel = new JLabel("TIME: ");
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setFont(normalFont);
        timeValue = new JLabel(String.valueOf(playerOne.getSteps()));
        timeValue.setForeground(Color.GREEN);
        timeValue.setFont(normalFont);

        playerPanel.add(healthLabel);
        playerPanel.add(healthValue);
        playerPanel.add(timeLabel);
        playerPanel.add(timeValue);

        northButton = new JButton("North");
        northButton.setBackground(Color.red);
        northButton.setForeground(Color.WHITE);
        eastButton = new JButton("East");
        eastButton.setBackground(Color.red);
        eastButton.setForeground(Color.WHITE);
        southButton = new JButton("South");
        southButton.setBackground(Color.red);
        southButton.setForeground(Color.WHITE);
        westButton = new JButton("West");
        westButton.setBackground(Color.red);
        westButton.setForeground(Color.WHITE);

        directionalPanel.add(northButton);
        directionalPanel.add(eastButton);
        directionalPanel.add(southButton);
        directionalPanel.add(westButton);
    }

    public class IntroHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                createIntroScreen();
            } catch (InterruptedException ex) {}
        }
    }

    public static void main(String[] args){
        Window test = new Window();


    }
}


