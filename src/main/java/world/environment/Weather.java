package world.environment;

import java.util.Random;

public class Weather {
    private static volatile Weather instance;

    private String currentWeather;
    private int rainyDaysThisSeason;
    private Random random = new Random();

    public Weather() {
        this.currentWeather = generateWeather();
        this.rainyDaysThisSeason = 0;
    }

    public static Weather getInstance() {
        if (instance == null) {
            synchronized (Weather.class) {
                if (instance == null) {
                    instance = new Weather();
                }
            }
        }
        return instance;
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public synchronized void nextWeather(Season season) {
        this.currentWeather = generateWeather();
        if (isRainy()) {
            rainyDaysThisSeason++;
        }

        // Reset rain counter if new season
        if (season.getDayInSeason() == 1) {
            rainyDaysThisSeason = isRainy() ? 1 : 0;
        }
    }

    private String generateWeather() {
        // Minimal 2 Rainy Days per season: force Rainy if belum cukup di akhir season
        if (rainyDaysThisSeason < 2 && (10 - rainyDaysThisSeason <= 3)) {
            return "Rainy";
        }

        return random.nextDouble() < 0.3 ? "Rainy" : "Sunny"; // ~30% chance
    }

    public boolean isRainy() {
        return "Rainy".equalsIgnoreCase(currentWeather);
    }

    // Cheat tool
    public synchronized void setWeather(String weather) {
        if (weather.equalsIgnoreCase("Rainy") || weather.equalsIgnoreCase("Sunny")) {
            this.currentWeather = weather;
        }
    }

    public int getRainyDaysThisSeason() {
        return rainyDaysThisSeason;
    }

    public void setRainyDaysThisSeason(int rainyDaysThisSeason) {
        this.rainyDaysThisSeason = rainyDaysThisSeason;
    }
}