package data; 

import items.Misc; 
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class MiscData {

    private static final List<Misc> allMiscItems = new ArrayList<>();
    private static final Map<String, Misc> miscByName = new HashMap<>();

    static {
        addMisc(new Misc("Coal", 200,100));
        addMisc(new Misc("Firewood", 100,50));
        addMisc(new Misc("Proposal Ring", 3000,0));
        addMisc(new Misc("Egg", 100,50));
        addMisc(new Misc("Gift", 150,50));
        addMisc(new Misc("Eggplant", 80,40));
    }

    private static void addMisc(Misc MiscItem) {
        allMiscItems.add(MiscItem);
        miscByName.put(MiscItem.getName().toLowerCase(), MiscItem);
    }

    public static List<Misc> getAllMiscItems() {
        return Collections.unmodifiableList(allMiscItems);
    }

    public static Misc getMiscByName(String name) {
        if (name == null) {
            return null;
        }
        return miscByName.get(name.toLowerCase());
    }
}