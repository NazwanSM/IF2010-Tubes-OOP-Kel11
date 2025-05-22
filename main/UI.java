package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Color;
import java.awt.BasicStroke;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font helv_40, helv_60B;
    BufferedImage staminaBar;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false; // ini buat nanti kalau udah ending
    public String curretDialog = "";
    

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

        // if (gameFinished){
        //     g2.setFont(helv_40);
        //     g2.setColor(Color.white);
            
        //     String text;
        //     int textLength;
        //     int x;
        //     int y;

        //     text = "Game Over"; // ini contoh doang
        //     textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        //     x = gp.screenWidth / 2 - textLength / 2;
        //     y = gp.screenHeight / 2 - (gp.tileSize*3);
        //     g2.drawString(text, x, y);

        // }
        // else {
        //     g2.setFont(helv_40);
        //     g2.setColor(Color.white);
        //     // g2.drawImage(staminaBar, gp.tilesSize/2, gp.tileSize, gp.tileSize, null); stamina bar blom dibkin
        //     g2.drawString("Stamina", 74, 65);

        //     // TIME
        //     playTime += (double) 1 / 60; // ini nanti mah buat DAY
        //     // g2.drawString("Time")
    
        //     if (messageOn) {
        //         g2.setFont(g2.getFont().deriveFont(30F));
        //         g2.drawString(message, 25, 100);
        //         messageOn = false;
    
        //         messageCounter++;
    
        //         if (messageCounter > 120) {
        //             messageCounter = 0;
        //             messageOn = false;
        //         }
        //     }
        // }

        this.g2 = g2;
        g2.setFont(helv_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState) {

        }
        else if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        else if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(helv_60B);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    
    public void drawDialogueScreen(){
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(curretDialog, x, y);

        for (String line : curretDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        
        Color c = new Color(15,15,15,199);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(81,64,58,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}
