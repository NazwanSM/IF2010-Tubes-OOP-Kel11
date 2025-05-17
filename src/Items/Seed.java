package Items;

public class Seed extends Items implements Sellable {
    private String season;
    private int daysToHarvest;
    private int buyPrice;
    private int sellPrice;
    private int daysPlanted;

    public Seed(String name, String season, int daysToHarvest, int buyPrice, int sellPrice) {
        super(name, "Seed");
        this.season = season;
        this.daysToHarvest = daysToHarvest;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.daysPlanted = 0;
    }

    public String getSeason() {
        return season;
    }

    public int getDaysToHarvest() {
        return daysToHarvest;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSeason(String season) {
        this.season = season;
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



}
