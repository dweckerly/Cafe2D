package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Object_Door extends SuperObject {
    public Object_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
