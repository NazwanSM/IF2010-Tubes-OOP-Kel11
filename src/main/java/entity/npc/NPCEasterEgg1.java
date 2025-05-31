package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class NPCEasterEgg1 extends NPC {

    public NPCEasterEgg1(GamePanel gp) {
        super("NPCEasterEgg1",
            List.of("Egg"),
            List.of("Eggplant"),
            List.of("Cooked Pig's Head")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/NPCEasterEgg1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            String message = "Berhasil sampai sini? Gila, saya kira kamu nyangkut\ndi stack overflow! Selamat ya, debug saya berhasil!\nEh, atau ini cuma mimpi?";
            gp.ui.currentDialog = message;
            gp.gameState = gp.dialogueState;
        }
    }
    
}
