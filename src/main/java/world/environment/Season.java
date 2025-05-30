package world.environment;

public class Season {
    private static volatile Season instance;

    private String[] season = {"Summer", "Spring", "Fall", "Winter"};
    private int currentSeason;
    private int dayCounter;
    private static final int MAX_DAYS_IN_SEASON = 10;

    private Season() {
        this.currentSeason = 0;
        this.dayCounter = 0;
    }

    public static Season getInstance() {
        if (instance == null) {
            synchronized (Season.class) {
                if (instance == null) {
                    instance = new Season();
                }
            }
        }
        return instance;
    }

    public String getCurrentSeason() {
        return season[this.currentSeason];
    }

    public int getDayInSeason() {
        return dayCounter + 1;
    }

    public synchronized void nextSeason() {
        dayCounter++;
        if (dayCounter >= MAX_DAYS_IN_SEASON) {
            dayCounter = 0;
            currentSeason = (currentSeason + 1) % season.length;
        }
    }

    // Cheat tool
    public synchronized void setSeason(String seasonName) {
        for (int i = 0; i < season.length; i++) {
            if (season[i].equalsIgnoreCase(seasonName)) {
                currentSeason = i;
                dayCounter = 0;
                break;
            }
        }
    }
}
