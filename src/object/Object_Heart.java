package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object_Heart extends SuperObject {

    public BufferedImage image2, image3, image4, image5;

    public Object_Heart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_3-4.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_1-4.png"));
            image5 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_empty.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = utilityTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = utilityTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            image4 = utilityTool.scaleImage(image4, gp.tileSize, gp.tileSize);
            image5 = utilityTool.scaleImage(image5, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
