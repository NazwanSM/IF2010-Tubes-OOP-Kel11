package data;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import items.ConcreteItem;
import items.Items;
import items.Recipe;

public class RecipeData {
    public static final List<Recipe> Recipes = new ArrayList<>();

    static {
        // Recipe 1: Fish n’ Chips
        Map<Items, Integer> ingredients1 = new HashMap<>();
        ingredients1.put(new ConcreteItem("Any Fish", "Fish"), 2);
        ingredients1.put(CropsData.getCropByName("Wheat"), 1);
        ingredients1.put(CropsData.getCropByName("Potato"), 1);
        Recipes.add(new Recipe("recipe_1", FoodData.getFoodByName("Fish n' Chips"), ingredients1, false, "Buy in store"));

        // Recipe 2: Baguette
        Map<Items, Integer> ingredients2 = new HashMap<>();
        ingredients2.put(CropsData.getCropByName("Wheat"), 3);
        Recipes.add(new Recipe("recipe_2", FoodData.getFoodByName("Baguette"), ingredients2, true, "Default"));

        // Recipe 3: Sashimi
        Map<Items, Integer> ingredients3 = new HashMap<>();
        ingredients3.put(FishData.getFishByName("Salmon"), 3);
        Recipes.add(new Recipe("recipe_3", FoodData.getFoodByName("Sashimi"), ingredients3, false, "After fishing 10 times"));

        // Recipe 4: Fugu
        Map<Items, Integer> ingredients4 = new HashMap<>();
        ingredients4.put(FishData.getFishByName("Pufferfish"), 1);
        Recipes.add(new Recipe("recipe_4", FoodData.getFoodByName("Fugu"), ingredients4, false, "Fish 'Pufferfish'"));

        // Recipe 5: Wine
        Map<Items, Integer> ingredients5 = new HashMap<>();
        ingredients5.put(CropsData.getCropByName("Grape"), 2);
        Recipes.add(new Recipe("recipe_5", FoodData.getFoodByName("Wine"), ingredients5, true, "Default"));

        // Recipe 6: Pumpkin Pie
        Map<Items, Integer> ingredients6 = new HashMap<>();
        ingredients6.put(MiscData.getMiscByName("Egg"), 1);
        ingredients6.put(CropsData.getCropByName("Wheat"), 1);
        ingredients6.put(CropsData.getCropByName("Pumpkin"), 1);
        Recipes.add(new Recipe("recipe_6", FoodData.getFoodByName("Pumpkin Pie"), ingredients6, true, "Default"));

        // Recipe 7: Veggie Soup
        Map<Items, Integer> ingredients7 = new HashMap<>();
        ingredients7.put(CropsData.getCropByName("Cauliflower"), 1);
        ingredients7.put(CropsData.getCropByName("Parsnip"), 1);
        ingredients7.put(CropsData.getCropByName("Potato"), 1);
        ingredients7.put(CropsData.getCropByName("Tomato"), 1);
        Recipes.add(new Recipe("recipe_7", FoodData.getFoodByName("Veggie Soup"), ingredients7, false, "Harvest for the first time"));

        // Recipe 8: Fish Stew
        Map<Items, Integer> ingredients8 = new HashMap<>();
        ingredients8.put(new ConcreteItem("Any Fish", "Fish"), 2);
        ingredients8.put(CropsData.getCropByName("Hot Pepper"), 1);
        ingredients8.put(CropsData.getCropByName("Cauliflower"), 2);
        Recipes.add(new Recipe("recipe_8", FoodData.getFoodByName("Fish Stew"), ingredients8, false, "Harvest 'Hot Pepper'"));

        // Recipe 9: Spakbor Salad
        Map<Items, Integer> ingredients9 = new HashMap<>();
        ingredients9.put(CropsData.getCropByName("Melon"), 1);
        ingredients9.put(CropsData.getCropByName("Cranberry"), 1);
        ingredients9.put(CropsData.getCropByName("Blueberry"), 1);
        ingredients9.put(CropsData.getCropByName("Tomato"), 1);
        Recipes.add(new Recipe("recipe_9", FoodData.getFoodByName("Spakbor Salad"), ingredients9, true, "Default"));

        // Recipe 10: Fish Sandwich
        Map<Items, Integer> ingredients10 = new HashMap<>();
        ingredients10.put(new ConcreteItem("Any Fish", "Fish"), 1);
        ingredients10.put(CropsData.getCropByName("Wheat"), 2);
        ingredients10.put(CropsData.getCropByName("Tomato"), 1);
        ingredients10.put(CropsData.getCropByName("Hot Pepper"), 1);
        Recipes.add(new Recipe("recipe_10", FoodData.getFoodByName("Fish Sandwich"), ingredients10, false, "Buy in store"));

        // Recipe 11: The Legends of Spakbor
        Map<Items, Integer> ingredients11 = new HashMap<>();
        ingredients11.put(new ConcreteItem("Legend Fish", "Fish"), 1);
        ingredients11.put(CropsData.getCropByName("Potato"), 2);
        ingredients11.put(CropsData.getCropByName("Parsnip"), 1);
        ingredients11.put(CropsData.getCropByName("Tomato"), 1);
        ingredients11.put(MiscData.getMiscByName("Eggplant"), 1);
        Recipes.add(new Recipe("recipe_11", FoodData.getFoodByName("The Legends of Spakbor"), ingredients11, false, "Fishing 'Legend Fish'"));
    }

    public static List<Recipe> getAllRecipes() {
        return Collections.unmodifiableList(Recipes);
    }

    public static Recipe getRecipeById(String id) {
        for (Recipe recipe : Recipes) {
            if (recipe.getItemID().equals(id)) {
                return recipe;
            }
        }
        return null;
    }
}