// File: Data/EquipmentData.java
package data;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import items.equipment.*;

public class EquipmentData {
    private static final List<Equipment> allEquipment = new ArrayList<>();
    private static final Map<String, Equipment> equipmentByName = new HashMap<>();

    static {
        addEquipment(new Hoe("Hoe", "Cangkul untuk menyiapkan lahan tanam."));
        addEquipment(new WateringCan("Watering Can", "Digunakan untuk menyiram tanaman."));
        addEquipment(new Pickaxe("Pickaxe", "Untuk menghancurkan batu dan mengembalikan lahan."));
        addEquipment(new FishingRod("Fishing Rod", "Alat standar untuk menangkap ikan di perairan."));
    }

    private static void addEquipment(Equipment equip) {
        allEquipment.add(equip);
        equipmentByName.put(equip.getName().toLowerCase().replace(" ", ""), equip);
    }

    public static List<Equipment> getAllEquipment() {
        return Collections.unmodifiableList(allEquipment);
    }

    public static Equipment getEquipmentByName(String name) {
        if (name == null) return null;
        return equipmentByName.get(name.toLowerCase().replace(" ", ""));
    }
}