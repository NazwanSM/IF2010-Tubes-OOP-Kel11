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
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[50];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("map1.txt");
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

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream("/resource/maps/" + filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
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
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
