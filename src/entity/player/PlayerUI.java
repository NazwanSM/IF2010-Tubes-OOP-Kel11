package entity.player;

import java.awt.Graphics2D;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import entity.Entity;

import java.io.IOException;
import java.awt.Rectangle;
public class PlayerUI extends Entity {
    KeyHandler keyH;
    Player player;

    public PlayerUI (GamePanel gp, KeyHandler keyH, Player player) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.player = player;

        solidArea = new Rectangle();
        solidArea.x = 3*2;
        solidArea.y = 16*2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 10*2;
        solidArea.height = 14*2 - 2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage () {
        up1 = setup("Up1");
        up2 = setup("Up2");
        up3 = setup("Up3");
        down1 = setup("Down1");
        down2 = setup("Down2");
        down3 = setup("Down3");
        left1 = setup("Left1");
        left2 = setup("Left2");
        left3 = setup("Left3");
        right1 = setup("Right1");
        right2 = setup("Right2");
        right3 = setup("Right3");
    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/player/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize * 2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            }
            else if (keyH.downPressed == true) {
                direction = "down";
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
            }
        
            // Check for collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }
    
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum += spriteDirection;
                if (spriteNum == 3) {
                    spriteDirection = -1;
                } else if (spriteNum == 2) {
                    spriteDirection = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            spriteNum = 1;
            spriteCounter = 0;
            spriteDirection = 1;
        }
    }

    // public void INTERACT (bkin fungsi interact barang barang yang disentuh di sini)
    // gp.ui.showMessage("You have picked up " + gp.objects[objIndex].name); buat bkin notifikasi

    public void draw(Graphics2D g2) {
        BufferedImage image = null;


        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                else if (spriteNum == 2) {
                    image = up2;
                }
                else if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                else if (spriteNum == 2) {
                    image = down2;
                }
                else if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                else if (spriteNum == 2) {
                    image = left2;
                }
                else if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                else if (spriteNum == 2) {
                    image = right2;
                }
                else if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, x, y, null);
    }
}
