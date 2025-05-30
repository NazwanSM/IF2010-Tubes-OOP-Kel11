package data;

public enum FishRarity {
    COMMON("Common"),
    REGULAR("Regular"),
    LEGENDARY("Legendary");

    private String rarity;

    FishRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }
    
}
