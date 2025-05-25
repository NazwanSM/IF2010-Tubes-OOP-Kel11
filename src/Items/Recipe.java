package items;
import java.util.Map;

public class Recipe {
    private String itemID;
    private Food result;
    private Map<Items, Integer> ingredient;
    private boolean unlocked;
    private String unlockInfo;

    public Recipe(String itemID, Food result, Map<Items, Integer> ingredient, boolean unlocked, String unlockInfo) {
        this.itemID = itemID;
        this.result = result;
        this.ingredient = ingredient;
        this.unlocked = unlocked;
        this.unlockInfo = unlockInfo;
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

    public boolean isUnlocked() {
        return unlocked;
    }

    public String getUnlockInfo() {
        return unlockInfo;
    }

    public void setUnlockInfo(String unlockInfo) {
        this.unlockInfo = unlockInfo;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
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