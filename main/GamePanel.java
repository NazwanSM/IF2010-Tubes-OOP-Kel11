package main;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import World.Farm;
import World.Environment.Lighting;
import data.NPCData;
import entity.player.PlayerUI;
import entity.object.SuperObject;
import entity.player.Player;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import statistics.IStatisticProvider;
import statistics.IStatisticTracker;
import statistics.StatisticsManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    final int originalTileSize = 16;
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    // World settings
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 32;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public final int maxMap = 15;
    public int currentMap = 0;

    // FPS
    final int FPS = 60;
    
    public TileManager tileM;
    public KeyHandler keyH = new KeyHandler(this);
    MouseHandler mouseH = new MouseHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public EventHandler eHandler;
    Thread gameThread;
    public Lighting lightingSystem;
    public int randomMapIndex;
    
    
    // Entity and object
    public Player playerData;
    public PlayerUI player;
    public SuperObject[][] objects = new SuperObject[maxMap][20];
    public IStatisticTracker statisticTracker;
    public IStatisticProvider statisticProvider;
    public StatisticsManager manager = new StatisticsManager(NPCData.getAllNPCNames());
    public Farm farm;
    
    
    // Game State
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int statsDisplayState = 4;
    public final int inventoryState = 5;
    public final int worldMapState = 6;
    public final int cheatState = 7;
    public final int cookingState = 8;
    public boolean alreadyProcessedCheatKey = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
        // this.statisticTracker = manager;
        // this.statisticProvider = manager;
    }
    
    public void setupGame() {
        // playMusic(0);
        gameState = titleState;
    }
    
    public void setupNewGame() {
        this.currentMap = 0;
        String playerName = JOptionPane.showInputDialog(this, "Enter Player Name:", "New Game", JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            gameState = titleState;
            ui.titleScreenState = 0; 
            ui.commandNum = 0;
            return;
        }
        
        String[] genders = {"Male", "Female"};
        int genderChoice = JOptionPane.showOptionDialog(this, "Select Gender:", "New Game",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, genders, genders[0]);
        if (genderChoice == JOptionPane.CLOSED_OPTION) {
            gameState = titleState;
            ui.titleScreenState = 0;
            ui.commandNum = 0;
            return;
        }
        String playerGender = genders[genderChoice];
        
        String farmNameInput = JOptionPane.showInputDialog(this, "Enter Farm Name:", "New Game", JOptionPane.PLAIN_MESSAGE);
        if (farmNameInput == null || farmNameInput.trim().isEmpty()) {
            gameState = titleState;
            ui.titleScreenState = 0;
            ui.commandNum = 0;
            return;
        }
        
        playMusic(0);
        this.playerData = new Player(playerName, playerGender, farmNameInput, 1000, this);
        
        this.player = new PlayerUI(this, keyH, this.playerData);
        
        this.farm = new Farm(farmNameInput, this.playerData, this);

        int randomMapIndex = (int) (Math.random() * 5) + 1;
        
        tileM = new TileManager(this, randomMapIndex);
        farm.getGameClock().startTime();
        farm.getSeason().setSeason("Summer");
        
        farm.getWeather().nextWeather(farm.getSeason());

        this.lightingSystem = new Lighting(this);
        this.lightingSystem.resetDay();

        aSetter.setObject(randomMapIndex);
        eHandler = new EventHandler(this, randomMapIndex);

        gameState = playState;
        System.out.println("New game started. Player: " + playerName + ", Farm: " + farmNameInput);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }

            if (timer >= 1000000000) {
                timer = 0;
            }
        }
        
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            if (keyH.iPressed){
                gameState = inventoryState;
            }
            else if (keyH.cPressed && !alreadyProcessedCheatKey) {
                alreadyProcessedCheatKey = true;
                handleCheatActivation();
            }

            if (farm.getGameClock().getHours() == 2) {
                playerData.performAction("Sleep", null);
            }
        }
        else if (gameState == pauseState) {
            // Pause logic
        }
        

        // if (statisticProvider.isAnyMilestoneAchieved() && gameState != statsDisplayState) {
        //     gameState = statsDisplayState;
        // }

        if (lightingSystem != null) {
            lightingSystem.update();
        }
    }

    private void handleCheatActivation() {
        gameState = cheatState;
        ui.processCheatInputs(); 
        if (gameState == playState) { 
            alreadyProcessedCheatKey = false; 
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }

        else {
            // TILE
            tileM.draw(g2);
    
            // Object
            for (int i = 0; i < objects[currentMap].length; i++) {
                if (objects[currentMap][i] != null) {
                    objects[currentMap][i].draw(g2, this);
                }
            }
    
            // PLAYER
            player.draw(g2);

            // LIGHTING
            if (lightingSystem != null) {
            lightingSystem.draw(g2);
            }
    
            // UI
            ui.draw(g2);
        }


        // Debugging
        if (keyH.showDebugText) {
            g2.setFont(new Font("Helvetica", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("Debug Info", x, y); y+= lineHeight;
            g2.drawString("WorldX " + player.worldX, x, y); y+= lineHeight;
            g2.drawString("WorldY " + player.worldY, x, y); y+= lineHeight;
            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize, x, y); y+= lineHeight;
            g2.drawString("Row " + (player.worldY + player.solidArea.y)/tileSize, x, y); y+= lineHeight;
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}