package entity.object;

import javax.imageio.ImageIO;
import main.GamePanel;
import java.io.IOException;
import java.awt.Rectangle;

public class OBJ_Stove extends SuperObject {

    public OBJ_Stove(GamePanel gp) {
        super(gp);
        name = "Stove";
        collision = true;
        width = 1;
        height = 1;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Stove.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed){
            gp.gameState = gp.cookingState;
            gp.keyH.enterPressed = false;
        }
    }
    
}
