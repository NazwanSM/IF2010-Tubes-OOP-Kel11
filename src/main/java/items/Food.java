package items;

import entity.player.Player;

public class Food extends Items implements Sellable, Edible {
    private int energy;  
    private int buyPrice;
    private int sellPrice;

    public Food(String name, int energy, int buyPrice, int sellPrice) {
        super(name, "Food");
        this.energy = energy;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        loadImage("/items/food/" + name + ".png");
        loadBnwImage("/items/food/bnw/" + name + ".png");
    }

    public int getBuyPrice() {
        return buyPrice;
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
