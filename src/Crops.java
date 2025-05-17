public class Crops extends Items implements Sellable {
    private int buyPrice;
    private int sellPrice;
    private int energy;
    private String rarity;

    public Crops(String name, int buyPrice, int sellPrice, int energy, String rarity, int quantity) {
        super(name, "Crop", quantity);
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.energy = energy;
        this.rarity = rarity;
    }

    // Getter and Setter
    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public String getRarity() {
        return rarity;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
