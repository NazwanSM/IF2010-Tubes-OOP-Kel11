package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.PlayerUI;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    final int originalTileSize = 16;
    final int scale = 2;
    
    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 32;
    public final int maxWorldCol = 32;
    public final int maxWorldRow = 32;
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 

    // FPS
    final int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound music = new Sound();
    Sound se = new Sound();
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // Entity and object
    public PlayerUI player = new PlayerUI(this, keyH);
    public SuperObject[] objects = new SuperObject[20];

    public void setupGame() {
        aSetter.setObject();
        playMusic(0);
    }


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Tile
        tileM.draw(g2);

        // Object
        for (SuperObject obj : objects) {
            if (obj != null) {
                obj.draw(g2, this);
            }
        }

        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);

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