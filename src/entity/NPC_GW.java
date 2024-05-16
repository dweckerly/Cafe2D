package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_GW extends Entity {
    public NPC_GW(GamePanel gp) {
        super(gp);
        direction = "down";
        speed = 2;

        getNPCImages();
        setDialogue();
    }

    public void getNPCImages() {
        up1 = setupImage("/npc/gw_up_1.png");
        up2 = setupImage("/npc/gw_up_1.png");
        down1 = setupImage("/npc/gw_down_1.png");
        down2 = setupImage("/npc/gw_down_1.png");
        left1 = setupImage("/npc/gw_left_1.png");
        left2 = setupImage("/npc/gw_left_1.png");
        right1 = setupImage("/npc/gw_right_1.png");
        right2 = setupImage("/npc/gw_right_1.png");
    }

    public void setDialogue() {
        dialogues[0] = "Boy howdy!";
        dialogues[1] = "What a hot day... I like ice cubes";
        dialogues[2] = "But they make my hands so cold...";
        dialogues[3] = "Wish I had a little sweet kitten to warm them up and slip it in my mouth.";
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i  = random.nextInt(100);
            if (i < 25) {
                direction = "up";
            }
            else if (i < 50) {
                direction = "down";
            }
            else if (i < 75) {
                direction = "left";
            }
            else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }

}
