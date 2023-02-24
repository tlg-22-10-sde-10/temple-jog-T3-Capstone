package com.game.templejog.client;

import com.game.templejog.Game;
import com.game.templejog.Item;
import com.game.templejog.Sound;
import com.game.templejog.Temple;
import com.game.templejog.animation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


public class GameUI {
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 800;
    private static Animation animation;
    private static String currentLocationMap;
    private static Image icon;
    private static final FileLoader fileLoader = new FileLoader();
    private static JFrame window;
    private static Container container;
    private static JPanel titleNamePanel;
    private static JPanel startButtonPanel;
    private static JPanel mainTextPanel;
    private static JPanel difficultyPanel;
    private static JPanel enterPanel;
    private static JPanel musicPanel;
    private static JPanel inventoryDescriptionPanel;
    private static JPanel encountersImagePanel;
    private static JPanel playerPanel;
    private static JPanel mainGamePanel;
    private static JPanel directionalPanel;
    private static JPanel areaItemPanel;
    private static JPanel playerInventoryPanel;
    private static JPanel settings;
    private static JPanel helpeventPanel;
    private static JPanel mapPanel;
    private static JPanel settingsPanel;
    private static JLabel healthLabel;
    private static JLabel titleLabel;
    private static JLabel musicLabel;
    private static JLabel soundFxLabel;
    private static JLabel blankLabel1;
    private static JLabel blankLabel3;
    private static JLabel blankLabel5;
    private static JLabel blankLabel7;
    private static JLabel blankLabel9;
    private static JLabel encountersLabel;
    private static JButton northButton;
    private static JButton eastButton;
    private static JButton southButton;
    private static JButton westButton;
    private static JButton getMapButton;
    private static JButton VolumeDown;
    private static JButton VolumeUp;
    private static JButton startButton;
    private static JButton choice1;
    private static JButton choice2;
    private static JButton choice3;
    private static JButton enterButton;
    private static JButton settingsButton;
    private static JButton helpButton;
    private static JTextArea mainTextArea;
    private static JTextArea encounterTextArea;
    private static JTextArea helpMenuTextArea;
    private static JTextArea itemDescriptionTextArea;

    private static JComboBox musicStatus;
    private static JComboBox soundFXStatus;

    private static final Font titleFont = new Font("Castellar", Font.PLAIN, 90);

    private static final Font standardFont = new Font("Times New Roman", Font.PLAIN, 30);
    private static final Font smallFont = new Font("Times New Roman", Font.PLAIN, 15);
    private static final Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
    private static final Font gameFont = new Font("Times New Roman", Font.PLAIN, 25);
    private static final Font dPadBoldFont = new Font("Arial", Font.BOLD, 15);
    private static final Font helpFont = new Font("Arial", Font.BOLD, 12);

    private static final ActionHandler actionHandler = new ActionHandler();
    private static Game game;
    static Temple gameFiles;


    public GameUI() throws InterruptedException {

        setupJFrameWindow();
        setupTitleScreen();

    }


    private void setupJFrameWindow() {
        setWindow(new JFrame("Temple Jog"));
        getWindow().setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when window is closed
        getWindow().setContentPane(new JLabel(new ImageIcon
                (getFileLoader().imageLoader("img/8_bit_ufo.png"))));
        getWindow().setLayout(null);
        getWindow().setVisible(true);
        getWindow().setResizable(false);
        setContainer(getWindow().getContentPane());
    }


    private void setupTitleScreen() {
        setTitleNamePanel(new JPanel());
        getTitleNamePanel().setBounds(100, 190, 600, 150); //x and y axis starting top left. width starting from xy axis. height starting from top.
        getTitleNamePanel().setBackground(Color.BLUE);
        getTitleNamePanel().setOpaque(false);
        setTitleLabel(new JLabel("Temple Jog")); //text label
        getTitleLabel().setForeground(Color.WHITE);// text color
        getTitleLabel().setFont(getTitleFont());
        getTitleNamePanel().add(getTitleLabel());
        getContainer().add(getTitleNamePanel());

        setStartButtonPanel(new JPanel());
        getStartButtonPanel().setBounds(290, 300, 200, 75);
        getStartButtonPanel().setBackground(Color.GREEN);
        getStartButtonPanel().setOpaque(false);
        setStartButton(new JButton("New Game"));
        getStartButton().setBackground(Color.BLACK);
        getStartButton().setForeground(Color.WHITE);
        getStartButton().setFont(getStandardFont());
        //functionality of start button
        getStartButton().setActionCommand("start");
        getStartButton().addActionListener(getActionHandler());
        getStartButton().setFocusPainted(false);
        getStartButtonPanel().add(getStartButton());
        getContainer().add(getStartButtonPanel());


        Sound.Title();
        setSettings(eventPanel(200, 150, 400, 200, "settings"));
        getSettings().setBackground(Color.BLACK);
        getSettings().setVisible(false);
        settingMenuOption();

        setHelpeventPanel(eventPanel(150, 150, 450, 300, "help"));
        getHelpeventPanel().setVisible(false);
        getHelpeventPanel().setBackground(Color.BLACK);

        setMapPanel(eventPanel(0, 0, 780, 550, "getMap"));
        getMapPanel().setVisible(false);
        getMapPanel().setBackground(Color.BLACK);
    }


    public static void difficultyScreen() {
        getTitleNamePanel().setVisible(false);
        getStartButtonPanel().setVisible(false);

        setDifficultyPanel(new JPanel());
        getDifficultyPanel().setBounds(250, 250, 300, 150);
        getDifficultyPanel().setBackground(Color.GREEN);
        getDifficultyPanel().setLayout(new GridLayout(3, 1));
        getDifficultyPanel().setOpaque(false);
        getContainer().add(getDifficultyPanel());

        setMainTextPanel(new JPanel());
        getMainTextPanel().setBounds(100, 0, 600, 400);
        getMainTextPanel().setBackground(Color.BLACK);
        getMainTextPanel().setOpaque(false);
        getContainer().add(getMainTextPanel());

        setMainTextArea(new JTextArea("Choose your difficulty: "));
        getMainTextArea().setBounds(100, 0, 600, 400);

        getMainTextArea().setOpaque(false);
        getMainTextArea().setForeground(Color.WHITE);
        getMainTextArea().setFont(getStandardFont());
        getMainTextArea().setLineWrap(true);
        getMainTextPanel().add(getMainTextArea());

        setChoice1(new JButton("choice1"));
        getChoice1().setBackground(Color.BLACK);
        getChoice1().setForeground(Color.WHITE);
        getChoice1().setFont(getStandardFont());
        getChoice1().setFocusPainted(false);
        getChoice1().addActionListener(getActionHandler());
        getChoice1().setActionCommand("c1");
        getDifficultyPanel().add(getChoice1());

        setChoice2(new JButton("choice2"));
        getChoice2().setBackground(Color.BLACK);
        getChoice2().setForeground(Color.WHITE);
        getChoice2().setFont(getStandardFont());
        getChoice2().setFocusPainted(false);
        getChoice2().addActionListener(getActionHandler());
        getChoice2().setActionCommand("c2");
        getDifficultyPanel().add(getChoice2());

        setChoice3(new JButton("choice3"));
        getChoice3().setBackground(Color.BLACK);
        getChoice3().setForeground(Color.WHITE);
        getChoice3().setFont(getStandardFont());
        getChoice3().setFocusPainted(false);
        getChoice3().addActionListener(getActionHandler());
        getChoice3().setActionCommand("c3");
        getDifficultyPanel().add(getChoice3());
        difficultySettings();
    }

    public static void difficultySettings() {
        getMainTextArea().setText("\n\n\n\n\nChoose your difficulty: ");
        getChoice1().setText("Easy");
        getChoice2().setText("Medium");
        getChoice3().setText("Hard");
    }

    public static void easyGame() throws InterruptedException, IOException {
        getChoice1().setVisible(false);
        getChoice2().setVisible(false);
        getChoice3().setVisible(false);
        getDifficultyPanel().setVisible(false);

        gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
        setGame(new Game(gameFiles));
        Sound.stopSound();
        Sound.gameIntro(getGame());
        /* Stop the background music when entering landing zone */
        if (Game.getPlaySound()) {
            Sound.stopSound();
            Sound.themeSound("sounds/landing_zone.wav");
        }
        getMainTextArea().setOpaque(true);
        getMainTextArea().setBackground(Color.BLACK);
        String intro = getGame().getGameText().get("intro");
        getMainTextArea().setFont(getSmallFont());
        getMainTextArea().setText(intro);

        setEnterPanel(new JPanel());
        getEnterPanel().setBounds(350, 380, 100, 100);
        getEnterPanel().setOpaque(false);
        getContainer().add(getEnterPanel());

        setEnterButton(new JButton("Enter"));
        getEnterButton().setBackground(Color.BLACK);
        getEnterButton().setForeground(Color.WHITE);
        getEnterButton().setFont(getStandardFont());
        getEnterButton().setFocusPainted(false);
        getEnterButton().setActionCommand("play");
        getEnterButton().addActionListener(getActionHandler());

        getEnterPanel().add(getEnterButton());

    }

    public static void mediumGame() throws IOException, InterruptedException {
        easyGame();
        getGame().getPlayer().setHealth(5);
        getGame().getPlayer().setSteps(4);
    }

    public static void hardGame() throws IOException, InterruptedException {
        easyGame();
        getGame().getPlayer().setHealth(1);
        getGame().getPlayer().setSteps(8);
        getGame().getItems().get("machete").setReuse(1);
    }

    public static void mainGameDisplay() {
        getMainTextPanel().setVisible(false);
        getEnterPanel().setVisible(false);

        setMainGamePanel(new JPanel());
        getMainGamePanel().setBounds(0, 50, WINDOW_WIDTH, 350);
        getMainGamePanel().setBackground(Color.GREEN);
        getMainGamePanel().setOpaque(false);
        getContainer().add(getMainGamePanel());

        setMainTextArea(new JTextArea(String.valueOf(Game.getCurrentRoom().getDescription())));
        getMainTextArea().setBounds(0, 50, WINDOW_WIDTH, 100);
        getMainTextArea().setBackground(Color.BLACK);
        getMainTextArea().setForeground(Color.GREEN);
        getMainTextArea().setFont(getGameFont());
        getMainTextArea().setLineWrap(true);
        getMainGamePanel().add(getMainTextArea());

        setEncounterTextArea(new JTextArea(String.valueOf(Game.getCurrentRoom().getEncounters_to())));
        getEncounterTextArea().setBounds(0, 150, WINDOW_WIDTH, 100);
        getEncounterTextArea().setBackground(Color.BLACK);
        getEncounterTextArea().setForeground(Color.RED);
        getEncounterTextArea().setFont(getGameFont());
        getEncounterTextArea().setLineWrap(true);
        getMainGamePanel().add(getEncounterTextArea());
        getEncounterTextArea().setVisible(false);

        setAreaItemPanel(new JPanel());
        getAreaItemPanel().setBounds(0, 250, WINDOW_WIDTH, 100);
        getAreaItemPanel().setBackground(Color.BLUE);
        getAreaItemPanel().setLayout(new GridLayout(1, 4));
        getMainGamePanel().add(getAreaItemPanel());
        getAreaItemPanel().setVisible(false);

        setEncountersImagePanel(new JPanel());
        getEncountersImagePanel().setBounds(0, 350, WINDOW_WIDTH, 100);
        getEncountersImagePanel().setBackground(Color.PINK);
        getEncountersImagePanel().setVisible(false);
        getMainGamePanel().add(getEncountersImagePanel());
        setEncountersLabel(new JLabel());
        getEncountersImagePanel().add(getEncountersLabel());


        setInventoryDescriptionPanel(new JPanel());
        getInventoryDescriptionPanel().setLayout(new GridLayout(1, 1));
        getInventoryDescriptionPanel().setBounds(250, 375, 275, 150);
        getInventoryDescriptionPanel().setBackground(Color.WHITE);
        getInventoryDescriptionPanel().setVisible(false);
        getContainer().add(getInventoryDescriptionPanel());

        setDirectionalPanel(new JPanel());
        getDirectionalPanel().setBounds(WINDOW_WIDTH - 200, 400, 150, 150);
        getDirectionalPanel().setLayout(new GridLayout(3, 3));
        getDirectionalPanel().setOpaque(false);
        getDirectionalPanel().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        getContainer().add(getDirectionalPanel());


        setPlayerInventoryPanel(new JPanel());
        getPlayerInventoryPanel().setBounds(0, 375, 200, 180);
        getPlayerInventoryPanel().setBackground(Color.LIGHT_GRAY);
        getPlayerInventoryPanel().setLayout(new GridLayout(8, 1));
        getPlayerInventoryPanel().setOpaque(false);
        getContainer().add(getPlayerInventoryPanel());
        getPlayerInventoryPanel().setVisible(false);


        setPlayerPanel(new JPanel());
        getPlayerPanel().setBounds(0, 0, WINDOW_WIDTH, 50);
        getPlayerPanel().setBackground(Color.BLACK);
        getPlayerPanel().setLayout(new GridLayout(1, 4));
        getContainer().add(getPlayerPanel());

        setHealthLabel(new JLabel("Location: " + Game.getCurrentRoom().getName() + "         " +
                "HP: " + getGame().getPlayer().getHealth() + "             TIME: " + time()));
        getHealthLabel().setForeground(Color.GREEN);
        getHealthLabel().setFont(getNormalFont());

        getPlayerPanel().add(getHealthLabel());

        setBlankLabel1(new JLabel());
        setBlankLabel3(new JLabel());
        setBlankLabel5(new JLabel());
        setBlankLabel7(new JLabel());
        setBlankLabel9(new JLabel());
        setNorthButton(new JButton("N"));
        getNorthButton().setBackground(Color.GREEN);
        getNorthButton().setForeground(Color.BLACK);
        getNorthButton().setFont(getdPadBoldFont());
        getNorthButton().addActionListener(getActionHandler());
        getNorthButton().setActionCommand("north");
        setEastButton(new JButton("E"));
        getEastButton().setBackground(Color.GREEN);
        getEastButton().setForeground(Color.BLACK);
        getEastButton().setFont(getdPadBoldFont());
        getEastButton().addActionListener(getActionHandler());
        getEastButton().setActionCommand("east");
        setSouthButton(new JButton("S"));
        getSouthButton().setBackground(Color.GREEN);
        getSouthButton().setForeground(Color.BLACK);
        getSouthButton().setFont(getdPadBoldFont());
        getSouthButton().addActionListener(getActionHandler());
        getSouthButton().setActionCommand("south");
        setWestButton(new JButton("W"));
        getWestButton().setBackground(Color.GREEN);
        getWestButton().setForeground(Color.BLACK);
        getWestButton().setFont(getdPadBoldFont());
        getWestButton().addActionListener(getActionHandler());
        getWestButton().setActionCommand("west");

        getDirectionalPanel().add(getBlankLabel1());
        getDirectionalPanel().add(getNorthButton());
        getDirectionalPanel().add(getBlankLabel3());
        getDirectionalPanel().add(getWestButton());
        getDirectionalPanel().add(getBlankLabel5());
        getDirectionalPanel().add(getEastButton());
        getDirectionalPanel().add(getBlankLabel7());
        getDirectionalPanel().add(getSouthButton());
        getDirectionalPanel().add(getBlankLabel9());


        //Setting Panel
        setSettingsPanel(new JPanel());
        getSettingsPanel().setLayout(new GridLayout(1, 3));
        getSettingsPanel().setBounds(570, 320, 200, 100);
        getSettingsPanel().setBackground(Color.WHITE);
        getSettingsPanel().setOpaque(false);
        getContainer().add(getSettingsPanel());

        //settings button
        ImageIcon settingIcon = new ImageIcon(getFileLoader().imageLoader("img/gear.png"));
        setSettingsButton(new JButton());
        getSettingsButton().setIcon(settingIcon);
        getSettingsButton().setBorderPainted(false);
        getSettingsButton().setFocusPainted(false);
        getSettingsButton().setContentAreaFilled(false);
        getSettingsButton().setPreferredSize(new Dimension(50, 50));
        getSettingsButton().addActionListener(getActionHandler());
        getSettingsButton().setActionCommand("settings");
        getSettingsPanel().add(getSettingsButton());


        //help button
        ImageIcon helpIcon = new ImageIcon(getFileLoader().imageLoader("img/helpBubble.png"));
        setHelpButton(new JButton());
        getHelpButton().setIcon(helpIcon);
        getHelpButton().setBorderPainted(false);
        getHelpButton().setFocusPainted(false);
        getHelpButton().setContentAreaFilled(false);
        getHelpButton().setPreferredSize(new Dimension(50, 50));
        getHelpButton().addActionListener(getActionHandler());
        getHelpButton().setActionCommand("help");
        getSettingsPanel().add(getHelpButton());

        //HelpPanel
        setHelpMenuTextArea(new JTextArea());
        getHelpMenuTextArea().setBounds(0, 35, 450, 265);
        getHelpMenuTextArea().setOpaque(false);
        getHelpMenuTextArea().setForeground(Color.GREEN);
        getHelpMenuTextArea().setBackground(Color.BLACK);
        getHelpMenuTextArea().setFont(getHelpFont());
        getHelpMenuTextArea().setLineWrap(true);
        String gameHelp = getGame().getGameText().get("guiGameHelp");
        getHelpMenuTextArea().setText(gameHelp);

        getHelpeventPanel().add(getHelpMenuTextArea());


        ImageIcon mapIcon = new ImageIcon(getFileLoader().imageLoader("img/radar4.png"));
        setGetMapButton(new JButton());
        getGetMapButton().setIcon(mapIcon);
        getGetMapButton().setBorderPainted(false);
        getGetMapButton().setFocusPainted(false);
        getGetMapButton().setContentAreaFilled(false);
        getGetMapButton().setPreferredSize(new Dimension(50, 50));
        getGetMapButton().addActionListener(getActionHandler());
        getGetMapButton().setActionCommand("getMap");
        getSettingsPanel().add(getGetMapButton());


        setCurrentLocationMap(Game.getCurrentRoom().getCurLocation());
        setIcon(getFileLoader().imageLoader(getCurrentLocationMap()));
        ImageIcon roomIcon = new ImageIcon(getIcon());
        JLabel locationMap = new JLabel();
        locationMap.setIcon(roomIcon);
        locationMap.setBounds(0, 0, 800, 550);

        getMapPanel().add(locationMap);

    }

    public static void eventPanelClose(String name) {
        String panelName = name.replace("close ", "");
        switch (panelName) {
            case "getMap":
                getMapPanel().setVisible(false);
                break;
            case "settings":
                getSettings().setVisible(false);
                break;
            case "help":
                getHelpeventPanel().setVisible(false);
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
        getWindow().add(panelBuilder);
        JButton exitButton = new JButton("X");
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(getNormalFont());
        exitButton.setBounds(width - 50, 0, 50, 30);
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(getActionHandler());
        exitButton.setActionCommand("close " + target);
        panelBuilder.add(exitButton);
        return panelBuilder;
    }

    private static int time() {
        int hoursPlayed = getGame().getPlayer().getSteps() * 15;
        int hours = hoursPlayed / 60;
        int minutes = hoursPlayed % 60;
        return 1200 + (100 * hours) + minutes;
    }

    private static String encounterDescription() {
        boolean hasEncounters = !Game.getCurrentRoom().getEncounters_to().isEmpty();
        StringBuilder encounterDescription = new StringBuilder();
        if (hasEncounters) {
            for (String encounter : Game.getCurrentRoom().getEncounters_to()) {
                encounterDescription.append(getGame().getEncounters().get(encounter).getDescription());
            }
        } else {
            encounterDescription = new StringBuilder().append("nothing here");
        }
        return String.valueOf(encounterDescription);
    }

    private static Image encountersPicture() {
        boolean hasEncounters = !Game.getCurrentRoom().getEncounters_to().isEmpty();
        Image encountersImage = null;
        if (hasEncounters) {
            for (String encounter : Game.getCurrentRoom().getEncounters_to()) {
                String image = getGame().getEncounters().get(encounter).getImage();
                encountersImage = new ImageIcon(getFileLoader().imageLoader(image)).getImage();
            }
        }
        return encountersImage;
    }


    public static void showAreaItems() {
        getAreaItemPanel().removeAll();
        List<String> itemList = Game.getCurrentRoom().getItems();
        if (!itemList.isEmpty()) {
            for (String item : itemList) {
                JButton areaItem = new JButton(item);
                areaItem.setForeground(Color.BLACK);
                areaItem.setBackground(Color.YELLOW);
                areaItem.setFont(getGameFont());
                areaItem.addActionListener(getActionHandler());
                areaItem.setActionCommand(item);
                getAreaItemPanel().add(areaItem);
                getAreaItemPanel().setVisible(true);
            }
        }
    }

    static void pickUpItem(String item) {
        getGame().processGetting(item);
        updateItemPanel();
    }

    private static void updateItemPanel() {
        getPlayerInventoryPanel().removeAll();
        JButton inventory = new JButton("Inventory");
        inventory.setBackground(Color.GREEN);
        getPlayerInventoryPanel().add(inventory);
        List<Item> items = getGame().getPlayer().getInventory();
        for (Item item : items) {
            JButton inventoryItem = new JButton(item.getName());
            inventoryItem.setActionCommand("use " + item.getName());
            inventoryItem.setBackground(Color.BLACK);
            inventoryItem.setForeground(Color.WHITE);
            inventoryItem.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    Color c = inventoryItem.getBackground(); // When the mouse moves over a label, the background color changed.
                    inventoryItem.setBackground(inventoryItem.getForeground());
                    inventoryItem.setForeground(c);
                    setItemDescriptionTextArea(new JTextArea(item.getDescription()));
                    getItemDescriptionTextArea().setLineWrap(true);
                    getItemDescriptionTextArea().setBackground(Color.BLACK);
                    getItemDescriptionTextArea().setForeground(Color.YELLOW);
                    getInventoryDescriptionPanel().add(getItemDescriptionTextArea());
                    getInventoryDescriptionPanel().setVisible(true);
                }

                public void mouseExited(MouseEvent evt) {
                    Color c = inventoryItem.getBackground();
                    inventoryItem.setBackground(inventoryItem.getForeground());
                    inventoryItem.setForeground(c);
                    getInventoryDescriptionPanel().setVisible(false);
                    getInventoryDescriptionPanel().removeAll();
                }
            });
            inventoryItem.addActionListener(getActionHandler());
            getPlayerInventoryPanel().add(inventoryItem);
            if (item.getReuse() < 1) {
                inventoryItem.setVisible(false);
                getInventoryDescriptionPanel().setVisible(false);
                getInventoryDescriptionPanel().removeAll();
            }
            if (items.size() > 0) {
                getPlayerInventoryPanel().setVisible(true);
            } else getPlayerInventoryPanel().setVisible(true);
            updateGameScreen();

        }

    }

    static void useItem(String item) {
        String itemName = item.replace("use ", "");
        String action = getGame().processUsing(itemName);

        if (action.contains("is EFFECTIVE against")) {
            getEncounterTextArea().setText(action);
            getEncountersImagePanel().setVisible(false);

        } else if (action.contains("Success!!!")) {
            getEncounterTextArea().setText(action);
            getEncountersImagePanel().setVisible(false);
            if (Game.getCurrentRoom().getName().equalsIgnoreCase("Alien Communication Room")) {
                Sound.getToTheChopper();
            }
        } else {
            Sound.wrongItemSound();
        }
        updateItemPanel();

    }

    static void updateGameScreen(String direction) {
        if (getGame().processNavigating(direction).contains("Traveling")) {
            getMainTextArea().setText(String.valueOf(Game.getCurrentRoom().getDescription()));
            getMapPanel().removeAll();
            String newMap = Game.getCurrentRoom().getCurLocation();
            setIcon(getFileLoader().imageLoader(newMap));
            ImageIcon roomIcon = new ImageIcon(getIcon());
            JLabel locationMap = new JLabel();
            locationMap.setIcon(roomIcon);
            locationMap.setBounds(0, 0, 800, 550);


            JButton exitButton = new JButton("X");
            exitButton.setForeground(Color.WHITE);
            exitButton.setFont(getNormalFont());
            //exitButton.setOpaque(false);
            exitButton.setBounds(780 - 50, 0, 50, 30);
            exitButton.setBackground(Color.RED);
            exitButton.addActionListener(getActionHandler());
            exitButton.setActionCommand("close getMap");
            getMapPanel().add(exitButton);
            getMapPanel().add(locationMap);

            getEncounterTextArea().setText(encounterDescription());
            showAreaItems();


            getHealthLabel().setText("Location: " + Game.getCurrentRoom().getName() + "         " +
                    "HP: " + getGame().getPlayer().getHealth() + "             TIME: " + time());
            getEncounterTextArea().setVisible(!encounterDescription().equals("nothing here"));
            if (encountersPicture() != null) {
                getEncountersLabel().setIcon(new ImageIcon(encountersPicture()));
                getEncountersImagePanel().setVisible(true);
                getEncountersImagePanel().setOpaque(false);
            } else {
                getEncountersImagePanel().setVisible(false);
            }
        } else {
            Sound.wrongWaySound();
        }
        checkWinLoss();
    }

    private static void updateGameScreen() {
        getMainTextArea().setText(String.valueOf(Game.getCurrentRoom().getDescription()));
        showAreaItems();
        getHealthLabel().setText("Location: " + Game.getCurrentRoom().getName() + "         " +
                "HP: " + getGame().getPlayer().getHealth() + "             TIME: " + time());
    }

    private static void checkWinLoss() {
        if (getGame().getPlayer().getSteps() >= 24 || getGame().getPlayer().getHealth() <= 0) {
            getMainGamePanel().removeAll();
            getDirectionalPanel().setVisible(false);
            getPlayerInventoryPanel().setVisible(false);
            getMainTextArea().setText("Sorry You lose...");
            getMainGamePanel().add(getMainTextArea());


        } else if (getGame().getCommunicatorOff() && Game.getCurrentRoom().getName().equalsIgnoreCase("landing zone")) {
            getMainGamePanel().removeAll();
            getDirectionalPanel().setVisible(false);
            getPlayerInventoryPanel().setVisible(false);
            getSettingsPanel().setVisible(false);
            getHelpeventPanel().setVisible(false);
            getMainTextArea().setText("You Win!!!");
            getMainGamePanel().setOpaque(false);
            getMainGamePanel().add(getMainTextArea());
            //Image heliIcon = new ImageIcon(GameUI.class.getClassLoader().getResource("chopper.png")).getImage();
            Image heliIcon = new ImageIcon(getFileLoader().imageLoader("img/chopper.png")).getImage();
            setAnimation(new Animation(heliIcon));
            getAnimation().setBounds(200, 100, WINDOW_WIDTH, 400);
            getContainer().add(getAnimation());

        }
    }


    public void settingMenuOption() {
        //holds setting options for musix and sfx
        setMusicPanel(new JPanel());
        getMusicPanel().setBounds(25, 5, 340, 180);
        getMusicPanel().setBackground(Color.GRAY);
        String[] select = {"ON", "OFF"};

        setMusicStatus(new JComboBox(select));
        getMusicStatus().setBackground(Color.BLACK);
        getMusicStatus().setForeground(Color.GREEN);
        getMusicStatus().addActionListener(getActionHandler());
        getMusicStatus().setActionCommand("toggle sound");
        setSoundFXStatus(new JComboBox(select));
        setMusicLabel(new JLabel("Music"));
        setSoundFxLabel(new JLabel("SoundFX"));
        getSoundFXStatus().addActionListener(getActionHandler());
        getSoundFXStatus().setBackground(Color.BLACK);
        getSoundFXStatus().setForeground(Color.GREEN);
        getSoundFXStatus().setActionCommand("toggle fx");
        setVolumeDown(new JButton("volume down"));
        getVolumeDown().addActionListener(getActionHandler());
        getVolumeDown().setBackground(Color.BLACK);
        getVolumeDown().setForeground(Color.RED);
        getVolumeDown().setActionCommand("volume down");
        setVolumeUp(new JButton("Volume Up"));
        getVolumeUp().addActionListener(getActionHandler());
        getVolumeUp().setActionCommand("volume up");
        getVolumeUp().setBackground(Color.BLACK);
        getVolumeUp().setForeground(Color.GREEN);
        getVolumeDown().setBounds(80, 120, 150, 25);
        getVolumeUp().setBounds(80, 90, 150, 25);
        getMusicLabel().setBounds(80, 30, 75, 25);
        getSoundFxLabel().setBounds(80, 60, 75, 25);
        getMusicStatus().setBounds(150, 30, 85, 25);
        getSoundFXStatus().setBounds(150, 60, 85, 25);

        getSettings().add(getVolumeDown());
        getSettings().add(getVolumeUp());
        getSettings().add(getSoundFxLabel());
        getSettings().add(getMusicLabel());
        getSettings().add(getMusicStatus());
        getSettings().add(getSoundFXStatus());
        getSettings().add(getMusicPanel());
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
        GameUI.settings = settings;
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

    public static JPanel getMapPanel() {
        return mapPanel;
    }

    public static void setMapPanel(JPanel mapPanel) {
        GameUI.mapPanel = mapPanel;
    }

    public static Animation getAnimation() {
        return animation;
    }

    public static void setAnimation(Animation animation) {
        GameUI.animation = animation;
    }

    public static String getCurrentLocationMap() {
        return currentLocationMap;
    }

    public static void setCurrentLocationMap(String currentLocationMap) {
        GameUI.currentLocationMap = currentLocationMap;
    }

    public static Image getIcon() {
        return icon;
    }

    public static void setIcon(Image icon) {
        GameUI.icon = icon;
    }

    public static FileLoader getFileLoader() {
        return fileLoader;
    }


    public static JFrame getWindow() {
        return window;
    }

    public static void setWindow(JFrame window) {
        GameUI.window = window;
    }

    public static Container getContainer() {
        return container;
    }

    public static void setContainer(Container container) {
        GameUI.container = container;
    }

    public static JPanel getTitleNamePanel() {
        return titleNamePanel;
    }

    public static void setTitleNamePanel(JPanel titleNamePanel) {
        GameUI.titleNamePanel = titleNamePanel;
    }

    public static JPanel getStartButtonPanel() {
        return startButtonPanel;
    }

    public static void setStartButtonPanel(JPanel startButtonPanel) {
        GameUI.startButtonPanel = startButtonPanel;
    }

    public static JPanel getMainTextPanel() {
        return mainTextPanel;
    }

    public static void setMainTextPanel(JPanel mainTextPanel) {
        GameUI.mainTextPanel = mainTextPanel;
    }

    public static JPanel getDifficultyPanel() {
        return difficultyPanel;
    }

    public static void setDifficultyPanel(JPanel difficultyPanel) {
        GameUI.difficultyPanel = difficultyPanel;
    }

    public static JPanel getEnterPanel() {
        return enterPanel;
    }

    public static void setEnterPanel(JPanel enterPanel) {
        GameUI.enterPanel = enterPanel;
    }

    public static JPanel getInventoryDescriptionPanel() {
        return inventoryDescriptionPanel;
    }

    public static void setInventoryDescriptionPanel(JPanel inventoryDescriptionPanel) {
        GameUI.inventoryDescriptionPanel = inventoryDescriptionPanel;
    }

    public static JPanel getEncountersImagePanel() {
        return encountersImagePanel;
    }

    public static void setEncountersImagePanel(JPanel encountersImagePanel) {
        GameUI.encountersImagePanel = encountersImagePanel;
    }

    public static JPanel getPlayerPanel() {
        return playerPanel;
    }

    public static void setPlayerPanel(JPanel playerPanel) {
        GameUI.playerPanel = playerPanel;
    }

    public static JPanel getMainGamePanel() {
        return mainGamePanel;
    }

    public static void setMainGamePanel(JPanel mainGamePanel) {
        GameUI.mainGamePanel = mainGamePanel;
    }

    public static JPanel getDirectionalPanel() {
        return directionalPanel;
    }

    public static void setDirectionalPanel(JPanel directionalPanel) {
        GameUI.directionalPanel = directionalPanel;
    }

    public static JPanel getAreaItemPanel() {
        return areaItemPanel;
    }

    public static void setAreaItemPanel(JPanel areaItemPanel) {
        GameUI.areaItemPanel = areaItemPanel;
    }

    public static JPanel getPlayerInventoryPanel() {
        return playerInventoryPanel;
    }

    public static void setPlayerInventoryPanel(JPanel playerInventoryPanel) {
        GameUI.playerInventoryPanel = playerInventoryPanel;
    }

    public static JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public static void setSettingsPanel(JPanel settingsPanel) {
        GameUI.settingsPanel = settingsPanel;
    }

    public static JLabel getHealthLabel() {
        return healthLabel;
    }

    public static void setHealthLabel(JLabel healthLabel) {
        GameUI.healthLabel = healthLabel;
    }

    public static JLabel getTitleLabel() {
        return titleLabel;
    }

    public static void setTitleLabel(JLabel titleLabel) {
        GameUI.titleLabel = titleLabel;
    }

    public static JLabel getBlankLabel1() {
        return blankLabel1;
    }

    public static void setBlankLabel1(JLabel blankLabel1) {
        GameUI.blankLabel1 = blankLabel1;
    }

    public static JLabel getBlankLabel3() {
        return blankLabel3;
    }

    public static void setBlankLabel3(JLabel blankLabel3) {
        GameUI.blankLabel3 = blankLabel3;
    }

    public static JLabel getBlankLabel5() {
        return blankLabel5;
    }

    public static void setBlankLabel5(JLabel blankLabel5) {
        GameUI.blankLabel5 = blankLabel5;
    }

    public static JLabel getBlankLabel7() {
        return blankLabel7;
    }

    public static void setBlankLabel7(JLabel blankLabel7) {
        GameUI.blankLabel7 = blankLabel7;
    }

    public static JLabel getBlankLabel9() {
        return blankLabel9;
    }

    public static void setBlankLabel9(JLabel blankLabel9) {
        GameUI.blankLabel9 = blankLabel9;
    }

    public static JLabel getEncountersLabel() {
        return encountersLabel;
    }

    public static void setEncountersLabel(JLabel encountersLabel) {
        GameUI.encountersLabel = encountersLabel;
    }

    public static JButton getNorthButton() {
        return northButton;
    }

    public static void setNorthButton(JButton northButton) {
        GameUI.northButton = northButton;
    }

    public static JButton getEastButton() {
        return eastButton;
    }

    public static void setEastButton(JButton eastButton) {
        GameUI.eastButton = eastButton;
    }

    public static JButton getSouthButton() {
        return southButton;
    }

    public static void setSouthButton(JButton southButton) {
        GameUI.southButton = southButton;
    }

    public static JButton getWestButton() {
        return westButton;
    }

    public static void setWestButton(JButton westButton) {
        GameUI.westButton = westButton;
    }

    public static JButton getGetMapButton() {
        return getMapButton;
    }

    public static void setGetMapButton(JButton getMapButton) {
        GameUI.getMapButton = getMapButton;
    }

    public static JButton getStartButton() {
        return startButton;
    }

    public static void setStartButton(JButton startButton) {
        GameUI.startButton = startButton;
    }

    public static JButton getChoice1() {
        return choice1;
    }

    public static void setChoice1(JButton choice1) {
        GameUI.choice1 = choice1;
    }

    public static JButton getChoice2() {
        return choice2;
    }

    public static void setChoice2(JButton choice2) {
        GameUI.choice2 = choice2;
    }

    public static JButton getChoice3() {
        return choice3;
    }

    public static void setChoice3(JButton choice3) {
        GameUI.choice3 = choice3;
    }

    public static JButton getEnterButton() {
        return enterButton;
    }

    public static void setEnterButton(JButton enterButton) {
        GameUI.enterButton = enterButton;
    }

    public static JButton getSettingsButton() {
        return settingsButton;
    }

    public static void setSettingsButton(JButton settingsButton) {
        GameUI.settingsButton = settingsButton;
    }

    public static JButton getHelpButton() {
        return helpButton;
    }

    public static void setHelpButton(JButton helpButton) {
        GameUI.helpButton = helpButton;
    }

    public static JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public static void setMainTextArea(JTextArea mainTextArea) {
        GameUI.mainTextArea = mainTextArea;
    }

    public static JTextArea getEncounterTextArea() {
        return encounterTextArea;
    }

    public static void setEncounterTextArea(JTextArea encounterTextArea) {
        GameUI.encounterTextArea = encounterTextArea;
    }

    public static JTextArea getHelpMenuTextArea() {
        return helpMenuTextArea;
    }

    public static void setHelpMenuTextArea(JTextArea helpMenuTextArea) {
        GameUI.helpMenuTextArea = helpMenuTextArea;
    }

    public static JTextArea getItemDescriptionTextArea() {
        return itemDescriptionTextArea;
    }

    public static void setItemDescriptionTextArea(JTextArea itemDescriptionTextArea) {
        GameUI.itemDescriptionTextArea = itemDescriptionTextArea;
    }

    public static Font getTitleFont() {
        return titleFont;
    }


    public static Font getStandardFont() {
        return standardFont;
    }

    public static Font getSmallFont() {
        return smallFont;
    }

    public static Font getNormalFont() {
        return normalFont;
    }

    public static Font getGameFont() {
        return gameFont;
    }


    public static Font getdPadBoldFont() {
        return dPadBoldFont;
    }


    public static Font getHelpFont() {
        return helpFont;
    }


    public static ActionHandler getActionHandler() {
        return actionHandler;
    }


    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameUI.game = game;
    }
}
