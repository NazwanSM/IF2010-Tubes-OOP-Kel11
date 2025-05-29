package actions;

import main.GamePanel;
import entity.player.Player;
import items.Items;
import java.util.List;

public class SleepingAction extends Action {
    private Player playerData;
    public List<Items> itemsToSell;

    public SleepingAction(Player playerData, GamePanel gp) {
        super("Sleep", 0, 0, gp);
        this.playerData = playerData;
        this.itemsToSell = gp.ui.soldItems;
    }

    @Override
    public boolean executeAction() {
        if (playerData.getEnergy() <= 0 && playerData.getEnergy() > Player.MIN_ENERGY){
            playerData.setEnergy(10);
        } else if (playerData.getEnergy() < (Player.MAX_ENERGY / 10) && playerData.getEnergy() > Player.MIN_ENERGY) {
            playerData.setEnergy(Player.MAX_ENERGY / 2);
        } else {
            playerData.setEnergy(Player.MAX_ENERGY);
        }

        if (itemsToSell != null) {
            for (Items item : itemsToSell) {
                if (item != null) {
                    gp.playerData.performAction("sell", null, item);
                }
            }
        }

        gp.farm.nextDay();


        
        if (gp.ui != null) gp.ui.addMessage("You feel rested. Energy: " + playerData.getEnergy());
        gp.eHandler.teleport(1, 13, 8);

        return true;
    }
}