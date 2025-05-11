import java.util.Random;
import java.util.Scanner;

public class Fishing extends Action
{
    private Point location;
    private Weather weatherCondition;
    private Player player;
    private GameTime gameTime;
    private GameClock gameClock; 

    public Fishing(Point location, Weather weatherCondition, Player player, GameTime gameTime, GameClock gameClock) 
    {
        super("Fishing", 5, 15);
        this.location = location;
        this.weatherCondition = weatherCondition;
        this.player = player;
        this.gameTime = gameTime;
        this.gameClock = gameClock;
    }

    public void startFishing() 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        gameClock.pauseClock();
        player.decreaseEnergy(energyRequired);
        gameTime.advance(timeRequired);

        //misal fish nya ini, tp harus nunggu class Fish dlu
        String[] COMMON = {"Carp", "Chub", "Bullhead"};
        String[] REGULAR = {"Halibut", "Sardine", "Salmon"};
        String[] LEGENDARY = {"Legend", "Crimsonfish", "Glacierfish"};
        
        int fishRarity = random.nextInt(3);

        String fishName = "";
        String rarity = "";
        int randomingRange = 0;
        int maxTry = 0;
        int price = 0;
        
        if (fishRarity == 0) //COMMON
        {
            fishName = COMMON[random.nextInt(COMMON.length)];
            rarity = "COMMON";
            randomingRange = 10;
            maxTry = 10;
            price = 10;
        }
        else if (fishRarity == 1) //REGULAR
        {
            fishName = REGULAR[random.nextInt(REGULAR.length)];
            rarity = "REGULAR";
            randomingRange = 100;
            maxTry = 10;
            price = 50;
        }
        if (fishRarity == 2) //LEGENDARY
        {
            fishName = LEGENDARY[random.nextInt(LEGENDARY.length)];
            rarity = "LEGENDARY";
            randomingRange = 500;
            maxTry = 7;
            price = 200;
        }

        int targetRandoming = random.nextInt(randomingRange) + 1;
        boolean caught = false;

        for (int i = 1; i <= maxTry; i++)
        {
            System.out.print(rarity + " HIT! Attempt " + i + "/" + maxTry + ", guess the number (1-" + randomingRange + "): ");

            String input = scanner.nextLine();

        if (input.isBlank() || input.contains(" ") || !input.matches("\\d+")) {
            System.out.println("Invalid input!");
            i--;
            continue;
        }

        if (input.length() > 3) {
        System.out.println("Invalid input, number too large!");
        i--;
        continue;
        }

            int guess = Integer.parseInt(input);

            if (guess == targetRandoming) 
            {
                caught = true;
                break;
            }

            else if (guess < targetRandoming)
            {
                System.out.println("Wrong number, too low!");
            }

            else if (guess > targetRandoming)
            {
                System.out.println("Wrong number, too high!");
            }
            else
            {
                System.out.println("Invalid input!");
            }
        }

        if(caught)
        {
            Fish caughtFish = new Fish(name, "Fish", rarity, "Any", weatherCondition.getCurrentWeather(), location, gameTime, price);
            player.getInventory().addItem(caughtFish, 1);
            System.out.println("Nice catch!");
        }
        else 
        {
            System.out.println("The fish swam away...");
        }

        gameClock.resumeClock();
    }

    public Point getLocation() 
    {
        return location;
    }

    public Weather getWeatherCondition() 
    {
        return weatherCondition;
    }

    public void setLocation(Point location) 
    {
        this.location = location;
    }

    public void setWeatherCondition(Weather weatherCondition) 
    {
        this.weatherCondition = weatherCondition;
    }

    @Override
    public void executeAction()
    {
        startFishing();
    }

    public static void main(String[] args) 
    {
    Point location = new Point(0,0);
    Weather weather = new Weather();
    Player player = new Player("Asep", "Male", "Spakbor", 100);
    GameTime time = new GameTime();
    GameClock clock = new GameClock(time);

    Fishing fishing = new Fishing(location, weather, player, time, clock);
    fishing.startFishing();
    }
}