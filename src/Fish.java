public class Fish extends Items implements Sellable {
    private String season;
    private String time;
    private String weather;
    private String location;
    private int sellPrice;
    private String rarity;

    public Fish(String name, String season, String time, String weather, String location, String rarity, int sellPrice, int quantity) {
        super(name, "Fish", quantity);
        this.season = season;
        this.time = time;
        this.weather = weather;
        this.location = location;
        this.sellPrice = sellPrice;
        this.rarity = rarity;}

    // Getter and Setter
    public String getSeason() {
        return season;
    }

    public String getTime() {
        return time;
    }

    public String getWeather() {
        return weather;
    }

    public String getLocation() {
        return location;
    }

    public int getSellPrice() {
        return sellPrice;
    }
    public String getRarity() {
        return rarity;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
