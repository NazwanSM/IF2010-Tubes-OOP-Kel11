package World.Object;
import Actions.Fishing;


public class Pond extends DeployedObject {
    public Pond(String name, int width, int length) {
        super(name, "Pond", width, length);
    }

    public void interact() {
        System.out.println("You interact with the pond.");
        startFishing();
    }
}
