package items.equipment;

import main.GamePanel;
// import Actions.TillingAction;
import entity.player.Player;

public class Hoe extends Equipment {

    public Hoe(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }

    @Override
    public boolean use(Player playerData, GamePanel gp, int targetWorldX, int targetWorldY) {
        // // 1. (Opsional) Panggil PlayerUI untuk memulai animasi mencangkul SEBELUM aksi logika dieksekusi
        // //    Ini memberikan feedback visual langsung.
        // if (gp.player != null) { // gp.player adalah PlayerUI
        //     gp.player.startToolAnimation(getUseAnimationName(playerData.getDirection()));
        // }
        
        // // 2. Buat dan jalankan objek Action yang sesuai
        // TillingAction tillingAction = new TillingAction(playerData, gp, targetWorldX, targetWorldY);
        // boolean success = tillingAction.executeAction(); // executeAction() akan menangani energi, waktu, dll.

        // // 3. (Opsional) Jika animasi tidak di-handle sepenuhnya oleh state di PlayerUI,
        // //    dan aksi gagal, Anda mungkin ingin menghentikan animasi di sini.
        // //    Namun, biasanya startToolAnimation akan mengatur durasi atau state.
        // // if (!success && gp.playerUI != null) {
        // //     gp.playerUI.stopToolAnimation(); // Jika perlu
        // // }
        
        // return success;
        return true;
    }

    @Override
    public String getUseAnimationName(String playerDirection) {
        return "hoe_swing_" + playerDirection.toLowerCase();
    }
}