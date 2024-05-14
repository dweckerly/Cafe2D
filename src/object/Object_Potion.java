package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Potion extends SuperObject {
    public Object_Potion() {
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
