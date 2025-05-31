package main;

import java.util.Collection;
import java.util.Map;

import world.CropsPlanted;

// This class acts as a data transfer object for saving and loading game state.
public class SaveData {
    // Player data
    public String playerName;
    public String playerGender;
    public int playerEnergy;
    public int playerGold;
    public int playerWorldX;
    public int playerWorldY;
    public int currentMap;
    public String equippedItemName;
    // --- START MODIFICATION ---
    public Map<String, Integer> inventoryItemQuantities; // Store item name -> quantity
    public Map<String, String> inventoryItemTypes; // Store item name -> type for reconstruction
    // --- END MODIFICATION ---
    public int proposingDay;
    public String partnerName;

    // Farm data
    public String farmName;
    public int day;
    public String currentSeason;
    public int currentSeasonDayCounter;
    public String currentWeather;
    public int rainyDaysThisSeason;
    public int gameClockHours;
    public int gameClockMinutes;
    public int randomMapIndex;

    // NPC data
    public Map<String, Integer> npcHeartPoints;
    public Map<String, String> npcRelationshipStatus;

    // Crops data
    public Collection<CropsPlanted> plantedCrops;

    // Recipe data
    public Map<String, Boolean> unlockedRecipes;

    // Statistics data
    public long totalIncome;
    public long totalExpenditure;
    public int totalDaysPlayed;
    public Map<String, Integer> npcChatFrequencies;
    public Map<String, Integer> npcGiftFrequencies;
    public Map<String, Integer> npcVisitFrequencies;
    public int totalCropsHarvested;
    public int totalFishCaught;
    // Added missing fields for fish rarity counts
    public int totalCommonFishCaught;
    public int totalRegularFishCaught;
    public int totalLegendaryFishCaught;
    public boolean goldMilestoneAchieved;
    public boolean marriageMilestoneAchieved;
    public int milestoneDays;
}