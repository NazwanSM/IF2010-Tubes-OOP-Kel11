package items;
import java.util.Map;

import entity.player.Inventory;

public class Recipe {
    private String itemID;
    private Food result;
    private Map<Items, Integer> ingredient;

    public Recipe(String itemID, Food result, Map<Items, Integer> ingredient) {
        this.itemID = itemID;
        this.result = result;
        this.ingredient = ingredient;
    }

    public String getItemID() {
        return itemID;
    }

    public Food getResult() {
        return result;
    }

    public Map<Items, Integer> getIngredient() {
        return ingredient;
    }

    public boolean isAvailable(Inventory inventory) {
        Map<Items, Integer> itemsMap = inventory.checkInventory();
        for (Map.Entry<Items, Integer> requiredItems : ingredient.entrySet()) {
            Items items = requiredItems.getKey();
            int requiredAmount = requiredItems.getValue();
            if (!itemsMap.containsKey(items) || itemsMap.get(items) <= requiredAmount) {
                return false;
            }
        }
        return true;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setResult(Food result) {
        this.result = result;
    }

    public void setIngredient(Map<Items, Integer> ingredient) {
        this.ingredient = ingredient;
    }
}