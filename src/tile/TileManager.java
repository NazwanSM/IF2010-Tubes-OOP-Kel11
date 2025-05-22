package tile;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import main.UtilityTool;


public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("map1.txt", 0);
        loadMap("house.txt", 1);
    }

    public void getTileImage() {
        setup (0, "/summer/0-Grass", false);
        setup (1, "/summer/1-Tillable", false);
        setup (2, "/summer/2-Grass_Tillable_Top_Left", false);
        setup (3, "/summer/3-Grass_Tillable_Top", false);
        setup (4, "/summer/4-Grass_Tillable_Top_Right", false);
        setup (5, "/summer/5-Grass_Tillable_Right", false);
        setup (6, "/summer/6-Grass_Tillable_Bottom_Right", false);
        setup (7, "/summer/7-Grass_Tillable_Bottom", false);
        setup (8, "/summer/8-Grass_Tillable_Bottom_Left", false);
        setup (9, "/summer/9-Grass_Tillable_Left", false);
        setup (10, "/house/House_Tiles_1", false);
        setup (11, "/house/House_Tiles_2", false);
        setup (12, "/house/House_Tiles_3", false);
        setup (13, "/house/House_Tiles_4", false);
        setup (14, "/house/House_Tiles_5", false);
        setup (15, "/house/House_Tiles_6", false);
        setup (16, "/house/House_Tiles_7", false);
        setup (17, "/house/House_Tiles_8", false);
        setup (18, "/house/House_Tiles_9", false);
        setup (19, "/house/House_Tiles_10", false);
        setup (20, "/house/House_Tiles_11", false);
        setup (21, "/house/House_Tiles_12", false);
        setup (22, "/house/House_Tiles_13", false);
        setup (23, "/house/House_Tiles_14", false);
        setup (24, "/house/House_Tiles_15", false);
        setup (25, "/house/House_Tiles_16", false);
        setup (26, "/house/Black", false);

    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resource/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream("/resource/maps/" + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int cameraLeftWorldX = gp.player.worldX - gp.player.screenX;
            int cameraTopWorldY = gp.player.worldY - gp.player.screenY;

            if (worldX + gp.tileSize > cameraLeftWorldX &&
                worldX < cameraLeftWorldX + gp.screenWidth &&
                worldY + gp.tileSize > cameraTopWorldY &&
                worldY < cameraTopWorldY + gp.screenHeight) {
                
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }


            worldCol++;
            
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
