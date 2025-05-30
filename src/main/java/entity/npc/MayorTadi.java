package entity.npc;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.awt.Rectangle;

public class MayorTadi extends NPC {

    public MayorTadi(GamePanel gp) {
        super("Mayor Tadi", 
            List.of("Legend"),
            List.of("Angler", "Crimsonfish", "Glacierfish"),
            List.of("Bullhead", "Carp", "Chub", "Largemouth Bass", "Rainbow Trout", "Sturgeon", "Midnight Carp",
                    "Flounder", "Halibut", "Octopus", "Pufferfish", "Sardine", "Super Cucumber", "Catfish", "Salmon",
                    "Parsnip Seeds", "Cauliflower Seeds", "Potato Seeds", "Wheat Seeds",
                    "Blueberry Seeds", "Tomato Seeds", "Hot Pepper Seeds", "Melon Seeds", "Cranberry Seeds", "Pumpkin Seeds",
                    "Grape Seeds", "Parsnip", "Cauliflower", "Potato", "Wheat", "Blueberry", "Tomato", "Hot Pepper", "Melon",
                    "Cranberry", "Pumpkin", "Grape", "Fish n’ Chips", "Baguette", "Sashimi", "Fugu", "Wine", "Pumpkin Pie",
                    "Veggie Soup", "Fish Stew", "Spakbor Salad", "Fish Sandwich", "The Legends of Spakbor", "Cooked Pig's Head",
                    "Firewood", "Coal", "Egg", "Eggplant", "Gift")
            , gp);
        width = 1;
        height = 2;
        collision = true;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            this.npcImage = ImageIO.read(getClass().getResourceAsStream("/npc/Mayor_Tadi.png"));
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
