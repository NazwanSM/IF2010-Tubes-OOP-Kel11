package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.GamePanel;
import data.FishRarity;
import entity.player.Player;
import items.ConcreteItem;
import items.Fish;
import items.Food;
import items.Items;
import items.Recipe;

public class CookingAction extends Action {
    private Player player;
    private Recipe recipe;
    private Food food;
    private int foodCount = 1;
    private boolean isCooking = false;
    private long cookingStartTime = 0;
    private final long cookingDuration = 12000;

    public CookingAction(Player player, Recipe recipe, Food food, GamePanel gp) {
        super("Cooking", 10, 60, gp);
        this.player = player;
        this.recipe = recipe;
        this.food = food;
    }

    public boolean hasIngredients(Player player) {
        Map<Items, Integer> playerInventoryMap = player.getInventory().checkInventory();

        for (Map.Entry<Items, Integer> requiredEntry : recipe.getIngredient().entrySet()) { 
            Items requiredItemKey = requiredEntry.getKey(); 
            int requiredAmount = requiredEntry.getValue(); 

            if (requiredItemKey instanceof ConcreteItem) {
                String placeholderName = requiredItemKey.getName();
                if ("Any Fish".equals(placeholderName)) {
                    int countFishInInventory = 0;
                    for (Map.Entry<Items, Integer> inventoryEntry : playerInventoryMap.entrySet()) {
                        if (inventoryEntry.getKey() instanceof Fish) {
                            countFishInInventory += inventoryEntry.getValue();
                        }
                    }
                    if (countFishInInventory < requiredAmount) {
                        return false;
                    }
                } else if ("Legend Fish".equals(placeholderName)) {
                    int countLegendaryFishInInventory = 0;
                    for (Map.Entry<Items, Integer> inventoryEntry : playerInventoryMap.entrySet()) {
                        if (inventoryEntry.getKey() instanceof Fish) {
                            Fish fishItem = (Fish) inventoryEntry.getKey();
                            if (fishItem.getRarity() == FishRarity.LEGENDARY) {
                                countLegendaryFishInInventory += inventoryEntry.getValue();
                            }
                        }
                    }
                    if (countLegendaryFishInInventory < requiredAmount) {
                        return false;
                    }
                }
            } else { 
                boolean specificItemFoundAndSufficient = false;
                for (Map.Entry<Items, Integer> inventoryEntry : playerInventoryMap.entrySet()) {
                    if (requiredItemKey.equals(inventoryEntry.getKey())) {
                        if (inventoryEntry.getValue() >= requiredAmount) {
                            specificItemFoundAndSufficient = true;
                            break;
                        }
                    }
                }
                if (!specificItemFoundAndSufficient) {
                    return false;
                }
            }
        }
        return true; 
    }

    public void startCooking() {
        if (!recipe.isUnlocked()) {
            gp.ui.addMessage("This recipe is locked. You haven't met the unlock requirements.");
            return;
        }

        if (!hasIngredients(player)) {
            gp.ui.addMessage("Required ingredients are not available in your inventory.");
            return;
        }

        if (player.getEnergy() < energyRequired) {
            gp.ui.addMessage("Not enough energy to cook!");
            return;
        }

        Map<Items, Integer> inventoryMap = player.getInventory().checkInventory();
        Items fuelUsed = null;

        for (Items item : inventoryMap.keySet()) {
            String name = item.getName();
            int qty = inventoryMap.get(item);

            if (name.equals("Firewood") && qty >= 1) {
                fuelUsed = item;
                foodCount = 1;
            } else if (name.equals("Coal") && qty >= 1) {
                fuelUsed = item;
                foodCount = 2;
            }
        }

        if (fuelUsed == null) {
            gp.ui.addMessage("Not enough fuel to cook!");
            return;
        }

        // Jalankan proses memasak
        player.decreaseEnergy(energyRequired);

        for (Map.Entry<Items, Integer> entry : recipe.getIngredient().entrySet()) { 
            Items ingredientKeyFromRecipe = entry.getKey(); 
            int baseAmountToConsume = entry.getValue(); 
            int actualAmountToConsume = baseAmountToConsume;

            if (ingredientKeyFromRecipe instanceof ConcreteItem) {
                String placeholderName = ingredientKeyFromRecipe.getName();
                if ("Any Fish".equals(placeholderName)) {
                    consumeAnyFishFromInventory(player, actualAmountToConsume);
                } 
                else if ("Legend Fish".equals(placeholderName)) {
                    consumeLegendFishFromInventory(player, actualAmountToConsume);
                }
            }
            else { 
                Items itemToRemove = null;
                for (Items invItem : player.getInventory().checkInventory().keySet()){
                    if (invItem.equals(ingredientKeyFromRecipe)){
                        itemToRemove = invItem;
                        break;
                    }
                }
                if (itemToRemove != null){
                    player.removeItemFromInventory(itemToRemove, actualAmountToConsume);
                }
            }
        }


        player.removeItemFromInventory(fuelUsed, 1);

        isCooking = true;
        cookingStartTime = System.currentTimeMillis();
        gp.ui.addMessage("Cooking " + food.getName() + "...");
            
        gp.addActiveCookingAction(this);
    }

    private void consumeAnyFishFromInventory(Player player, int amountToConsume) {
        int consumedCount = 0;
        List<Map.Entry<Items, Integer>> fishInInventory = new ArrayList<>();
        for(Map.Entry<Items,Integer> entry : player.getInventory().checkInventory().entrySet()){
            if(entry.getKey() instanceof Fish){
                fishInInventory.add(entry);
            }
        }

        for (Map.Entry<Items, Integer> entry : fishInInventory) {
            if (consumedCount >= amountToConsume) break;
            Fish fishItem = (Fish) entry.getKey();
            int availableAmount = entry.getValue();
            int canConsumeFromStack = Math.min(availableAmount, amountToConsume - consumedCount);

            player.removeItemFromInventory(fishItem, canConsumeFromStack);
            consumedCount += canConsumeFromStack;
        }
    }

    private void consumeLegendFishFromInventory(Player player, int amountToConsume) {
        int consumedCount = 0;
        List<Map.Entry<Items, Integer>> legendaryFishInInventory = new ArrayList<>();
        for(Map.Entry<Items,Integer> entry : player.getInventory().checkInventory().entrySet()){
            if(entry.getKey() instanceof Fish){
                Fish fishItem = (Fish) entry.getKey();
                if (fishItem.getRarity() == FishRarity.LEGENDARY) {
                    legendaryFishInInventory.add(entry);
                }
            }
        }

        for (Map.Entry<Items, Integer> entry : legendaryFishInInventory) {
            if (consumedCount >= amountToConsume) break;
            Fish fishItem = (Fish) entry.getKey();
            int availableAmount = entry.getValue();
            int canConsumeFromStack = Math.min(availableAmount, amountToConsume - consumedCount);

            player.removeItemFromInventory(fishItem, canConsumeFromStack);
            consumedCount += canConsumeFromStack;
        }
    }

    public void update() {
        if (isCooking) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - cookingStartTime;
            
            if (elapsedTime % 3000 == 0) {
                int progressPercent = (int)((elapsedTime * 100) / cookingDuration);
                gp.ui.addMessage("Cooking progress: " + Math.min(progressPercent, 100) + "%");
            }
            
            if (elapsedTime >= cookingDuration) {
                completeCooking();
            }
        }
    }

    private void completeCooking() {
        isCooking = false;
        player.addItemToInventory(food, foodCount);
        gp.playSE(6);
        gp.ui.addMessage("Cooking successful! " + food.getName() + " is ready.");
        
        gp.removeActiveCookingAction(this);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Food getFood() {
        return food;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setFood(Food food) {
        this.food = food;
    }


    @Override
    public boolean executeAction() {
        startCooking();
        return true;
    }
}