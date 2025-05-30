package data;

import main.SimpleDataManager;
import items.Crops;

import java.util.List;

public class CropsData {

    private static final SimpleDataManager<Crops> manager = new SimpleDataManager<>();

    static {
        addCrop(new Crops("Parsnip", 50, 35, 1));      
        addCrop(new Crops("Cauliflower", 200, 150, 1)); 
        addCrop(new Crops("Potato", 0, 80, 1));
        addCrop(new Crops("Wheat", 50, 30, 3));        
        addCrop(new Crops("Blueberry", 150, 40, 3));   
        addCrop(new Crops("Tomato", 90, 60, 1));       
        addCrop(new Crops("Hot Pepper", 0, 40, 1));
        addCrop(new Crops("Melon", 0, 250, 1));
        addCrop(new Crops("Cranberry", 0, 25, 10)); 
        addCrop(new Crops("Pumpkin", 300, 250, 1));    
        addCrop(new Crops("Grape", 100, 10, 20));       
    }

    private static void addCrop(Crops crop) {
        manager.addItem(crop);
    }

    public static List<Crops> getAllCrops() {
        return manager.getAllItems();
    }

    public static Crops getCropByName(String name) {
        return manager.getItemByName(name);
    }

    public static Crops getCropBySeed(String seedName) {
        if (seedName == null || seedName.isEmpty()) {
            return null;
        }

        String cropNameFromSeed = "";
        String suffixToRemove = " Seeds";

        if (seedName.endsWith(suffixToRemove)) {
            cropNameFromSeed = seedName.substring(0, seedName.length() - suffixToRemove.length());
        } else {
            cropNameFromSeed = seedName;
        }

        cropNameFromSeed = cropNameFromSeed.trim();

        if (cropNameFromSeed.isEmpty()) {
            return null; 
        }

        return getCropByName(cropNameFromSeed);
    }
}