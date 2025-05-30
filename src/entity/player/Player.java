package entity.player;

import entity.NPC.NPC;
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
    public static final int MAX_ENERGY = 100;
    public static final int MIN_ENERGY = -20;
    private IStatisticTracker statisticTracker;
    private GamePanel gp;
    private static int proposingDay;

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

    public static int getProposingDay() {
        return proposingDay;
    }

    public static void setProposingDay(int proposingDay) {
        Player.proposingDay = proposingDay;
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
            performAction("Sleep", null, null);
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

    public void performAction(String actionName, String parameter, Items item) {
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
            case "menonton":
            case "watch":
                WatchingAction menonton = new WatchingAction(this, this.gp);
                menonton.executeAction();
                break;
            case "berbincang":
            case "chat":
                NPC npcToChat = gp.npcs[gp.currentMap][0];
                ChattingAction chatting = new ChattingAction(this, npcToChat, this.gp);
                chatting.executeAction();
                break;
            case "memberi hadiah":
            case "gifting":
                NPC npcToGift = gp.npcs[gp.currentMap][0];
                Items giftItem = inventory.getItemByName(parameter);
                GiftingAction gifting = new GiftingAction(this, npcToGift, this.gp, giftItem);
                gifting.executeAction();
                break;
            case "melamar":
            case "propose":
                NPC npcToPropose = gp.npcs[gp.currentMap][0];
                ProposingAction proposing = new ProposingAction(this, npcToPropose, this.gp);
                proposing.executeAction();
                break;
            case "menikah":
            case "marry":
                NPC npcToMarry = gp.npcs[gp.currentMap][0];
                MarryingAction marrying = new MarryingAction(this, npcToMarry, this.gp);
                marrying.executeAction();
                gp.gameState = gp.playState;
                break;
            case "menjual":
            case "sell":
                SellingAction menjual = new SellingAction(this, item, this.gp);
                menjual.executeAction();
                break;
            case "membeli":
            case "buy":
                BuyingAction membeli = new BuyingAction(this, item, this.gp, Integer.parseInt(parameter));
                membeli.executeAction();
                break;
        }
    }

    public boolean isProposeable(NPC npc) {
        if (inventory.getItemByName("Proposal Ring") == null) {
            return false;
        }
        int heartPoint = npc.getHeartPoints();
        String relationshipStatus = npc.getRelationshipStatus();

        if (partner == null && heartPoint == 150 && relationshipStatus.equalsIgnoreCase("single") && inventory.getItemByName("Proposal Ring") != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMarriable(NPC npc) {
        if (partner != null && partner.equals(npc) && partner.getRelationshipStatus().equalsIgnoreCase("fiance") && proposingDay < gp.farm.getDay()) {
            return true;
        } else {
            return false;
        }
    }
}