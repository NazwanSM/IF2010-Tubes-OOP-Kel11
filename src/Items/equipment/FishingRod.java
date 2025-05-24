// File: Items/tools/FishingRod.java
package items.equipment;

// import Actions.FishingAction; // Impor kelas FishingAction yang sudah Anda buat
import entity.player.Player;
import main.GamePanel;

public class FishingRod extends Equipment {

    public FishingRod(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }

    @Override
    public boolean use(Player playerData, GamePanel gp, int targetWorldX, int targetWorldY) {
        // // Untuk memancing, targetWorldX/Y mungkin tidak relevan atau digunakan untuk menentukan spot memancing.
        // // FishingAction Anda mungkin sudah menangani logika lokasi.
        // if (gp.player != null && playerData != null) {
        //     // Animasi memancing mungkin lebih kompleks (cast, wait, reel)
        //     // startToolAnimation bisa memulai animasi "cast"
        //     gp.player.startToolAnimation(getUseAnimationName(playerData.getDirection()));
        // }
        
        // // FishingAction dari Source 3 di giliran sebelumnya, konstruktornya: Fishing(Player player)
        // // Mungkin perlu disesuaikan untuk menerima GamePanel jika butuh akses ke UI, GameClock, dll.,
        // // atau jika FishingAction mengambilnya dari Player.
        // // Untuk konsistensi, kita buat konstruktor FishingAction menerima Player dan GamePanel.
        // FishingAction fishingAction = new FishingAction(playerData, gp, playerData.getPlace()); // Asumsi Player punya getPlace() untuk lokasi
        // return fishingAction.executeAction();
        // Sementara, kembalikan true untuk menandakan aksi berhasil
        return true; // Sementara, kembalikan true untuk menandakan aksi berhasil
    }

    @Override
    public String getUseAnimationName(String playerDirection) {
        // Animasi memancing bisa lebih dari satu tahap (cast, idle, reel)
        // Ini bisa menjadi animasi awal "cast".
        return "fishingrod_cast_" + playerDirection.toLowerCase();
    }
}