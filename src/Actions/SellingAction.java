package actions;

import main.GamePanel;
import entity.player.Player;
import items.Items;
import items.Sellable;

public  class SellingAction extends Action {

    private Items item;
    private int price;
    private static int energyCost = 0;
    private static final int SELLING_TIME_COST_MINUTES = 0;
    private Player player;

    public SellingAction(Player player, Items item, GamePanel gp) {
        super("Sell " + item.getName(), energyCost, SELLING_TIME_COST_MINUTES, gp);
        this.item = item;
        this.player = player;
    }

    @Override
    public boolean executeAction() {
        if (item instanceof Sellable){
            Sellable sellableItem = (Sellable) item;
            price = sellableItem.getSellPrice();
        } else {
            gp.ui.addMessage("This item cannot be sold.");
            return false;
        }
        player.addGold(price);
        return true;
    }
}
