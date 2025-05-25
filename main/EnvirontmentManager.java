package main;

import World.Environment.GameClock;
import World.Environment.Season;
import World.Environment.Weather;

public class EnvirontmentManager {

    private GameClock gameClock;
    private Season season;
    private Weather weather;



    public EnvirontmentManager() {
        this.gameClock = GameClock.getInstance(); 
        this.season = Season.getInstance();     
        this.weather = Weather.getInstance();   
    }

    public GameClock getGameClock() {
        return gameClock;
    }

    public Season getSeason() {
        return season;
    }

    public Weather getWeather() {
        return weather;
    }
}