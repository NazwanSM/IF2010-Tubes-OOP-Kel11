package actions;

import main.GamePanel;
import entity.npc.NPC;
import entity.player.Player;
import items.Items;

public class GiftingAction extends Action {
    private Player playerData;
    private NPC targetNPC;
    private Items gift;

    private static final int GIFTING_ENERGY_COST = 5;
    private static final int GIFTING_TIME_COST_MINUTES = 10;

    public GiftingAction(Player playerData, NPC targetNPC, GamePanel gp, Items gift) {
        super("Gift item to " + targetNPC.getNPCName(), GIFTING_ENERGY_COST, GIFTING_TIME_COST_MINUTES, gp);
        this.playerData = playerData;
        this.targetNPC = targetNPC;
        this.gift = gift;
    }

    @Override
    public boolean executeAction() {
        if (playerData.getInventory().hasItem(gift)) {
            if (targetNPC.getLovedItems().contains(gift.getName())) {
                targetNPC.increaseHeartPoints(25); 
            }
            else if (targetNPC.getLikedItems().contains(gift.getName())) {
                targetNPC.increaseHeartPoints(20); 
            }
            else if (targetNPC.getHatedItems().contains(gift.getName())) {
                targetNPC.decreaseHeartPoints(25); 
            }

            gp.ui.addMessage("Giving " + gift.getName() + " to " + targetNPC.getNPCName() + ".");
            playerData.removeItemFromInventory(gift, 1); 

            gp.farm.getGameClock().advance(10);
            playerData.decreaseEnergy(5);
            gp.manager.trackNPCGift(targetNPC.getNPCName());
            System.out.println(targetNPC.getHeartPoints());
        } else {
            gp.ui.addMessage("You don't have this item in your inventory.");
        }
        return true; 
    }
}