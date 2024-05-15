package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidAreaDefaultX = gp.tileSize / 6;
        solidAreaDefaultY = gp.tileSize / 3;

        solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, (int) (gp.tileSize / 1.5), (int) (gp.tileSize / 1.5));

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = setupPlayerImage("player_up_1.png");
        up2 = setupPlayerImage("player_up_2.png");
        down1 = setupPlayerImage("player_down_1.png");
        down2 = setupPlayerImage("player_down_2.png");
        left1 = setupPlayerImage("player_left_1.png");
        left2 = setupPlayerImage("player_left_2.png");
        right1 = setupPlayerImage("player_right_1.png");
        right2 = setupPlayerImage("player_right_2.png");
    }

    public BufferedImage setupPlayerImage(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image =  ImageIO.read(getClass().getResourceAsStream("/player/" + imageName));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.downPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.objects[index].name;
            switch (objectName) {
                case "Key":
                    hasKey++;
                    gp.objects[index] = null;
                    gp.ui.showMessage("You picked up " + objectName);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        hasKey--;
                        gp.objects[index] = null;
                        gp.ui.showMessage("You opened the " + objectName);
                    } else {
                        gp.ui.showMessage("You need a Key");
                    }
                    break;
                case "Chest":
                    if (hasKey > 0) {
                        //gp.stopMusic();
                        gp.ui.gameFinished = true;
                    }
                    break;
                case "Potion":
                    speed += 1;
                    gp.objects[index] = null;
                    gp.ui.showMessage("You picked up " + objectName);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            default:
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }
}
