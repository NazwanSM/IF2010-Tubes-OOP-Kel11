package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;

public class UI {
    
    GamePanel gp;
    Font helv_40, helv_60B;
    BufferedImage staminaBar;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false; // ini buat nanti kalau udah ending

    double playTime;
    

    public UI(GamePanel gp) {
        this.gp = gp;
        helv_40 = new Font("Helvetica", Font.PLAIN, 40);
        helv_60B = new Font("Helvetica", Font.BOLD, 60);
        // stamina bar blom dibkin
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished){
            g2.setFont(helv_40);
            g2.setColor(Color.white);
            
            String text;
            int textLength;
            int x;
            int y;

            text = "Game Over"; // ini contoh doang
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

        }
        else {
            g2.setFont(helv_40);
            g2.setColor(Color.white);
            // g2.drawImage(staminaBar, gp.tilesSize/2, gp.tileSize, gp.tileSize, null); stamina bar blom dibkin
            g2.drawString("Stamina", 74, 65);

            // TIME
            playTime += (double) 1 / 60; // ini nanti mah buat DAY
            // g2.drawString("Time")
    
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, 25, 100);
                messageOn = false;
    
                messageCounter++;
    
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
