package World.Object;

import Actions.Cooking;
import Player.Player;
import World.Environment.GameClock;
import World.Environment.Season;
import World.Environment.Weather;
import World.Object.Furniture;
import World.Object.DeployedObject;

public class Stove extends Furniture {
    public Stove() {
        super("Stove");
    }
    public void interact(Player player) {
        System.out.println("You interact with the stove.");
        System.out.println("Do you want to cook something?");
        
        Cooking cooking = new Cooking(player);
        cooking.executeAction();
    }
}
