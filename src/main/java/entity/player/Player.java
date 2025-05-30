package entity.player;

import main.GamePanel;
import actions.BuyingAction;
import actions.ChattingAction;
import actions.CookingAction;
import actions.EatingAction;
import actions.FishingAction;
import actions.GiftingAction;
import actions.MarryingAction;
import actions.ProposingAction;
import actions.SellingAction;
import actions.SleepingAction;
import actions.VisitingAction;
import actions.WatchingAction;
import actions.farmActions.FarmingAction;
import actions.farmActions.HarvestingAction;
import actions.farmActions.PlantingAction;
import actions.farmActions.RecoverLandAction;
import actions.farmActions.TillingAction;
import actions.farmActions.WateringAction;
import data.RecipeData;
import entity.npc.NPC;
import items.Items;
import items.Seed;
import statistics.IStatisticTracker;
import java.awt.Point;

public class Player {
    private String name;
    private String gender;
    private int energy;
    private String famName;
    private NPC partner;
    private int gold;
    private Items equppedItem;
    private Inventory inventory;
    public static final int MAX_ENERGY = 100;
    public static final int MIN_ENERGY = -20;
    private IStatisticTracker statisticTracker;
    private GamePanel gp;
    private static int proposingDay;
    private int location;
    public boolean isSleeping = false;
    private long sleepStartTime = 0;
    private final long SLEEP_DURATION = 1300;

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
        this.location = gp.currentMap;
        this.equppedItem = null;

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

    public int getLocation() {
        return location;
    }

    public Items getEquppedItem() {
        return equppedItem;
    }

    public void setEquppedItem(Items equppedItem) {
        this.equppedItem = equppedItem;
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
        statisticTracker.trackCurrentPlayerGold(gold);;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setLocation(int location) {
        this.location = location;
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
                gp.gameState = gp.playState;   
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
            case "mengolah tanah":
            case "tilling":
                Point tilePos = FarmingAction.getTilePlayerIsOn(gp);
                TillingAction mengolahTanah = new TillingAction(this, this.gp, tilePos);
                mengolahTanah.executeAction();
                break;
            case "mengembalikan tanah":
            case "recovering land":
                Point recoverTilePos = FarmingAction.getTilePlayerIsOn(gp);
                RecoverLandAction mengembalikanTanah = new RecoverLandAction(this, this.gp, recoverTilePos);
                mengembalikanTanah.executeAction();
                break;
            case "menyiram tanaman":
            case "watering":
                Point waterTilePos = FarmingAction.getTilePlayerIsOn(gp);
                WateringAction menyiramTanaman = new WateringAction(this, this.gp, waterTilePos);
                menyiramTanaman.executeAction();
                break;
            case "panen":
            case "harvest":
                Point harvestTilePos = FarmingAction.getTilePlayerIsOn(gp);
                HarvestingAction panen = new HarvestingAction(this, this.gp, harvestTilePos);
                panen.executeAction();
                break;
            case "menanam":
            case "planting":
                Seed seedToPlant = (Seed) item;
                Point plantTilePos = FarmingAction.getTilePlayerIsOn(gp);
                PlantingAction menanam = new PlantingAction(this, this.gp, plantTilePos, seedToPlant);
                menanam.executeAction();
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

    public void startSleeping() {
    gp.showingSleepScreen = true;
    sleepStartTime = System.currentTimeMillis();
    }

    public boolean isSleeping() {
        if (!gp.showingSleepScreen) return false;
        
        if (System.currentTimeMillis() - sleepStartTime >= SLEEP_DURATION) {
            gp.showingSleepScreen = false;
            return false;
        }
        return true;
    }
}