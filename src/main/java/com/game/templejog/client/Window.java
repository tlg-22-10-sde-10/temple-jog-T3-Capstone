package com.game.templejog.client;

import com.game.templejog.Game;
import com.game.templejog.Player;
import com.game.templejog.Temple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window {
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 600;
    private final JFrame window;
    private Container container;
    private JPanel titlePanel, startButtonPanel, introPanel;
    private JLabel titleText;
    private JPanel playerPanel, mainGamePanel, directionalPanel;
    private JLabel healthLabel, timeLabel;
    private JButton startButton, northButton, eastButton, southButton, westButton;
    private JTextArea introTextArea, mainTextArea;
    private IntroHandler introHandler = new IntroHandler();
    private NavigationHandler navigationHandler = new NavigationHandler();
    private static Player playerOne = new Player();
    private static Game game;
    private static Temple gameFiles;




    private Font titleFont = new Font("Times New Roman", Font.PLAIN, 75);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    private Font gameFont = new Font("Times New Roman", Font.PLAIN, 25);


    public Window() throws IOException {

        window = new JFrame();
        window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
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

    public void createIntroScreen() throws InterruptedException, IOException {
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
        gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        game = new Game(gameFiles);
        mainGameDisplay();
    }


    public void mainGameDisplay()  {

        introPanel.setVisible(false);
        mainGamePanel = new JPanel();
        mainGamePanel.setBounds(1,50,598,200);
        mainGamePanel.setBackground(Color.GREEN);
        container.add(mainGamePanel);

        mainTextArea = new JTextArea(String.valueOf(game.getCurrentRoom().getDescription()));
        mainTextArea.setBounds(1,1,598,150);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.GREEN);
        mainTextArea.setFont(gameFont);
        mainTextArea.setLineWrap(true);
        mainGamePanel.add(mainTextArea);

        directionalPanel = new JPanel();
        directionalPanel.setBounds(375,350,150,150);
        directionalPanel.setBackground(Color.BLACK);
        directionalPanel.setLayout(new GridLayout(4,1));
        container.add(directionalPanel);

        playerPanel = new JPanel();
        playerPanel.setBounds(5,10, 600,40);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1,4));
        container.add(playerPanel);

        healthLabel = new JLabel("Location: " + game.getCurrentRoom().getName() + "         " +
                "HP: " + game.getPlayer().getHealth());
        healthLabel.setForeground(Color.GREEN);
        healthLabel.setFont(normalFont);
//        healthValue = new JLabel(String.valueOf(playerOne.getHealth()));
//        healthValue.setForeground(Color.GREEN);
//        healthValue.setFont(normalFont);

        timeLabel = new JLabel("             TIME: " + time());
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setFont(normalFont);
//        timeValue = new JLabel(String.valueOf(playerOne.getSteps()));
//        timeValue.setForeground(Color.GREEN);
//        timeValue.setFont(normalFont);

        playerPanel.add(healthLabel);
//        playerPanel.add(healthValue);
        playerPanel.add(timeLabel);
//        playerPanel.add(timeValue);

        northButton = new JButton("North");
        northButton.setBackground(Color.red);
        northButton.setForeground(Color.WHITE);
        northButton.addActionListener(navigationHandler);
        northButton.setActionCommand("north");
        eastButton = new JButton("East");
        eastButton.setBackground(Color.red);
        eastButton.setForeground(Color.WHITE);
        eastButton.addActionListener(navigationHandler);
        eastButton.setActionCommand("east");
        southButton = new JButton("South");
        southButton.setBackground(Color.red);
        southButton.setForeground(Color.WHITE);
        southButton.addActionListener(navigationHandler);
        southButton.setActionCommand("south");
        westButton = new JButton("West");
        westButton.setBackground(Color.red);
        westButton.setForeground(Color.WHITE);
        westButton.addActionListener(navigationHandler);
        westButton.setActionCommand("west");

        directionalPanel.add(northButton);
        directionalPanel.add(eastButton);
        directionalPanel.add(southButton);
        directionalPanel.add(westButton);
    }

    private int time() {
        int hoursPlayed = game.getPlayer().getSteps() * 15;
        int hours = hoursPlayed / 60;
        int minutes = hoursPlayed % 60;
        int time = 1200 + (100 * hours) + minutes;
        return time;
    }

    public class IntroHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                createIntroScreen();
            } catch (InterruptedException | IOException ex) {}
        }
    }

    public class NavigationHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                String direction = event.getActionCommand();

                switch (direction) {
                    case "north":
                        break;
                    case "east":
                        break;
                    case "south":
                        break;
                    case "west":
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }


    public static void main(String[] args) throws IOException {
        Window test = new Window();


    }
}


