// File: Items/tools/WateringCan.java
package items.equipment;

public class WateringCan extends Equipment {

    public WateringCan(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }
}