package Data;

import java.util.ArrayList;
import java.util.List;
import World.NPC;

public class NPCData {
    private static List<NPC> allNPCs;

    static {
        allNPCs = new ArrayList<>();

        allNPCs.add(new NPC("Mayor Tadi",
            List.of("Legend"),
            List.of("Angler", "Crimsonfish", "Glacierfish"),
            null // semua item lain dianggap hate
        ));

        allNPCs.add(new NPC("Caroline",
            List.of("Firewood", "Coal"),
            List.of("Potato", "Wheat"),
            List.of("Hot Pepper")
        ));

        allNPCs.add(new NPC("Perry",
            List.of("Cranberry", "Blueberry"),
            List.of("Wine"),
            List.of("Bullhead", "Carp", "Chub", "Largemouth Bass", "Rainbow Trout", "Sturgeon",
                    "Midnight Carp", "Flounder", "Halibut", "Octopus", "Pufferfish", "Sardine",
                    "Super Cucumber", "Catfish", "Salmon", "Angler", "Crimsonfish", "Glacierfish", "Legend")
        ));

        allNPCs.add(new NPC("Dasco",
            List.of("The Legends of Spakbor", "Cooked Pig's Head", "Wine", "Fugu", "Spakbor Salad"),
            List.of("Fish Sandwich", "Fish Stew", "Baguette", "Fish n' Chips"),
            List.of("Legend", "Grape", "Cauliflower", "Wheat", "Pufferfish", "Salmon")
        ));

        allNPCs.add(new NPC("Emily",
            List.of("Parsnip Seeds", "Cauliflower Seeds", "Potato Seeds", "Wheat Seeds", "Blueberry Seeds",
                    "Tomato Seeds", "Hot Pepper Seeds", "Melon Seeds", "Cranberry Seeds", "Pumpkin Seeds", "Grape Seeds"),
            List.of("Catfish", "Salmon", "Sardine"),
            List.of("Coal", "Wood")
        ));

        allNPCs.add(new NPC("Abigail",
            List.of("Blueberry", "Melon", "Pumpkin", "Grape", "Cranberry"),
            List.of("Baguette", "Pumpkin Pie", "Wine"),
            List.of("Hot Pepper", "Cauliflower", "Parsnip", "Wheat")
        ));
    }

    public static List<NPC> getAllNPCs() {
        return allNPCs;
    }

    public static NPC getNPCByName(String name) {
        return allNPCs.stream()
                .filter(n -> n.getNPCName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}