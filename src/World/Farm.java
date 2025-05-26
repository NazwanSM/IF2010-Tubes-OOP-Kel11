package World;

import entity.player.Player;
import main.GamePanel;
import World.Environment.GameClock;
import World.Environment.Season;
import World.Environment.Weather;

public class Farm {
    private String farmName;
    private Player player;
    private Season season;
    private Weather weather;
    private GameClock gameClock;
    private GamePanel gp;
    private int day; 

    public Farm(String farmName, Player player, GamePanel gp) {
        this.farmName = farmName;
        this.player = player;
        this.season = Season.getInstance();
        this.gameClock = GameClock.getInstance();
        this.weather = Weather.getInstance();
        this.day = 1;
        this.gp = gp; 
    }

    // --- Getter Methods ---
    public String getFarmName() { 
        return farmName; 
    }
    public Player getPlayer() { 
        return player; 
    }
    public Season getSeason() { 
        return season; 
    }
    public GameClock getGameClock() { 
        return gameClock; 
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
        season.nextSeason();
        gp.manager.trackDayPlayed();

        weather.nextWeather(season);
        gameClock.skipToMorning();

        gp.ui.addMessage("Day " + day + " starts!");
        gp.ui.addMessage("Season: " + season.getCurrentSeason() + ", Weather: " + weather.getCurrentWeather());
    }

    // --- Cheat Tools (manual override) ---
    public void cheatSetSeason(String seasonName) {
        season.setSeason(seasonName);
    }

    public void cheatSetWeather(String weatherName) {
        weather.setWeather(weatherName);
    }

    public void displayTimeInfo() {
        System.out.println("Waktu: " + gameClock.getTime());
        System.out.println("Musim: " + season.getCurrentSeason());
        System.out.println("Hari ke-" + day);
        System.out.println("Cuaca: " + weather.getCurrentWeather());
    }

    public void advanceTime(int inGameMinutes) {
        gameClock.advance(inGameMinutes);
    }

    public boolean isRainy() {
        return weather.isRainy();
    }

    // public void endDayAndSell() {
    //     System.out.println("Menjual barang dari shipping bin...");
    //     player.getInventory().addGold(ShippingLogic.sell(farmMap)); // logika harga bisa dari file eksternal
    //     nextDay();
    // }
}
