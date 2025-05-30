package data; 

import main.SimpleDataManager;
import items.Misc;

import java.util.List;

public class MiscData {

    private static final SimpleDataManager<Misc> manager = new SimpleDataManager<>();

    static {
        addMisc(new Misc("Coal", 200,100));
        addMisc(new Misc("Firewood", 100,50));
        addMisc(new Misc("Proposal Ring", 3000,0));
        addMisc(new Misc("Egg", 100,50));
        addMisc(new Misc("Gift", 150,50));
        addMisc(new Misc("Eggplant", 80,40));
    }

    private static void addMisc(Misc MiscItem) {
        manager.addItem(MiscItem);
    }

    public static List<Misc> getAllMiscItems() {
        return manager.getAllItems();
    }

    public static Misc getMiscByName(String name) {
        return manager.getItemByName(name);
    }
}