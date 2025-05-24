// File: Items/tools/WateringCan.java
package items.equipment;

// import Actions.WateringAction; // Impor kelas Action yang sesuai
import entity.player.Player;
import main.GamePanel;

public class WateringCan extends Equipment {

    public WateringCan(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }

    @Override
    public boolean use(Player playerData, GamePanel gp, int targetWorldX, int targetWorldY) {
        // if (gp.player != null && playerData != null) {
        //     gp.player.startToolAnimation(getUseAnimationName(playerData.getDirection()));
        // }
        
        // WateringAction wateringAction = new WateringAction(playerData, gp, targetWorldX, targetWorldY);
        // return wateringAction.executeAction();

        return true;
    }

    @Override
    public String getUseAnimationName(String playerDirection) {
        return "wateringcan_use_" + playerDirection.toLowerCase();
    }
}