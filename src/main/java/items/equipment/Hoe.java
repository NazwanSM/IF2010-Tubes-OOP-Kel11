package items.equipment;


public class Hoe extends Equipment {

    public Hoe(String name, String description) {
        super(name, description);
        loadImage("/items/tools/" + name.replace(" ", "_") + ".png");
    }
}