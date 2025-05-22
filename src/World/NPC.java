package World;

import java.util.List;
import java.util.Scanner;
import Items.Items;
import entity.player.Player;

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
        this.lovedItems = lovedItems;
        this.likedItems = likedItems;
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

    public void interact(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do with " + npcName + "?");
        System.out.println("1. Chat");
        System.out.println("2. Gift");
        System.out.println("3. Propose");
        System.out.println("4. Marry");

        int choice = -1;
        while (choice < 1 || choice > 4) {
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                player.chatting(this);
            }

            if (choice == 2) {
                System.out.print("Enter the name of the item you want to give: ");
                String itemName = scanner.nextLine();
                Items gift = null;
                for (Items item : player.getInventory().checkInventory().keySet()) {
                    if (gift == null && item.getName().equalsIgnoreCase(itemName)) {
                        gift = item;
                    }
                }
                if (gift != null) {
                    player.gifting(this, gift);
                }
                else {
                    System.out.println("Item not found in your inventory.");
                }
            }

            if (choice == 3) {
                player.propose(this);
            }

            if (choice == 4) {
                player.marry(this);
            }

            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please choose between 1 and 4.");
            }
        }

        scanner.close();
    }
}