package actions;

import main.GamePanel;
import entity.player.Player;
import items.Items;
import items.Sellable;

import java.util.Map;

public class SleepingAction extends Action {
    private Player playerData;
    public Map<Items, Integer> itemsToSell;
    private static int sellNum = 0;
    private static int totalSellNum = 0;

    public SleepingAction(Player playerData, GamePanel gp) {
        super("Sleep", 0, 0, gp);
        this.playerData = playerData;
        this.itemsToSell = gp.ui.soldItemsMap;
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

        playerData.isSleeping = true;
        playerData.startSleeping();

        for (Map.Entry<Items, Integer> entry : itemsToSell.entrySet()) {
            Items item = entry.getKey();
            Sellable sellableItem = (Sellable) item;
            int quantity = entry.getValue();
            if (quantity > 0) {
                for (int i = 0; i < quantity; i++) {
                    playerData.performAction("Sell", null, item);
                    sellNum++;
                    totalSellNum += sellableItem.getSellPrice();
                }
            }
        }
        gp.ui.soldItemsMap.clear();
        itemsToSell.clear();
        
        gp.farm.nextDay();
        
        
        
        if (gp.ui != null) gp.ui.addMessage("You feel rested. Energy: " + playerData.getEnergy());
        if (sellNum > 0) {
            gp.ui.addMessage("You sold " + sellNum + " items for a total of " + totalSellNum + " gold.");
        }
        gp.eHandler.teleport(1, 13, 8);
        sellNum = 0;
        playerData.isSleeping = false;

        return true;
    }
}