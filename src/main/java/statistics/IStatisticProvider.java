package statistics;

import java.util.List;

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

    int getTotalCropsHarvested();

    int getTotalFishCaught();
    int getTotalCommonFishCaught();
    int getTotalRegularFishCaught();
    int getTotalLegendaryFishCaught();

    boolean isGoldMilestoneAchieved();
    boolean isMarriageMilestoneAchieved();
    boolean isAnyMilestoneAchieved();
}