import java.util.ArrayList;
import java.util.List;

public class ShippingBins<Player> {
    private List<Items> listItemToSell;  // Daftar item yang ingin dijual
    private static final int MAX_BIN = 10;  // Batas jumlah item yang dapat ditambahkan ke Shipping Bin

    public ShippingBins() {
        listItemToSell = new ArrayList<>();
    }

    // Menambahkan item ke bin untuk dijual
    public void addItemsToBin(Items item, int amount, Inventory inventory) {
        // Periksa apakah Shipping Bin sudah penuh
        if (listItemToSell.size() >= MAX_BIN) {
            System.out.println("Shipping Bin penuh.");
            return;
        }

        if (inventory.hasItem(item)) {
            if (inventory.checkInventory().get(item) >= amount) {
                if (item instanceof Sellable) {  // Periksa apakah item dapat dijual
                    Sellable sellableItem = (Sellable) item;
                    if (sellableItem.getSellPrice() > 0) {
                        boolean itemExists = false;
                        for (Items binItem : listItemToSell) {
                            if (binItem.getName().equals(item.getName())) {
                                binItem.setQuantity(binItem.getQuantity() + amount);
                                itemExists = true;
                                break;
                            }
                        }

                        if (!itemExists) {
                            item.setQuantity(amount);
                            listItemToSell.add(item);
                        }

                        inventory.removeItem(item, amount);
                        //System.out.println(amount + " " + item.getName() + " berhasil ditambahkan ke Shipping Bin.");
                    } else {
                        System.out.println(item.getName() + " tidak bisa dijual.");
                    }
                } else {
                    System.out.println(item.getName() + " tidak bisa dijual.");
                }
            } else {
                System.out.println("Kamu tidak memiliki cukup " + item.getName() + " untuk dimasukkan ke Shipping Bin.");
            }
        } else {
            System.out.println("Kamu tidak mempunyai " + item.getName() + " di inventory.");
        }
    }

    // Menjual semua item yang ada di bin (otomatis ketika pemain tidur dan bangun)
    public void sellItems(Player player) {
        if (listItemToSell.isEmpty()) {
            //System.out.println("Tidak ada item yang dapat dijual di Shipping Bin.");
            return;
        }

        int totalSale = 0;
        for (Items item : listItemToSell) {
            if (item instanceof Sellable) {
                Sellable sellableItem = (Sellable) item;
                totalSale += sellableItem.getSellPrice() * item.getQuantity();
            }
        }

        System.out.println("Total hasil penjualan: " + totalSale + " gold.");
        listItemToSell.clear();
    }

    // Menampilkan isi bin
    public void displayItemsInBin() {
        if (listItemToSell.isEmpty()) {
            System.out.println("Shipping Bin kosong.");
        } else {
            System.out.println("Isi Shipping Bin:");
            for (Items item : listItemToSell) {
                if (item instanceof Sellable) { // Periksa apakah item dapat dijual
                    Sellable sellableItem = (Sellable) item;
                    System.out.println(item.getName() + " | Jumlah: " + item.getQuantity() + " | Harga Jual: " + sellableItem.getSellPrice() + " gold.");
                } else {
                    System.out.println(item.getName() + " | Jumlah: " + item.getQuantity() + " | Tidak dapat dijual.");
                }
            }
        }
    }
}
