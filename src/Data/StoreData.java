package data;

import items.*; // Asumsi semua kelas item seperti Items, Seed, Crops, Food, Misc, dan mungkin Furniture ada di sini

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kelas StoreData bertanggung jawab untuk menyediakan daftar item yang dapat dibeli di toko.
 * Item diambil dari kelas-kelas Data (SeedData, CropsData, FoodData, MiscData)
 * dan hanya item dengan harga beli yang valid (lebih dari 0) yang disertakan.
 * Item furniture ditambahkan secara manual berdasarkan ItemList.pdf.
 */
public class StoreData {

    private static List<Items> storeItemList;

    /**
     * Metode helper untuk menambahkan item ke daftar toko jika item tersebut valid
     * dan memiliki harga beli lebih besar dari 0.
     *
     * @param item Objek item yang akan ditambahkan.
     */
    private static void addItemToStoreIfPurchasable(Items item) {
        // if (item != null && item.getBuyPrice() > 0) {
        //     storeItemList.add(item);
        // }
    }

    public static void initializeStoreItems() {
        storeItemList = new ArrayList<>();

        List<Seed> allSeeds = SeedData.getAllSeeds();
        for (Seed seed : allSeeds) {
            addItemToStoreIfPurchasable(seed);
        }
        List<Crops> allCrops = CropsData.getAllCrops(); 
        for (Crops crop : allCrops) {
            addItemToStoreIfPurchasable(crop);
        }

        List<Food> allFoodItems = FoodData.getAllFoodItems();
        for (Food food : allFoodItems) {
            addItemToStoreIfPurchasable(food);
        }

        List<Misc> allMiscItems = MiscData.getAllMiscItems();
        for (Misc misc : allMiscItems) {
            addItemToStoreIfPurchasable(misc);
        }
    }

    public static List<Items> getStoreItemList() {
        if (storeItemList == null) {
            initializeStoreItems();
        }
        return Collections.unmodifiableList(storeItemList);
    }
}