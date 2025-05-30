package actions;

import main.GamePanel;
import entity.npc.NPC;
import entity.player.Player;

public class ChattingAction extends Action {
    private Player playerData;
    private NPC targetNPC;

    private static final int CHATTING_ENERGY_COST = 10;
    private static final int CHATTING_TIME_COST_MINUTES = 10;
    private static final int HEART_POINTS_GAIN = 10;

    public ChattingAction(Player playerData, NPC targetNPC, GamePanel gp) {
        super("Chat with " + targetNPC.getNPCName(), CHATTING_ENERGY_COST, CHATTING_TIME_COST_MINUTES, gp);
        this.playerData = playerData;
        this.targetNPC = targetNPC;
    }

    @Override
    public boolean executeAction() {
        if (playerData.getEnergy() < getEnergyRequired() && playerData.getEnergy() > Player.MIN_ENERGY) {

        } 
        else if (playerData.getEnergy() <= Player.MIN_ENERGY && getEnergyRequired() > 0) {
            if (gp.ui != null) gp.ui.addMessage("Not enough energy to chat!");
            return false;
        }
        
        playerData.decreaseEnergy(getEnergyRequired());
        gp.farm.getGameClock().advance(getTimeRequired()); 

        targetNPC.increaseHeartPoints(HEART_POINTS_GAIN);

        gp.gameState = gp.dialogueState;
        String dialogue = targetNPC.getDialogue();
        gp.ui.currentDialog = dialogue;

        if (gp.manager != null) {
            gp.manager.trackNPCChat(targetNPC.getNPCName());
        }
        return true; 
    }
}