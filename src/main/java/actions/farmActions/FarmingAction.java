package actions.farmActions;

import main.GamePanel;
import entity.player.Player;
import world.Farm;

import java.awt.Point;

public class FarmingAction{
    protected Point tilePosition;
    protected Farm farm;
    protected Player player;
    protected GamePanel gp;
    protected final int energyRequired = 5;
    protected final int timeRequired = 5;

    public FarmingAction(Player player, GamePanel gp, Point tilePosition) {
        this.gp = gp;
        this.player = player;
        this.tilePosition = tilePosition;
        this.farm = gp.farm;
    }
    
    public static Point screenToTilePosition(GamePanel gp, int screenX, int screenY) {
        int worldX = screenX + gp.player.worldX - gp.player.screenX;
        int worldY = screenY + gp.player.worldY - gp.player.screenY;
        
        int col = worldX / gp.tileSize;
        int row = worldY / gp.tileSize;
        
        return new Point(col, row);
    }

    public static Point getTilePlayerIsOn(GamePanel gp) {
        int playerReferenceX = gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width / 2; 
        int playerReferenceY = gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height - 1;

        int tileX = playerReferenceX / gp.tileSize; 
        int tileY = playerReferenceY / gp.tileSize; 

        return new Point(tileX, tileY);
    }

    public boolean executeAction() {
        return false;
    }
}