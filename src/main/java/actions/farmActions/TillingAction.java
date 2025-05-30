package actions.farmActions;

import main.GamePanel;
import entity.player.Player;

import java.awt.Point;

public class TillingAction extends FarmingAction {

    public TillingAction(Player player, GamePanel gp, Point tilePosition) {
        super(player, gp, tilePosition);
    }

    @Override
    public boolean executeAction() {
        if (player.getEnergy() < -15) {
            gp.ui.addMessage("Not enough energy to till land.");
            return false;
        }
        
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y];
        
        if (isGrassTile(tileNum)) {
            int soilTileNum = getSoilTileNumber(gp.farm.getSeason().getCurrentSeason());
            gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y] = soilTileNum;
            
            player.decreaseEnergy(energyRequired);
            gp.farm.getGameClock().advance(timeRequired);
            
            gp.playSE(14);
            
            gp.ui.addMessage("Land tilled successfully!");
            return true;
        } else {
            gp.ui.addMessage("This land cannot be tilled.");
            return false;
        }
    }
    
    private boolean isGrassTile(int tileNum) {
        return tileNum == 0 || tileNum == 944 || tileNum == 946 || tileNum == 960;
    }
    
    private int getSoilTileNumber(String season) {
        switch (season) {
            case "Spring": return 975; 
            case "Summer": return 27; 
            case "Fall": return 974;   
            case "Winter": return 976; 
            default: return 1;
        }
    }
}