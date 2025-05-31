package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class NPCEasterEgg6 extends NPC {

    public NPCEasterEgg6(GamePanel gp) {
        super("NPCEasterEgg6",
            List.of("Egg"),
            List.of("Eggplant"),
            List.of("Cooked Pig's Head")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/NPCEasterEgg6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            String message = "Kita resmi menamatkan SPAKBOR HILLS!!!";
            gp.ui.currentDialog = message;
            gp.gameState = gp.dialogueState;
            gp.playSE(24);
        }
    }
    
}
