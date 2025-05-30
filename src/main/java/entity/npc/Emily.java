package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class Emily extends NPC {

    public Emily(GamePanel gp) {
        super("Emily",
            List.of("Parsnip Seeds", "Cauliflower Seeds", "Potato Seeds", "Wheat Seeds", "Blueberry Seeds",
                    "Tomato Seeds", "Hot Pepper Seeds", "Melon Seeds", "Cranberry Seeds", "Pumpkin Seeds", "Grape Seeds"),
            List.of("Catfish", "Salmon", "Sardine"),
            List.of("Coal", "Wood")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/Emily.png"));
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
