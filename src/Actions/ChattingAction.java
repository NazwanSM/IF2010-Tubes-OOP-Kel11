// package actions;

// import entity.player.Player;
// import main.GamePanel;
// import World.NPC;      

// public class ChattingAction extends Action {
//     private Player playerData;
//     private NPC targetNPC;

//     private static final int CHATTING_ENERGY_COST = 10;
//     private static final int CHATTING_TIME_COST_MINUTES = 10;
//     private static final int HEART_POINTS_GAIN = 10;

//     public ChattingAction(Player playerData, NPC targetNPC, GamePanel gp) {
//         super("Chat with " + targetNPC.getNPCName(), CHATTING_ENERGY_COST, CHATTING_TIME_COST_MINUTES, gp);
//         this.playerData = playerData;
//         this.targetNPC = targetNPC;
//     }

//     @Override
//     public boolean executeAction() {
//         if (playerData.getEnergy() < getEnergyRequired() && playerData.getEnergy() > Player.MIN_ENERGY) {

//         } 
//         else if (playerData.getEnergy() <= Player.MIN_ENERGY && getEnergyRequired() > 0) {
//             if (gp.ui != null) gp.ui.showMessage("Not enough energy to chat!");
//             System.out.println(playerData.getName() + " does not have enough energy to chat.");
//             return false; // Aksi gagal
//         }
        
//         playerData.decreaseEnergy(getEnergyRequired());
//         playerData.getGameClock().advance(getTimeRequired()); 

//         targetNPC.increaseHeartPoints(HEART_POINTS_GAIN);

//         String dialogue = targetNPC.getDialogue();
//         if (gp.ui != null) {
//             gp.ui.currentDialog = targetNPC.getNPCName() + ":\n" + dialogue;
//             gp.gameState = gp.dialogueState;
//         } else {
//             System.out.println("Chatting with " + targetNPC.getNPCName() + ": " + dialogue);
//         }

//         // 5. Catat statistik
//         // if (playerData.getStatisticTracker() != null) {
//         //     playerData.getStatisticTracker().trackNPCChat(targetNPC.getNPCName());
//         // }
        
//         System.out.println(getName() + " action executed.");
//         return true; 
//     }
// }