package actions.farmActions;

import main.GamePanel;
import data.RecipeData;
import entity.player.Player;
import items.Crops;

import java.awt.Point;

public class HarvestingAction extends FarmingAction {

    public HarvestingAction(Player player, GamePanel gp, Point tilePosition) {
        super(player, gp, tilePosition);
    }

    @Override
    public boolean executeAction() {
        if (player.getEnergy() < -15) {
            gp.ui.addMessage("Not enough energy to harvest crops.");
            return false;
        }
        
        if (hasCrop(tilePosition)) {
            if (!isReadyToHarvest(tilePosition)) {
                gp.ui.addMessage("This crop is not ready for harvest yet.");
                return false;
            }
            
            Crops harvestedCrop = farm.getFieldManager().harvestCrop(tilePosition.x, tilePosition.y);
            
            if (harvestedCrop != null) {
                player.getInventory().addItem(harvestedCrop, harvestedCrop.getQuantityPerHarvest());
                
                player.decreaseEnergy(energyRequired);
                gp.farm.getGameClock().advance(timeRequired);
                
                gp.manager.trackCropHarvested(harvestedCrop.getName(), harvestedCrop.getQuantityPerHarvest());
                
                gp.playSE(18);
                
                gp.ui.addMessage("Harvested " + harvestedCrop.getName() + "!");
                gp.manager.trackCropHarvested(harvestedCrop.getName(), harvestedCrop.getQuantityPerHarvest());
                RecipeData.getRecipeById("recipe_7").setUnlocked(true);
                if (harvestedCrop.getName().equalsIgnoreCase("Hot Pepper")) {
                    RecipeData.getRecipeById("recipe_8").setUnlocked(true);
                }
                return true;
            } else {
                gp.ui.addMessage("Couldn't harvest the crop.");
                return false;
            }
        } else {
            gp.ui.addMessage("There's no crop to harvest here.");
            return false;
        }
    }
    
    private boolean hasCrop(Point tilePosition) {
        return farm.getFieldManager().hasCropAt(tilePosition.x, tilePosition.y);
    }
    
    private boolean isReadyToHarvest(Point tilePosition) {
        return farm.getFieldManager().isCropReadyToHarvest(tilePosition.x, tilePosition.y);
    }
}