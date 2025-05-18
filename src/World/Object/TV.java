package World.Object;

import Player.Player;

public class TV extends Furniture {
    public TV() {
        super("TV");
    }
    
    public void interact(Player player) {
        System.out.println("You interact with the TV.");
    }
}

