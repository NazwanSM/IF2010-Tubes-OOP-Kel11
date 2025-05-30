package entity.object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;
// import java.awt.Color;

public abstract class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public int width = 1, height = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 1, 1);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public GamePanel gp;

    public SuperObject(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX * gp.tileSize - gp.player.worldX + gp.player.screenX;
        int screenY = worldY * gp.tileSize - gp.player.worldY + gp.player.screenY;

        g2.drawImage(
            image,
            screenX,
            screenY,
            gp.tileSize * width,    // width dalam pixel
            gp.tileSize * height,   // height dalam pixel
            null
        );

        // Debugging
        // g2.setColor(Color.RED);
        // g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

    }

    abstract public void interact();
}
