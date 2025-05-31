package world;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import items.Crops;
import items.Seed;
import main.GamePanel;

public class FieldManager {
    private Map<Point, CropsPlanted> plantedCrops;
    private GamePanel gp;
    
    public FieldManager(GamePanel gp) {
        plantedCrops = new HashMap<>();
        this.gp = gp;
    }
    
    public boolean plantCrop(int x, int y, Seed seed) {
        Point position = new Point(x, y);
        
        if (plantedCrops.containsKey(position)) {
            return false;
        }
        
        CropsPlanted cropsPlanted = new CropsPlanted(x, y, seed);
        plantedCrops.put(position, cropsPlanted);
        return true;
    }
    
    public boolean waterCrop(int x, int y) {
        Point position = new Point(x, y);
        
        if (!plantedCrops.containsKey(position)) {
            return false;
        }
        
        CropsPlanted cropsPlanted = plantedCrops.get(position);
        cropsPlanted.setWateredThisDay(true);
        return true;
    }
    
    public Crops harvestCrop(int x, int y) {
        Point position = new Point(x, y);
        
        if (!plantedCrops.containsKey(position)) {
            return null;
        }
        
        CropsPlanted cropsPlanted = plantedCrops.get(position);

        if (!cropsPlanted.isReadyToHarvest()) {
            return null;
        }
        
        Crops harvest = cropsPlanted.getCrop();
        
        plantedCrops.remove(position);
        return harvest;
    }
    
    public boolean hasCropAt(int x, int y) {
        return plantedCrops.containsKey(new Point(x, y));
    }
    
    public boolean isCropWatered(int x, int y) {
        Point position = new Point(x, y);
        if (!plantedCrops.containsKey(position)) {
            return false;
        }
        return plantedCrops.get(position).isWateredThisDay();
    }
    
    public boolean isCropReadyToHarvest(int x, int y) {
        Point position = new Point(x, y);
        if (!plantedCrops.containsKey(position)) {
            return false;
        }
        return plantedCrops.get(position).isReadyToHarvest();
    }
    
    public boolean doesTileNeedWateringVisual(int x, int y) {
        Point position = new Point(x, y);
        CropsPlanted crop = plantedCrops.get(position);
        if (crop != null) {
            return crop.needsWateringVisualCue();
        }
        return false;
    }

    public void processNewDay() {
        if (gp == null || gp.farm == null || gp.farm.getWeather() == null || gp.farm.getSeason() == null) {
            return;
        }

        String currentWeather = gp.farm.getWeather().getCurrentWeather();
        String currentSeason = gp.farm.getSeason().getCurrentSeason();

        boolean isHotWeather = currentWeather.equalsIgnoreCase("Sunny");
        boolean isRainy = gp.farm.getWeather().isRainy();

        Iterator<Map.Entry<Point, CropsPlanted>> iterator = plantedCrops.entrySet().iterator();
        List<String> deadCropNames = new ArrayList<>();

        while (iterator.hasNext()) {
            Map.Entry<Point, CropsPlanted> entry = iterator.next();
            CropsPlanted crop = entry.getValue();
            if (crop.getSeed() != null && crop.getSeed().getSeason() != null && !crop.getSeed().getSeason().contains(currentSeason)) {
                deadCropNames.add(crop.getCrop().getName());
                iterator.remove();
            } else {
                crop.processEndOfDay(isHotWeather, isRainy);
            }
        }

        for (String cropName : deadCropNames) {
            gp.ui.addMessage(cropName + " has withered due to season change.");
        }
    }


    public Collection<CropsPlanted> getAllPlantedCrops() {
        return plantedCrops.values();
    }

    public void setPlantedCrops(Collection<CropsPlanted> crops) {
        this.plantedCrops.clear();
        for (CropsPlanted crop : crops) {
            this.plantedCrops.put(new Point(crop.getX(), crop.getY()), crop);
        }
    }
}