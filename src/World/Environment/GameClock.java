package World.Environment;


public class GameClock extends Thread {
    private static volatile GameClock instance;

    private int hours;
    private int minutes;
    private boolean isRunning = true;
    private boolean isPaused = false;

    public GameClock() {
        this.hours = 6;
        this.minutes = 0;
    }

    public static GameClock getInstance() {
        if (instance == null) {
            synchronized (GameClock.class) {
                if (instance == null) {
                    instance = new GameClock();
                    instance.start();  // Mulai thread saat instance pertama dibuat
                }
            }
        }
        return instance;
    }

    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000); // 1 detik dunia nyata
                if (!isPaused) {
                    advance(5); // 5 menit waktu game
                    System.out.println("Waktu sekarang: " + getTime());
                }
            } catch (InterruptedException e) {
                System.out.println("Clock interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stopClock() { isRunning = false; }
    public void pauseClock() { isPaused = true; }
    public void resumeClock() { isPaused = false; }

    // Fungsi-fungsi waktu
    public String getTime() {
        return String.format("%02d:%02d", hours, minutes);
    }

    public boolean isDayTime() {
        return hours >= 6 && hours < 18;
    }

    public boolean isNightTime() {
        return hours >= 18 || hours < 6;
    }

    public synchronized void advance(int minutesToAdd) {
        int totalMinutes = hours * 60 + minutes + minutesToAdd;
        hours = (totalMinutes / 60) % 24;
        minutes = totalMinutes % 60;
    }

    public synchronized void skipToMorning() {
        this.hours = 6;
        this.minutes = 0;
    }

    public synchronized int getHours() { return hours; }
    public synchronized int getMinutes() { return minutes; }
}
