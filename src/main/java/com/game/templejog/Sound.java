package com.game.templejog;

import com.game.templejog.client.Main;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private static Clip clip;

    /* Handles the background theme music */
    public static void themeSound(String file) {
        try {
            InputStream landingSound = Main.class.getClassLoader().getResourceAsStream(file);
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

    public static void stopSound(){
        clip.stop();
    }
}
