package data;

import java.util.List;
import java.util.Set;

import main.SimpleDataManager;
import items.Fish;

public class FishData {
    private static final SimpleDataManager<Fish> manager = new SimpleDataManager<>();

    static {
        // === Common Fish ===
        addFish(new Fish("Bullhead", FishRarity.COMMON,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            0, 24,
            Set.of("Sunny", "Rainy"),
            Set.of("Mountain Lake")));

        addFish(new Fish("Carp", FishRarity.COMMON,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            0, 24,
            Set.of("Sunny", "Rainy"),
            Set.of("Mountain Lake", "Pond")));

        addFish(new Fish("Chub", FishRarity.COMMON,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            0, 24,
            Set.of("Sunny", "Rainy"),
            Set.of("Forest River", "Mountain Lake")));

        // === Regular Fish ===
        addFish(new Fish("Largemouth Bass", FishRarity.REGULAR,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            6, 18,
            Set.of("Sunny", "Rainy"),
            Set.of("Mountain Lake")));

        addFish(new Fish("Rainbow Trout", FishRarity.REGULAR,
            Set.of("Summer"),
            6, 18,
            Set.of("Sunny"),
            Set.of("Forest River", "Mountain Lake")));

        addFish(new Fish("Sturgeon", FishRarity.REGULAR,
            Set.of("Summer", "Winter"),
            6, 18,
            Set.of("Sunny", "Rainy"),
            Set.of("Mountain Lake")));

        addFish(new Fish("Midnight Carp", FishRarity.REGULAR,
            Set.of("Fall", "Winter"),
            20, 2,
            Set.of("Sunny", "Rainy"),
            Set.of("Mountain Lake", "Pond")));

        addFish(new Fish("Flounder", FishRarity.REGULAR,
            Set.of("Spring", "Summer"),
            6, 22,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Halibut", FishRarity.REGULAR,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            6, 11,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));
        addFish(new Fish("Halibut", FishRarity.REGULAR, 
            Set.of("Spring", "Summer", "Fall", "Winter"),
            19, 2, 
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Octopus", FishRarity.REGULAR,
            Set.of("Summer"),
            6, 22,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Pufferfish", FishRarity.REGULAR,
            Set.of("Summer"),
            0, 16,
            Set.of("Sunny"),
            Set.of("Ocean")));

        addFish(new Fish("Sardine", FishRarity.REGULAR,
            Set.of("Spring", "Summer", "Fall", "Winter"),
            6, 18,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Super Cucumber", FishRarity.REGULAR,
            Set.of("Summer", "Fall", "Winter"),
            18, 2,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Catfish", FishRarity.REGULAR,
            Set.of("Spring", "Summer", "Fall"),
            6, 22,
            Set.of("Rainy"),
            Set.of("Forest River", "Pond")));

        addFish(new Fish("Salmon", FishRarity.REGULAR,
            Set.of("Fall"),
            6, 18,
            Set.of("Sunny", "Rainy"),
            Set.of("Forest River")));

        // === Legendary Fish ===
        addFish(new Fish("Angler", FishRarity.LEGENDARY,
            Set.of("Fall"),
            8, 20,
            Set.of("Sunny", "Rainy"),
            Set.of("Pond")));

        addFish(new Fish("Crimsonfish", FishRarity.LEGENDARY,
            Set.of("Summer"),
            8, 20,
            Set.of("Sunny", "Rainy"),
            Set.of("Ocean")));

        addFish(new Fish("Glacierfish", FishRarity.LEGENDARY,
            Set.of("Winter"),
            8, 20,
            Set.of("Sunny", "Rainy"),
            Set.of("Forest River")));

        addFish(new Fish("Legend", FishRarity.LEGENDARY,
            Set.of("Spring"),
            8, 20,
            Set.of("Rainy"),
            Set.of("Mountain Lake")));
    }

    private static void addFish(Fish fishItem) {
        manager.addItem(fishItem);
    }

    public static List<Fish> getAllFish() {
        return manager.getAllItems();
    }

    public static Fish getFishByName(String name) {
        return manager.getItemByName(name);
    }
}