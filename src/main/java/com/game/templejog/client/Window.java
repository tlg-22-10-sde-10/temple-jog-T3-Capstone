package com.game.templejog.client;
import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Temple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Window {
    private final int WINDOW_HEIGHT = 600;
    private final int WINDOW_WIDTH = 800;
    private final JFrame window;
    private Container container;
    private JPanel titlePanel, startButtonPanel, introPanel;
    private JLabel titleText;
    private JPanel playerPanel, mainGamePanel, directionalPanel, areaItemPanel, playerInventoryPanel;
    private JLabel healthLabel;
    private JButton startButton, northButton, eastButton, southButton, westButton;
    private JTextArea introTextArea, mainTextArea, encounterTextArea, itemTextArea;
    private IntroHandler introHandler = new IntroHandler();
    private NavigationHandler navigationHandler = new NavigationHandler();
    private ItemPickUpHandler itemHandler = new ItemPickUpHandler();
    private ItemUseHandler useHandler = new ItemUseHandler();
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
        mainGamePanel.setBounds(0,50,WINDOW_WIDTH,350);
        mainGamePanel.setBackground(Color.GREEN);
        container.add(mainGamePanel);

        mainTextArea = new JTextArea(String.valueOf(game.getCurrentRoom().getDescription()));
        mainTextArea.setBounds(0,50,WINDOW_WIDTH,100);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setForeground(Color.GREEN);
        mainTextArea.setFont(gameFont);
        mainTextArea.setLineWrap(true);
        mainGamePanel.add(mainTextArea);

        encounterTextArea = new JTextArea(String.valueOf(game.getCurrentRoom().getEncounters_to()));
        encounterTextArea.setBounds(0,150, WINDOW_WIDTH, 100);
        encounterTextArea.setBackground(Color.RED);
        encounterTextArea.setForeground(Color.GREEN);
        encounterTextArea.setFont(gameFont);
        encounterTextArea.setLineWrap(true);
        mainGamePanel.add(encounterTextArea);
        encounterTextArea.setVisible(false);

        areaItemPanel = new JPanel();
        areaItemPanel.setBounds(0,250,WINDOW_WIDTH,100);
        areaItemPanel.setBackground(Color.BLUE);
        areaItemPanel.setLayout(new GridLayout(1,4));
        mainGamePanel.add(areaItemPanel);
        areaItemPanel.setVisible(false);

        directionalPanel = new JPanel();
        directionalPanel.setBounds(WINDOW_WIDTH - 200,400,150,150);
        directionalPanel.setBackground(Color.BLACK);
        directionalPanel.setLayout(new GridLayout(4,1));
        container.add(directionalPanel);

        playerInventoryPanel = new JPanel();
        playerInventoryPanel.setBounds(0, 400, 400,150);
        playerInventoryPanel.setBackground(Color.WHITE);
        playerInventoryPanel.setLayout(new GridLayout(4,3));
        container.add(playerInventoryPanel);
        playerInventoryPanel.setVisible(false);


        playerPanel = new JPanel();
        playerPanel.setBounds(0,0, WINDOW_WIDTH,50);
        playerPanel.setBackground(Color.BLACK);
        playerPanel.setLayout(new GridLayout(1,4));
        container.add(playerPanel);

        healthLabel = new JLabel("Location: " + game.getCurrentRoom().getName() + "         " +
                "HP: " + game.getPlayer().getHealth() + "             TIME: " + time());
        healthLabel.setForeground(Color.GREEN);
        healthLabel.setFont(normalFont);

//        timeLabel = new JLabel(;
//        timeLabel.setForeground(Color.GREEN);
//        timeLabel.setFont(normalFont);

        playerPanel.add(healthLabel);
//        playerPanel.add(timeLabel);

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

    private String encounterDescription() {
        boolean hasEncounters = !game.getCurrentRoom().getEncounters_to().isEmpty();
        StringBuilder encounterDescription = null;
        if (hasEncounters) {
            for (String encounter : game.getCurrentRoom().getEncounters_to()) {
                encounterDescription = new StringBuilder();
                encounterDescription.append(game.getEncounters().get(encounter).getDescription().toString());
            }
        }
        else {
            encounterDescription = new StringBuilder().append("nothing here");
        }
        return String.valueOf(encounterDescription);
    }

    public void showAreaItems() {
        areaItemPanel.removeAll();
        List<String> itemList = game.getCurrentRoom().getItems();
        if (!itemList.isEmpty()) {
            for (String item : itemList) {
                JButton areaItem = new JButton(item);
                areaItem.setForeground(Color.BLACK);
                areaItem.setBackground(Color.YELLOW);
                areaItem.setFont(gameFont);
                areaItem.addActionListener(itemHandler);
                areaItem.setActionCommand(item);
                areaItemPanel.add(areaItem);
                areaItemPanel.setVisible(true);
            }
        }
    }

    private void pickUpItem(String item) {
        game.processGetting(item);
        updateItemPanel();
    }

    private void updateItemPanel() {
        playerInventoryPanel.removeAll();
        List<Item> items = game.getPlayer().getInventory();
        for (Item item : items) {
            JButton inventoryItem = new JButton(item.getName());
            inventoryItem.setBackground(Color.WHITE);
            inventoryItem.setForeground(Color.BLACK);
            inventoryItem.addActionListener(useHandler);
            playerInventoryPanel.add(inventoryItem);
            if (item.getReuse() < 1) {
                inventoryItem.setVisible(false);
            }
            if (items.size() > 0) {
                playerInventoryPanel.setVisible(true);
            }
            else playerInventoryPanel.setVisible(true);
            updateGameScreen();

        }
    }
    private void useItem(String item) {
        String action = game.processUsing(item);

        if (action.contains("is EFFECTIVE against")) {
            encounterTextArea.setText(action);
        }
        else if (action.contains("Success!!!")) {
            encounterTextArea.setText(action);
        }
        updateItemPanel();

        }


    private void updateGameScreen(String direction) {
        if (game.processNavigating(direction).contains("Traveling")) {
            mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
            encounterTextArea.setText(encounterDescription());
            showAreaItems();

            healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                    "HP: " + game.getPlayer().getHealth()+ "             TIME: " + time());
            if (encounterDescription().equals("nothing here")) {
                encounterTextArea.setVisible(false);
            }
            else {
                encounterTextArea.setVisible(true);
            }
        }
    }
    private void updateGameScreen() {

            mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
//            encounterTextArea.setText(encounterDescription());
            showAreaItems();

            healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                    "HP: " + game.getPlayer().getHealth()+ "             TIME: " + time());

//            if (encounterDescription().equals("nothing here")) {
//                encounterTextArea.setVisible(false);
//            }
//            else {
//                encounterTextArea.setVisible(true);
//            }

    }

    private class IntroHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                createIntroScreen();
            } catch (InterruptedException | IOException ex) {}
        }
    }
    private class NavigationHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                updateGameScreen(event.getActionCommand());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class ItemPickUpHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            pickUpItem(event.getActionCommand());
        }
    }

    private class ItemUseHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            useItem(event.getActionCommand());
        }
    }

    public static void main(String[] args) throws IOException {
        Window test = new Window();
    }
}


