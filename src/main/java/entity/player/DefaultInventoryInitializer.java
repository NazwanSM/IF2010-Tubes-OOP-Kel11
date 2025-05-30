package entity.player;


import data.EquipmentData;
import data.SeedData;
import items.Items;
public class DefaultInventoryInitializer {

    public void setDefaultItems(Inventory playerInventory) {
        if (playerInventory == null) {
            System.err.println("Error: Gagal menginisialisasi inventaris default, objek Inventory null.");
            return;
        }

        Items parsnipSeedsPrototype = SeedData.getSeedByName("Parsnip Seeds");
        playerInventory.addItem(parsnipSeedsPrototype, 15);
        Items hoePrototype = EquipmentData.getEquipmentByName("Hoe");
        playerInventory.addItem(hoePrototype, 1);
        Items wateringCanPrototype = EquipmentData.getEquipmentByName("Watering Can");
        playerInventory.addItem(wateringCanPrototype, 1);
        Items pickaxePrototype = EquipmentData.getEquipmentByName("Pickaxe"); 
        playerInventory.addItem(pickaxePrototype, 1);
        Items fishingRodPrototype = EquipmentData.getEquipmentByName("Fishing Rod");
        playerInventory.addItem(fishingRodPrototype, 1);

    }
}