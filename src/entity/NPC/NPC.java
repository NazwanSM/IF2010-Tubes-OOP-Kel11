package entity.NPC;

import java.util.List;

import entity.player.Player;
import main.UI;

public class NPC {
    private String npcName;
    private int heartPoints;
    private List<String> lovedItems;
    private List<String> likedItems;
    private List<String> hatedItems;
    private String relationshipStatus;
    private static final int MAX_HEART_POINTS = 150;

    public NPC(String npcName, List<String> lovedItems, List<String> likedItems, List<String> hatedItems) {
        this.npcName = npcName;
        this.lovedItems = lovedItems != null ? lovedItems : List.of();
        this.likedItems = likedItems != null ? likedItems : List.of();
        this.hatedItems = hatedItems;
        this.relationshipStatus = "Single";
        this.heartPoints = 0;
    }

    public String getNPCName() {
        return npcName;
    }

    public int getHeartPoints() {
        return heartPoints;
    }

    public List<String> getLovedItems() {
        return lovedItems;
    }

    public List<String> getLikedItems() {
        return likedItems;
    }

    public List<String> getHatedItems() {
        return hatedItems;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public String getDialogue() {
        if (relationshipStatus.equals("Married")) {
            return npcName + " smiles: \"I'm happy to spend every day with you.\"";
        }

        if (relationshipStatus.equals("Engaged")) {
            if (heartPoints == 150) {
                return npcName + " beams: \"I can't wait to marry you!\"";
            }
            else {
                return npcName + " says softly: \"I'm looking forward to our wedding... once we're ready.\"";
            }
        }

        if (heartPoints == 150) {
            return npcName + " looks at you with admiration: \"I feel like I can trust you with anything...\"";
        }
        else if (heartPoints >= 100) {
            return npcName + " smiles warmly: \"You're someone I really care about.\"";
        }
        else if (heartPoints >= 60) {
            return npcName + " says: \"It's nice spending time with you.\"";
        }
        else if (heartPoints >= 30) {
            return npcName + " nods: \"Oh, hey. Need something?\"";
        }
        else {
            return npcName + " avoids eye contact: \"...Yes? Do I know you?\"";
        }
    }

    public void setNPCName(String npcName) {
        this.npcName = npcName;
    }

    public void setLovedItems(List<String> lovedItems) {
        this.lovedItems = lovedItems;
    }

    public void setLikedItems(List<String> likedItems) {
        this.likedItems = likedItems;
    }

    public void setHatedItems(List<String> hatedItems) {
        this.hatedItems = hatedItems;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public void increaseHeartPoints(int amount) {
        if (heartPoints + amount <= MAX_HEART_POINTS) {
            heartPoints += amount;
        }
        else {
            heartPoints = MAX_HEART_POINTS;
        }
    }

    public void decreaseHeartPoints(int amount) {
        if (heartPoints - amount >= 0) {
            heartPoints -= amount;
        }
        else {
            heartPoints = 0;
        }
    }

    /**
     * Untuk sekarang, kita akan biarkan ini sebagai placeholder,
     * karena interaksi akan lebih banyak di-drive oleh Player dan UI.
     */
    public void startInteractionSequence(Player playerLogic, UI gameUI) { // Menggunakan Player data logic dan UI
        // Daripada Scanner, saatnya menggunakan sistem dialog GUI Anda.
        // 1. PlayerUI mendeteksi pemain dekat NPCUI dan menekan tombol aksi.
        // 2. GamePanel/PlayerUI mendapatkan referensi ke AbstractNPCLogic ini.
        // 3. Panggil metode yang sesuai di Player.java, misalnya player.chatting(this),
        //    yang kemudian akan mengambil dialog dari this.getDialogue().
        // 4. Dialog tersebut ditampilkan melalui gameUI.curretDialog dan gp.gameState = gp.dialogueState [cite: 5]

        // Contoh: Jika aksi 'chat' dipilih (melalui GUI nantinya):
        // String dialogue = getDialogue();
        // gameUI.curretDialog = dialogue;
        // gameUI.gp.gameState = gameUI.gp.dialogueState; // Mengatur game state untuk menampilkan dialog

        System.out.println("Interaksi dengan " + npcName + " dimulai (Versi Logika). UI akan menangani tampilan.");
        // Logika untuk menampilkan pilihan (Chat, Gift, etc.) akan ada di UI / Game State Manager Anda
    }

    // Anda bisa menambahkan metode abstrak di sini jika ada perilaku yang *harus*
    // diimplementasikan secara unik oleh setiap NPC turunan.
    // public abstract void performUniqueDailyRoutine();
}