package entity.npc;

import java.awt.image.BufferedImage;
import java.util.List;
import main.GamePanel;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public abstract class NPC {
    protected String npcName;
    protected int heartPoints;
    protected List<String> lovedItems;
    protected List<String> likedItems;
    protected List<String> hatedItems;
    protected String relationshipStatus;
    protected BufferedImage npcImage;
    protected GamePanel gp;
    public boolean collision = false;
    public int worldX, worldY;
    public int width = 1, height = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 1, 1);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    protected static final int MAX_HEART_POINTS = 150;

    public  NPC(String npcName, List<String> lovedItems, List<String> likedItems, List<String> hatedItems, GamePanel gp) {
        this.npcName = npcName;
        this.lovedItems = lovedItems != null ? lovedItems : List.of();
        this.likedItems = likedItems != null ? likedItems : List.of();
        this.hatedItems = hatedItems;
        this.relationshipStatus = "Single";
        this.heartPoints = 0;
        this.gp = gp;
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
            return npcName + " smiles warmly: \"You're someone I really \ncare about.\"";
        }
        else if (heartPoints >= 60) {
            return npcName + " says: \"It's nice spending time with you.\"";
        }
        else if (heartPoints >= 30) {
            return npcName + " nods: \"Oh, hey. Need something?\"";
        }
        else {
            return npcName + " avoids eye contact: \n" + "...Yes? Do I know you?\n";
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

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX * gp.tileSize - gp.player.worldX + gp.player.screenX;
        int screenY = worldY * gp.tileSize - gp.player.worldY + gp.player.screenY;

        g2.drawImage(
            npcImage,
            screenX,
            screenY,
            gp.tileSize * width,    // width dalam pixel
            gp.tileSize * height,   // height dalam pixel
            null
        );
    }

    abstract public void interact();
}