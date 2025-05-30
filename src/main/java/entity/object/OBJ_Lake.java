package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import main.GamePanel;

public class OBJ_Lake extends SuperObject {

    public OBJ_Lake(GamePanel gp) {
        super(gp);
        name = "Lake";
        collision = true;
        width = 1;
        height = 1;
        solidArea = new Rectangle(0, 0, width*gp.tileSize, height*gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Lake.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            gp.playerData.performAction("fishing", null, null);
            gp.keyH.enterPressed = false;
        }
    }
}
