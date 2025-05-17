package Items;
public class Food extends Items implements Sellable {
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
    
    public int getSellPrice() {
        return sellPrice;
    }
    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
