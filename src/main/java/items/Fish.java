package items;

import java.util.Set;
import data.FishRarity;
import entity.player.Player;
public class Fish extends Items implements Sellable, Edible {
    private FishRarity rarity;
    private Set<String> seasons;
    private int startHour;
    private int endHour;
    private int hourRange;
    private Set<String> locations;
    private Set<String> weathers;
    private final int energy = 1;

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
        this.rarity = rarity;
        loadImage("/items/fish/" + name + ".png");
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
    @Override
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

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public void eat(Player player) {
        System.out.println(player.getName() + " memakan " + getName() + " dan mendapat " + getEnergy() + " energi!");
    }
}
