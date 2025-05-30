package actions;

import main.GamePanel;
import entity.player.Player;

public class WatchingAction extends Action {
    private Player playerData;

    public WatchingAction(Player playerData, GamePanel gp) {
        super("Watch", 5, 15, gp);
        this.playerData = playerData;
    }

    @Override
    public boolean executeAction() {
        if (gp.playerData.getEnergy() < -15){
            gp.ui.addMessage("You don't have enough energy to watch!");
            return false;
        }

        gp.farm.getGameClock().advance(timeRequired);
        playerData.decreaseEnergy(energyRequired);
        gp.gameState = gp.watchState;
        return true;
    }
}
