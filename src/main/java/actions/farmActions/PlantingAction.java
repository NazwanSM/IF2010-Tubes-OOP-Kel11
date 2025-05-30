package actions.farmActions;

import main.GamePanel;
import entity.player.Player;
import items.Seed;

import java.awt.Point;

public class PlantingAction extends FarmingAction {
    private Seed seed;

    public PlantingAction(Player player, GamePanel gp, Point tilePosition, Seed seed) {
        super(player, gp, tilePosition);
        this.seed = seed;
    }

    @Override
    public boolean executeAction() {
        if (player.getEnergy() < -15) {
            gp.ui.addMessage("Not enough energy to plant seeds.");
            return false;
        }
        
        if (!player.getInventory().hasItem(seed)) {
            gp.ui.addMessage("You don't have any " + seed.getName() + " seeds.");
            return false;
        }
        
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y];
        
        if (isSoilTile(tileNum)) {
            if (hasCrop(tilePosition)) {
                gp.ui.addMessage("There's already a crop planted here.");
                return false;
            }
            
            String currentSeason = gp.farm.getSeason().getCurrentSeason();
            if (!seed.getSeason().contains(currentSeason)) {
                gp.ui.addMessage("These seeds can't be planted in " + currentSeason + ".");
                return false;
            }
            
            boolean planted = farm.getFieldManager().plantCrop(tilePosition.x, tilePosition.y, seed);
            
            if (planted) {
                player.removeItemFromInventory(seed, 1);
                player.decreaseEnergy(energyRequired);
                gp.farm.getGameClock().advance(timeRequired);
                
                gp.playSE(17);
                
                gp.ui.addMessage(seed.getName() + " planted successfully!");
                return true;
            } else {
                gp.ui.addMessage("Couldn't plant the seed.");
                return false;
            }
        } else {
            gp.ui.addMessage("Seeds can only be planted on tilled soil.");
            return false;
        }
    }
    
    private boolean isSoilTile(int tileNum) {
        return tileNum == 27 || tileNum == 974 || tileNum == 975 || tileNum == 976;
    }
    
    private boolean hasCrop(Point tilePosition) {
        return farm.getFieldManager().hasCropAt(tilePosition.x, tilePosition.y);
    }
}