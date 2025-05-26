// File: Items/tools/Pickaxe.java
package items.equipment;

import main.GamePanel;
// import Actions.RecoverLandAction; // Impor kelas Action yang sesuai (atau MiningAction)
import entity.player.Player;

public class Pickaxe extends Equipment {

    public Pickaxe(String name, String description) {
        super(name, description);
        loadImage("/resource/items/tools/" + name.replace(" ", "_") + ".png");
    }

    @Override
    public boolean use(Player playerData, GamePanel gp, int targetWorldX, int targetWorldY) {
        // if (gp.player != null && playerData != null) {
        //     gp.player.startToolAnimation(getUseAnimationName(playerData.getDirection()));
        // }
        
        // // Anda mungkin perlu logika tambahan di sini untuk menentukan apakah targetnya
        // // adalah tanah yang bisa dikembalikan (soil to land) atau batu untuk ditambang.
        // // Untuk sekarang, kita panggil RecoverLandAction sebagai contoh.
        // RecoverLandAction recoverAction = new RecoverLandAction(playerData, gp, targetWorldX, targetWorldY);
        // return recoverAction.executeAction();
        return true; // Sementara, kembalikan true untuk menandakan aksi berhasil
    }

    @Override
    public String getUseAnimationName(String playerDirection) {
        return "pickaxe_swing_" + playerDirection.toLowerCase();
    }
}