package Items;

import java.util.List;
import java.util.Set;
import Enum.FishRarity;

public class Fish extends Items implements Sellable {
    private FishRarity rarity;
    private Set<String> seasons;
    private int startHour;
    private int endHour;
    private int hourRange;
    private Set<String> locations;
    private Set<String> weathers;

    public Fish(String name, FishRarity rarity,  Set<String> seasons, int startHour, int endHour, Set<String> weathers, Set<String> locations) {
        super(name, "Fish");
        this.seasons = seasons;
        this.startHour = startHour;
        this.endHour = endHour;
        if (startHour < endHour){
            this.hourRange = endHour - startHour;
        } else {
            this.hourRange = (24 - startHour) + endHour; 
        } 
        this.weathers = weathers;
        this.locations = locations;
        this.rarity = rarity;}

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


    // Getter and Setter
    public Set<String> getSeason() {
        return seasons;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public Set<String> getWeather() {
        return weathers;
    }

    public Set<String> getLocation() {
        return locations;
    }

    public FishRarity getRarity() {
        return rarity;
    }

    public void setSeason(Set<String> seasons) {
        this.seasons = seasons;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setWeather(Set<String> weathers) {
        this.weathers = weathers;
    }

    public void setLocation(Set<String> locations) {
        this.locations = locations;
    }

    public void setRarity(FishRarity rarity) {
        this.rarity = rarity;
    }

    public boolean canBeCaught(String currentSeason, int currentHour, String currentWeather, String currentLocation) {
        boolean timeCheck;
    
        if (startHour < endHour) {
            timeCheck = currentHour >= startHour && currentHour < endHour;
        } else {
            timeCheck = currentHour >= startHour || currentHour < endHour;
        }
        
        return seasons.contains(currentSeason)
            && timeCheck
            && weathers.contains(currentWeather)
            && locations.contains(currentLocation);
    }

    public int getSellPrice() {
        int C = switch (rarity) {
            case REGULAR -> 5;
            case COMMON -> 10;
            case LEGENDARY -> 25;
        };

        int seasonCount = seasons.size();            // banyak season
        int hourRange = this.hourRange;             // range jam
        int weatherCount = weathers.size();          // variasi weather
        int locationCount = locations.size();        // banyak lokasi

        if (seasonCount == 0 || hourRange == 0 || weatherCount == 0 || locationCount == 0) return 0;

        double multiplier = (4.0 / seasonCount)
                          * (24.0 / hourRange)
                          * (2.0 / weatherCount)
                          * (4.0 / locationCount);

        return (int) Math.round(multiplier * C);
    }
}
