package world;

import main.GamePanel;
import entity.player.Player;
import world.environment.*;

public class Farm {
    private String farmName;
    private Player player;
    private Season season;
    private Weather weather;
    private GameClock gameClock;
    private GamePanel gp;
    private FieldManager fieldManager; // Assuming you have a FieldManager class to manage crops
    private int day; 

    public Farm(String farmName, Player player, GamePanel gp) {
        this.farmName = farmName;
        this.player = player;
        this.season = Season.getInstance();
        this.gameClock = GameClock.getInstance();
        this.weather = Weather.getInstance();
        this.day = 1;
        this.gp = gp; 
        this.fieldManager = new FieldManager(gp);
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

    public FieldManager getFieldManager() { 
        return fieldManager; 
    }

    // --- Day Progression ---
    public void nextDay() {
        day++;
        gp.manager.trackDayPlayed();
        season.nextSeason();
        weather.nextWeather(season);
        fieldManager.processNewDay();
        gameClock.skipToMorning();

        gp.ui.addMessage("Day " + day + " starts!");
        gp.ui.addMessage("Season: " + season.getCurrentSeason() + ", Weather: " + weather.getCurrentWeather());

        if (season.getCurrentSeason().equals("Winter")) {
            gp.stopMusic();
            gp.playMusic(10); 
        } else if (season.getCurrentSeason().equals("Spring")) {
            gp.stopMusic();
            gp.playMusic(8); 
        } else if (season.getCurrentSeason().equals("Summer")) {
            gp.stopMusic();
            gp.playMusic(0); 
        } else if (season.getCurrentSeason().equals("Fall")) {
            gp.stopMusic();
            gp.playMusic(9);
        }
    }

    // --- Cheat Tools (manual override) ---
    public void cheatSetSeason(String seasonName) {
        season.setSeason(seasonName);
    }

    public void cheatSetWeather(String weatherName) {
        weather.setWeather(weatherName);
    }

    public boolean isRainy() {
        return weather.isRainy();
    }
}
