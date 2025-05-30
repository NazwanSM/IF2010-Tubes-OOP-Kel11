package actions.farmActions;

import main.GamePanel;
import entity.player.Player;

import java.awt.Point;

public class RecoverLandAction extends FarmingAction {

    public RecoverLandAction(Player player, GamePanel gp, Point tilePosition) {
        super(player, gp, tilePosition);
    }

    @Override
    public boolean executeAction() {
        if (player.getEnergy() < -15) {
            gp.ui.addMessage("Not enough energy to recover land.");
            return false;
        }
        
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y];
        
        if (isSoilTile(tileNum)) {
            if (hasCrop(tilePosition)) {
                gp.ui.addMessage("Cannot recover land with crops planted.");
                return false;
            }
            
            int grassTileNum = getGrassTileNumber(gp.farm.getSeason().getCurrentSeason());
            gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y] = grassTileNum;

            player.decreaseEnergy(energyRequired);
            gp.farm.getGameClock().advance(timeRequired);
            
            gp.playSE(15);
            
            gp.ui.addMessage("Land recovered successfully!");
            return true;
        } else {
            gp.ui.addMessage("This land cannot be recovered.");
            return false;
        }
    }
    
    private boolean isSoilTile(int tileNum) {
        return tileNum == 27 || tileNum == 974 || tileNum == 975 || tileNum == 976;
    }
    
    private boolean hasCrop(Point tilePosition) {
        // Check if there's a crop planted at this position
        // You'll need to implement this based on your crop tracking system
        return farm.getFieldManager().hasCropAt(tilePosition.x, tilePosition.y);
    }
    
    private int getGrassTileNumber(String season) {
        switch (season) {
            case "Spring": return 946;
            case "Summer": return 0;
            case "Fall": return 960;   
            case "Winter": return 944;
            default: return 0;
        }
    }
}