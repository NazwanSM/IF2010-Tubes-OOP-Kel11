package World.Object;
import World.Point;
import Player.Player;

public class House extends DeployedObject {
    private Point position;

    public House(String name) {
        super(name, "House", 6, 6); 
    }

    @Override
    public void interact(Player player) {
        System.out.println("You enter your house.");
        // Add house interaction logic here
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}