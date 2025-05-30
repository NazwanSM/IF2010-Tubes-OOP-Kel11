package actions;

import main.GamePanel;
import entity.npc.NPC;
import entity.player.Player;

public class ProposingAction extends Action {

    private NPC targetNPC;
    private static int energyCost = 10;
    private static final int PROPOSING_TIME_COST_MINUTES = 60;
    private Player player;

    public ProposingAction(Player player, NPC targetNPC, GamePanel gp) {
        super("Propose to " + targetNPC.getNPCName(), energyCost, PROPOSING_TIME_COST_MINUTES, gp);
        this.targetNPC = targetNPC;
        this.player = player;
    }

    @Override
    public boolean executeAction() {
        gp.farm.getGameClock().advance(PROPOSING_TIME_COST_MINUTES);
        String relationshipStatus = targetNPC.getRelationshipStatus();
        if (player.isProposeable(targetNPC)) {
            player.setPartner(targetNPC);
            targetNPC.setRelationshipStatus("Fiance");
            player.decreaseEnergy(energyCost);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialog = "You proposed to " + targetNPC.getNPCName() + ".\n" + targetNPC.getNPCName() + " accepted your proposal!\n" + "You are now engaged to " + targetNPC.getNPCName() + ".";
            Player.setProposingDay(gp.farm.getDay());
            return true;
        } else {
            if (player.getInventory().getItemByName("Proposal Ring") == null){
                gp.ui.addMessage("You need a Proposal Ring to propose.");
            } else if (!relationshipStatus.equalsIgnoreCase("single")) {
                gp.ui.addMessage(targetNPC.getNPCName() + " is already your partner.");
                return false;
            } else if (player.getPartner() != null) {
                gp.ui.addMessage("You are already in a relationship with " + player.getPartner().getNPCName() + ".");
            } else {
                gp.ui.addMessage(targetNPC.getNPCName() + "'s heart points are not enough.");
            }
            player.decreaseEnergy(energyCost + 10);
            gp.ui.addMessage("You are rejected");
            return false;
        }
    }
    
}
