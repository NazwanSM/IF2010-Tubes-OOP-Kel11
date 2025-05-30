package entity.object;

import javax.imageio.ImageIO;

import main.GamePanel;
import java.io.IOException;
import java.awt.Rectangle;

public class OBJ_House extends SuperObject {

    public OBJ_House(GamePanel gp) {
        super(gp);
        name = "House";
        collision = true;
        width = 6;
        height = 6;
        solidArea = new Rectangle(0, 0, width*gp.tileSize, height*gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Home.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialog = "You see a house here. It looks cozy and welcoming.";
    }
}
