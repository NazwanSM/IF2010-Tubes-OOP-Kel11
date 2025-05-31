package statistics;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.FishRarity;
import items.Fish;

public class StatisticsManager implements IStatisticTracker, IStatisticProvider {
    
    private long totalIncome = 0;
    private long totalExpenditure = 0;
    private int totalDaysPlayed = 0;

    private final Map<String, Integer> npcChatFrequencies;
    private final Map<String, Integer> npcGiftFrequencies;
    private final Map<String, Integer> npcVisitFrequencies;
    private final List<String> internalNpcNameList;

    private int totalCropsHarvested = 0;

    private int totalFishCaught = 0;
    private int totalCommonFishCaught = 0;
    private int totalRegularFishCaught = 0;
    private int totalLegendaryFishCaught = 0;

    // Milestone
    private boolean goldMilestoneAchieved = false;
    private boolean marriageMilestoneAchieved = false;
    private final int GOLD_MILESTONE_TARGET = 17209; 
    private final int DAYS_PER_SEASON = 10;
    private int milestoneDays = 0;
    public List<String> allNPCNames = List.of(
            "Mayor Tadi", "Caroline", "Perry", "Dasco" , "Emily" , "Abigail"
        );

    public StatisticsManager() {
        this.npcChatFrequencies = new HashMap<>();
        this.npcGiftFrequencies = new HashMap<>();
        this.npcVisitFrequencies = new HashMap<>();
        this.internalNpcNameList = allNPCNames;

        for (String npcName : this.internalNpcNameList) {
            this.npcChatFrequencies.put(npcName, 0);
            this.npcGiftFrequencies.put(npcName, 0);
            this.npcVisitFrequencies.put(npcName, 0);
        }
    }

    public int getMilestoneDays() {
        return this.milestoneDays;
    }

    public void setMilestoneDays(int milestoneDays) {
        this.milestoneDays = milestoneDays;
    }

    @Override
    public void trackIncome(int amount) {
        if (amount > 0) this.totalIncome += amount;
    }

    @Override
    public void trackExpenditure(int amount) {
        if (amount > 0) this.totalExpenditure += amount;
    }

    @Override
    public void trackDayPlayed() {
        this.totalDaysPlayed++;
    }

    @Override
    public void trackNPCChat(String npcName) {
        this.npcChatFrequencies.compute(npcName, (key, val) -> (val == null) ? 1 : val + 1);
    }

    @Override
    public void trackNPCGift(String npcName) {
        this.npcGiftFrequencies.compute(npcName, (key, val) -> (val == null) ? 1 : val + 1);
    }

    @Override
    public void trackNPCVisit(String npcName) {
        this.npcVisitFrequencies.compute(npcName, (key, val) -> (val == null) ? 1 : val + 1);
    }

    @Override
    public void trackCropHarvested(String cropType, int quantity) {
        if (quantity > 0) this.totalCropsHarvested += quantity;
    }

    @Override
    public void trackFishCaught(Fish fish, FishRarity rarity) {
        this.totalFishCaught++;
        if (rarity == FishRarity.COMMON) {
            this.totalCommonFishCaught++;
        } else if (rarity == FishRarity.REGULAR) {
            this.totalRegularFishCaught++;
        } else if (rarity == FishRarity.LEGENDARY) {
            this.totalLegendaryFishCaught++;
        }
    }

    @Override
    public void trackPlayerMarriageStatus(boolean isMarried) {
        if (isMarried) {
            this.marriageMilestoneAchieved = true;
        }
    }

    @Override
    public void trackCurrentPlayerGold(int currentGold) {
        if (currentGold >= GOLD_MILESTONE_TARGET) {
            this.goldMilestoneAchieved = true;
        }
    }

    @Override
    public long getTotalIncome() { return this.totalIncome; }

    @Override
    public long getTotalExpenditure() { return this.totalExpenditure; }

    @Override
    public double getAverageSeasonalIncome() {
        if (this.totalDaysPlayed == 0) return 0.0;
        double numberOfSeasons = Math.max(1.0, (double)this.totalDaysPlayed / DAYS_PER_SEASON);
        return this.totalIncome / numberOfSeasons;
    }

    @Override
    public double getAverageSeasonalExpenditure() {
        if (this.totalDaysPlayed == 0) return 0.0;
        double numberOfSeasons = Math.max(1.0, (double)this.totalDaysPlayed / DAYS_PER_SEASON);
        return this.totalExpenditure / numberOfSeasons;
    }

    @Override
    public int getTotalDaysPlayed() { return this.totalDaysPlayed; }

    @Override
    public int getNPCChatFrequency(String npcName) {
        return this.npcChatFrequencies.getOrDefault(npcName, 0);
    }

    @Override
    public int getNPCGiftFrequency(String npcName) {
        return this.npcGiftFrequencies.getOrDefault(npcName, 0);
    }

    @Override
    public int getNPCVisitFrequency(String npcName) {
        return this.npcVisitFrequencies.getOrDefault(npcName, 0);
    }

    @Override
    public Map<String, Integer> getNPCChatFrequencies() { // Added method
        return Collections.unmodifiableMap(this.npcChatFrequencies);
    }

    @Override
    public Map<String, Integer> getNPCGiftFrequencies() { // Added method
        return Collections.unmodifiableMap(this.npcGiftFrequencies);
    }

    @Override
    public Map<String, Integer> getNPCVisitFrequencies() { // Added method
        return Collections.unmodifiableMap(this.npcVisitFrequencies);
    }

    @Override
    public List<String> getTrackedNPCNames() {
        return Collections.unmodifiableList(this.internalNpcNameList);
    }

    @Override
    public int getTotalCropsHarvested() { return this.totalCropsHarvested; }

    @Override
    public int getTotalFishCaught() { return this.totalFishCaught; }

    @Override
    public int getTotalCommonFishCaught() { return this.totalCommonFishCaught; }

    @Override
    public int getTotalRegularFishCaught() { return this.totalRegularFishCaught; }

    @Override
    public int getTotalLegendaryFishCaught() { return this.totalLegendaryFishCaught; }

    @Override
    public boolean isGoldMilestoneAchieved() { return this.goldMilestoneAchieved; }

    @Override
    public boolean isMarriageMilestoneAchieved() { return this.marriageMilestoneAchieved; }

    @Override
    public boolean isAnyMilestoneAchieved() {
        return this.goldMilestoneAchieved || this.marriageMilestoneAchieved;
    }

    // Setters for loading data
    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalExpenditure(long totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }

    public void setTotalDaysPlayed(int totalDaysPlayed) {
        this.totalDaysPlayed = totalDaysPlayed;
    }

    public void setNpcChatFrequencies(Map<String, Integer> npcChatFrequencies) {
        this.npcChatFrequencies.clear();
        this.npcChatFrequencies.putAll(npcChatFrequencies);
    }

    public void setNpcGiftFrequencies(Map<String, Integer> npcGiftFrequencies) {
        this.npcGiftFrequencies.clear();
        this.npcGiftFrequencies.putAll(npcGiftFrequencies);
    }

    public void setNpcVisitFrequencies(Map<String, Integer> npcVisitFrequencies) {
        this.npcVisitFrequencies.clear();
        this.npcVisitFrequencies.putAll(npcVisitFrequencies);
    }

    public void setTotalCropsHarvested(int totalCropsHarvested) {
        this.totalCropsHarvested = totalCropsHarvested;
    }

    public void setTotalFishCaught(int totalFishCaught) {
        this.totalFishCaught = totalFishCaught;
    }

    public void setTotalCommonFishCaught(int totalCommonFishCaught) {
        this.totalCommonFishCaught = totalCommonFishCaught;
    }

    public void setTotalRegularFishCaught(int totalRegularFishCaught) {
        this.totalRegularFishCaught = totalRegularFishCaught;
    }

    public void setTotalLegendaryFishCaught(int totalLegendaryFishCaught) {
        this.totalLegendaryFishCaught = totalLegendaryFishCaught;
    }

    public void setGoldMilestoneAchieved(boolean goldMilestoneAchieved) {
        this.goldMilestoneAchieved = goldMilestoneAchieved;
    }

    public void setMarriageMilestoneAchieved(boolean marriageMilestoneAchieved) {
        this.marriageMilestoneAchieved = marriageMilestoneAchieved;
    }
}