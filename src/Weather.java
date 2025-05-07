import java.util.*;

public class Weather {
    private String currentWeather;
    private int rainyDaysThisSeason = 0;
    private Random random = new Random();

    public Weather() {
        this.currentWeather = generateWeather();
    }

    public String getCurrentWeather() {
        return currentWeather;
    }

    public void nextDay(Season season) {
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
        return "Rainy".equals(currentWeather);
    }

    // Cheat tool
    public void setWeather(String weather) {
        if (weather.equalsIgnoreCase("Rainy") || weather.equalsIgnoreCase("Sunny")) {
            this.currentWeather = weather;
        }
    }

    public int getRainyDaysThisSeason() {
        return rainyDaysThisSeason;
    }
}
