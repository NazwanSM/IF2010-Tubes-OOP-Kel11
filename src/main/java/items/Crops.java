package items;

import entity.player.Player;

public class Crops extends Items implements Sellable, Edible {
    private int buyPrice;
    private int sellPrice;
    private static final int ENERGY = 3;
    private int energy;
    private int quantityPerHarvest;

    public Crops(String name, int buyPrice, int sellPrice, int quantityPerHarvest) {
        super(name, "Crop");
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.energy = ENERGY;
        this.quantityPerHarvest = quantityPerHarvest;
        loadImage("/items/crops/" + name.toLowerCase() + ".png");
    }

    // Getter and Setter
    public int getBuyPrice() {
        return buyPrice;
    }

    public int getQuantityPerHarvest() {
        return quantityPerHarvest;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setQuantityPerHarvest(int quantityPerHarvest) {
        this.quantityPerHarvest = quantityPerHarvest;
    }
    
    
    @Override
    public int getSellPrice() {
        return sellPrice;
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
