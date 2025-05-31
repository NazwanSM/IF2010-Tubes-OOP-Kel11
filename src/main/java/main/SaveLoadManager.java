package main;

import java.awt.Point;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import custom_adapters.CropsPlantedAdapter;
import custom_adapters.ItemsAdapter;
import custom_adapters.NPCAdapter;
import custom_adapters.PointAdapter;
import data.NPCData;
import data.RecipeData;
import entity.npc.NPC;
import entity.player.Player;
import entity.player.PlayerUI;
import items.Items;
import world.CropsPlanted;
import world.Farm;
import world.TileManager;


public class SaveLoadManager {
    private final GamePanel gp;
    private final Gson gson;
    private final ScheduledExecutorService executor;

    public SaveLoadManager(GamePanel gp) {
        this.gp = gp;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Point.class, new PointAdapter());
        gsonBuilder.registerTypeAdapter(Items.class, new ItemsAdapter());
        gsonBuilder.registerTypeAdapter(NPC.class, new NPCAdapter());
        gsonBuilder.registerTypeAdapter(CropsPlanted.class, new CropsPlantedAdapter());
        this.gson = gsonBuilder.setPrettyPrinting().create();
        this.executor = Executors.newSingleThreadScheduledExecutor();

        NPCAdapter.setGamePanel(gp);
        CropsPlantedAdapter.setGamePanel(gp);
    }

    public void saveGame(String filePath) {
        executor.submit(() -> {
            System.out.println("Attempting to save game to: " + filePath);
            try (FileWriter writer = new FileWriter(filePath)) {
                SaveData data = new SaveData();
                data.playerName = gp.playerData.getName();
                data.playerGender = gp.playerData.getGender();
                data.playerEnergy = gp.playerData.getEnergy();
                data.playerGold = gp.playerData.getGold();
                data.playerWorldX = gp.player.worldX;
                data.playerWorldY = gp.player.worldY;
                data.currentMap = gp.currentMap;
                data.equippedItemName = (gp.playerData.getEquippedItem() != null) ? gp.playerData.getEquippedItem().getName() : null;
                data.inventoryItemQuantities = new HashMap<>();
                data.inventoryItemTypes = new HashMap<>();
                for (Map.Entry<Items, Integer> entry : gp.playerData.getInventory().checkInventory().entrySet()) {
                    data.inventoryItemQuantities.put(entry.getKey().getName(), entry.getValue());
                    data.inventoryItemTypes.put(entry.getKey().getName(), entry.getKey().getType());
                }
                data.proposingDay = Player.getProposingDay();
                data.partnerName = (gp.playerData.getPartner() != null) ? gp.playerData.getPartner().getNPCName() : null;

                if (gp.farm != null) {
                    data.farmName = gp.farm.getFarmName();
                    data.day = gp.farm.getDay();
                    data.currentSeason = gp.farm.getSeason().getCurrentSeason();
                    data.currentSeasonDayCounter = gp.farm.getSeason().getDayInSeason() - 1;
                    data.currentWeather = gp.farm.getWeather().getCurrentWeather();
                    data.rainyDaysThisSeason = gp.farm.getWeather().getRainyDaysThisSeason();
                    data.gameClockHours = gp.farm.getGameClock().getHours();
                    data.gameClockMinutes = gp.farm.getGameClock().getMinutes();
                    data.randomMapIndex = gp.randomMapIndex;
                    data.plantedCrops = gp.farm.getFieldManager().getAllPlantedCrops();
                } else {
                    data.farmName = null;
                    data.day = 0;
                    data.currentSeason = "Spring";
                    data.currentSeasonDayCounter = 0;
                    data.currentWeather = "Sunny";
                    data.rainyDaysThisSeason = 0;
                    data.gameClockHours = 6;
                    data.gameClockMinutes = 0;
                    data.randomMapIndex = 0;
                    data.plantedCrops = null;
                }

                data.npcHeartPoints = new HashMap<>();
                data.npcRelationshipStatus = new HashMap<>();
                for (NPC npc : NPCData.getAllNPC()) {
                    data.npcHeartPoints.put(npc.getNPCName(), npc.getHeartPoints());
                    data.npcRelationshipStatus.put(npc.getNPCName(), npc.getRelationshipStatus());
                }
                
                data.unlockedRecipes = new HashMap<>();
                RecipeData.getAllRecipes().forEach(recipe ->
                    data.unlockedRecipes.put(recipe.getItemID(), recipe.isUnlocked())
                );

                data.totalIncome = gp.manager.getTotalIncome();
                data.totalExpenditure = gp.manager.getTotalExpenditure();
                data.totalDaysPlayed = gp.manager.getTotalDaysPlayed();
                data.npcChatFrequencies = gp.manager.getNPCChatFrequencies();
                data.npcGiftFrequencies = gp.manager.getNPCGiftFrequencies();
                data.npcVisitFrequencies = gp.manager.getNPCVisitFrequencies();
                data.totalCropsHarvested = gp.manager.getTotalCropsHarvested();
                data.totalFishCaught = gp.manager.getTotalFishCaught();
                data.totalCommonFishCaught = gp.manager.getTotalCommonFishCaught();
                data.totalRegularFishCaught = gp.manager.getTotalRegularFishCaught();
                data.totalLegendaryFishCaught = gp.manager.getTotalLegendaryFishCaught();
                data.goldMilestoneAchieved = gp.manager.isGoldMilestoneAchieved();
                data.marriageMilestoneAchieved = gp.manager.isMarriageMilestoneAchieved();
                data.milestoneDays = gp.manager.getMilestoneDays();

                gson.toJson(data, writer);
                writer.flush();
                System.out.println("Data serialized to JSON. File written successfully: " + filePath);
                gp.ui.addMessage("Game saved successfully!");
            } catch (IOException e) {
                System.err.println("IOException during saving to " + filePath + ": " + e.getMessage());
                e.printStackTrace();
                gp.ui.addMessage("Failed to save game: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("General Exception during saving to " + filePath + ": " + e.getMessage());
                e.printStackTrace();
                gp.ui.addMessage("Error during game saving: " + e.getMessage());
            }
        });
    }

    public void loadGame(String filePath) {
        executor.submit(() -> {
            System.out.println("Attempting to load game from: " + filePath);
            try (FileReader reader = new FileReader(filePath)) {
                SaveData data = gson.fromJson(reader, SaveData.class);

                if (data == null) {
                    gp.ui.addMessage("Save file is empty or corrupted.");
                    return;
                }

                String loadedFarmName = (data.farmName != null) ? data.farmName : "Default Farm";
                if (gp.playerData == null) {
                     gp.playerData = new Player(data.playerName, data.playerGender, loadedFarmName, data.playerGold, gp);
                }

                gp.player = new PlayerUI(gp, gp.keyH, gp.playerData);

                NPCData.initialize(gp);

                gp.playerData.setName(data.playerName);
                gp.playerData.setGender(data.playerGender);
                gp.playerData.setEnergy(data.playerEnergy);
                gp.playerData.setGold(data.playerGold);
                gp.player.worldX = data.playerWorldX;
                gp.player.worldY = data.playerWorldY;
                gp.currentMap = data.currentMap;

                gp.playerData.getInventory().checkInventory().clear();
                if (data.inventoryItemQuantities != null && data.inventoryItemTypes != null) {
                    for (Map.Entry<String, Integer> entry : data.inventoryItemQuantities.entrySet()) {
                        String itemName = entry.getKey();
                        int quantity = entry.getValue();
                        String itemType = data.inventoryItemTypes.get(itemName);
                        if (itemType != null) {
                            Items item = ItemsAdapter.getItemInstance(itemName, itemType);
                            if (item != null) {
                                gp.playerData.getInventory().addItem(item, quantity);
                            } else {
                                System.err.println("Warning: Could not recreate item " + itemName + " of type " + itemType);
                            }
                        } else {
                            System.err.println("Warning: Item type not found for " + itemName);
                        }
                    }
                }

                if (data.equippedItemName != null) {
                    gp.playerData.setEquippedItem(gp.playerData.getInventory().getItemByName(data.equippedItemName));
                } else {
                    gp.playerData.setEquippedItem(null);
                }
                Player.setProposingDay(data.proposingDay);
                if (data.partnerName != null) {
                    gp.playerData.setPartner(NPCData.getNPCByName(data.partnerName));
                } else {
                    gp.playerData.setPartner(null);
                }

                if (data.farmName != null) {
                    gp.farm = new Farm(data.farmName, gp.playerData, gp);
                    gp.farm.setDay(data.day);
                    gp.farm.getSeason().setSeason(data.currentSeason);
                    gp.farm.getSeason().setDayCounter(data.currentSeasonDayCounter);
                    gp.farm.getWeather().setWeather(data.currentWeather);
                    gp.farm.getWeather().setRainyDaysThisSeason(data.rainyDaysThisSeason);
                    gp.farm.getGameClock().setHours(data.gameClockHours);
                    gp.farm.getGameClock().setMinutes(data.gameClockMinutes);
                    gp.randomMapIndex = data.randomMapIndex;
                    gp.tileM = new TileManager(gp, gp.randomMapIndex);
                } else {
                    System.err.println("Warning: Farm data not found in save file. Setting up a new farm with default values.");
                    gp.farm = new Farm("Default Farm", gp.playerData, gp);
                    gp.farm.setDay(1);
                    gp.farm.getSeason().setSeason("Summer");
                    gp.farm.getWeather().setWeather("Sunny");
                    gp.farm.getGameClock().skipToMorning();
                    gp.tileM = new TileManager(gp, gp.randomMapIndex);
                    gp.aSetter.setObject(gp.randomMapIndex);
                    gp.aSetter.setNPC();
                    gp.eHandler = new EventHandler(gp, gp.randomMapIndex); // Inisialisasi EventHandler di sini jika farm baru
                    gp.lightingSystem.resetDay();
                }

                for (Map.Entry<String, Integer> entry : data.npcHeartPoints.entrySet()) {
                    NPC npc = NPCData.getNPCByName(entry.getKey());
                    if (npc != null) {
                        npc.setHeartPoints(entry.getValue());
                    }
                }
                for (Map.Entry<String, String> entry : data.npcRelationshipStatus.entrySet()) {
                    NPC npc = NPCData.getNPCByName(entry.getKey());
                    if (npc != null) {
                        npc.setRelationshipStatus(entry.getValue());
                    }
                }

                if (gp.farm != null && gp.farm.getFieldManager() != null) {
                    gp.farm.getFieldManager().setPlantedCrops(data.plantedCrops);
                }
                
                for (Map.Entry<String, Boolean> entry : data.unlockedRecipes.entrySet()) {
                    RecipeData.getRecipeById(entry.getKey()).setUnlocked(entry.getValue());
                }

                gp.manager.setTotalIncome(data.totalIncome);
                gp.manager.setTotalExpenditure(data.totalExpenditure);
                gp.manager.setTotalDaysPlayed(data.totalDaysPlayed);
                gp.manager.setNpcChatFrequencies(data.npcChatFrequencies);
                gp.manager.setNpcGiftFrequencies(data.npcGiftFrequencies);
                gp.manager.setNpcVisitFrequencies(data.npcVisitFrequencies);
                gp.manager.setTotalCropsHarvested(data.totalCropsHarvested);
                gp.manager.setTotalFishCaught(data.totalFishCaught);
                gp.manager.setTotalCommonFishCaught(data.totalCommonFishCaught);
                gp.manager.setTotalRegularFishCaught(data.totalRegularFishCaught);
                gp.manager.setTotalLegendaryFishCaught(data.totalLegendaryFishCaught);
                gp.manager.setGoldMilestoneAchieved(data.goldMilestoneAchieved);
                gp.manager.setMarriageMilestoneAchieved(data.marriageMilestoneAchieved);
                gp.manager.setMilestoneDays(data.milestoneDays);

                gp.aSetter.setObject(gp.randomMapIndex);
                gp.aSetter.setNPC();

                // --- START NEW FIX: Initialize gp.eHandler only if farm data was loaded ---
                // If farm data was loaded (data.farmName != null), then gp.randomMapIndex is from save.
                // Otherwise, it was set up by the "new farm" block.
                if (data.farmName != null) { // Only re-initialize if it was loaded from save.
                    gp.eHandler = new EventHandler(gp, gp.randomMapIndex); //
                }
                // --- END NEW FIX ---

                gp.gameState = gp.playState;
                gp.ui.addMessage("Game loaded successfully!");
                System.out.println("Game loaded successfully from: " + filePath);
            } catch (IOException e) {
                System.err.println("IOException during loading from " + filePath + ": " + e.getMessage());
                e.printStackTrace();
                gp.ui.addMessage("Failed to load game: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("General Exception during loading from " + filePath + ": " + e.getMessage());
                e.printStackTrace();
                gp.ui.addMessage("Error during game loading: " + e.getMessage());
            }
        });
    }

    public void shutdownExecutor() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}