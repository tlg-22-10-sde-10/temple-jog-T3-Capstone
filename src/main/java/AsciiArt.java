/**
 * Appropriated from: https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-console
 */

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class AsciiArt {
    public AsciiArt() {
    }

    public String drawString(String text, String artChar) {
        Font font = new Font("arial",0,16);
        Settings settings = new Settings(font,80,11);
        BufferedImage image = getImageIntegerMode(settings.width, settings.height);

        Graphics2D graphics2D = getGraphics2D(image.getGraphics(), settings);
        graphics2D.drawString(text, 6, ((int) (settings.height * 0.67)));
        StringBuilder finalString = new StringBuilder();
        finalString.append("\033[0;32m");
        for (int y = 0; y < settings.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < settings.width; x++) {
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? artChar : " ");
            }

            if (stringBuilder.toString()
                    .trim()
                    .isEmpty()) {
                continue;
            }

            finalString.append(stringBuilder).append('\n');
        }
        finalString.append("\033[0m");
        return finalString.toString();
    }

    private BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics, Settings settings) {
        graphics.setFont(settings.font);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    public class Settings {
        public Font font;
        public int width;
        public int height;

        public Settings(Font font, int width, int height) {
            this.font = font;
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        AsciiArt ascii = new AsciiArt();
        String text = "TEMPLE";
        String artChar = "█";
        String result = ascii.drawString(text,artChar);
        System.out.println(result);
    }
}