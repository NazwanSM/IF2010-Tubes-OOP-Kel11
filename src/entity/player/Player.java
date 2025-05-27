package entity.player;

import entity.NPC.NPC;
import World.Environment.GameClock;
import actions.*;
import data.RecipeData;
import items.Items;
import statistics.IStatisticTracker;
import main.GamePanel;
public class Player {
    private String name;
    private String gender;
    private int energy;
    private String famName;
    private NPC partner;
    private int gold;
    private Inventory inventory;
    private GameClock gameClock;
    public static final int MAX_ENERGY = 100;
    public static final int MIN_ENERGY = -20;
    private IStatisticTracker statisticTracker;
    private GamePanel gp;

    public Player(String name, String gender, String famName, int gold, GamePanel gp) {
        this.name = name;
        this.gender = gender;
        this.energy = MAX_ENERGY;
        this.famName = famName;
        this.partner = null;
        this.gold = gold;
        this.inventory = new Inventory();
        this.statisticTracker = gp.statisticTracker;
        this.gp = gp;

        DefaultInventoryInitializer inventoryInitializer = new DefaultInventoryInitializer();
        inventoryInitializer.setDefaultItems(this.inventory);
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getEnergy() {
        return energy;
    }

    public String getFamName() {
        return famName;
    }

    public NPC getPartner() {
        return partner;
    }

    public int getGold() {
        return gold;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnergy(int energy) { 
        if (energy > MAX_ENERGY) {
            this.energy = MAX_ENERGY;
        } else if (energy <= MIN_ENERGY) {
            performAction("Sleep", null);
        } else {
            this.energy = energy;
        }
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public void setPartner(NPC partner) {
        this.partner = partner;
    }

    public void setGold(int gold) {  
        if (gold < 0) {
            this.gold = 0;
        } else {
            this.gold = gold;
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public void increaseEnergy(int amount) {
        setEnergy(this.energy + amount);
    }

    public void decreaseEnergy(int amount) {
        setEnergy(this.energy - amount);
    }

    public void addGold(int amount) {
        setGold(this.gold + amount);
        statisticTracker.trackIncome(amount);
    }

    public void subtractGold(int amount) { 
        setGold(this.gold - amount);
        if (amount > 0) {
            statisticTracker.trackExpenditure(amount);
        }
    }

    public boolean hasItem(Items item) {
        return inventory.hasItem(item); 
    }

    public void addItemToInventory(Items item, int amount) {
        inventory.addItem(item, amount); 
    }

    public void removeItemFromInventory(Items item, int amount) {
        inventory.removeItem(item, amount); 
    }

    public void performAction(String actionName, String parameter) {
        switch (actionName.toLowerCase()) { 
            case "tidur":
            case "sleep":
                SleepingAction tidur = new SleepingAction(this, this.gp);
                tidur.executeAction();
                break;
            case "makan":
            case "eat":
                Items itemToEat = inventory.getItemByName(parameter);
                EatingAction makan = new EatingAction(this, this.gp, itemToEat);
                makan.executeAction();
                break;
            case "mancing":
            case "fishing":
                FishingAction mancing = new FishingAction(this, this.gp);
                mancing.executeAction();
                break;
            case "mengunjungi":
            case "visit":
                VisitingAction kunjungi = new VisitingAction(parameter, this.gp);
                kunjungi.executeAction();
                break;
            case "memasak":
            case "cook":
                CookingAction memasak = new CookingAction(this, RecipeData.getRecipeById(parameter), RecipeData.getRecipeById(parameter).getResult() ,this.gp);
                memasak.executeAction();
                break;
        }
    }



    public void chatting(NPC npc) {
        System.out.println("Chatting with " + npc.getNPCName() + ": " + npc.getDialogue());
        npc.increaseHeartPoints(10);
        decreaseEnergy(10);
        gameClock.advance(10);
        
        this.statisticTracker.trackNPCChat(npc.getNPCName());
    }

    public void gifting(NPC npc, Items gift) {
        if (inventory.hasItem(gift)) {
            if (npc.getLovedItems().contains(gift.getName())) {
                npc.increaseHeartPoints(25); 
            }
            else if (npc.getLikedItems().contains(gift.getName())) {
                npc.increaseHeartPoints(20); 
            }
            else if (npc.getHatedItems().contains(gift.getName())) {
                npc.decreaseHeartPoints(25); 
            }

            System.out.println("Giving " + gift.getName() + " to " + npc.getNPCName() + ".");
            removeItemFromInventory(gift, 1); // asumsi amount of gift selalu 1, nanti diganti kalo ada perubahan

            gameClock.advance(10);
            decreaseEnergy(5);
        } else {
            System.out.println("You don't have this item in your inventory."); // implementasi ini blom tentu dipake
        }
    }

    public boolean isProposeable(NPC npc) {
        if (inventory.getItemByName("Proposal Ring") == null) {
            System.out.println("You need a Proposal Ring to propose.");
            return false;
        }
        int heartPoint = npc.getHeartPoints();
        String relationshipStatus = npc.getRelationshipStatus();

        if (partner == null && heartPoint == 150 && relationshipStatus.equalsIgnoreCase("single")) {
            return true;
        } else {
            if (partner != null) {
                System.out.println("You are already in a relationship with " + partner.getNPCName() + ".");
            } else if (!relationshipStatus.equalsIgnoreCase("single")) {
                System.out.println(npc.getNPCName() + " is not single.");
            } else {
                System.out.println(npc.getNPCName() + "'s heart points are not enough.");
            }
            return false;
        }
    }

    public void propose(NPC npc) {
        gameClock.advance(60);
        if (isProposeable(npc)) {
            partner = npc;
            npc.setRelationshipStatus("Fiance");
            Items proposalRing = inventory.getItemByName("Proposal Ring");
            gifting(npc, proposalRing);
            decreaseEnergy(10);
            System.out.println("You proposed to " + npc.getNPCName() + ".");
        } else {
            decreaseEnergy(20);
            System.out.println("You are rejected");
        }
    }

    public boolean isMarriable(NPC npc) {
        if (partner != null && partner.equals(npc) && partner.getRelationshipStatus().equalsIgnoreCase("fiance")) {
            return true;
        } else {
            System.out.println("You are not engaged to " + npc.getNPCName() + ".");

            return false;
        }
    }

    public void marry(NPC npc) {
        if (isMarriable(npc)) {
            partner.setRelationshipStatus("Married");
            decreaseEnergy(80);

            int currentHour = gameClock.getHours();
            int currentMinute = gameClock.getMinutes();
            int targetHour = 22;
            int targetMinute = 0;

            int currentTotalMinutes = currentHour * 60 + currentMinute;
            int targetTotalMinutes = targetHour * 60 + targetMinute;
            int minutesToAdvance = targetTotalMinutes - currentTotalMinutes;

            if (minutesToAdvance < 0) {
                minutesToAdvance += 24 * 60; // skip ke 22:00 hari berikutnya (opsional)
            }

            gameClock.advance(minutesToAdvance);

            System.out.println("You are now married to " + partner.getNPCName() + ".");
            System.out.println("Time has skipped to 22:00. You are back home.");
        } else {
            System.out.println("You are not engaged to " + npc.getNPCName() + ".");
        }
    }
}