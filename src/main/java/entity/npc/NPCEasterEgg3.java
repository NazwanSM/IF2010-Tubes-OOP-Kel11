package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class NPCEasterEgg3 extends NPC {

    public NPCEasterEgg3(GamePanel gp) {
        super("NPCEasterEgg3",
            List.of("Egg"),
            List.of("Eggplant"),
            List.of("Cooked Pig's Head")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/NPCEasterEgg3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            String message = "Woah, sudah selesai? Mantap! Selamat ya, kawan! Ini\nnamanya ketekunan, tidak seperti saya yang malah\nngoding game di tengah tugas besar. Eh, ini bukan game saya lho ya!";
            gp.ui.currentDialog = message;
            gp.gameState = gp.dialogueState;
        }
    }
    
}
