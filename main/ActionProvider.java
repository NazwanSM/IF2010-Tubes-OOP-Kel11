// // File: gamecore/ActionProvider.java (Contoh package)
// package main;

// import Actions.Action;
// import Actions.*; // Impor semua kelas Aksi spesifik Anda
// import entity.player.Player;
// import World.NPC;
// import items.Items; // Kelas dasar item
// import items.Equipment;
// import items.tools.Hoe; // Contoh spesifik tool
// import items.Seed;  // Contoh spesifik seed
// // import tile.Tile; // Jika Anda memiliki kelas Tile yang bisa jadi target

// import java.util.List;
// import java.util.ArrayList;

// public class ActionProvider {

//     public List<Action> getAvailableActions(Player playerData, Object target, Items equippedItem, GamePanel gp) {
//         List<Action> availableActions = new ArrayList<>();

//         // Aksi berdasarkan target
//         if (target instanceof NPC) {
//             NPC npcTarget = (NPC) target;
//             // Selalu bisa diajak bicara (jika pemain di lokasi NPC)
//             availableActions.add(new ChattingAction(playerData, npcTarget, gp));
            
//             // Cek apakah bisa memberi hadiah (misalnya jika pemain punya item di inventory)
//             // Untuk UI sederhana, opsi Gift bisa selalu muncul, lalu pemain memilih item dari inventory.
//             availableActions.add(new GiftingAction(playerData, npcTarget, gp)); // GiftingAction perlu dibuat

//             // Cek kondisi untuk melamar & menikah (dari Player.java Anda)
//             if (playerData.isProposeable(npcTarget) && playerData.hasItem(gp.getProposalRingItem())) { // Asumsi gp bisa menyediakan instance ProposalRing
//                  availableActions.add(new ProposeAction(playerData, npcTarget, gp.getProposalRingItem(), gp)); // ProposeAction perlu dibuat
//             }
//             if (playerData.isMarriable(npcTarget)) {
//                  availableActions.add(new MarryAction(playerData, npcTarget, gp.getProposalRingItem(), gp)); // MarryAction perlu dibuat
//             }
//         }
//         // else if (target instanceof Tile) { // Jika targetnya adalah sebuah tile
//         //     Tile tileTarget = (Tile) target;
//         //     if (equippedItem instanceof Hoe && tileTarget.isTillable()) { // Asumsi Tile punya isTillable()
//         //         availableActions.add(new TillingAction(playerData, gp, tileTarget.worldX, tileTarget.worldY));
//         //     } else if (equippedItem instanceof Seed && tileTarget.isSoil()) { // Asumsi Tile punya isSoil()
//         //         availableActions.add(new PlantingAction(playerData, gp, tileTarget.worldX, tileTarget.worldY, (Seed)equippedItem));
//         //     } // ... dan seterusnya untuk WateringCan, Pickaxe, Harvesting
//         // }
//         else if (target instanceof entity.SuperObject) {
//             entity.SuperObject gameObj = (entity.SuperObject) target;
//             if ("Shipping Bin".equalsIgnoreCase(gameObj.name)) { // Berdasarkan deskripsi Shipping Bin
//                 availableActions.add(new SellingAction(playerData, gp, gameObj)); // SellingAction perlu dibuat
//             }
//             // Contoh lain: jika target adalah Bed di dalam House
//             // else if ("Bed".equalsIgnoreCase(gameObj.name) && playerData.getLocation().getPlace().equals("House")) {
//             //     availableActions.add(new SleepingAction(playerData, gp));
//             // }
//         }

//         // Aksi yang mungkin selalu tersedia dari menu utama atau tidak bergantung target spesifik
//         // (jika dipicu dari menu, 'target' bisa null)
//         if (target == null) { // Contoh: aksi dari menu utama game
//             // availableActions.add(new OpenInventoryAction(playerData, gp)); // OpenInventoryAction perlu dibuat
//             // availableActions.add(new ShowTimeAction(playerData, gp)); // ...perlu dibuat
//             // availableActions.add(new ShowLocationAction(playerData, gp)); // ...perlu dibuat
//         }
        
//         // Aksi berdasarkan lokasi, misal jika di dalam rumah
//         if ("House".equals(playerData.getPlace())) { // Asumsi Player punya getPlace()
//             boolean alreadyHasSleep = false;
//             for(Action a : availableActions) if(a instanceof SleepingAction) alreadyHasSleep = true;
//             if(!alreadyHasSleep) availableActions.add(new SleepingAction(playerData, gp));
            
//             // Cek apakah ada kompor untuk memasak
//             // availableActions.add(new CookingActionMenu(playerData, gp)); // Membuka menu resep
//         }
        
//         // Aksi memancing jika dekat air dan punya FishingRod
//         // if (playerData.hasItem(EquipmentData.getEquipmentByName("Fishing Rod")) && isNearWater(playerData, gp) ) {
//         //    availableActions.add(new FishingAction(playerData, gp, playerData.getLocation().getPlace())); // FishingAction perlu disesuaikan
//         // }


//         // Contoh aksi 'Eating' jika pemain memilih item makanan dari inventory
//         // Ini mungkin bukan dari interaksi objek, tapi dari dalam UI Inventory
//         // if (equippedItem instanceof Edible) { // Jika item yang di-equip adalah makanan
//         //    availableActions.add(new EatingAction(playerData, (Items & Edible)equippedItem, gp));
//         // }


//         return availableActions;
//     }
// }