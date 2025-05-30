package items;

import java.util.Set;

public class Seed extends Items implements Sellable {
    private Set<String> seasons;
    private int daysToHarvest;
    private int buyPrice;
    private int sellPrice;
    private int daysPlanted;

    public Seed(String name, Set<String> seasons, int daysToHarvest, int buyPrice, int sellPrice) {
        super(name, "Seed");
        this.seasons = seasons;
        this.daysToHarvest = daysToHarvest;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.daysPlanted = 0;
        loadImage("/items/seed/" + name.toLowerCase() + ".png");
    }

    public Set<String> getSeason() {
        return seasons;
    }

    public int getDaysToHarvest() {
        return daysToHarvest;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setSeason(Set<String> seasons) {
        this.seasons = seasons;
    }

    public void setDaysToHarvest(int daysToHarvest) {
        this.daysToHarvest = daysToHarvest;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void growOneDay() {
        if (daysPlanted < daysToHarvest) {
            daysPlanted++;
        }
    }

    public boolean isReadyToHarvest() {
        return daysPlanted >= daysToHarvest;
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }
}
