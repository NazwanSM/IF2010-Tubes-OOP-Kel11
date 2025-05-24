package data;

import items.Crops;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class CropsData {

    private static final List<Crops> allCrops = new ArrayList<>();
    private static final Map<String, Crops> cropsByName = new HashMap<>();

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
        allCrops.add(crop);
        cropsByName.put(crop.getName().toLowerCase(), crop);
    }

    public static List<Crops> getAllCrops() {
        return Collections.unmodifiableList(allCrops);
    }

    public static Crops getCropByName(String name) {
        if (name == null) {
            return null;
        }
        return cropsByName.get(name.toLowerCase());
    }
}