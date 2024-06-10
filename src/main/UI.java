package main;

import object.Object_Heart;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font baseFont;
    BufferedImage heartFull, heart3_4, heartHalf, heart1_4, heartEmpty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageTime = 120;

    public boolean gameFinished = false;

    public String currentDialogue = "";

    public int commandNumber = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/base.ttf");
            baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Object_Heart hearts = new Object_Heart(gp);
        heartFull = hearts.image;
        heart3_4 = hearts.image2;
        heartHalf = hearts.image3;
        heart1_4 = hearts.image4;
        heartEmpty = hearts.image5;
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(baseFont);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayerHealth();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerHealth();
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawPlayerHealth();
            drawDialogueScreen();
        }
    }

    public void drawPlayerHealth() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        // DRAW MAX LIFE
        while (i < gp.player.maxHealth / 4) {
            g2.drawImage(heartEmpty, x, y, null);
            x += gp.tileSize;
            i++;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        // DRAW CURRENT LIFE
        while (i < gp.player.health) {
            g2.drawImage(heart1_4, x, y, null);
            i++;
            if (i < gp.player.health) {
                g2.drawImage(heartHalf, x, y, null);
                i++;
                if (i < gp.player.health) {
                    g2.drawImage(heart3_4, x, y, null);
                    i++;
                    if (i < gp.player.health) {
                        g2.drawImage(heartFull, x, y, null);
                    }
                }
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "Game Title";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 3;
        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+4, y+4);
        // MAIN TEXT COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // SOME IMAGE
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/pot-1.png")));
            g2.drawImage(image, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        } catch (IOException e) { e.printStackTrace(); }

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));

        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);

        if (commandNumber == 0) {
            g2.drawString(">", x - (gp.tileSize / 2), y);
        }

        text = "CONTINUE";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        if (commandNumber == 1) {
            g2.drawString(">", x - (gp.tileSize / 2), y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        
        if (commandNumber == 2) {
            g2.drawString(">", x - (gp.tileSize / 2), y);
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawUIWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (float) gp.tileSize / 2));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += gp.tileSize / 2;
        }
    }

    public void drawUIWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255, 255, 255, 200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (length / 2);
    }

    public String createWrappedText(String text) {
        int windowWidth = gp.tileSize * 10;
        // need to set font again for proper text length calculation.
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (float) gp.tileSize / 2));
        StringBuilder output = new StringBuilder();
        String temp = "";
        int length = 0;
        for (String word : text.split(" ")) {
            if (temp.isEmpty()) {
                temp = word + " ";
            } else {
                temp += word + " ";
            }
            length = (int) g2.getFontMetrics().getStringBounds(temp, g2).getWidth();
            if (length > windowWidth) {
                output.append("\n").append(word).append(" ");
                temp = word + " ";
            }
            else {
                output.append(word).append(" ");
            }
        }
        return output.toString();
    }
}
