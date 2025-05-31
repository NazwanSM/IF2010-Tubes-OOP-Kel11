package main;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import data.CropsData;
import data.FoodData;
import data.MiscData;
import data.NPCData;
import data.RecipeData;
import data.SeedData;
import entity.npc.NPC;
import items.Crops;
import items.Edible;
import items.Food;
import items.Items;
import items.Misc;
import items.Recipe;
import items.Seed;
import items.Sellable;
import items.equipment.Equipment;

import java.util.HashMap;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font stdw, titleFont;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false; // ini buat nanti kalau udah ending
    public String currentDialog = "";
    public Map<Items, Integer> soldItemsMap = new HashMap<>();
    public List<Items> soldItems;
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
    private BufferedImage dialoguePanel;
    private BufferedImage clockHUD;
    private BufferedImage arrow1;
    private BufferedImage arrow2;
    private BufferedImage arrow3;
    private BufferedImage worldMapMenu;
    private BufferedImage menu1;
    private BufferedImage menu2;
    private BufferedImage menu3;
    private BufferedImage menu4;
    private BufferedImage menu5;
    private BufferedImage menu6;
    private BufferedImage menu7;
    private BufferedImage menu8;
    private BufferedImage menu9;
    private BufferedImage menu10;
    private BufferedImage playerDown;
    private BufferedImage summer;
    private BufferedImage fall;
    private BufferedImage winter;
    private BufferedImage spring;
    private BufferedImage rainy;
    private BufferedImage sunny;
    private BufferedImage staminaBar;
    private BufferedImage staminaBar1;
    private BufferedImage staminaBar2;
    private BufferedImage staminaBar3;
    private BufferedImage staminaBar4;
    private BufferedImage staminaBar5;
    private BufferedImage cookingPanel;
    private BufferedImage cloud;
    private BufferedImage sunnyTV;
    private BufferedImage rainyTV;
    private BufferedImage chatSelected;
    private BufferedImage giftSelected;
    private BufferedImage proposeSelected;
    private BufferedImage marrySelected;
    private BufferedImage shippingBinUI;
    private BufferedImage creditScene;
    private BufferedImage helpScene;
    private BufferedImage actionsScene;
    private BufferedImage objectScene;
    private BufferedImage seedsStore;
    private BufferedImage cropsStore;
    private BufferedImage foodStore;
    private BufferedImage miscStore;
    private BufferedImage recipeStore;
    private BufferedImage equipedItem;
    private BufferedImage emptyScreen;
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    public int cookCol = 0;
    public int cookRow = 0;
    public int worldMapNum = 0;
    public int npcNum = 0;
    public int storeSection = 0;
    public int storeCol = 0;
    public int storeRow = 0;

    

    public UI(GamePanel gp) {
        this.gp = gp;
        try{
            InputStream is = getClass().getResourceAsStream("/font/StardewValley.ttf");
            stdw = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/TitleFont.ttf");
            titleFont = Font.createFont(Font.TRUETYPE_FONT, is);
            InputStream bgIs = getClass().getResourceAsStream("/ui/TitleBackground.png");
            titleScreenBackground = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/NewGame.png");
            newGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/LoadGame.png");
            loadGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ExitGame.png");
            exitGame = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/NewGameSelected.png");
            newGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/LoadGameSelected.png");
            loadGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ExitGameSelected.png");
            exitGameSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Help.png");
            help = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/HelpSelected.png");
            helpSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Player.png");
            player = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/PlayerSelected.png");
            playerSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Object.png");
            object = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ObjectSelected.png");
            objectSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Stats.png");
            stats = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StatsSelected.png");
            statsSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Action.png");
            action = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ActionSelected.png");
            actionSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Credits.png");
            credits = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/CreditsSelected.png");
            creditsSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Back.png");
            back = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/BackSelected.png");
            backSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/InventoryPanel.png");
            inventoryPanel = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/InventorySelected.png");
            inventorySelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/DialoguePanel.png");
            dialoguePanel = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ClockHUD.png");
            clockHUD = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Arrow1.png");
            arrow1 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Arrow2.png");
            arrow2 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Arrow3.png");
            arrow3 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/WorldMapMenu.png");
            worldMapMenu = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu1.png");
            menu1 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu2.png");
            menu2 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu3.png");
            menu3 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu4.png");
            menu4 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu5.png");
            menu5 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu6.png");
            menu6 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu7.png");
            menu7 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu8.png");
            menu8 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu9.png");
            menu9 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/menu10.png");
            menu10 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/player/Down1.png");
            playerDown = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Summer.png");
            summer = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Fall.png");
            fall = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Winter.png");
            winter = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Spring.png");
            spring = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Rainy.png");
            rainy = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Sunny.png");
            sunny = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar.png");
            staminaBar = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar1.png");
            staminaBar1 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar2.png");
            staminaBar2 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar3.png");
            staminaBar3 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar4.png");
            staminaBar4 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/StaminaBar5.png");
            staminaBar5 = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/CookingPanel.png");
            cookingPanel = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/Cloud.png");
            cloud = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/SunnyTV.png");
            sunnyTV = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/RainyTV.png");
            rainyTV = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ChatSelected.png");
            chatSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/GiftSelected.png");
            giftSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ProposeSelected.png");
            proposeSelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/MarrySelected.png");
            marrySelected = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/shippingbinUI.png");
            shippingBinUI = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/CreditScene.png");
            creditScene = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/HelpScreen.png");
            helpScene = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ActionsScene.png");
            actionsScene = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/ObjectScene.png");
            objectScene = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/seedsStore.png");
            seedsStore = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/cropsStore.png");
            cropsStore = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/foodStore.png");
            foodStore = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/miscStore.png");
            miscStore = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/recipeStore.png");
            recipeStore = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/equipedItem.png");
            equipedItem = ImageIO.read(bgIs);
            bgIs = getClass().getResourceAsStream("/ui/emptyScreen.png");
            emptyScreen = ImageIO.read(bgIs);
        }
        catch (FontFormatException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // stamina bar blom dibkin
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(stdw.deriveFont(Font.PLAIN, 60F));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState && titleScreenState == 0){
            drawTitleScreen();
        }
        else if (gp.gameState == gp.titleState && titleScreenState == 1) {
            drawCreditScreen();
        }
        else if (gp.gameState == gp.titleState && titleScreenState == 2) {
            drawHelpScreen();
        }
        else {
            if (gp.farm.isRainy()){
                drawCloudHUD();
            }
            drawClockHUD();
            drawStaminaBar();
            
            if(gp.gameState == gp.playState) {
                drawMessage();
            }
            else if(gp.gameState == gp.pauseState) {
                drawPauseScreen();
            }
            else if(gp.gameState == gp.dialogueState) {
                drawDialogueScreen();
            }
            else if(gp.gameState == gp.inventoryState) {
                drawInventory();
                drawMessage();
            }
            else if(gp.gameState == gp.worldMapState) {
                drawWorldMap();
            }
            else if(gp.gameState == gp.cheatState) {
                g2.setFont(stdw.deriveFont(Font.BOLD, 30F));
                g2.setColor(Color.YELLOW);
                String cheatText = "Cheat Mode Active - Check Dialog";
                int x = getXforCenteredText(cheatText);
                int y = gp.screenHeight / 2;
                g2.drawString(cheatText, x, y);
            }
            else if(gp.gameState == gp.cookingState) {
                drawCookingScreen();
                drawMessage();
            }
            else if(gp.gameState == gp.watchState){
                drawWatchScreen();
            }
            else if(gp.gameState == gp.npcState){
                drawNPCScreen();
                drawMessage();
            }
            else if(gp.gameState == gp.giftingState){
                drawGiftingScreen();
            }
            else if(gp.gameState == gp.sellingState){
                drawSellingScreen();
                drawShippingBin();
                drawMessage();
            }
            else if(gp.gameState == gp.creditState){
                drawCreditScreen();
            }
            else if(gp.gameState == gp.helpState){
                drawHelpScreen();
            }
            else if(gp.gameState == gp.actionListState){
                drawActionListScreen();
            }
            else if(gp.gameState == gp.objectListState){
                drawObjectListScreen();
            }
            else if(gp.gameState == gp.buyingState){
                drawBuyingScreen();
                drawMessage();
            }
            else if(gp.gameState == gp.playerInfoState){
                drawPlayerInfoScreen();
            }
            else if(gp.gameState == gp.statisticState){
                drawStatisticScreen();
            }
            else if(gp.gameState == gp.statsDisplayState){
                drawMilestoneScreen();
                gp.manager.setMilestoneDays(0);
            }
        }
    }

    public void processCheatInputs() {
        if (gp.gameState != gp.cheatState) { 
            if (gp.keyH.cPressed) {
                gp.keyH.cPressed = false;
            } 
            return;
        }

        String seasonInput = JOptionPane.showInputDialog(gp, "Enter the season (Summer, Fall, Winter, Spring):", "Cheat: Set Season", JOptionPane.PLAIN_MESSAGE);
        if (seasonInput != null && !seasonInput.trim().isEmpty()) {
            if (gp.farm != null){
                gp.farm.cheatSetSeason(seasonInput.trim().toLowerCase());
            }    
            else {
                System.err.println("Farm object is null in UI.processCheatInputs for season.");
            }
        } else if (seasonInput == null) { 
            gp.gameState = gp.playState; 
            gp.keyH.cPressed = false;    
            gp.alreadyProcessedCheatKey = false;
            return;
        }


        String weatherInput = JOptionPane.showInputDialog(gp, "Enter the weather (Rainy, Sunny):", "Cheat: Set Weather", JOptionPane.PLAIN_MESSAGE);
        if (weatherInput != null && !weatherInput.trim().isEmpty()) {
            if (gp.farm != null){
            gp.farm.cheatSetWeather(weatherInput.trim());
            } 
            else {
                System.err.println("Farm object is null in UI.processCheatInputs for weather.");
            }
        } else if (weatherInput == null) { 
            gp.gameState = gp.playState; 
            gp.keyH.cPressed = false;
            gp.alreadyProcessedCheatKey = false;
            return;
        }

        gp.gameState = gp.playState; 
        gp.keyH.cPressed = false;    
        gp.alreadyProcessedCheatKey = false;
    }
    
    public void drawSleeping(){
        if (!gp.showingSleepScreen) return;
        
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    public void drawClockHUD() {
        int x = gp.tileSize / 2 - gp.tileSize / 4 + gp.tileSize * 10 + gp.tileSize / 2 + gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize / 4;

        // Weather
        if (gp.farm.getWeather().isRainy()) {
            g2.drawImage(rainy, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8 - gp.tileSize / 32, gp.tileSize, gp.tileSize, null);
        } 
        else {
            g2.drawImage(sunny, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8 - gp.tileSize / 32, gp.tileSize, gp.tileSize, null);
        }

        // Season
        if (gp.farm.getSeason().getCurrentSeason() == "Summer") {
            g2.drawImage(summer, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize * 2 - gp.tileSize / 4 - gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8, gp.tileSize, gp.tileSize, null);
        } 
        else if (gp.farm.getSeason().getCurrentSeason() == "Spring") {
            g2.drawImage(spring, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize * 2 - gp.tileSize / 4 - gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8, gp.tileSize, gp.tileSize, null);
        } 
        else if (gp.farm.getSeason().getCurrentSeason() == "Fall") {
            g2.drawImage(fall, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize * 2 - gp.tileSize / 4 - gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8, gp.tileSize, gp.tileSize, null);
        } 
        else if (gp.farm.getSeason().getCurrentSeason() == "Winter") {
            g2.drawImage(winter, x + gp.tileSize + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize * 2 - gp.tileSize / 4 - gp.tileSize / 8, y + gp.tileSize * 2 - gp.tileSize / 2 - gp.tileSize / 4 - gp.tileSize / 4 - gp.tileSize / 8, gp.tileSize, gp.tileSize, null);
        }
        
        // Clock HUD
        g2.drawImage(clockHUD, x, y, gp.tileSize * 5, gp.tileSize * 4, null);

        // Clock
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        g2.setColor(new Color(23,8,31,255));
        g2.drawString(gp.farm.getGameClock().getTime(), x + gp.tileSize * 2, y + gp.tileSize * 2 + gp.tileSize / 4 + gp.tileSize / 12);

        // Gold
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 39F));
        g2.setColor(new Color(23,8,31,255));
        g2.drawString("" + gp.playerData.getGold(), x + gp.tileSize * 2 - gp.tileSize + gp.tileSize / 4, y + gp.tileSize * 3 + gp.tileSize / 4 + gp.tileSize / 3 + gp.tileSize / 17);

        // Clock
        if (gp.farm.getGameClock().getHours() >= 6 && gp.farm.getGameClock().getHours() < 13) {
            g2.drawImage(arrow1, x - gp.tileSize / 4 - gp.tileSize / 8 , y - gp.tileSize / 16 - gp.tileSize / 16, gp.tileSize * 3, gp.tileSize * 3, null);
        }
        else if (gp.farm.getGameClock().getHours() >= 13){
            g2.drawImage(arrow2, x - gp.tileSize / 4 - gp.tileSize / 8 , y - gp.tileSize / 16 - gp.tileSize / 16, gp.tileSize * 3, gp.tileSize * 3, null);
        }
        else if (gp.farm.getGameClock().getHours() >= 20 && gp.farm.getGameClock().getHours() < 6){
            g2.drawImage(arrow3, x - gp.tileSize / 4 - gp.tileSize / 8 , y - gp.tileSize / 16 - gp.tileSize / 16, gp.tileSize * 3, gp.tileSize * 3, null);
        }
        // Day
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.drawString("Day - " + gp.farm.getDay(), x + gp.tileSize * 2, y + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize / 12);
    }

    public void drawStaminaBar() {
        int x = gp.tileSize / 2 - gp.tileSize / 4 + gp.tileSize * 10 + gp.tileSize / 2 + gp.tileSize / 8 + gp.tileSize / 2 + gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize / 4 + gp.tileSize * 4;
        if (gp.playerData.getEnergy() > 80) {
            g2.drawImage(staminaBar, x, y, gp.tileSize * 4, gp.tileSize, null);
        } else if (gp.playerData.getEnergy() > 60) {
            g2.drawImage(staminaBar1, x, y, gp.tileSize * 4, gp.tileSize, null);
        } else if (gp.playerData.getEnergy() > 40) {
            g2.drawImage(staminaBar2, x, y, gp.tileSize * 4, gp.tileSize, null);
        } else if (gp.playerData.getEnergy() > 20) {
            g2.drawImage(staminaBar3, x, y, gp.tileSize * 4, gp.tileSize, null);
        } else if (gp.playerData.getEnergy() > 0) {
            g2.drawImage(staminaBar4, x, y, gp.tileSize * 4, gp.tileSize, null);
        } else {
            g2.drawImage(staminaBar5, x, y, gp.tileSize * 4, gp.tileSize, null);
        }
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 39F));
        g2.setColor(new Color(23,8,31,255));
        g2.drawString(String.valueOf(gp.playerData.getEnergy()), x + gp.tileSize * 2 - gp.tileSize + gp.tileSize / 4 + gp.tileSize / 16 - gp.tileSize / 3 - gp.tileSize / 32, y + gp.tileSize / 2 + gp.tileSize / 4 + gp.tileSize / 14);
    }

    public void drawCloudHUD() {
        g2.drawImage(cloud, gp.tileSize - gp.tileSize / 2 - gp.tileSize / 2, gp.tileSize - gp.tileSize / 2 - gp.tileSize / 2, gp.tileSize * 16, gp.tileSize * 3, null);
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {

            g2.drawImage(titleScreenBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
            
            int x = gp.screenWidth / 2 - gp.tileSize / 2;
            int y = gp.tileSize * 6;
            g2.drawImage(playerDown, x, y, gp.tileSize, gp.tileSize*2, null);
    
            x = gp.screenWidth / 2 - gp.tileSize * 5 + gp.tileSize / 4;
            y = gp.tileSize * 8 + gp.tileSize / 4;
            g2.drawImage(newGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 0) {
                g2.drawImage(newGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }

            x = gp.screenWidth / 2 + gp.tileSize / 4;
            y = gp.tileSize * 8 + gp.tileSize / 4;
            g2.drawImage(help, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 2) {
                g2.drawImage(helpSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize * 2 - gp.tileSize / 4;
            y = gp.tileSize * 8 + gp.tileSize / 4;
            g2.drawImage(loadGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 1) {
                g2.drawImage(loadGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 + gp.tileSize * 3 - gp.tileSize / 4;
            y = gp.tileSize * 8 + gp.tileSize / 4;
            g2.drawImage(exitGame, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            if (commandNum == 3) {
                g2.drawImage(exitGameSelected, x, y, gp.tileSize * 4 / 2, gp.tileSize * 3 / 2, null);
            }
    
            x = gp.screenWidth / 2 - gp.tileSize - gp.tileSize / 4;
            y = gp.tileSize * 10 + gp.tileSize / 4;
            g2.drawImage(credits, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            if (commandNum == 4) { 
                g2.drawImage(creditsSelected, x, y, gp.tileSize * 7 / 3, gp.tileSize * 3 / 3, null);
            }
    
        }

        else if (titleScreenState == 1){

        }
    }

    public void drawPauseScreen() {
            g2.drawImage(titleScreenBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
            
            int x = gp.screenWidth / 2 - gp.tileSize / 2;
            int y = gp.tileSize * 6;
            g2.drawImage(playerDown, x, y, gp.tileSize, gp.tileSize*2, null);
    
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
    
    public void drawDialogueScreen() {
        gp.playSE(5);
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9 - gp.tileSize / 2;
        int width = gp.tileSize * 12;
        int height = gp.tileSize * 3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        g2.setColor(Color.white);
        x += gp.tileSize / 2;
        y += gp.tileSize;

        for (String line : currentDialog.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        
        g2.drawImage(dialoguePanel, x, y, width, height, null);
    }

    public void drawStatsScreen() {
        gp.gameState = gp.dialogueState;
        currentDialog = "You have achieved a milestone! Check your statistics.";
        gp.gameState = gp.statsDisplayState;
    }
    
    public void drawInventory() {
        int x = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8;
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
                if (item == gp.playerData.getEquppedItem()) {
                    g2.drawImage(equipedItem, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
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
            if (gp.keyH.enterPressed) {
                if (selectedItem instanceof Edible){
                    gp.playerData.performAction("eat", selectedItem.getName(), null);;
                    addMessage(selectedItem.getName() + " eaten, restoring " + ((Edible) selectedItem).getEnergy() + " energy.");
                    gp.playSE(4);
                }
                else if (selectedItem instanceof Seed || selectedItem instanceof Equipment){
                    if (gp.playerData.getEquppedItem() == selectedItem) {
                        gp.playerData.setEquppedItem(null);
                        addMessage("Unequipped " + selectedItem.getName() + ".");
                    } else {
                        gp.playerData.setEquppedItem(selectedItem);
                        addMessage("Equipped " + selectedItem.getName() + ".");
                        addMessage("Press 'E' to use the equipped item.");
                    }
                    gp.playSE(16);
                }
                gp.keyH.enterPressed = false;
            }
        } else {
            g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
        }

    }

    public void drawBuyingScreen() {
        int x = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 10;

        final int slotXstart = x + gp.tileSize * 2;
        final int slotYstart = y + gp.tileSize * 3;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize;

        int cursorX = slotXstart + (slotSize * storeCol);
        int cursorY = slotYstart + (slotSize * storeRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        int textX = x + gp.tileSize * 2;
        int textY = y + gp.tileSize * 3 + gp.tileSize / 4;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F));
        g2.setColor(new Color(88,43,42,255));
        int itemIndex = storeCol + (storeRow * 5);

        
        if (storeSection == 0){
            g2.drawImage(seedsStore, x + gp.tileSize, y + gp.tileSize, width, height, null);
            List<Seed> allSeeds = SeedData.getAllSeeds();
            List<Items> seedsList = new ArrayList<>();
            for (Seed seed : allSeeds) {
                if (seed.getBuyPrice() > 0) {
                    seedsList.add(seed);
                }
            }
            
            for (int i = 0; i < seedsList.size(); i++){
                Items item = seedsList.get(i);
                BufferedImage itemImage = item.getItemImage();
                if (itemImage != null) {
                    g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }

                if (itemIndex < seedsList.size()) {
                    Items selectedItem = seedsList.get(itemIndex);
                    String itemName = selectedItem.getName();
                    Seed selectedSeed = (Seed) selectedItem;
                    String itemPrice = String.valueOf(selectedSeed.getBuyPrice());
                    g2.drawString(itemName, textX, textY + gp.tileSize * 6);
                    g2.drawString("Price: " + itemPrice, textX, textY + gp.tileSize * 6 + gp.tileSize / 2);
                    if (gp.keyH.enterPressed) {
                        if (gp.playerData.getGold() >= selectedSeed.getBuyPrice()) {
                            gp.playerData.performAction("buy", itemPrice, selectedItem);
                            gp.playSE(12);
                        } else {
                            addMessage("Not enough gold to buy " + itemName + ".");
                        }
                        gp.keyH.enterPressed = false;
                    }
                } else {
                    g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
                }
            }
        }
        else if (storeSection == 1){
            g2.drawImage(cropsStore, x + gp.tileSize, y + gp.tileSize, width, height, null);
            List<Crops> allCrops = CropsData.getAllCrops();
            List<Items> cropsList = new ArrayList<>();
            for (Crops crop : allCrops) {
                if (crop.getBuyPrice() > 0) {
                    cropsList.add(crop);
                }
            }
            
            for (int i = 0; i < cropsList.size(); i++){
                Items item = cropsList.get(i);
                BufferedImage itemImage = item.getItemImage();
                if (itemImage != null) {
                    g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }

                if (itemIndex < cropsList.size()) {
                    Items selectedItem = cropsList.get(itemIndex);
                    String itemName = selectedItem.getName();
                    Crops selectedCrops = (Crops) selectedItem;
                    String itemPrice = String.valueOf(selectedCrops.getBuyPrice());
                    g2.drawString(itemName, textX, textY + gp.tileSize * 6);
                    g2.drawString("Price: " + itemPrice, textX, textY + gp.tileSize * 6 + gp.tileSize / 2);
                    if (gp.keyH.enterPressed) {
                        if (gp.playerData.getGold() >= selectedCrops.getBuyPrice()) {
                            gp.playerData.performAction("buy", itemPrice, selectedItem);
                            gp.playSE(12);
                        } else {
                            addMessage("Not enough gold to buy " + itemName + ".");
                        }
                        gp.keyH.enterPressed = false;
                    }
                } else {
                    g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
                }
            }
        }
        else if (storeSection == 2){
            g2.drawImage(foodStore, x + gp.tileSize, y + gp.tileSize, width, height, null);
            List<Food> allFood = FoodData.getAllFoodItems();
            List<Items> foodList = new ArrayList<>();
            for (Food food : allFood) {
                if (food.getBuyPrice() > 0) {
                    foodList.add(food);
                }
            }
            
            for (int i = 0; i < foodList.size(); i++){
                Items item = foodList.get(i);
                BufferedImage itemImage = item.getItemImage();
                if (itemImage != null) {
                    g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }

                if (itemIndex < foodList.size()) {
                    Items selectedItem = foodList.get(itemIndex);
                    String itemName = selectedItem.getName();
                    Food selectedFood = (Food) selectedItem;
                    String itemPrice = String.valueOf(selectedFood.getBuyPrice());
                    g2.drawString(itemName, textX, textY + gp.tileSize * 6);
                    g2.drawString("Price: " + itemPrice, textX, textY + gp.tileSize * 6 + gp.tileSize / 2);
                    if (gp.keyH.enterPressed) {
                        if (gp.playerData.getGold() >= selectedFood.getBuyPrice()) {
                            gp.playerData.performAction("buy", itemPrice, selectedItem);
                            gp.playSE(12);
                        } else {
                            addMessage("Not enough gold to buy " + itemName + ".");
                        }
                        gp.keyH.enterPressed = false;
                    }
                } else {
                    g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
                }
            }
        }
        else if (storeSection == 3){
            g2.drawImage(miscStore, x + gp.tileSize, y + gp.tileSize, width, height, null);
            List<Misc> allMisc = MiscData.getAllMiscItems();
            List<Items> miscList = new ArrayList<>();
            for (Misc misc : allMisc) {
                if (misc.getBuyPrice() > 0) {
                    miscList.add(misc);
                }
            }
            
            for (int i = 0; i < miscList.size(); i++){
                Items item = miscList.get(i);
                BufferedImage itemImage = item.getItemImage();
                if (itemImage != null) {
                    g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }

                boolean isOwned = false;

                
                if (itemIndex < miscList.size()) {
                    Items selectedItem = miscList.get(itemIndex);
                    String itemName = selectedItem.getName();
                    Misc selectedMisc = (Misc) selectedItem;
                    String itemPrice = String.valueOf(selectedMisc.getBuyPrice());
                    g2.drawString(itemName, textX, textY + gp.tileSize * 6);
                    g2.drawString("Price: " + itemPrice, textX, textY + gp.tileSize * 6 + gp.tileSize / 2);
                    if (itemName.equals("Proposal Ring")) {
                        isOwned = gp.playerData.getInventory().checkInventory().containsKey(selectedItem);
                    }
                    if (gp.keyH.enterPressed) {
                        if (gp.playerData.getGold() >= selectedMisc.getBuyPrice() && !isOwned) {
                            gp.playerData.performAction("buy", itemPrice, selectedItem);
                            gp.playSE(12);
                        }
                        else if (isOwned) {
                            addMessage("You already own this item: " + itemName + ".");
                        }
                        else {
                            addMessage("Not enough gold to buy " + itemName + ".");
                        }
                        gp.keyH.enterPressed = false;
                    }
                } else {
                    g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
                }
            }
        }
        else if (storeSection == 4){
            g2.drawImage(recipeStore, x + gp.tileSize, y + gp.tileSize, width, height, null);
            List<Recipe> allRecipe = RecipeData.getAllRecipes();
            List<Recipe> recipeList = new ArrayList<>();
            for (Recipe recipe : allRecipe) {
                if (recipe.getItemID().equals("recipe_1") || recipe.getItemID().equals("recipe_10")){
                    recipeList.add(recipe);
                }
            }
            
            for (int i = 0; i < recipeList.size(); i++){
                Recipe item = recipeList.get(i);
                BufferedImage itemImage = item.getIcon();
                if (itemImage != null) {
                    g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }
                slotX += slotSize;
                if (i == 4 || i == 9 || i == 14 || i == 19) {
                    slotX = slotXstart;
                    slotY += slotSize;
                }

                if (itemIndex < recipeList.size()) {
                    Recipe selectedItem = recipeList.get(itemIndex);
                    String itemName = selectedItem.getItemID();
                    int price = 150;
                    String itemPrice = String.valueOf(price);
                    g2.drawString(itemName, textX, textY + gp.tileSize * 6);
                    g2.drawString("Price: " + itemPrice, textX, textY + gp.tileSize * 6 + gp.tileSize / 2);
                    if (gp.keyH.enterPressed) {
                        if (gp.playerData.getGold() >= price && !RecipeData.getRecipeById(itemName).isUnlocked()) {
                            RecipeData.getRecipeById(itemName).setUnlocked(true);
                            gp.playerData.subtractGold(price);
                            addMessage("Recipe " + itemName + " purchased successfully!");
                            recipeList.remove(selectedItem);
                            gp.playSE(12);
                        }
                        else if (RecipeData.getRecipeById(itemName).isUnlocked()) {
                            addMessage("You already own this recipe: " + itemName + ".");
                        }
                        else {
                            addMessage("Not enough gold to buy " + itemName + ".");
                        }
                        gp.keyH.enterPressed = false;
                    }
                } else {
                    g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
                }
            }
        }
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        x += gp.tileSize + gp.tileSize / 4;
        y += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Store", x, y);

        g2.drawImage(inventorySelected, cursorX, cursorY, cursorWidth, cursorHeight, null);
        
    }

    public void drawWorldMap() {
        int x = gp.tileSize * 2 + gp.tileSize / 2;
        int y = gp.tileSize * 2 + gp.tileSize / 2;
        int width = (gp.tileSize * 22) / 2;
        int height = (gp.tileSize * 14 + 10) / 2;
        g2.drawImage(worldMapMenu, x, y, width, height, null);

        if (worldMapNum == 0) {
            g2.drawImage(menu1, x, y ,width, height,  null);
        }
        if (worldMapNum == 1) {
            g2.drawImage(menu2, x, y ,width, height,  null);
        }
        if (worldMapNum == 2) {
            g2.drawImage(menu3, x, y ,width, height,  null);
        }
        if (worldMapNum == 3) {
            g2.drawImage(menu4, x, y ,width, height,  null);
        }
        if (worldMapNum == 4) {
            g2.drawImage(menu5, x, y ,width, height,  null);
        }
        if (worldMapNum == 5) {
            g2.drawImage(menu6, x, y ,width, height,  null);
        }
        if (worldMapNum == 6) {
            g2.drawImage(menu7, x, y ,width, height,  null);
        }
        if (worldMapNum == 7) {
            g2.drawImage(menu8, x, y ,width, height,  null);
        }
        if (worldMapNum == 8) {
            g2.drawImage(menu9, x, y ,width, height,  null);
        }
        if (worldMapNum == 9) {
            g2.drawImage(menu10, x, y ,width, height,  null);
        }
    }

    public void drawCheatMenu() {
        String season = JOptionPane.showInputDialog(gp, "Enter the season (Summer, Fall, Winter, Spring):", JOptionPane.PLAIN_MESSAGE);
        if (season != null && !season.isEmpty()) {
            switch (season.toLowerCase()) {
                case "summer":
                    gp.farm.cheatSetSeason("Summer");
                    break;
                case "fall":
                    gp.farm.cheatSetSeason("Fall");
                    break;
                case "winter":
                    gp.farm.cheatSetSeason("Winter");
                    break;
                case "spring":
                    gp.farm.cheatSetSeason("Spring");
                    break;
                default:
                    return;
            }
        }
        String weather = JOptionPane.showInputDialog(gp, "Enter the weather (Rainy, Sunny):", JOptionPane.PLAIN_MESSAGE);
        if (weather != null && !weather.isEmpty()) {
            switch (weather.toLowerCase()) {
                case "rainy":
                    gp.farm.cheatSetWeather("Rainy");
                    break;
                case "sunny":
                    gp.farm.cheatSetWeather("Sunny");
                    break;
                default:
                    return;
            }
        }
        gp.gameState = gp.playState;
    }

    public void drawCookingScreen() {
        int x = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 8;

        g2.drawImage(cookingPanel, x + gp.tileSize, y + gp.tileSize, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        x += gp.tileSize + gp.tileSize / 4;
        y += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Cooking Time!", x, y);

        final int slotXstart = x + gp.tileSize - gp.tileSize / 4;
        final int slotYstart = y + gp.tileSize - gp.tileSize / 4;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize;

        List<Recipe> recipeList = RecipeData.getAllRecipes();

        for (int i = 0; i < recipeList.size(); i++){
            Recipe item = recipeList.get(i);
            BufferedImage itemImage = item.getResult().getItemImage();
            BufferedImage bnwItemImage = item.getResult().getBnwItemImage();
            if (item.isUnlocked()) {
                g2.drawImage(itemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
            }
            else {
                g2.drawImage(bnwItemImage, slotX, slotY, gp.tileSize, gp.tileSize, null);
            }
            slotX += slotSize;
            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        int cursorX = slotXstart + (slotSize * cookCol);
        int cursorY = slotYstart + (slotSize * cookRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.drawImage(inventorySelected, cursorX, cursorY, cursorWidth, cursorHeight, null);
        
        // Derkripsi item yang dipilih
        int textX = x + gp.tileSize - gp.tileSize / 4 - gp.tileSize / 8;
        int textY = y - gp.tileSize / 10;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(new Color(88,43,42,255));
        int itemIndex = cookCol + (cookRow * 5);
        
        if (itemIndex < recipeList.size()) {
            Recipe selectedItem = recipeList.get(itemIndex);
            String recipeName = selectedItem.getItemID();
            String foodName = selectedItem.getResult().getName();
            String isUnlocked;
            if (selectedItem.isUnlocked()) {
                isUnlocked = "(Unlocked)";
            } 
            else {
                isUnlocked = "(Locked) - " + selectedItem.getUnlockInfo();
            }
            g2.drawString(recipeName + " - " + foodName, textX, textY + gp.tileSize * 6);
            g2.drawString(isUnlocked, textX, textY + gp.tileSize * 6 + 30);
            if (gp.keyH.enterPressed) {
                gp.playerData.performAction("cook", selectedItem.getItemID(), null);
                gp.keyH.enterPressed = false;
            }
        } else {
            g2.drawString("No recipe selected", textX, textY + gp.tileSize * 6);
        }
    }

    public void drawWatchScreen() {
        int drawX = gp.tileSize * 5;
        int drawY = gp.tileSize * 3;
        int width = gp.tileSize * 6;
        int height = gp.tileSize * 6;
        
        if (!gp.farm.isRainy()){
            g2.drawImage(sunnyTV, drawX, drawY, width, height, null);
        }
        else {
            g2.drawImage(rainyTV, drawX, drawY, width, height, null);
        }

    }

    public void drawNPCScreen() {
        int imageX = gp.tileSize * 4;
        int imageY = gp.tileSize * 4;
        int width = gp.tileSize * 6 / 2;
        int height = gp.tileSize * 6 / 2;

        if (npcNum == 0){
            g2.drawImage(chatSelected, imageX, imageY, width, height, null);
        }
        else if (npcNum == 1){
            g2.drawImage(giftSelected, imageX, imageY, width, height, null);
        }
        else if (npcNum == 2){
            g2.drawImage(proposeSelected, imageX, imageY, width, height, null);
        }
        else if (npcNum == 3){
            g2.drawImage(marrySelected, imageX, imageY, width, height, null);
        }
    }

    public void drawGiftingScreen() {
        int x = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 7;

        g2.drawImage(inventoryPanel, x + gp.tileSize, y + gp.tileSize, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        x += gp.tileSize + gp.tileSize / 4;
        y += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Gifting", x, y);

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
            if (gp.keyH.enterPressed) {
                if (selectedItem instanceof Equipment){
                }
                else {
                    gp.playerData.performAction("gifting", selectedItem.getName(), null);
                    gp.playSE(11);
                    gp.gameState = gp.playState;
                }
                gp.keyH.enterPressed = false;
            }
        } else {
            g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
        }
    }

    public void drawSellingScreen() {
        int x = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8;
        int y = gp.tileSize / 2 - gp.tileSize;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 7;

        g2.drawImage(inventoryPanel, x + gp.tileSize, y + gp.tileSize, width, height, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        x += gp.tileSize + gp.tileSize / 4;
        y += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Selling", x, y);

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
            if (gp.keyH.enterPressed) {
                if (selectedItem instanceof Sellable && !selectedItem.getName().equals("Proposal Ring")) {
                    gp.playerData.removeItemFromInventory(selectedItem, 1);
                    if (soldItemsMap.containsKey(selectedItem)) {
                        soldItemsMap.put(selectedItem, soldItemsMap.get(selectedItem) + 1);
                    } else {
                        soldItemsMap.put(selectedItem, 1);
                    }
                    gp.ui.addMessage(selectedItem.getName() + " added to shipping bin ");
                    gp.playSE(13);
                }
                else {
                    gp.ui.addMessage("This item cannot be sold.");
                }
                gp.keyH.enterPressed = false;
            }
        } else {
            g2.drawString("No item selected", textX, textY + gp.tileSize * 6);
        }
    }
    
    public void drawShippingBin(){
        int a = gp.tileSize / 2 - gp.tileSize - gp.tileSize / 8 + gp.tileSize * 7;
        int b = gp.tileSize / 2 - gp.tileSize;

        g2.drawImage(shippingBinUI, a + gp.tileSize, b + gp.tileSize, gp.tileSize * 6, gp.tileSize * 7, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(new Color(88,43,42,255));
        a += gp.tileSize + gp.tileSize / 4;
        b += gp.tileSize + gp.tileSize / 4;
        g2.drawString("Shipping Bin", a, b);

        int totalPrice = 0;

        final int slotAstart = a + gp.tileSize - gp.tileSize / 4;
        final int slotBstart = b + gp.tileSize - gp.tileSize / 4;
        int slotA = slotAstart;
        int slotB = slotBstart;
        int slotSiZe = gp.tileSize;

        soldItems = new ArrayList<>(soldItemsMap.keySet());

        for (int i = 0; i < soldItems.size(); i++){
            Items item = soldItems.get(i);
            BufferedImage itemImage = item.getItemImage();
            Sellable sel = (Sellable) item;
            g2.drawImage(itemImage, slotA, slotB, gp.tileSize, gp.tileSize, null);
            slotA += slotSiZe;
            totalPrice += sel.getSellPrice() * soldItemsMap.get(item);
            if (i == 3 || i == 7 || i == 11) {
                slotA = slotAstart;
                slotB += slotSiZe;
            }
        }

        int textA = a + gp.tileSize * 3;
        int textB = b + gp.tileSize*6;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        g2.setColor(new Color(88,43,42,255));
        g2.drawString("" + totalPrice, textA, textB);
    }

    public void drawCreditScreen() {
        g2.drawImage(creditScene, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

    public void drawHelpScreen() {
        g2.drawImage(helpScene, 0, 0, gp.screenWidth, gp.screenHeight, null);
    }

    public void drawActionListScreen() {
        g2.drawImage(actionsScene, 0, 0, gp.screenWidth, gp.screenHeight, null); 
    }

    public void drawObjectListScreen() {
        g2.drawImage(objectScene, 0, 0, gp.screenWidth, gp.screenHeight, null); 
    }

    public void drawPlayerInfoScreen() {
        g2.drawImage(emptyScreen, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(Color.WHITE);
        String player = "Player Info";
        int x = getXforCenteredText(player);
        int y = gp.tileSize * 2 + gp.tileSize / 2;
        g2.drawString(player, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        int textX = gp.tileSize * 2 + gp.tileSize / 2;
        int textY = y + gp.tileSize;
        g2.drawString("Name: " + gp.playerData.getName(), textX, textY);
        textY += gp.tileSize;
        g2.drawString("Gender: " + gp.playerData.getGender(), textX, textY);
        textY += gp.tileSize;
        g2.drawString("Energy: " + gp.playerData.getEnergy(), textX, textY);
        textY += gp.tileSize;
        if (gp.playerData.getPartner() != null) {
            g2.drawString("Partner: " + gp.playerData.getPartner().getNPCName(), textX, textY);
        } else {
            g2.drawString("Partner: None", textX, textY);
        }
        textY += gp.tileSize;
        g2.drawString("Favorite Item: The Legend of Spakbor", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Gold: " + gp.playerData.getGold(), textX, textY);
        textY += gp.tileSize;
    }

    public void drawStatisticScreen() {
        g2.drawImage(emptyScreen, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(Color.WHITE);
        String player = "STATISTICS";
        int x = getXforCenteredText(player);
        int y = gp.tileSize * 2 + gp.tileSize / 2;
        g2.drawString(player, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        int textX = gp.tileSize * 2 + gp.tileSize / 2;
        int textY = y + gp.tileSize;
        g2.drawString("Total Income: " + gp.manager.getTotalIncome(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Total Expenses: " + gp.manager.getTotalExpenditure(), textX, textY);
        textY -= gp.tileSize / 2;
        textX += gp.tileSize * 4;
        g2.drawString("Average Season Income: " + gp.manager.getAverageSeasonalIncome(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Average Season Expenses: " + gp.manager.getAverageSeasonalExpenditure(), textX, textY);
        textX -= gp.tileSize * 4;
        textY += gp.tileSize / 2;
        g2.drawString("Total Days Played: " + gp.manager.getTotalDaysPlayed(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("NPC Status: (Relationship Status, Chatting x, Gifting x , Visiting x)", textX, textY);
        textY += gp.tileSize / 2;
        List<NPC> npcs = NPCData.getAllNPC();
        for (NPC npc : npcs) {
            g2.drawString(npc.getNPCName() + ": " + npc.getRelationshipStatus()+ ", " + gp.manager.getNPCChatFrequency(npc.getNPCName()) + ", " + gp.manager.getNPCGiftFrequency(npc.getNPCName()) + ", " + gp.manager.getNPCVisitFrequency(npc.getNPCName()),textX + gp.tileSize / 4, textY);
            textY += gp.tileSize / 2;
        }
        g2.drawString("Total Crops Harvested: " + gp.manager.getTotalCropsHarvested(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Total Fish Caught: " + gp.manager.getTotalFishCaught(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Common: " + gp.manager.getTotalCommonFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Regular: " + gp.manager.getTotalRegularFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Legendary: " + gp.manager.getTotalLegendaryFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize;
    }

    public void drawMilestoneScreen() {
        gp.playSE(22);
        g2.drawImage(emptyScreen, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
        g2.setColor(Color.WHITE);
        String player = "CONGRATULATIONS! for reaching a milestone!";
        int x = getXforCenteredText(player);
        int y = gp.tileSize * 2 + gp.tileSize / 2;
        g2.drawString(player, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        int textX = gp.tileSize * 2 + gp.tileSize / 2;
        int textY = y + gp.tileSize;
        g2.drawString("Total Income: " + gp.manager.getTotalIncome(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Total Expenses: " + gp.manager.getTotalExpenditure(), textX, textY);
        textY -= gp.tileSize / 2;
        textX += gp.tileSize * 4;
        g2.drawString("Average Season Income: " + gp.manager.getAverageSeasonalIncome(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Average Season Expenses: " + gp.manager.getAverageSeasonalExpenditure(), textX, textY);
        textX -= gp.tileSize * 4;
        textY += gp.tileSize / 2;
        g2.drawString("Total Days Played: " + gp.manager.getTotalDaysPlayed(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("NPC Status: (Relationship Status, Chatting x, Gifting x , Visiting x)", textX, textY);
        textY += gp.tileSize / 2;
        List<NPC> npcs = NPCData.getAllNPC();
        for (NPC npc : npcs) {
            g2.drawString(npc.getNPCName() + ": " + npc.getRelationshipStatus()+ ", " + gp.manager.getNPCChatFrequency(npc.getNPCName()) + ", " + gp.manager.getNPCGiftFrequency(npc.getNPCName()) + ", " + gp.manager.getNPCVisitFrequency(npc.getNPCName()),textX + gp.tileSize / 4, textY);
            textY += gp.tileSize / 2;
        }
        g2.drawString("Total Crops Harvested: " + gp.manager.getTotalCropsHarvested(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Total Fish Caught: " + gp.manager.getTotalFishCaught(), textX, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Common: " + gp.manager.getTotalCommonFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Regular: " + gp.manager.getTotalRegularFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize / 2;
        g2.drawString("Legendary: " + gp.manager.getTotalLegendaryFishCaught(), textX + gp.tileSize / 4, textY);
        textY += gp.tileSize;
    }

    public void drawLoadScene(){
        JOptionPane.showMessageDialog(gp, "This feature is still under development.", "Load Game", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getItemIndexSlot() {
        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
    }

    public void drawMessage() {
        int messageY = gp.tileSize * 4 + gp.tileSize / 2 + gp.tileSize * 2 + gp.tileSize / 2;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,24F));
        
        for(int i = 0; i < message.size(); i++)
        {
            if(message.get(i) != null)
            {
                //Shadow
                int messageX = getXforCenteredText(message.get(i));
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2,messageY+2);
                //Text
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX,messageY);

                int counter = messageCounter.get(i) + 1; 
                messageCounter.set(i,counter);           
                messageY += 50;

                if(messageCounter.get(i) > 150)          
                {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }

        }
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

}
