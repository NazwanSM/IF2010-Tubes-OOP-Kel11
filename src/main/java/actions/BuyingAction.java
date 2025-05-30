package actions;

import main.GamePanel;
import entity.player.Player;
import items.Items;

public  class BuyingAction extends Action {

    private Items item;
    private static int energyCost = 0;
    private static final int BUYING_TIME_COST_MINUTES = 0;
    private Player player;
    private int price;

    public BuyingAction(Player player, Items item, GamePanel gp, int price) {
        super("Buy " + item.getName(), energyCost, BUYING_TIME_COST_MINUTES, gp);
        this.item = item;
        this.player = player;
        this.price = price;
    }

    @Override
    public boolean executeAction() {
        player.addItemToInventory(item,1);
        player.subtractGold(price);
        if (gp.ui != null) {
            gp.ui.addMessage("You bought " + item.getName() + " for " + price + " gold.");
        }
        return true;
    }
}
