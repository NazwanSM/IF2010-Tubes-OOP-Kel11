// File: Items/tools/Pickaxe.java
package items.equipment;

public class Pickaxe extends Equipment {

    public Pickaxe(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }
}