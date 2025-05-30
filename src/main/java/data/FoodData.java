package data; 

import java.util.List;
import main.SimpleDataManager;
import items.Food;

public class FoodData {

    private static final SimpleDataManager<Food> manager = new SimpleDataManager<>();

    static {
        addFood(new Food("Fish n' Chips", 50, 150, 135));
        addFood(new Food("Baguette", 25, 100, 80));
        addFood(new Food("Sashimi", 70, 300, 275));
        addFood(new Food("Fugu", 50, 0, 135));                 
        addFood(new Food("Wine", 20, 100, 90));
        addFood(new Food("Pumpkin Pie", 35, 120, 100));
        addFood(new Food("Veggie Soup", 40, 140, 120));
        addFood(new Food("Fish Stew", 70, 280, 260));
        addFood(new Food("Spakbor Salad", 70, 0, 250));        
        addFood(new Food("Fish Sandwich", 50, 200, 180));
        addFood(new Food("The Legends of Spakbor", 100, 0, 2000));
        addFood(new Food("Cooked Pig's Head", 100, 1000, 0));  
    }

    private static void addFood(Food foodItem) {
        manager.addItem(foodItem);
    }

    public static List<Food> getAllFoodItems() {
        return manager.getAllItems();
    }

    public static Food getFoodByName(String name) {
        return manager.getItemByName(name);
    }
}