package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.GamePanel;

public abstract class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public int width = 1, height = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 1, 1);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
    // Hitung posisi di layar relatif terhadap player
        int screenX = x * gp.tileSize - gp.player.x + gp.player.x;
        int screenY = y * gp.tileSize - gp.player.y + gp.player.y;

        // Gambar satu kali, skalakan sesuai ukuran object (dalam tile)
        g2.drawImage(
            image,
            screenX,
            screenY,
            gp.tileSize * width,    // width dalam pixel
            gp.tileSize * height,   // height dalam pixel
            null
        );
    }
}
