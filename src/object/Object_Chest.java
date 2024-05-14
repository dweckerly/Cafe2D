package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Chest extends SuperObject {
    public Object_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest-1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
