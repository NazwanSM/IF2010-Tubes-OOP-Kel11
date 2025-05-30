package data;


import java.util.List;
import main.SimpleDataManager;
import items.equipment.Equipment;
import items.equipment.FishingRod;
import items.equipment.Hoe;
import items.equipment.Pickaxe;
import items.equipment.WateringCan;

public class EquipmentData {
    private static final SimpleDataManager<Equipment> manager = new SimpleDataManager<>();

    static {
        addEquipment(new Hoe("Hoe", "Cangkul untuk menyiapkan lahan tanam."));
        addEquipment(new WateringCan("Watering Can", "Digunakan untuk menyiram tanaman."));
        addEquipment(new Pickaxe("Pickaxe", "Untuk menghancurkan batu dan mengembalikan lahan."));
        addEquipment(new FishingRod("Fishing Rod", "Alat standar untuk menangkap ikan di perairan."));
    }

    private static void addEquipment(Equipment equip) {
        manager.addItem(equip);
    }

    public static List<Equipment> getAllEquipment() {
        return manager.getAllItems();
    }

    public static Equipment getEquipmentByName(String name) {
        return manager.getItemByName(name);
    }
}