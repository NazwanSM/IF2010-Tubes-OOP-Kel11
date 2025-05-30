package data; 

import main.SimpleDataManager;
import items.Seed;

import java.util.List;
import java.util.Set;

public class SeedData {

    private static final SimpleDataManager<Seed> manager = new SimpleDataManager<>();

    static {

        addSeed(new Seed("Parsnip Seeds", Set.of("Spring"), 1, 20, 10)); 
        addSeed(new Seed("Cauliflower Seeds", Set.of("Spring"), 5, 80, 40)); 
        addSeed(new Seed("Potato Seeds", Set.of("Spring"), 3, 50, 25));    
        addSeed(new Seed("Blueberry Seeds", Set.of("Summer"), 7, 80, 40)); 
        addSeed(new Seed("Tomato Seeds", Set.of("Summer"), 3, 50, 25));    
        addSeed(new Seed("Hot Pepper Seeds", Set.of("Summer"), 1, 40, 20)); 
        addSeed(new Seed("Melon Seeds", Set.of("Summer"), 4, 80, 40));      
        addSeed(new Seed("Cranberry Seeds", Set.of("Fall"), 2, 100, 50));  
        addSeed(new Seed("Pumpkin Seeds", Set.of("Fall"), 7, 150, 75));    
        addSeed(new Seed("Grape Seeds", Set.of("Fall"), 3, 60, 30));       
        addSeed(new Seed("Wheat Seeds", Set.of("Spring", "Fall"), 1, 60, 30)); 

    }

    private static void addSeed(Seed seed) {
        manager.addItem(seed);
    }

    public static List<Seed> getAllSeeds() {
        return manager.getAllItems();
    }

    public static Seed getSeedByName(String name) {
        return manager.getItemByName(name);
    }
}