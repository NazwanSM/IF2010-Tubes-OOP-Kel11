package World.Object;
import Player.Player;

public class Bed extends Furniture {
    
    public Bed() {
        super("Bed");
    }
    
    @Override
    public void interact(Player player) {
        System.out.println("You lie down on the bed.");
        System.out.println("Do you want to sleep until morning?");
        player.sleeping();
    }
}