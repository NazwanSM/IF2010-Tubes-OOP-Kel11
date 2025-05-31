package actions ;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import data.FishData;
import data.FishRarity;
import data.RecipeData;
import entity.player.Player;
import items.Fish;
import main.GamePanel;
import world.environment.Season;
import world.environment.Weather;

public class FishingAction extends Action
{
    private String currentLocation;
    private String currentWeather;
    private Player player;
    private int currentHour;
    private String currentSeason;

    public FishingAction(Player player, GamePanel gp)
    {
        super("Fishing", 5, 15, gp);
        this.currentSeason = Season.getInstance().getCurrentSeason();
        this.currentWeather = Weather.getInstance().getCurrentWeather();
        this.player = player;
        this.currentHour = gp.farm.getGameClock().getHours();
        if (gp.currentMap == 0) {
            this.currentLocation = "Pond";
        } else if (gp.currentMap == 2) {
            this.currentLocation = "Forest River";
        } else if (gp.currentMap == 3) {
            this.currentLocation = "Mountain Lake";
        } else if (gp.currentMap == 4) {
            this.currentLocation = "Ocean";
        } else {
            this.currentLocation = "Unknown Location";
        }
    }

    public void startFishing()
    {
        // Corrected method name from getEquppedItem() to getEquippedItem()
        if (player.getEquippedItem() == null || !player.getEquippedItem().getName().equalsIgnoreCase("Fishing Rod")) {
            gp.ui.addMessage("You need to equip a fishing rod to fish!");
            return;
        }
        if (player.getEnergy() < -15) {
            gp.ui.addMessage("You don't have enough energy to fish!");
            return;
        }
        Random random = new Random();


        List<Fish> allFish = FishData.getAllFish();
        List<Fish> validFish = new ArrayList<>();
        for (Fish fish : allFish) {
            if (fish.canBeCaught(currentSeason, currentHour, currentWeather, currentLocation)) {
                validFish.add(fish);
            }
        }

        if (validFish.isEmpty()) {
            gp.ui.addMessage("No fish can be caught here at this time.");
            return;
        }

        gp.farm.getGameClock().pauseClock();
        player.decreaseEnergy(energyRequired);
        gp.farm.getGameClock().advance(timeRequired);

        Fish fish = validFish.get(random.nextInt(validFish.size()));
        ImageIcon fishIcon = null;
        if (fish != null && fish.getItemImage() != null) {
            try {
                BufferedImage originalImage = fish.getItemImage();
                Image scaledImage = originalImage.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                fishIcon = new ImageIcon(scaledImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(gp, "You attempt to fish... Might catch a " + fish.getName() + "!", "Fishing", JOptionPane.INFORMATION_MESSAGE, fishIcon);
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
            String input = JOptionPane.showInputDialog(gp, "HIT! Attempt " + i + "/" + maxTry + ", guess the number (1-" + randomingRange + "):", "Fishing " + fish.getName(), JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            JOptionPane.showMessageDialog(gp, "Fishing cancelled.", "Fishing " + fish.getName(), JOptionPane.INFORMATION_MESSAGE, fishIcon);
            gp.farm.getGameClock().resumeClock();
            gp.gameState = gp.playState;
            return;
        }
        if (input.contains(" ") || !input.matches("\\d+")) {
            JOptionPane.showMessageDialog(gp, "Invalid input! Please enter a number between 1 and " + randomingRange + ".", "Fishing " + fish.getName(), JOptionPane.ERROR_MESSAGE, fishIcon);
            i--;
            continue;
        }

        if (input.length() > 3) {
            JOptionPane.showMessageDialog(gp, "Input too long! Please enter a number between 1 and " + randomingRange + ".", "Fishing " + fish.getName(), JOptionPane.ERROR_MESSAGE, fishIcon);
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
                JOptionPane.showMessageDialog(gp, "Wrong number, too low! Try again.", "Fishing " + fish.getName(), JOptionPane.INFORMATION_MESSAGE, fishIcon);
            }

            else if (guess > targetRandoming)
            {
                JOptionPane.showMessageDialog(gp, "Wrong number, too high! Try again.", "Fishing " + fish.getName(), JOptionPane.INFORMATION_MESSAGE, fishIcon);
            }
            else
            {
                JOptionPane.showMessageDialog(gp, "Invalid input! Please enter a number between 1 and " + randomingRange + ".", "Fishing " + fish.getName(), JOptionPane.ERROR_MESSAGE, fishIcon);
            }
        }

        if(caught)
        {
            Fish caughtFish = fish;
            player.getInventory().addItem(caughtFish, 1);
            gp.manager.trackFishCaught(caughtFish, caughtFish.getRarity());
            gp.playSE(7);
            JOptionPane.showMessageDialog(gp, "Congratulations! You caught a " + caughtFish.getName() + "!", "Fishing Success", JOptionPane.INFORMATION_MESSAGE, fishIcon);
            if (gp.manager.getTotalFishCaught() >= 10) {
                RecipeData.getRecipeById("recipe_3").setUnlocked(true);
            }
            if (fish.getRarity() == FishRarity.LEGENDARY) {
                RecipeData.getRecipeById("recipe_11").setUnlocked(true);
            }
            if (fish.getName().equalsIgnoreCase("Pufferfish")) {
                RecipeData.getRecipeById("recipe_4").setUnlocked(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(gp, "You failed to catch the fish. Better luck next time!", "Fishing Failed", JOptionPane.INFORMATION_MESSAGE, fishIcon);
        }

        gp.farm.getGameClock().resumeClock();
        gp.gameState = gp.playState;
    }

    @Override
    public boolean executeAction()
    {
        startFishing();
        return true;
    }
}