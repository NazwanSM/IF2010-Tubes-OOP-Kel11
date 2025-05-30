package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class Abigail extends NPC {

    public Abigail(GamePanel gp) {
        super("Abigail",
            List.of("Blueberry", "Melon", "Pumpkin", "Grape", "Cranberry"),
            List.of("Baguette", "Pumpkin Pie", "Wine"),
            List.of("Hot Pepper", "Cauliflower", "Parsnip", "Wheat")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/Abigail.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.npcState;
            gp.keyH.enterPressed = false;
        }
    }
    
}
