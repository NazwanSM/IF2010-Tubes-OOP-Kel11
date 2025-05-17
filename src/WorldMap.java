import java.util.Scanner;

public class WorldMap extends Map {
    private Player player;
    private GameTime gameTime;

    public WorldMap(String name, int width, int length, Player player, GameTime gameTime) {
        super(name, width, length);
        this.player = player;
        this.gameTime = gameTime;
    }

    public void interact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WORLD MAP");
        System.out.println("Where do you want to go?");
        System.out.println("1. Mayor Tadi's House");
        System.out.println("2. Forest River");
        System.out.println("3. Mountain Lake");
        System.out.println("4. Ocean");
        System.out.println("5. Store Emily");
        System.out.print("Your Choice: ");
        int choice = scanner.nextInt();

        Point Destination = null;

        //asumsi point dari lokasi-lokasi di world map
        switch (choice) {
            case 1:
                System.out.println("OTW Mayor Tadi...");
                Destination = new Point(5, 5);
                break;
            case 2:
                System.out.println("OTW Forest River...");
                Destination = new Point(10, 3);
                break;
            case 3:
                System.out.println("OTW Mountain Lake...");
                Destination = new Point(2, 12);
                break;
            case 4:
                System.out.println("OTW Ocean...");
                Destination = new Point(15, 7);
                break;
            case 5:
                System.out.println("OTW Store Emily...");
                Destination = new Point(7, 18);
                break;
            default:
                System.out.println("Invalid input!");
                return;
        }

        if (Destination != null) {
            player.setLocation(Destination);
            player.decreaseEnergy(10);
            gameTime.advance(15);
            System.out.println("Player is now here: " + Destination);
        }
    }
}