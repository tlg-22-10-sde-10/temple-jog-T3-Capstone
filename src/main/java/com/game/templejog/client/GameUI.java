package com.game.templejog.client;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Sound;
import com.game.templejog.Temple;
import com.game.templejog.animation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GameUI {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private static final Color PRIMARY_COLOR = new Color(0, 0, 0);
    private static final Color SECONDARY_COLOR = new Color(0, 0, 0);
    static Animation animation;

    static JFrame window;
    static Container container;
    static JPanel titleNamePanel, startButtonPanel, quitButtonPanel, mainTextPanel, difficultyPanel, enterPanel, musicPanel;
    static JPanel playerPanel, mainGamePanel, directionalPanel, areaItemPanel, playerInventoryPanel, settings, helpeventPanel, mapPanel, settingsPanel;
    static JLabel healthLabel, titleLabel, musicLabel, soundFxLabel;
    static JButton northButton, eastButton, southButton, westButton, soundOnOffButton, VolumeDown, VolumeUp;
    static JButton startButton, quitButton, choice1, choice2, choice3, enterButton, settingsButton, helpButton;
    static JTextArea mainTextArea, encounterTextArea, helpMenuTextArea;

    static JComboBox musicStatus, soundFXStatus;

    static Font titleFont = new Font("Arial", Font.PLAIN, 90);//cutomize font
    static Font standardFont = new Font("Times New Roman", Font.PLAIN, 30);
    static Font smallFont = new Font("Times New Roman", Font.PLAIN, 15);
    static Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    static Font gameFont = new Font("Times New Roman", Font.PLAIN, 25);

    static ActionHandler actionHandler = new ActionHandler();
    static Game game;
    static Temple gameFiles;


    public GameUI() throws InterruptedException {
        window = new JFrame("Temple Jog");
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
//        Image icon = new ImageIcon("src/main/resources/car.jpg").getImage();
//        animation = new Animation(icon);
//        animation.setBounds(50,300,200,200);
//        container.add(animation);

        container.add(titleNamePanel);
        container.add(startButtonPanel);
        container.add(quitButtonPanel);


        Sound.Title();
        settings = eventPanel(200, 150, 400, 200, "settings");
        settings.setVisible(false);
        settingMenuOption();

        helpeventPanel = eventPanel(200, 150, 800, 400, "help");
        helpeventPanel.setVisible(false);

        mapPanel = eventPanel(200, 150, 800, 400, "map");
        mapPanel.setVisible(false);
    }


    public static void difficultyScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);
        quitButtonPanel.setVisible(false);

        difficultyPanel = new JPanel();
        difficultyPanel.setBounds(250, 300, 300, 150);
        difficultyPanel.setBackground(Color.GREEN);
        difficultyPanel.setLayout(new GridLayout(3, 1));
        container.add(difficultyPanel);

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
        Sound.stopSound();
        Sound.gameIntro(game);
        /* Stop the background music when entering landing zone */
        if (game.getPlaySound()) {
            Sound.stopSound();
            Sound.themeSound("sounds/landing_zone.wav");
        }
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
                "HP: " + game.getPlayer().getHealth() + "             TIME: " + time());
        healthLabel.setForeground(Color.GREEN);
        healthLabel.setFont(normalFont);

        playerPanel.add(healthLabel);

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


        //Setting Panel
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(1, 3));
        settingsPanel.setBounds(400, 500, 150, 50);
        settingsPanel.setBackground(Color.BLACK);
        container.add(settingsPanel);

        //settings button
        ImageIcon settingIcon = new ImageIcon(GameUI.class.getClassLoader().getResource("img/gear.png"));
        settingsButton = new JButton();
        settingsButton.setIcon(settingIcon);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setPreferredSize(new Dimension(50, 50));
        settingsButton.addActionListener(actionHandler);
        settingsButton.setActionCommand("settings");
        settingsPanel.add(settingsButton);

        //HelpPanel


        //help button
        ImageIcon helpIcon = new ImageIcon(GameUI.class.getClassLoader().getResource("img/monitor.png"));
        helpButton = new JButton();
        helpButton.setIcon(helpIcon);
        helpButton.setBorderPainted(false);
        helpButton.setFocusPainted(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setPreferredSize(new Dimension(50, 50));
//        helpButton.setBackground(Color.red);
//        helpButton.setForeground(Color.WHITE);
        helpButton.addActionListener(actionHandler);
        helpButton.setActionCommand("help");
        settingsPanel.add(helpButton);
    }


    public static void eventPanelClose(String name) {
        String panelName = name.replace("close ", "");
        switch (panelName) {
            case "playerMap":
                //remove all used to update map everytime it is pulled up
                //playerMap.setVisible(false);
                //playerMap.removeAll();
                break;
            case "settings":
                settings.setVisible(false);
                break;
            case "help":
                helpeventPanel.setVisible(false);
        }
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
        exitButton.setActionCommand("close " + target);
        panelBuilder.add(exitButton);
        return panelBuilder;
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
        StringBuilder encounterDescription = new StringBuilder();
        if (hasEncounters) {

            for (String encounter : game.getCurrentRoom().getEncounters_to()) {
//                encounterDescription = new StringBuilder();
                encounterDescription.append(game.getEncounters().get(encounter).getDescription().toString());
                encounterDescription.append("\n");
                encounterDescription.append("\n");

            }
//            Image icon = new ImageIcon("src/main/resources/car.jpg").getImage();

        } else {
            encounterDescription = new StringBuilder().append("nothing here");
        }
        return String.valueOf(encounterDescription);
    }

    public static void showAreaItems() {
        areaItemPanel.removeAll();
        List<String> itemList = game.getCurrentRoom().getItems();
        if (!itemList.isEmpty()) {
            for (String item : itemList) {
                JButton areaItem = new JButton(item);
                areaItem.setForeground(Color.BLACK);
                areaItem.setBackground(Color.YELLOW);
                areaItem.setFont(gameFont);
                areaItem.addActionListener(actionHandler);
                areaItem.setActionCommand(item);
                areaItemPanel.add(areaItem);
                areaItemPanel.setVisible(true);
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
            inventoryItem.setActionCommand("use " + item.getName());
            inventoryItem.setBackground(Color.WHITE);
            inventoryItem.setForeground(Color.BLACK);
            inventoryItem.addActionListener(actionHandler);
            playerInventoryPanel.add(inventoryItem);
            if (item.getReuse() < 1) {
                inventoryItem.setVisible(false);
            }
            if (items.size() > 0) {
                playerInventoryPanel.setVisible(true);
            } else playerInventoryPanel.setVisible(true);
            updateGameScreen();

        }

    }

    static void useItem(String item) {
        String itemName = item.replace("use ", "");
        String action = game.processUsing(itemName);

        if (action.contains("is EFFECTIVE against")) {
            encounterTextArea.setText(action);

        } else if (action.contains("Success!!!")) {
            encounterTextArea.setText(action);
        } else {
            Sound.wrongItemSound();
        }
        updateItemPanel();

    }

    static void updateGameScreen(String direction) {
        if (game.processNavigating(direction).contains("Traveling")) {
            mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
            encounterTextArea.setText(encounterDescription());
            showAreaItems();

            healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                    "HP: " + game.getPlayer().getHealth() + "             TIME: " + time());
            if (encounterDescription().equals("nothing here")) {
                encounterTextArea.setVisible(false);
            } else {
                encounterTextArea.setVisible(true);
            }
        } else {
            Sound.wrongWaySound();
        }
        checkWinLoss();
    }

    private static void updateGameScreen() {
        mainTextArea.setText(String.valueOf(game.getCurrentRoom().getDescription()));
        showAreaItems();
        healthLabel.setText("Location: " + game.getCurrentRoom().getName() + "         " +
                "HP: " + game.getPlayer().getHealth() + "             TIME: " + time());
    }

    private static void checkWinLoss() {
        if (game.getPlayer().getSteps() >= 24 || game.getPlayer().getHealth() <= 0) {
            //TODO showLossScreen();
            mainGamePanel.removeAll();
            directionalPanel.setVisible(false);
            playerInventoryPanel.setVisible(false);
            mainTextArea.setText("Sorry You lose...");
            mainGamePanel.add(mainTextArea);


        } else if (game.getCommunicatorOff() && game.getCurrentRoom().getName().equalsIgnoreCase("landing zone")) {
            //TODO showWinScreen();
            mainGamePanel.removeAll();
            directionalPanel.setVisible(false);
            playerInventoryPanel.setVisible(false);
            mainTextArea.setText("You Win!!!");
            mainGamePanel.setOpaque(false);
            mainGamePanel.add(mainTextArea);
            Image heliIcon = new ImageIcon(GameUI.class.getClassLoader().getResource("helicopter.jpg")).getImage();


            animation = new Animation(heliIcon);
            animation.setBounds(200,100,400,400);

            container.add(animation);
        } else {

        }
    }


    public void settingMenuOption() {
        //holds setting options for musix and sfx
        setMusicPanel(new JPanel());
        getMusicPanel().setBounds(25, 5, 340, 180);
        getMusicPanel().setBackground(Color.white);
        String select[] = {"ON", "OFF"};


        setMusicStatus(new JComboBox(select));
        getMusicStatus().addActionListener(actionHandler);
        getMusicStatus().setActionCommand("toggle sound");
        setSoundFXStatus(new JComboBox(select));
        setMusicLabel(new JLabel("Music"));
        setSoundFxLabel(new JLabel("SoundFX"));
        setVolumeDown(new JButton("volume down"));
        getVolumeDown().addActionListener(actionHandler);
        getVolumeDown().setActionCommand("volume down");
        setVolumeUp(new JButton("Volume Up"));
        getVolumeUp().addActionListener(actionHandler);
        getVolumeUp().setActionCommand("volume up");
        getVolumeDown().setBounds(80, 120, 150, 25);
        getVolumeUp().setBounds(80, 90, 150, 25);
        getMusicLabel().setBounds(80, 30, 75, 25);
        getSoundFxLabel().setBounds(80, 60, 75, 25);
        getMusicStatus().setBounds(150, 30, 85, 25);
        getSoundFXStatus().setBounds(150, 60, 85, 25);

        settings.add(getVolumeDown());
        settings.add(getVolumeUp());
        settings.add(getSoundFxLabel());
        settings.add(getMusicLabel());
        settings.add(getMusicStatus());
        settings.add(getSoundFXStatus());
        settings.add(musicPanel);
    }

    public static JPanel getMusicPanel() {
        return musicPanel;
    }

    public static void setMusicPanel(JPanel musicPanel) {
        GameUI.musicPanel = musicPanel;
    }

    public static JLabel getMusicLabel() {
        return musicLabel;
    }

    public static void setMusicLabel(JLabel musicLabel) {
        GameUI.musicLabel = musicLabel;
    }

    public static JLabel getSoundFxLabel() {
        return soundFxLabel;
    }

    public static void setSoundFxLabel(JLabel soundFxLabel) {
        GameUI.soundFxLabel = soundFxLabel;
    }

    public static JButton getVolumeDown() {
        return VolumeDown;
    }

    public static void setVolumeDown(JButton volumeDown) {
        VolumeDown = volumeDown;
    }

    public static JButton getVolumeUp() {
        return VolumeUp;
    }

    public static void setVolumeUp(JButton volumeUp) {
        VolumeUp = volumeUp;
    }

    public static JComboBox getMusicStatus() {
        return musicStatus;
    }

    public static void setMusicStatus(JComboBox musicStatus) {
        GameUI.musicStatus = musicStatus;
    }

    public static JComboBox getSoundFXStatus() {
        return soundFXStatus;
    }

    public static void setSoundFXStatus(JComboBox soundFXStatus) {
        GameUI.soundFXStatus = soundFXStatus;
    }

    public void setSettings(JPanel settings) {
        this.settings = settings;
    }

    public static JPanel getSettings() {
        return settings;
    }

    public static JPanel getHelpeventPanel() {
        return helpeventPanel;
    }

    public static void setHelpeventPanel(JPanel helpeventPanel) {
        GameUI.helpeventPanel = helpeventPanel;
    }

    //    public static void main(String[] args) {
//        new GameUI();
//    }

}
