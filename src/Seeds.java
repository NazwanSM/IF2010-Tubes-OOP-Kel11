public class Seeds extends Items implements Sellable {
    private String season;
    private int daysToHarvest;
    private int buyPrice;
    private int sellPrice;

    public Seeds(String name, String season, int daysToHarvest, int buyPrice, int sellPrice, int quantity) {
        super(name, "Seed", quantity);
        this.season = season;
        this.daysToHarvest = daysToHarvest;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
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

}
