package com.game.templejog;

import com.game.templejog.client.Main;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Sound {
    private static Clip clip;
    private static Clip fx;
    public static float currentVolume = 0;
    private static boolean soundFx = true;


    /* Handles the background theme music */
    public static void themeSound(String file) {
        try {
            //noinspection ConstantConditions
            InputStream landingSound = new BufferedInputStream(Main.class.getClassLoader().getResourceAsStream(file));
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
            //noinspection ConstantConditions
            InputStream landingSound = new BufferedInputStream(Main.class.getClassLoader().getResourceAsStream(file));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(landingSound);
            fx = AudioSystem.getClip();
            fx.open(audioStream);
            FloatControl gainControl =
                    (FloatControl) fx.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
            fx.start();
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
            Game.setPlaySound(true);
            Sound.themeSound(Game.getCurrentRoom().getSound());
        } else if (noun.equalsIgnoreCase("off")) {
            Sound.stopSound();
            Game.setPlaySound(false);
        }
        return "Turning sound " + noun;
    }

    public static void gameSound(Scanner scanner, Game game) {
        System.out.println(UserInput.TURN_MUSIC.getUserPrompt());
        String musicOn = scanner.nextLine();
        if (musicOn.equalsIgnoreCase("y")) {
            Game.setPlaySound(true);
            Sound.themeSound("sounds/background_music.wav");
        } else {
            Sound.stopSound();
            Game.setPlaySound(false);
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
            //noinspection ConstantConditions
            InputStream sound = new BufferedInputStream(Main.class.getClassLoader().getResourceAsStream(filePath));
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
        Game.setPlaySound(true);
        Sound.themeSound("sounds/background_music.wav");
    }

    public static void Title() {
        Sound.themeSound("sounds/background_music.wav");
    }

    public static void wrongWaySound() {
        if (soundFx) {
            Sound.onceSound("sounds/wrong_way.wav");
        }
    }

    public static void wrongItemSound() {
        if (soundFx) {
            Sound.onceSound("sounds/ineffective_item.wav");
        }
    }

    public static void getToTheChopper(){
        if (soundFx){
            Sound.onceSound("sounds/get-to-chopper.wav");
        }
    }

    public static float getCurrentVolume() {
        return currentVolume;
    }

    public static void setCurrentVolume(float currentVolume) {
        Sound.currentVolume = currentVolume;
    }

    public static boolean isSoundFx() {
        return soundFx;
    }

    public static void setSoundFx(boolean soundFx) {
        Sound.soundFx = soundFx;
    }
}

