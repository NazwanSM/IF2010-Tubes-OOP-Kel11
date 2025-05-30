package entity.object;

import javax.imageio.ImageIO;

import main.GamePanel;

import java.io.IOException;
import java.awt.Rectangle;

public class OBJ_ShippingBin extends SuperObject {
    public OBJ_ShippingBin(GamePanel gp) {
        super(gp);
        name = "Shipping Bin";
        collision = true;
        width = 3;
        height = 2;
        solidArea = new Rectangle(0, 0, width*gp.tileSize, height*gp.tileSize);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/shippingbin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interact() {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.sellingState;
            gp.keyH.enterPressed = false;
        }
    }
}
