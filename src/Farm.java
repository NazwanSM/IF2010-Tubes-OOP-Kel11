public class Farm {
    private String farmName;
    private Player player;
    private FarmMap farmMap;
    private GameTime gameTime;
    private Season season;
    private Weather weather;
    private int day; // hari ke-n dalam satu musim

    public Farm(String farmName, Player player) {
        this.farmName = farmName;
        this.player = player;
        this.farmMap = new FarmMap("Spakbor Hills");
        this.gameTime = new GameTime();
        this.season = new Season();
        this.weather = new Weather();
        this.day = 1;
    }

    // --- Getter Methods ---
    public String getFarmName() { 
        return farmName; 
    }
    public Player getPlayer() { 
        return player; 
    }
    public FarmMap getFarmMap() { 
        return farmMap; 
    }
    public GameTime getGameTime() { 
        return gameTime; 
    }
    public Season getSeason() { 
        return season; 
    }
    public Weather getWeather() { 
        return weather; 
    }
    public int getDay() { 
        return day; 
    }

    // --- Day Progression ---
    public void nextDay() {
        day++;
        if (day > 10) {
            day = 1;
            season.nextDay(); // ganti season setelah 10 hari
        }

        weather.nextDay(season);
        gameTime.skipToMorning();
        farmMap.getTilesManagement().growCropsOneDay();

        System.out.println("Day " + day + " starts!");
        System.out.println("Season: " + season.getCurrentSeason() + ", Weather: " + weather.getCurrentWeather());
    }

    // --- Cheat Tools (manual override) ---
    public void cheatSetSeason(String seasonName) {
        season.setSeason(seasonName);
    }

    public void cheatSetWeather(String weatherName) {
        weather.setWeather(weatherName);
    }

    // --- Utility ---
    public void displayFarmMap() {
        farmMap.display();
    }

    public void displayTimeInfo() {
        System.out.println("Waktu: " + gameTime.getTime());
        System.out.println("Musim: " + season.getCurrentSeason());
        System.out.println("Hari ke-" + day);
        System.out.println("Cuaca: " + weather.getCurrentWeather());
    }

    public void advanceTime(int inGameMinutes) {
        gameTime.advance(inGameMinutes);
    }

    public boolean isRainy() {
        return weather.isRainy();
    }

    public void endDayAndSell() {
        System.out.println("Menjual barang dari shipping bin...");
        player.getInventory().addGold(ShippingLogic.sell(farmMap)); // logika harga bisa dari file eksternal
        nextDay();
    }
}
