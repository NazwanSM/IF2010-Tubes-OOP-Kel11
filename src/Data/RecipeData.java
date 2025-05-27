package data;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import items.*;

public class RecipeData {
    public static final List<Recipe> Recipes = new ArrayList<>();

    static {
        // Recipe 1: Fish n’ Chips
        Map<Items, Integer> ingredients1 = new HashMap<>();
        ingredients1.put(new Items("Any Fish"), 2);
        ingredients1.put(new Items("Wheat"), 1);
        ingredients1.put(new Items("Potato"), 1);
        RECIPES.add(new Recipe("recipe_1", new Food("Fish n' Chips", 50, 150, 135), ingredients1, false, "Beli di store"));

        // Recipe 2: Baguette
        Map<Items, Integer> ingredients2 = new HashMap<>();
        ingredients2.put(new Items("Wheat"), 3);
        RECIPES.add(new Recipe("recipe_2", new Food("Baguette", 25, 100, 80), ingredients2, true, "Default/Bawaan"));

        // Recipe 3: Sashimi
        Map<Items, Integer> ingredients3 = new HashMap<>();
        ingredients3.put(new Items("Salmon"), 3);
        RECIPES.add(new Recipe("recipe_3", new Food("Sashimi", 70, 300, 275), ingredients3, false, "Setelah memancing 10 ikan"));

        // Recipe 4: Fugu
        Map<Items, Integer> ingredients4 = new HashMap<>();
        ingredients4.put(new Items("Pufferfish"), 1);
        RECIPES.add(new Recipe("recipe_4", new Food("Fugu", 50, 0, 135), ingredients4, false, "Memancing pufferfish"));

        // Recipe 5: Wine
        Map<Items, Integer> ingredients5 = new HashMap<>();
        ingredients5.put(new Items("Grape"), 2);
        RECIPES.add(new Recipe("recipe_5", new Food("Wine", 20, 100, 90), ingredients5, true, "Default/Bawaan"));

        // Recipe 6: Pumpkin Pie
        Map<Items, Integer> ingredients6 = new HashMap<>();
        ingredients6.put(new Items("Egg"), 1);
        ingredients6.put(new Items("Wheat"), 1);
        ingredients6.put(new Items("Pumpkin"), 1);
        RECIPES.add(new Recipe("recipe_6", new Food("Pumpkin Pie", 35, 120, 100), ingredients6, true, "Default/Bawaan"));

        // Recipe 7: Veggie Soup
        Map<Items, Integer> ingredients7 = new HashMap<>();
        ingredients7.put(new Items("Cauliflower"), 1);
        ingredients7.put(new Items("Parsnip"), 1);
        ingredients7.put(new Items("Potato"), 1);
        ingredients7.put(new Items("Tomato"), 1);
        RECIPES.add(new Recipe("recipe_7", new Food("Veggie Soup", 40, 140, 120), ingredients7, false, "Memanen untuk pertama kalinya"));

        // Recipe 8: Fish Stew
        Map<Items, Integer> ingredients8 = new HashMap<>();
        ingredients8.put(new Items("Any Fish"), 2);
        ingredients8.put(new Items("Hot Pepper"), 1);
        ingredients8.put(new Items("Cauliflower"), 2);
        RECIPES.add(new Recipe("recipe_8", new Food("Fish Stew", 70, 280, 260), ingredients8, false, "Dapatkan 'Hot Pepper' terlebih dahulu"));

        // Recipe 9: Spakbor Salad
        Map<Items, Integer> ingredients9 = new HashMap<>();
        ingredients9.put(new Items("Melon"), 1);
        ingredients9.put(new Items("Cranberry"), 1);
        ingredients9.put(new Items("Blueberry"), 1);
        ingredients9.put(new Items("Tomato"), 1);
        RECIPES.add(new Recipe("recipe_9", new Food("Spakbor Salad", 70, 0, 250), ingredients9, true, "Default/Bawaan"));

        // Recipe 10: Fish Sandwich
        Map<Items, Integer> ingredients10 = new HashMap<>();
        ingredients10.put(new Items("Any Fish"), 1);
        ingredients10.put(new Items("Wheat"), 2);
        ingredients10.put(new Items("Tomato"), 1);
        ingredients10.put(new Items("Hot Pepper"), 1);
        RECIPES.add(new Recipe("recipe_10", new Food("Fish Sandwich", 50, 200, 180), ingredients10, false, "Beli di store"));

        // Recipe 11: The Legends of Spakbor
        Map<Items, Integer> ingredients11 = new HashMap<>();
        ingredients11.put(new Items("Legend Fish"), 1);
        ingredients11.put(new Items("Potato"), 2);
        ingredients11.put(new Items("Parsnip"), 1);
        ingredients11.put(new Items("Tomato"), 1);
        ingredients11.put(new Items("Eggplant"), 1);
        RECIPES.add(new Recipe("recipe_11", new Food("The Legends of Spakbor", 100, 0, 2000), ingredients11, false, "Memancing 'Legend'"));
    }

    public static List<Recipe> getAllRecipes() {
        return Collections.unmodifiableList(Recipes);
    }
}
