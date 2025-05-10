import java.util.Random;
import java.util.Scanner;

public class Fishing extends Action
{
    private Point location;
    private Weather weatherCondition;

    public Fishing() 
    {
        super("Fishing", 5, 15);
        this.location = location;
        this.weatherCondition = weatherCondition;
    }

    public void startFishing(Player player, GameTime gameTime, GameClock gameClock) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        gameClock.pauseClock();
        player.decreaseEnergy(energyRequired);
        gameTime.advance(timeRequired);

        //misal fish nya ini, tp harus nunggu class Fish dlu
        String[] common = {"Carp", "Chub", "Bullhead"};
        String[] regular = {"Halibut", "Sardine", "Salmon"};
        String[] legendary = {"Legend", "Crimsonfish", "Glacierfish"};
        
        int fishRarity = random.nextInt(3);
        String fishName;
        String rarity;
        int randomingRange, maxTry, price;
        
        if (fishRarity == 0) //common
        {
            fishName = common[random.nextInt(common.length)];
            rarity = "common";
            randomingRange = 10;
            maxTry = 10;
            price = 10;
        }
        else if (fishRarity == 1) //regular
        {
            fishName = regular[random.nextInt(regular.length)];
            rarity = "regular";
            randomingRange = 100;
            maxTry = 10;
            price = 50;
        }
        if (fishRarity == 2) //legendary
        {
            fishName = legendary[random.nextInt(legendary.length)];
            rarity = "legendary";
            randomingRange = 500;
            maxTry = 7;
            price = 200;
        }

        int targetRandoming = random.nextInt(randomingRange) + 1;
        boolean caught = false;

        for (int i = 1; i <= maxTry; i++)
        {
            System.out.print("Attempt " + i + "/" + maxTry + ", guess the number (1-" + randomingRange + "): ");
            int guess = scanner.nextInt();

            if (guess == targetRandoming) 
            {
                caught = true;
                break;
            }

            else if (guess < targetRandoming)
            {
                System.out.println("Wrong number, too low!");
            }

            else
            {
                System.out.println("Wrong number, too high!");
            }
        }

        if(caught)
        {
            Fish caughtFish = new Fish(name, "Fish", rarity, "Any", weatherCondition.getCurrentWeather(), location, gameTime, price);
            player.addItemToInventory(caughtFish);
        }
        else 
        {
            System.out.println("The fish got away...");
        }

        gameClock.resumeClock();
        System.out.println("Fishing finished.");
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
    }
}