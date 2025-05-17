public class Season {
    private String[] season = {"Spring", "Summer", "Fall", "Winter"};
    private int currentSeason = 0;
    private int dayCounter = 0;

    public String getCurrentSeason() {
        return season[currentSeason];
    }

    public int getDayInSeason() {
        return dayCounter + 1;
    }

    public void nextSeason() {
        dayCounter++;
        if (dayCounter >= 10) {
            dayCounter = 0;
            currentSeason = (currentSeason + 1) % season.length;
        }
    }

    // Cheat tool
    public void setSeason(String seasonName) {
        for (int i = 0; i < season.length; i++) {
            if (season[i].equalsIgnoreCase(seasonName)) {
                currentSeason = i;
                dayCounter = 0;
                break;
            }
        }
    }
}
