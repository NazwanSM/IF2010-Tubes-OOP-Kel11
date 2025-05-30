// File: Items/tools/FishingRod.java
package items.equipment;

public class FishingRod extends Equipment {

    public FishingRod(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }
}