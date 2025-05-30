package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import main.GamePanel;

public class OBJ_Bed_2 extends SuperObject{
    public OBJ_Bed_2(GamePanel gp){
        super(gp);
        name = "Bed";
        collision = true;
        width = 4;
        height = 6;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Bed2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            gp.playerData.performAction("sleep", null, null);
            gp.keyH.enterPressed = false;
        }
    }
}
