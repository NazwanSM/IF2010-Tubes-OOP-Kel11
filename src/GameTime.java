public class GameTime {
    private int hours;
    private int minutes;

    public GameTime() {
        this.hours = 6;
        this.minutes = 0;
    }

    public String getTime() {
        return String.format("%02d:%02d", hours, minutes);
    }

    public boolean isDayTime() {
        return hours >= 6 && hours < 18;
    }

    public boolean isNightTime() {
        return hours >= 18 || hours < 6;
    }

    public void advance(int minutesToAdd) {
        int totalMinutes = hours * 60 + minutes + minutesToAdd;
        hours = (totalMinutes / 60) % 24;
        minutes = totalMinutes % 60;
    }

    public void skipToMorning() {
        this.hours = 6;
        this.minutes = 0;
    }

    public int getHours() { 
        return hours; 
    }
    public int getMinutes() { 
        return minutes; 
    }
}
