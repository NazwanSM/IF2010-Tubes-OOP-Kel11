package entity.player;


import items.Items;
import data.SeedData;
import data.CropsData;
import data.EquipmentData;
import data.MiscData;

public class DefaultInventoryInitializer {

    public void setDefaultItems(Inventory playerInventory) {
        if (playerInventory == null) {
            System.err.println("Error: Gagal menginisialisasi inventaris default, objek Inventory null.");
            return;
        }

        System.out.println("Menginisialisasi inventaris default pemain...");

        Items parsnipSeedsPrototype = SeedData.getSeedByName("Parsnip Seeds");
        playerInventory.addItem(parsnipSeedsPrototype, 15);
        Items hoePrototype = EquipmentData.getEquipmentByName("Hoe");
        playerInventory.addItem(hoePrototype, 1);
        Items wateringCanPrototype = EquipmentData.getEquipmentByName("WateringCan");
        playerInventory.addItem(wateringCanPrototype, 1);
        Items pickaxePrototype = EquipmentData.getEquipmentByName("Pickaxe"); 
        playerInventory.addItem(pickaxePrototype, 1);
        Items fishingRodPrototype = EquipmentData.getEquipmentByName("FishingRod");
        playerInventory.addItem(fishingRodPrototype, 1);

        //debugging purpose
        Items tomato = CropsData.getCropByName("Tomato");
        playerInventory.addItem(tomato, 5);
        Items wheat = CropsData.getCropByName("Wheat");
        playerInventory.addItem(wheat, 5);
        Items firewood = MiscData.getMiscByName("Firewood");
        playerInventory.addItem(firewood, 100);

        System.out.println("Inventaris default berhasil diinisialisasi.");
    }
}