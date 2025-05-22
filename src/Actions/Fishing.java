package Actions ;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


import Items.Fish;
import entity.player.Player;
import World.Environment.Weather;
import World.Environment.GameClock;
import World.Environment.Season;
import Data.FishData;

public class Fishing extends Action
{
    private String currentLocation;
    private String currentWeather;
    private Player player;
    private int currentHour;
    private String currentSeason;
    private GameClock gameClock;

    public Fishing(Player player) 
    {
        super("Fishing", 5, 15);
        this.currentLocation = player.getPlace() ; // ini harusnya nanti diimplement di player
        this.currentSeason = Season.getInstance().getCurrentSeason();
        this.currentWeather = Weather.getInstance().getCurrentWeather();
        this.player = player;
        this.gameClock =GameClock.getInstance();
        this.currentHour = gameClock.getHours();
    }


    public void startFishing() 
    {
        Random random = new Random();
        
        gameClock.pauseClock();
        player.decreaseEnergy(energyRequired); // harus ada handle exception kalo energy ga cukup
        gameClock.advance(timeRequired);
        
        List<Fish> allFish = FishData.getAllFish();
        List<Fish> validFish = new ArrayList<>();
        for (Fish fish : allFish) {
            if (fish.canBeCaught(currentSeason, currentHour, currentWeather, currentLocation)) {
                validFish.add(fish);
            }
        }
        
        if (validFish.isEmpty()) {
            System.out.println("No fish can be caught here at this time.");
            return;
        }
        
        
        Scanner scanner = new Scanner(System.in);
        Fish fish = validFish.get(random.nextInt(validFish.size()));
        System.out.println("You attempt to fish... Might catch a " + fish.getName());

        int targetRandoming = 0, maxTry = 0, randomingRange = 0;
        switch (fish.getRarity()) {
            case COMMON -> {
                targetRandoming = random.nextInt(10) + 1;
                maxTry = 10;
                randomingRange = 10;
            }
            case REGULAR -> {
                targetRandoming = random.nextInt(100) + 1;
                maxTry = 10;
                randomingRange = 100;
            }
            case LEGENDARY -> {
                targetRandoming = random.nextInt(500) + 1;
                maxTry = 7;
                randomingRange = 500;
            }
        }
        

        boolean caught = false;

        for (int i = 1; i <= maxTry; i++)
        {
            System.out.print(" HIT! Attempt " + i + "/" + maxTry + ", guess the number (1-" + randomingRange + "): ");

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
            Fish caughtFish = fish;
            player.getInventory().addItem(caughtFish, 1);
            System.out.println("Nice catch!");
        }
        else 
        {
            System.out.println("The fish swam away...");
        }

        scanner.close();
        gameClock.resumeClock();
    }

    @Override
    public void executeAction()
    {
        startFishing();
    }
}