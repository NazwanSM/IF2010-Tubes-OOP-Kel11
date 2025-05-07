public class Season {
    private String[] seasonCycle = {"Spring", "Summer", "Fall", "Winter"};
    private int currentIndex = 0;
    private int dayCounter = 0;

    public String getCurrentSeason() {
        return seasonCycle[currentIndex];
    }

    public int getDayInSeason() {
        return dayCounter + 1;
    }

    public void nextDay() {
        dayCounter++;
        if (dayCounter >= 10) {
            dayCounter = 0;
            currentIndex = (currentIndex + 1) % seasonCycle.length;
        }
    }

    // Cheat tool
    public void setSeason(String seasonName) {
        for (int i = 0; i < seasonCycle.length; i++) {
            if (seasonCycle[i].equalsIgnoreCase(seasonName)) {
                currentIndex = i;
                dayCounter = 0;
                break;
            }
        }
    }
}
