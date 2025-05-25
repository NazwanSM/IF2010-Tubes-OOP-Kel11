package Actions;

import entity.player.Player;
import items.Food;
import items.Items;
import items.Recipe;
import java.util.Map;

public class Cooking extends Action {
    private Player player;
    private Recipe recipe;
    private Food food;
    private int fuelRequired;

    public Cooking(Player player, Recipe recipe, Food food, int fuelRequired) {
        super("Cooking", 10, 60);
        this.player = player;
        this.recipe = recipe;
        this.food = food;
        this.fuelRequired = fuelRequired;
    }

    public boolean hasIngredients(Player player) {
        Map<Items, Integer> itemsMap = player.getInventory().checkInventory();
        for (Map.Entry<Items, Integer> requiredItems : recipe.getIngredient().entrySet()) {
            Items items = requiredItems.getKey();
            int requiredAmount = requiredItems.getValue();
            if (!itemsMap.containsKey(items) || itemsMap.get(items) < requiredAmount) {
                return false;
            }
        }
        return true;
    }

    public void startCooking() {
        if (!recipe.isUnlocked()) {
            System.out.println("This recipe is locked. You haven't met the unlock requirements.");
            return;
        }

        if (!hasIngredients(player)) {
            System.out.println("Required ingredients are not available in your inventory.");
            return;
        }

        if (player.getEnergy() < energyRequired) {
            System.out.println("Not enough energy to cook!");
            return;
        }

        Map<Items, Integer> inventoryMap = player.getInventory().checkInventory();
        Items fuelUsed = null;
        int foodCount = 1;

        for (Items item : inventoryMap.keySet()) {
            String name = item.getName();
            int qty = inventoryMap.get(item);

            if (name.equals("Firewood") && qty >= 1) {
                fuelUsed = item;
                foodCount = 1;
                // break;
            } else if (name.equals("Coal") && qty >= 1) {
                fuelUsed = item;
                foodCount = 2;
                // break;
            }
        }

        if (fuelUsed == null) {
            System.out.println("Not enough fuel to cook!");
            return;
        }

        // Jalankan proses memasak
        player.decreaseEnergy(energyRequired);

        for (Map.Entry<Items, Integer> entry : recipe.getIngredient().entrySet()) {
            Items ingredient = entry.getKey();
            int qty = entry.getValue() * foodCount;
            player.removeItemFromInventory(ingredient, qty);
        }

        player.removeItemFromInventory(fuelUsed, 1);

        System.out.println("Cooking " + food.getName() + "...");
        player.addItemToInventory(food, foodCount);
        System.out.println("Cooking successful! " + food.getName() + " is ready.");
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Food getFood() {
        return food;
    }

    public int getFuelRequired() {
        return fuelRequired;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setFuelRequired(int fuelRequired) {
        this.fuelRequired = fuelRequired;
    }

    @Override
    public void executeAction() {
        startCooking();
    }
}