package main;

import object.Object_Chest;
import object.Object_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.objects[0] = new Object_Key();
        gp.objects[0].worldX = 3 * gp.tileSize;
        gp.objects[0].worldY = 7 * gp.tileSize;

        gp.objects[1] = new Object_Key();
        gp.objects[1].worldX = 14 * gp.tileSize;
        gp.objects[1].worldY = 6 * gp.tileSize;

        gp.objects[2] = new Object_Chest();
        gp.objects[2].worldX = 4 * gp.tileSize;
        gp.objects[2].worldY = 10 * gp.tileSize;
    }
}
