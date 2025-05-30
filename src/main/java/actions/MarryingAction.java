package actions;

import main.GamePanel;
import entity.npc.NPC;
import entity.player.Player;

public class MarryingAction extends Action {

    private NPC targetNPC;
    private static int energyCost = 80;
    private Player player;

    public MarryingAction (Player player, NPC targetNPC, GamePanel gp) {
        super("Marrying to " + targetNPC.getNPCName(), energyCost, 60, gp);
        this.targetNPC = targetNPC;
        this.player = player;
    }

    @Override
    public boolean executeAction() {
        if (player.isMarriable(targetNPC)) {
            player.getPartner().setRelationshipStatus("Married");
            player.decreaseEnergy(energyCost);

            int currentHour = gp.farm.getGameClock().getHours();
            int currentMinute = gp.farm.getGameClock().getMinutes();
            int targetHour = 22;
            int targetMinute = 0;

            int currentTotalMinutes = currentHour * 60 + currentMinute;
            int targetTotalMinutes = targetHour * 60 + targetMinute;
            int minutesToAdvance = targetTotalMinutes - currentTotalMinutes;

            if (minutesToAdvance < 0) {
                minutesToAdvance += 24 * 60;
            }
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialog = "You are now married to " + player.getPartner().getNPCName() + ".";

            gp.farm.getGameClock().advance(minutesToAdvance);
            
            gp.ui.addMessage("Time has skipped to 22:00. You are back home.");
            gp.manager.trackPlayerMarriageStatus(true);
            gp.eHandler.teleport(1, 13, 8);
            return true;
        } else{
            if (Player.getProposingDay() == gp.farm.getDay()) {
                gp.ui.addMessage("You have to wait until tomorrow to marry.");
                return false;
            } 
            gp.ui.addMessage("You are not engaged to " + targetNPC.getNPCName() + ".");
            return false;
        }
    }
}
