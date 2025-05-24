package items.equipment; 

import entity.player.Player;
import items.Items;
import main.GamePanel;       

public abstract class Equipment extends Items {

    private String description;

    public Equipment(String name, String description) {
        super(name, "Equipment");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean use(Player playerData, GamePanel gp, int targetWorldX, int targetWorldY);

    public abstract String getUseAnimationName(String playerDirection);
}