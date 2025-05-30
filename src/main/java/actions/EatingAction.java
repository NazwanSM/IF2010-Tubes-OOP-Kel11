package actions;

import main.GamePanel;
import entity.player.Player;
import items.Edible;
import items.Items;

public class EatingAction extends Action {
    private Items item;
    private Player playerData;

    public EatingAction(Player playerData, GamePanel gp, Items item) {
        super("Eat", 0, 5, gp);
        this.playerData = playerData;
        this.item = item;
    }

    @Override
    public boolean executeAction() {
        if (item instanceof Edible) { 
            Edible edibleItem = (Edible) item;
            if (playerData.hasItem(item)) {
                    edibleItem.eat(playerData); 
                    gp.farm.getGameClock().advance(timeRequired);
                    playerData.removeItemFromInventory(item, 1);
                    playerData.increaseEnergy(edibleItem.getEnergy()); 
                } else {
                    gp.ui.addMessage("You don't have this item in your inventory."); 
                }
            } 
            else {
                gp.ui.addMessage("This item is not edible."); 
            }
        return true;
    }
}
