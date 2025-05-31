package statistics;

import java.util.List;
import java.util.Map; // Added import

public interface IStatisticProvider {

    long getTotalIncome();
    long getTotalExpenditure();
    double getAverageSeasonalIncome();
    double getAverageSeasonalExpenditure();
    int getTotalDaysPlayed();

    int getNPCChatFrequency(String npcName);
    int getNPCGiftFrequency(String npcName);
    int getNPCVisitFrequency(String npcName);
    List<String> getTrackedNPCNames();

    // Added methods to get the full frequency maps
    Map<String, Integer> getNPCChatFrequencies();
    Map<String, Integer> getNPCGiftFrequencies();
    Map<String, Integer> getNPCVisitFrequencies();

    int getTotalCropsHarvested();

    int getTotalFishCaught();
    int getTotalCommonFishCaught();
    int getTotalRegularFishCaught();
    int getTotalLegendaryFishCaught();

    boolean isGoldMilestoneAchieved();
    boolean isMarriageMilestoneAchieved();
    boolean isAnyMilestoneAchieved();
}