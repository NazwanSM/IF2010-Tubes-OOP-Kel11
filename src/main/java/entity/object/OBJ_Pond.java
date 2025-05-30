package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import main.GamePanel;

public class OBJ_Pond extends SuperObject {
    public OBJ_Pond(GamePanel gp) {
        super(gp);
        name = "Pond";
        collision = true;
        width = 4;
        height = 3;
        solidArea = new Rectangle(0, 0, width*gp.tileSize, height*gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Pond.png"));
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
