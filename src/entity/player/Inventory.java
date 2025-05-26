package entity.player;

import java.util.HashMap;
import java.util.Map;

import items.Items;

public class Inventory {
    private Map<Items, Integer> items;

    public Inventory(){
        items = new HashMap<>();
    }

    //Buat nambahin items
    public void addItem(Items item, int amount){
        if(items.containsKey(item)){ //Kalau udah ada barangnya sebelumnya, kita tinggal tambahin jumlahnya dengan 1
            int jumlahBarang = items.get(item);
            items.put(item, jumlahBarang + amount);
        } else {
            items.put(item, amount); //kalau belum ada, masukin ke list dan jumlahnya jadi 1
        }

        //Nampilin pesan berhasil
        // System.out.println(item.getName() + " Berhasil ditambahkan!");
    }

    //Buat ngurangin items
    public void removeItem(Items item, int amount){
        /** 
         * Kalau ada barangnya dikurangin 1
         * - Kalau cuma 1 jumlahnya --> hapus dari list
         * - kalau lebih dari 1 --> kurangin 1
         * 
         * Kalau barang yang mau dihapus gada di inventory --> pesan error "Kamu tidak mempunyai <nama barang>"
        */

        if (items.containsKey(item)) { // ngecek apakah item ada di inventory
            int jumlahBarang = items.get(item);
            if (jumlahBarang < amount) { // kalau jumlah barang kurang dari jumlah yang ingin dihapus
                System.out.println(item.getName() + " tidak cukup untuk dihapus.");
            } else if (jumlahBarang == amount) { // kalau jumlah barang sama dengan jumlah yang ingin dihapus
                items.remove(item); // ngehapus item dari inventory
                System.out.println(item.getName() + " telah dihapus dari inventory.");
            } else { // kalau jumlah barang lebih besar dari jumlah yang ingin dihapus
                items.put(item, jumlahBarang - amount); // Kurangi jumlah barang
                System.out.println(amount + " " + item.getName() + " berhasil dihapus. Sisa: " + (jumlahBarang - amount));
            }
        } else {
            System.out.println("Kamu tidak mempunyai " + item.getName() + " di inventory."); // Pesan error jika item tidak ditemukan
        }
    }

    public Map<Items, Integer> checkInventory(){
        return items;
    }

    public boolean hasItem(Items item) {
        return items.containsKey(item);
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Inventory kosong.");
            return;
        }
        for (Map.Entry<Items, Integer> entry : items.entrySet()) {
            System.out.println("Item: " + entry.getKey().getName() + " | Quantity: " + entry.getValue());
        }
    }

    public Items getItemByName(String itemName) {
        for (Items item : items.keySet()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}
