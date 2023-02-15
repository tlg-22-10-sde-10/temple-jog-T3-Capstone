package com.game.templejog.client;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Sound;
import com.game.templejog.Temple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GameUI {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    static JFrame window;
    static Container container;
    static JPanel titleNamePanel, startButtonPanel, quitButtonPanel, mainTextPanel, difficultyPanel, enterPanel;
    static JPanel playerPanel, mainGamePanel, directionalPanel, areaItemPanel, playerInventoryPanel, settings, settingsPanel;
    static JLabel healthLabel, timeLabel, titleLabel;
    static JButton northButton, eastButton, southButton, westButton, item1, item2;
    static JButton startButton, quitButton, choice1, choice2, choice3, enterButton, settingsButton;
    static JTextArea mainTextArea, encounterTextArea;

    static Font titleFont = new Font("Arial", Font.PLAIN, 90);//cutomize font
    static Font standardFont = new Font("Times New Roman", Font.PLAIN, 30);
    static Font smallFont = new Font("Times New Roman", Font.PLAIN, 15);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    static Font gameFont = new Font("Times New Roman", Font.PLAIN, 25);

    static ActionHandler actionHandler = new ActionHandler();
    static Game game;
    static Temple gameFiles;


    public GameUI() {
        window = new JFrame("Temple Jog");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when window is closed
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setVisible(true);
        container = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150); //x and y axis starting top left. width starting from xy axis. height starting from top.
        titleNamePanel.setBackground(Color.BLUE);
        titleLabel = new JLabel("Temple Jog"); //text label
        titleLabel.setForeground(Color.WHITE);// text color
        titleLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(250, 300, 200, 75);
        startButtonPanel.setBackground(Color.GREEN);

        startButton = new JButton("New Game");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(standardFont);
        //functionality of start button
        startButton.setActionCommand("start");
        startButton.addActionListener(actionHandler);
        startButton.setFocusPainted(false);

        quitButtonPanel = new JPanel();
        quitButtonPanel.setBounds(450, 300, 100, 75);
        quitButtonPanel.setBackground(Color.RED);

        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.BLACK);
        quitButton.setForeground(Color.WHITE);
        quitButton.setFont(standardFont);
        quitButton.setFocusPainted(false);

        titleNamePanel.add(titleLabel);
        startButtonPanel.add(startButton);
        quitButtonPanel.add(quitButton);

        container.add(titleNamePanel);
        container.add(startButtonPanel);
        container.add(quitButtonPanel);

        Sound.gameIntro();
        settings = eventPanel(200, 150, 400, 200, "settings");
        settings.setVisible(false);
    }


    public static void difficultyScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        quitButtonPanel.setVisible(false);
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 0, 600, 400);
        mainTextPanel.setBackground(Color.BLACK);
        container.add(mainTextPanel);

        mainTextArea = new JTextArea("Choose your difficulty: ");
        mainTextArea.setBounds(100, 0, 600, 400);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setFont(standardFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        difficultyPanel = new JPanel();
        difficultyPanel.setBounds(250, 300, 300, 150);
        difficultyPanel.setBackground(Color.GREEN);
        difficultyPanel.setLayout(new GridLayout(3, 1));
        container.add(difficultyPanel);

        choice1 = new JButton("choice1");
        choice1.setBackground(Color.BLACK);
        choice1.setForeground(Color.WHITE);
        choice1.setFont(standardFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(actionHandler);
        choice1.setActionCommand("c1");
        difficultyPanel.add(choice1);

        choice2 = new JButton("choice2");
        choice2.setBackground(Color.BLACK);
        choice2.setForeground(Color.WHITE);
        choice2.setFont(standardFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(actionHandler);
        choice2.setActionCommand("c2");
        difficultyPanel.add(choice2);

        choice3 = new JButton("choice3");
        choice3.setBackground(Color.BLACK);
        choice3.setForeground(Color.WHITE);
        choice3.setFont(standardFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(actionHandler);
        choice3.setActionCommand("c3");
        difficultyPanel.add(choice3);
        difficultySettings();
    }

    public static void difficultySettings() {
        mainTextArea.setText("Choose your difficulty: ");
        choice1.setText("Easy");
        choice2.setText("Medium");
        choice3.setText("Hard");
    }

    public static void easyGame() throws InterruptedException, IOException {
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        difficultyPanel.setVisible(false);

        gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        game = new Game(gameFiles);
        String intro = game.getGameText().get("intro");
        mainTextArea.setFont(smallFont);
        mainTextArea.setText(intro);

        enterPanel = new JPanel();
        enterPanel.setBounds(350, 400, 100, 100);
        enterPanel.setBackground(Color.RED);
        container.add(enterPanel);

        enterButton = new JButton("Enter");
        enterButton.setBackground(Color.BLACK);
        enterButton.setForeground(Color.WHITE);
        enterButton.setFont(standardFont);
        enterButton.setFocusPainted(false);
        enterButton.setActionCommand("play");
        enterButton.addActionListener(actionHandler);

        enterPanel.add(enterButton);

    }

    public static void normalGame() {
        mainTextArea.setText("You have chosen: Normal");
    }

    public static void hardGame() {
        mainTextArea.setText("You have chosen: Hard");
    }

    public static void mainGameDisplay() {
        mainTextPanel.setVisible(false);
        enterPanel.setVisible(false);

        mainGamePanel = new JPanel();
        mainGamePanel.setBounds(0, 50, WINDOW_WIDTH, 350);
        mainGamePanel.setBackground(Color.GREEN);
        container.add(mainGamePanel);

        mainTextArea = new JTextArea(String.valueOf(game.getCurrentRoom().getDescription()));
        mainTextArea.setBounds(0, 50, WINDOW_WIDTH, 100);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.GREEN);
        mainTextArea.setFont(gameFont);
        mainTextArea.setLineWrap(true);
        mainGamePanel.add(mainTextArea);

        encounterTextArea = new JTextArea(String.valueOf(game.getCurrentRoom().getEncounters_to()));
        encounterTextArea.setBounds(0, 150, WINDOW_WIDTH, 100);
        encounterTextArea.setBackground(Color.RED);
        encounterTextArea.setForeground(Color.GREEN);
        encounterTextArea.setFont(gameFont);
        encounterTextArea.setLineWrap(true);
        mainGamePanel.add(encounterTextArea);
        encounterTextArea.setVisible(false);

        areaItemPanel = new JPanel();
        areaItemPanel.setBounds(0, 250, WINDOW_WIDTH, 100);
        areaItemPanel.setBackground(Color.BLUE);
        areaItemPanel.setLayout(new GridLayout(1, 4));
        mainGamePanel.add(areaItemPanel);
        areaItemPanel.setVisible(false);

        directionalPanel = new JPanel();
        directionalPanel.setBounds(WINDOW_WIDTH - 200, 400, 150, 150);
        directionalPanel.setBackground(Color.BLACK);
        directionalPanel.setLayout(new GridLayout(4, 1));
        container.add(directionalPanel);

        playerInventoryPanel = new JPanel();
        playerInventoryPanel.setBounds(0, 400, 400, 150);
        playerInventoryPanel.setBackground(Color.WHITE);
        playerInventoryPanel.setLayout(new GridLayout(4, 3));
        container.add(playerInventoryPanel);
        playerInventoryPanel.setVisible(false);


        playerPanel = new JPanel();
        playerPanel.setBounds(0, 0, WINDOW_WIDTH, 50);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1, 4));
        container.add(playerPanel);

        healthLabel = new JLabel("Location: " + game.getCurrentRoom().getName() + "         " +
                "HP: " + game.getPlayer().getHealth());
        healthLabel.setForeground(Color.GREEN);
        healthLabel.setFont(normalFont);

        timeLabel = new JLabel("             TIME: " + time());
        timeLabel.setForeground(Color.GREEN);
        timeLabel.setFont(normalFont);

        playerPanel.add(healthLabel);
        playerPanel.add(timeLabel);

        northButton = new JButton("North");
        northButton.setBackground(Color.red);
        northButton.setForeground(Color.WHITE);
        northButton.addActionListener(actionHandler);
        northButton.setActionCommand("north");
        eastButton = new JButton("East");
        eastButton.setBackground(Color.red);
        eastButton.setForeground(Color.WHITE);
        eastButton.addActionListener(actionHandler);
        eastButton.setActionCommand("east");
        southButton = new JButton("South");
        southButton.setBackground(Color.red);
        southButton.setForeground(Color.WHITE);
        southButton.addActionListener(actionHandler);
        southButton.setActionCommand("south");
        westButton = new JButton("West");
        westButton.setBackground(Color.red);
        westButton.setForeground(Color.WHITE);
        westButton.addActionListener(actionHandler);
        westButton.setActionCommand("west");

        directionalPanel.add(northButton);
        directionalPanel.add(eastButton);
        directionalPanel.add(southButton);
        directionalPanel.add(westButton);

        //Setting options
        settingsPanel = new JPanel();
        settingsPanel.setBounds(500, 500, 50, 50);
        settingsPanel.setBackground(Color.RED);
        container.add(settingsPanel);

        //settings button
        settingsButton = new JButton("settings");
        settingsButton.setBackground(Color.red);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.addActionListener(actionHandler);
        settingsButton.setActionCommand("settings");
        settingsPanel.add(settingsButton);
    }

    public static void eventPanelClose() {
        settings.setVisible(false);
    }

    public static JPanel eventPanel(int x, int y, int width, int height, String target) {
        //this was made to be used as a recyclable function to create game panels
        JPanel panelBuilder = new JPanel();
        panelBuilder.setBounds(x, y, width, height);
        panelBuilder.setBackground(Color.white);
        panelBuilder.setLayout(null);
        panelBuilder.setName(target);
        panelBuilder.setOpaque(true);
        window.add(panelBuilder);
        JButton exitButton = new JButton("X");
        exitButton.setForeground(Color.black);
        //exitButton.setFont(getOldRetro().deriveFont(Font.ITALIC, 15));
        exitButton.setOpaque(false);
        exitButton.setBounds(width - 30, 0, 30, 30);
        exitButton.setBackground(Color.GRAY);
        exitButton.addActionListener(actionHandler);
        exitButton.setActionCommand("close");
        panelBuilder.add(exitButton);
        return panelBuilder;
    }

    public void setSettings(JPanel settings) {
        this.settings = settings;
    }

    public static JPanel getSettings() {
        return settings;
    }

    private static int time() {
        int hoursPlayed = game.getPlayer().getSteps() * 15;
        int hours = hoursPlayed / 60;
        int minutes = hoursPlayed % 60;
        int time = 1200 + (100 * hours) + minutes;
        return time;
    }

    private static String encounterDescription() {
        boolean hasEncounters = !game.getCurrentRoom().getEncounters_to().isEmpty();
        StringBuilder encounterDescription = null;
        if (hasEncounters) {

            for (String encounter : game.getCurrentRoom().getEncounters_to()) {
                encounterDescription = new StringBuilder();
                encounterDescription.append(game.getEncounters().get(encounter).getDescription().toString());
            }
        } else {
            encounterDescription = new StringBuilder().append("nothing here");
        }
        return String.valueOf(encounterDescription);
    }

    public static void showAreaItems() {
        areaItemPanel.removeAll();
        java.util.List<String> itemList = game.getCurrentRoom().getItems();
        if (!itemList.isEmpty()) {
            item1 = new JButton(itemList.get(0));
            item1.setForeground(Color.BLACK);
            item1.setBackground(Color.YELLOW);
            item1.setFont(gameFont);
            item1.addActionListener(actionHandler);
            item1.setActionCommand(itemList.get(0));
            areaItemPanel.add(item1);
            areaItemPanel.setVisible(true);

            try {
                item2 = new JButton(itemList.get(1));
                item2.setForeground(Color.BLACK);
                item2.setBackground(Color.YELLOW);
                item2.setFont(gameFont);
                item2.addActionListener(actionHandler);
                item2.setActionCommand(itemList.get(1));
                areaItemPanel.add(item2);
                areaItemPanel.setVisible(true);
            } catch (Exception e) {
//
            }
        }
    }

    static void pickUpItem(String item) {
        game.processGetting(item);
        updateItemPanel();
    }

    private static void updateItemPanel() {
        playerInventoryPanel.removeAll();
        List<Item> items = game.getPlayer().getInventory();
        for (Item item : items) {
            JButton inventoryItem = new JButton(item.getName());
            inventoryItem.setBackground(Color.WHITE);
            inventoryItem.setForeground(Color.BLACK);
            playerInventoryPanel.add(inventoryItem);
        }
        if (items.size() > 0) {
            playerInventoryPanel.setVisible(true);
        } else playerInventoryPanel.setVisible(false);
        updateGameScreen();


    }

    static void updateGameScreen(String direction) {
        if (game.processNavigating(direction).contains("Traveling")) {
            mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
            encounterTextArea.setText(encounterDescription());
            showAreaItems();

            healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                    "HP: " + game.getPlayer().getHealth());
            timeLabel.setText("             TIME: " + time());
            if (encounterDescription().equals("nothing here")) {
                encounterTextArea.setVisible(false);
            } else {
                encounterTextArea.setVisible(true);
            }
        }
    }

    private static void updateGameScreen() {

        mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
        encounterTextArea.setText(encounterDescription());
        showAreaItems();

        healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                "HP: " + game.getPlayer().getHealth());
        timeLabel.setText("             TIME: " + time());
        if (encounterDescription().equals("nothing here")) {
            encounterTextArea.setVisible(false);
        } else {
            encounterTextArea.setVisible(true);
        }

    }


    public static void main(String[] args) {
        new GameUI();
    }

}
