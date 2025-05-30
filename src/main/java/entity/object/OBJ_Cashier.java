package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Rectangle;
import main.GamePanel;

public class OBJ_Cashier extends SuperObject{
    public OBJ_Cashier(GamePanel gp){
        super(gp);
        name = "Cashier";
        collision = true;
        width = 2;
        height = 2;
        solidArea = new Rectangle(0, 0, width * gp.tileSize, height * gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Cashier.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.buyingState;
            gp.keyH.enterPressed = false;
        }
    }
}
