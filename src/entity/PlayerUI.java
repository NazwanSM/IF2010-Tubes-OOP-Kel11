package entity;

import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerUI extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public PlayerUI (GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

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
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/resource/player/Right3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize * 2, null);
    }
}
