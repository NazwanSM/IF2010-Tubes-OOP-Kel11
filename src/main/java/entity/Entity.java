package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
    
    public GamePanel gp;
    public int worldX,worldY;
    public int speed;
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteDirection = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    String dialogues[] = new String[20];

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
}
