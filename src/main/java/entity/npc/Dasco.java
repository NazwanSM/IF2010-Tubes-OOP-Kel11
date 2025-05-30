package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class Dasco extends NPC {

    public Dasco(GamePanel gp) {
        super("Dasco",
            List.of("The Legends of Spakbor", "Cooked Pig's Head", "Wine", "Fugu", "Spakbor Salad"),
            List.of("Fish Sandwich", "Fish Stew", "Baguette", "Fish n' Chips"),
            List.of("Legend", "Grape", "Cauliflower", "Wheat", "Pufferfish", "Salmon"),
            gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/Dasco.png"));
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
