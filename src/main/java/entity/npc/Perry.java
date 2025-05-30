package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class Perry extends NPC {

    public Perry(GamePanel gp) {
        super("Perry",
            List.of("Cranberry", "Blueberry"),
            List.of("Wine"),
            List.of("Bullhead", "Carp", "Chub", "Largemouth Bass", "Rainbow Trout", "Sturgeon",
                    "Midnight Carp", "Flounder", "Halibut", "Octopus", "Pufferfish", "Sardine",
                    "Super Cucumber", "Catfish", "Salmon", "Angler", "Crimsonfish", "Glacierfish", "Legend")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/Perry.png"));
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
