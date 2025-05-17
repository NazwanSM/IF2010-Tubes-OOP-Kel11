package World;

public class GameClock extends Thread {
    private GameTime gameTime;
    private boolean isRunning = true;
    private boolean isPaused = false;

    public GameClock(GameTime gameTime) {
        this.gameTime = gameTime;
    }

    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000); // 1 detik dunia nyata
                if (!isPaused) {
                    gameTime.advance(5); // 5 menit waktu game
                    System.out.println("Waktu sekarang: " + gameTime.getTime());
                }
            } catch (InterruptedException e) {
                System.out.println("Clock interrupted.");
            }
        }
    }

    public void stopClock() {
        isRunning = false;
    }

    public void pauseClock() {
        isPaused = true;
    }

    public void resumeClock() {
        isPaused = false;
    }
}
