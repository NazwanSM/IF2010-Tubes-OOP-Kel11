package main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.awt.BasicStroke;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import items.Items;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font stdw, titleFont;
    BufferedImage staminaBar;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false; // ini buat nanti kalau udah ending
    public String curretDialog = "";
    private BufferedImage titleScreenBackground;
    private BufferedImage newGame;
    private BufferedImage loadGame;
    private BufferedImage exitGame;
    private BufferedImage newGameSelected;
    private BufferedImage loadGameSelected;
    private BufferedImage exitGameSelected;
    private BufferedImage help;
    private BufferedImage helpSelected;
    private BufferedImage player;
    private BufferedImage playerSelected;
    private BufferedImage object;
    private BufferedImage objectSelected;
    private BufferedImage stats;
    private BufferedImage statsSelected;
    private BufferedImage action;
    private BufferedImage actionSelected;
    private BufferedImage credits;
    private BufferedImage creditsSelected;
    private BufferedImage back;
    private BufferedImage backSelected;
    // nanti ada save game tapi blom dibkin mager
    private BufferedImage inventoryPanel;
    private BufferedImage inventorySelected;
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;

    

    public UI(GamePanel gp) {
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("/resource/font/StardewValley.ttf");
            stdw = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/resource/font/TitleFont.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is);
            InputStream bgIs = getClass().getResourceAsStream("/resource/ui/TitleBackground.png");
            titleScreenBackground = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/NewGame.png");
            newGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/LoadGame.png");
            loadGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/ExitGame.png");
            exitGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/NewGameSelected.png");
            newGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/LoadGameSelected.png");
            loadGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/ExitGameSelected.png");
            exitGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Help.png");
            help = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/HelpSelected.png");
            helpSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Player.png");
            player = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/PlayerSelected.png");
            playerSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Object.png");
            object = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/ObjectSelected.png");
            objectSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Stats.png");
            stats = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/StatsSelected.png");
            statsSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Action.png");
            action = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/ActionSelected.png");
            actionSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Credits.png");
            credits = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/CreditsSelected.png");
            creditsSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/Back.png");
            back = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/BackSelected.png");
            backSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/InventoryPanel.png");
            inventoryPanel = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/resource/ui/InventorySelected.png");
            inventorySelected = ImageIO.read(bgIs);
        }
        catch (FontFormatException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // stamina bar blom dibkin
    }

    public void showMessage(String text) { // ini harus disesuain lagi
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(stdw.deriveFont(Font.PLAIN, 60F));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        else if(gp.gameState == gp.playState) {

        }
        else if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        else if(gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        else if(gp.gameState == gp.statsDisplayState) {
            drawStatsScreen();
        }
        else if(gp.gameState == gp.inventoryState) {
            drawInventory();
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {

            g2.drawImage(titleScreenBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
            
            int x = gp.screenWidth / 2 - gp.tileSize / 2;
            int y = gp.tileSize * 6;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize, gp.tileSize*2, null);
    
            x = gp.screenWidth / 2 - gp.tileSize * 5 + gp.tileSize / 4;
            y = gp.tileSize * 8;
            g2.drawImage(newGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 0) {
                g2.drawImage(newGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize * 2 - gp.tileSize / 4;
            y = gp.tileSize * 8;
            g2.drawImage(loadGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 1) {
                g2.drawImage(loadGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize / 4;
            y = gp.tileSize * 8;
            g2.drawImage(help, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 2) {
                g2.drawImage(helpSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize * 3 - gp.tileSize / 4;
            y = gp.tileSize * 8;
            g2.drawImage(action, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 3) {
                g2.drawImage(actionSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize * 5 + gp.tileSize / 4;
            y = gp.tileSize * 10;
            g2.drawImage(player, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 4) {
                g2.drawImage(playerSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize * 2 - gp.tileSize / 4;
            y = gp.tileSize * 10;
            g2.drawImage(object, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 5) {
                g2.drawImage(objectSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize / 4;
            y = gp.tileSize * 10;
            g2.drawImage(stats, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 6) {
                g2.drawImage(statsSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize * 3 - gp.tileSize / 4;
            y = gp.tileSize * 10;
            g2.drawImage(exitGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 7) {
                g2.drawImage(exitGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize * 7 - gp.tileSize / 4;
            y = gp.tileSize * 6 + gp.tileSize * 3 + gp.tileSize / 4;
            g2.drawImage(credits, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            if (commandNum == 8) { 
                g2.drawImage(creditsSelected, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize * 5;
            y = gp.tileSize * 6 + gp.tileSize * 3 + gp.tileSize / 4;
            g2.drawImage(back, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            if (commandNum == 9) { 
                g2.drawImage(backSelected, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            }
        }

        else if (titleScreenState == 1){

        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
        
    }
    
    public void drawDialogueScreen() {
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

    public void drawStatsScreen() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.screenHeight - (gp.tileSize * 4);
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Statistics", x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        y += gp.tileSize;
        g2.drawString("Total Income: " + gp.statisticProvider.getTotalIncome(), x, y);
        y += gp.tileSize;
        g2.drawString("Total Expenditure: " + gp.statisticProvider.getTotalExpenditure(), x, y);
        y += gp.tileSize;
        g2.drawString("Total Days Played: " + gp.statisticProvider.getTotalDaysPlayed(), x, y);
        y += gp.tileSize;
        g2.drawString("Total Crops Harvested: " + gp.statisticProvider.getTotalCropsHarvested(), x, y);
        y += gp.tileSize;
        g2.drawString("Total Fish Caught: " + gp.statisticProvider.getTotalFishCaught(), x, y);

        y += gp.tileSize;
        g2.drawString("NPC Chat Frequency: ", x, y);
        y += gp.tileSize;
        for (String npcName : gp.statisticProvider.getTrackedNPCNames()) {
            g2.drawString(npcName + ": " + gp.statisticProvider.getNPCChatFrequency(npcName), x, y);
            y += gp.tileSize;
        }
        y += gp.tileSize;
        g2.drawString("NPC Gift Frequency: ", x, y);
        y += gp.tileSize;
        for (String npcName : gp.statisticProvider.getTrackedNPCNames()) {
            g2.drawString(npcName + ": " + gp.statisticProvider.getNPCGiftFrequency(npcName), x, y);
            y += gp.tileSize;
        }
        y += gp.tileSize;
        g2.drawString("NPC Visit Frequency: ", x, y);
        y += gp.tileSize;
        for (String npcName : gp.statisticProvider.getTrackedNPCNames()) {
            g2.drawString(npcName + ": " + gp.statisticProvider.getNPCVisitFrequency(npcName), x, y);
            y += gp.tileSize;
        }
        y += gp.tileSize;
        g2.drawString("Average Seasonal Income: " + gp.statisticProvider.getAverageSeasonalIncome(), x, y);
        y += gp.tileSize;
        g2.drawString("Average Seasonal Expenditure: " + gp.statisticProvider.getAverageSeasonalExpenditure(), x, y);
        y += gp.tileSize;
    }
    
    public void drawInventory() {
        int x = gp.tileSize * 8;
        int y = gp.tileSize / 2 - gp.tileSize;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 7;

        g2.drawImage(inventoryPanel, x + gp.tileSize, y + gp.tileSize, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        x += gp.tileSize + gp.tileSize / 4;
        y += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Inventory", x, y);

        final int slotXstart = x + gp.tileSize - gp.tileSize / 4;
        final int slotYstart = y + gp.tileSize - gp.tileSize / 4;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize;

        Map<Items, Integer> playerItemsMap = gp.playerData.getInventory().checkInventory();
        List<Items> itemsList = new ArrayList<>(playerItemsMap.keySet());

        for (int i = 0; i < itemsList.size(); i++){
            Items item = itemsList.get(i);
            BufferedImage itemImage = item.getItemImage();
            if (itemImage != null) {
                g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.drawImage(inventorySelected, cursorX, cursorY, cursorWidth, cursorHeight, null);


        // Derkripsi item yang dipilih
        int textX = x + gp.tileSize - gp.tileSize / 4;
        int textY = y - gp.tileSize / 10;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.setColor(new Color(88,43,42,255));
        int itemIndex = getItemIndexSlot();
        
        if (itemIndex < itemsList.size()) {
            Items selectedItem = itemsList.get(itemIndex);
            String itemName = selectedItem.getName();
            String itemNum = String.valueOf(playerItemsMap.get(selectedItem));
            g2.drawString(itemNum + " - " + itemName, textX, textY + gp.tileSize * 6);
        } else {
            g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
        }
    }

    public int getItemIndexSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}
