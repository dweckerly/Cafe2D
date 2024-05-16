package main;

import entity.NPC_GW;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
//        gp.objects[0] = new Object_Key(gp);
//        gp.objects[0].worldX = 3 * gp.tileSize;
//        gp.objects[0].worldY = 7 * gp.tileSize;
//
//        gp.objects[1] = new Object_Key(gp);
//        gp.objects[1].worldX = 14 * gp.tileSize;
//        gp.objects[1].worldY = 6 * gp.tileSize;
//
//        gp.objects[2] = new Object_Chest(gp);
//        gp.objects[2].worldX = 4 * gp.tileSize;
//        gp.objects[2].worldY = 10 * gp.tileSize;
//
//        gp.objects[3] = new Object_Door(gp);
//        gp.objects[3].worldX = 15 * gp.tileSize;
//        gp.objects[3].worldY = 10 * gp.tileSize;
//
//        gp.objects[4] = new Object_Potion(gp);
//        gp.objects[4].worldX = 2 * gp.tileSize;
//        gp.objects[4].worldY = 10 * gp.tileSize;
    }

    public void setNPCs() {
        gp.npcs[0] = new NPC_GW(gp);
        gp.npcs[0].worldX = 2 * gp.tileSize;
        gp.npcs[0].worldY = 10 * gp.tileSize;
    }
}
