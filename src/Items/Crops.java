package Items;

import entity.player.Player;

public class Crops extends Items implements Sellable, Edible {
    private int buyPrice;
    private int sellPrice;
    private String rarity;
    private final int energy = 3;

    public Crops(String name, int buyPrice, int sellPrice, int energy, String rarity) {
        super(name, "Crop");
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.rarity = rarity;
    }

    // Getter and Setter
    public int getBuyPrice() {
        return buyPrice;
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
    
    public void setRarity(String rarity) {
        this.rarity = rarity;
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
