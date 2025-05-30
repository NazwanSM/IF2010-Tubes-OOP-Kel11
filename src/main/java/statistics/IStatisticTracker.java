package statistics;

import data.FishRarity;
import items.Fish;

public interface IStatisticTracker {

    void trackIncome(int amount);

    void trackExpenditure(int amount);

    void trackDayPlayed();

    void trackNPCChat(String npcName);

    void trackNPCGift(String npcName);

    void trackNPCVisit(String npcName);

    void trackCropHarvested(String cropType, int quantity);

    public void trackFishCaught(Fish fish, FishRarity rarity);

    void trackPlayerMarriageStatus(boolean isMarried);

    void trackCurrentPlayerGold(int currentGold);
}