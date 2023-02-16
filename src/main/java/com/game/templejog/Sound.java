package com.game.templejog;

import com.game.templejog.client.Main;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Sound {
    private static Clip clip;
    public static float currentVolume = 0;
    private static FloatControl floatControl;

    void Sound() {
        volumeUp();
        volumeDown();
    }

    /* Handles the background theme music */
    public static void themeSound(String file) {
        try {
            URL landingSound = Main.class.getClassLoader().getResource(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(landingSound);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        }
    }

    public static void onceSound(String file) {
        try {
            URL landingSound = Main.class.getClassLoader().getResource(file);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(landingSound);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        }
    }

    public static String turningSound(String noun, Game game) {
        if (noun.isEmpty()) {
            return InvalidNounInput.BAD_SOUND.getWarning();
        } else if (noun.equalsIgnoreCase("on")) {
            game.setPlaySound(true);
            Sound.themeSound(game.getCurrentRoom().getSound());
        } else if (noun.equalsIgnoreCase("off")) {
            Sound.stopSound();
            game.setPlaySound(false);
        }
        return "Turning sound " + noun;
    }

    public static void gameSound(Scanner scanner, Game game) {
        System.out.println(UserInput.TURN_MUSIC.getUserPrompt());
        String musicOn = scanner.nextLine();
        if (musicOn.equalsIgnoreCase("y")) {
            game.setPlaySound(true);
            Sound.themeSound("sounds/background_music.wav");
        } else {
            game.setPlaySound(false);
        }
    }

    public static void stopSound() {
        if (clip != null) {
            clip.stop();
        }
    }

    public static void volumeUp() {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        if (currentVolume < 6.0f) {
            gainControl.setValue(+1.0f); // Reduce volume by 10 decibels.
        }
    }

    public static void volumeDown() {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
    }

    public static void ending(String filePath) {
        try {
            stopSound();
            URL sound = Main.class.getClassLoader().getResource(filePath);
            AudioInputStream audiostream = AudioSystem.getAudioInputStream(sound);
            clip = AudioSystem.getClip();
            clip.open(audiostream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error: Unsupported audio file format.");
        } catch (IOException e) {
            System.out.println("Error: Could not read audio file.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Could not play audio clip.");
        }
    }


    public static void gameIntro(Game game) {
        game.setPlaySound(true);
        Sound.themeSound("sounds/background_music.wav");
    }

    public static void Title() {
        Sound.themeSound("sounds/background_music.wav");
    }

    public static void wrongWaySound() {
        Sound.onceSound("sounds/wrong_way.wav");
    }

    public static void wrongItemSound() {
        Sound.onceSound("sounds/ineffective_item.wav");
    }

    public static float getCurrentVolume() {
        return currentVolume;
    }

    public static void setCurrentVolume(float currentVolume) {
        Sound.currentVolume = currentVolume;
    }
}

//    public static FloatControl getFloatControl() {
//        return floatControl;
//    }
//
//    public void setFloatControl(FloatControl floatControl) {
//        this.floatControl = floatControl;
//    }
//}
