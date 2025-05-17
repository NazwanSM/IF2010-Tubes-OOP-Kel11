package Data;

import java.util.List;
import java.util.Set;

import Enum.FishRarity;
import Items.Fish;

public class FishData {
    private static List<Fish> allFish = List.of(
    // === Common Fish ===
    new Fish("Bullhead", FishRarity.COMMON,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        0, 24,
        Set.of("Sunny", "Rainy"),
        Set.of("Mountain Lake")),

    new Fish("Carp", FishRarity.COMMON,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        0, 24,
        Set.of("Sunny", "Rainy"),
        Set.of("Mountain Lake", "Pond")),

    new Fish("Chub", FishRarity.COMMON,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        0, 24,
        Set.of("Sunny", "Rainy"),
        Set.of("Forest River", "Mountain Lake")),

    // === Regular Fish ===
    new Fish("Largemouth Bass", FishRarity.REGULAR,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        6, 18,
        Set.of("Sunny", "Rainy"),
        Set.of("Mountain Lake")),

    new Fish("Rainbow Trout", FishRarity.REGULAR,
        Set.of("Summer"),
        6, 18,
        Set.of("Sunny"),
        Set.of("Forest River", "Mountain Lake")),

    new Fish("Sturgeon", FishRarity.REGULAR,
        Set.of("Summer", "Winter"),
        6, 18,
        Set.of("Sunny", "Rainy"),
        Set.of("Mountain Lake")),

    new Fish("Midnight Carp", FishRarity.REGULAR,
        Set.of("Fall", "Winter"),
        20, 2,
        Set.of("Sunny", "Rainy"),
        Set.of("Mountain Lake", "Pond")),

    new Fish("Flounder", FishRarity.REGULAR,
        Set.of("Spring", "Summer"),
        6, 22,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Halibut", FishRarity.REGULAR,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        6, 11,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),
    new Fish("Halibut", FishRarity.REGULAR,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        19, 2,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Octopus", FishRarity.REGULAR,
        Set.of("Summer"),
        6, 22,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Pufferfish", FishRarity.REGULAR,
        Set.of("Summer"),
        0, 16,
        Set.of("Sunny"),
        Set.of("Ocean")),

    new Fish("Sardine", FishRarity.REGULAR,
        Set.of("Spring", "Summer", "Fall", "Winter"),
        6, 18,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Super Cucumber", FishRarity.REGULAR,
        Set.of("Summer", "Fall", "Winter"),
        18, 2,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Catfish", FishRarity.REGULAR,
        Set.of("Spring", "Summer", "Fall"),
        6, 22,
        Set.of("Rainy"),
        Set.of("Forest River", "Pond")),

    new Fish("Salmon", FishRarity.REGULAR,
        Set.of("Fall"),
        6, 18,
        Set.of("Sunny", "Rainy"),
        Set.of("Forest River")),

    // === Legendary Fish ===
    new Fish("Angler", FishRarity.LEGENDARY,
        Set.of("Fall"),
        8, 20,
        Set.of("Sunny", "Rainy"),
        Set.of("Pond")),

    new Fish("Crimsonfish", FishRarity.LEGENDARY,
        Set.of("Summer"),
        8, 20,
        Set.of("Sunny", "Rainy"),
        Set.of("Ocean")),

    new Fish("Glacierfish", FishRarity.LEGENDARY,
        Set.of("Winter"),
        8, 20,
        Set.of("Sunny", "Rainy"),
        Set.of("Forest River")),

    new Fish("Legend", FishRarity.LEGENDARY,
        Set.of("Spring"),
        8, 20,
        Set.of("Rainy"),
        Set.of("Mountain Lake"))
    );

    public static List<Fish> getAllFish() {
        return allFish;
    }
}