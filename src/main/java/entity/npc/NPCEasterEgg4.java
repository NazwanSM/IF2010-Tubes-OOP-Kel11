package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class NPCEasterEgg4 extends NPC {

    public NPCEasterEgg4(GamePanel gp) {
        super("NPCEasterEgg4",
            List.of("Egg"),
            List.of("Eggplant"),
            List.of("Cooked Pig's Head")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/NPCEasterEgg4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            String message = "Huft, kamu berhasil lewat sini? Selamat! Katanya\nresponsive design-nya kok gini amat, tapi katanya\n'ini fitur, bukan bug'. Yah, sudahlah.";
            gp.ui.currentDialog = message;
            gp.gameState = gp.dialogueState;
        }
    }
    
}
