package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Potion extends SuperObject {
    public Object_Potion(GamePanel gp) {
        this.gp = gp;
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_1.png"));
            image = utilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
