package com.game.templejog.client;

import com.game.templejog.Game;
import com.game.templejog.Temple;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GameUI {
    static JFrame window;
    static Container container;
   static JPanel titleNamePanel, startButtonPanel, quitButtonPanel, mainTextPanel, difficultyPanel, enterPanel;
    static JLabel titleLabel;
    static Font titleFont = new Font("Arial", Font.PLAIN, 90);//cutomize font
    static Font standardFont = new Font("Times New Roman", Font.PLAIN, 30);
    static Font smallFont = new Font("Times New Roman", Font.PLAIN, 15);
    static JButton startButton, quitButton, choice1, choice2, choice3, enterButton;
    static JTextArea mainText;

    static TitleScreenHandler tsHandler = new TitleScreenHandler();
    static DifficultyHandler diffHandler = new DifficultyHandler();

public GameUI() {
    window = new JFrame("Temple Jog");
    window.setSize(800, 600);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close when window is closed
    window.getContentPane().setBackground(Color.BLACK);
    window.setLayout(null);
    window.setVisible(true);
    container = window.getContentPane();

    titleNamePanel = new JPanel();
    titleNamePanel.setBounds(100,100,600,150); //x and y axis starting top left. width starting from xy axis. height starting from top.
    titleNamePanel.setBackground(Color.BLUE);
    titleLabel = new JLabel("Temple Jog"); //text label
    titleLabel.setForeground(Color.WHITE);// text color
    titleLabel.setFont(titleFont);

    startButtonPanel = new JPanel();
    startButtonPanel.setBounds(250,300,200,75);
    startButtonPanel.setBackground(Color.GREEN);

    startButton = new JButton("New Game");
    startButton.setBackground(Color.BLACK);
    startButton.setForeground(Color.WHITE);
    startButton.setFont(standardFont);
    //functionality of start button
    startButton.addActionListener(tsHandler);
    startButton.setFocusPainted(false);

    quitButtonPanel = new JPanel();
    quitButtonPanel.setBounds(450,300,100,75);
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
}


public static void difficultyScreen() {
    titleNamePanel.setVisible(false);
    startButtonPanel.setVisible(false);
    quitButtonPanel.setVisible(false);
    mainTextPanel = new JPanel();
    mainTextPanel.setBounds(100,0,600,400);
    mainTextPanel.setBackground(Color.BLACK);
    container.add(mainTextPanel);

    mainText = new JTextArea("Choose your difficulty: ");
    mainText.setBounds(100,0,600,400);
    mainText.setBackground(Color.BLACK);
    mainText.setForeground(Color.WHITE);
    mainText.setFont(standardFont);
    mainText.setLineWrap(true);
    mainTextPanel.add(mainText);

    difficultyPanel = new JPanel();
    difficultyPanel.setBounds(250,300,300,150);
    difficultyPanel.setBackground(Color.GREEN);
    difficultyPanel.setLayout(new GridLayout(3,1));
    container.add(difficultyPanel);

    choice1 = new JButton("choice1");
    choice1.setBackground(Color.BLACK);
    choice1.setForeground(Color.WHITE);
    choice1.setFont(standardFont);
    choice1.setFocusPainted(false);
    choice1.addActionListener(diffHandler);
    choice1.setActionCommand("c1");
    difficultyPanel.add(choice1);

    choice2 = new JButton("choice2");
    choice2.setBackground(Color.BLACK);
    choice2.setForeground(Color.WHITE);
    choice2.setFont(standardFont);
    choice2.setFocusPainted(false);
    choice2.addActionListener(diffHandler);
    choice2.setActionCommand("c2");
    difficultyPanel.add(choice2);

    choice3 = new JButton("choice3");
    choice3.setBackground(Color.BLACK);
    choice3.setForeground(Color.WHITE);
    choice3.setFont(standardFont);
    choice3.setFocusPainted(false);
    choice3.addActionListener(diffHandler);
    choice3.setActionCommand("c3");
    difficultyPanel.add(choice3);
    difficultySettings();
}

public static void difficultySettings() {
    mainText.setText("Choose your difficulty: ");
    choice1.setText("Easy");
    choice2.setText("Medium");
    choice3.setText("Hard");
}
public static void easyGame() throws InterruptedException, IOException {
    choice1.setVisible(false);
    choice2.setVisible(false);
    choice3.setVisible(false);
    difficultyPanel.setVisible(false);

    Temple gameFiles = FileLoader.jsonLoader("JSON/gameFiles.json");
    Game game = new Game(gameFiles);
    String intro = game.getGameText().get("intro");
    mainText.setFont(smallFont);
    mainText.setText(intro);

    enterPanel = new JPanel();
    enterPanel.setBounds(350,400,100,100);
    enterPanel.setBackground(Color.RED);
    container.add(enterPanel);

    enterButton = new JButton("Enter");
    enterButton.setBackground(Color.BLACK);
    enterButton.setForeground(Color.WHITE);
    enterButton.setFont(standardFont);
    enterButton.setFocusPainted(false);

    enterPanel.add(enterButton);

}
    public static void normalGame() {
        mainText.setText("You have chosen: Normal");
    }
    public static void hardGame() {
        mainText.setText("You have chosen: Hard");
    }


    public static void main(String[] args) {
        new GameUI();
    }

}
