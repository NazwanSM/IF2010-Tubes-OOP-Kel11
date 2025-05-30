package actions.farmActions;

import main.GamePanel;
import entity.player.Player;
import items.Items;

import java.awt.Point;

public class WateringAction extends FarmingAction {
    private Items wateringCan;

    public WateringAction(Player player, GamePanel gp, Point tilePosition) {
        super(player, gp, tilePosition);
        this.wateringCan = player.getInventory().getItemByName("Watering Can");
    }

    @Override
    public boolean executeAction() {
        if (wateringCan == null) {
            gp.ui.addMessage("You need a watering can to water crops.");
            return false;
        }
        
        if (player.getEnergy() < energyRequired) {
            gp.ui.addMessage("Not enough energy to water crops.");
            return false;
        }
        
        int tileNum = gp.tileM.mapTileNum[gp.currentMap][tilePosition.x][tilePosition.y];
        
        if (isSoilTile(tileNum)) {
            if (!hasCrop(tilePosition)) {
                gp.ui.addMessage("There's no crop to water here.");
                return false;
            }
            
            if (isWatered(tilePosition)) {
                gp.ui.addMessage("This crop is already watered.");
                return false;
            }
            
            boolean watered = farm.getFieldManager().waterCrop(tilePosition.x, tilePosition.y);
            
            if (watered) {
                player.decreaseEnergy(energyRequired);
                gp.farm.getGameClock().advance(timeRequired);
                
                gp.playSE(20);
                
                gp.ui.addMessage("Crop watered successfully!");
                return true;
            } else {
                gp.ui.addMessage("Couldn't water the crop.");
                return false;
            }
        } else {
            gp.ui.addMessage("You can only water soil with crops.");
            return false;
        }
    }
    
    private boolean isSoilTile(int tileNum) {
        return tileNum == 27 || tileNum == 974 || tileNum == 975 || tileNum == 976;
    }
    
    private boolean hasCrop(Point tilePosition) {
        // Check if there's a crop planted at this position
        return farm.getFieldManager().hasCropAt(tilePosition.x, tilePosition.y);
    }
    
    private boolean isWatered(Point tilePosition) {
        // Check if the crop at this position is already watered
        return farm.getFieldManager().isCropWatered(tilePosition.x, tilePosition.y);
    }
}