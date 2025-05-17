package Items;

import Player.Player;

public class Food extends Items implements Sellable, Edible {
    private int buyPrice;
    private int sellPrice;
    private int energy;  



    public Food(String name, int buyPrice, int sellPrice, int energy) {
        super(name, "Food");
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.energy = energy;
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
