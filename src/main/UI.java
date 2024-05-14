package main;

import object.Object_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageTime = 120;

    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        Object_Key key = new Object_Key();
        keyImage = key.image;
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        if (gameFinished) {
            String text;
            int textLength, x, y;

            text = "You found the treasure!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = (gp.screenWidth / 2) - (textLength / 2);
            y = gp.screenHeight / 2;

            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(String.valueOf(gp.player.hasKey), 100, 80);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30f));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize / 2);
                messageCounter++;
                if (messageCounter > messageTime) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
