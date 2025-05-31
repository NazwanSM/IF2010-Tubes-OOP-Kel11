package world;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import data.CropsData;
import items.Crops;
import items.Seed;
import main.UtilityTool;

public class CropsPlanted {
    private int x;
    private int y;
    private boolean wateredThisDay;
    private int growthStage;
    private boolean readyToHarvest;
    private Seed seed;
    private Crops crop;
    private BufferedImage seedImage;
    private BufferedImage halfCropImage;
    private BufferedImage fullCropImage;
    private final int GAME_SCALE = 3;
    private int daysUnwatered;
    private boolean needsWateringVisualCue = false;

    public CropsPlanted(int x, int y, Seed seed) {
        this.x = x;
        this.y = y;
        this.seed = seed;
        this.wateredThisDay = true;
        this.growthStage = 0;
        this.readyToHarvest = false;
        this.crop = CropsData.getCropBySeed(seed.getName());
        this.daysUnwatered = 0;
        this.needsWateringVisualCue = false;

        UtilityTool uTool = new UtilityTool();

        this.seedImage = loadImage("/items/growth/" + crop.getName() + "/phase1.png", uTool);
        this.halfCropImage = loadImage("/items/growth/" + crop.getName() + "/phase2.png", uTool);
        this.fullCropImage = loadImage("/items/growth/" + crop.getName() + "/phase3.png", uTool);
    }

    // Constructor for loading from save data
    public CropsPlanted(int x, int y, Seed seed, boolean wateredThisDay, int growthStage, boolean readyToHarvest, int daysUnwatered) {
        this.x = x;
        this.y = y;
        this.seed = seed;
        this.wateredThisDay = wateredThisDay;
        this.growthStage = growthStage;
        this.readyToHarvest = readyToHarvest;
        this.crop = CropsData.getCropBySeed(seed.getName());
        this.daysUnwatered = daysUnwatered;
        // Recalculate needsWateringVisualCue based on loaded data
        this.needsWateringVisualCue = !wateredThisDay && !readyToHarvest && (growthStage > 0); // Simplified for loading
        if (this.crop == null) {
            System.err.println("Error: Crop data not found for seed: " + seed.getName());
        }


        UtilityTool uTool = new UtilityTool();
        if (crop != null) {
            this.seedImage = loadImage("/items/growth/" + crop.getName() + "/phase1.png", uTool);
            this.halfCropImage = loadImage("/items/growth/" + crop.getName() + "/phase2.png", uTool);
            this.fullCropImage = loadImage("/items/growth/" + crop.getName() + "/phase3.png", uTool);
        } else {
            this.seedImage = null;
            this.halfCropImage = null;
            this.fullCropImage = null;
        }
    }

    private BufferedImage loadImage(String imagePath, UtilityTool uTool) {
        BufferedImage originalImage = null;
        try {
            InputStream is = getClass().getResourceAsStream(imagePath);
            if (is == null) {
                return null;
            }
            originalImage = ImageIO.read(is);
            if (originalImage == null) {
                return null;
            }

            int targetWidth = originalImage.getWidth() * GAME_SCALE;
            int targetHeight = originalImage.getHeight() * GAME_SCALE;

            return uTool.scaleImage(originalImage, targetWidth, targetHeight);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Crops getCrop() {
        return crop;
    }

    public boolean isWateredThisDay() {
        return wateredThisDay;
    }

    public void setWateredThisDay(boolean watered) {
        this.wateredThisDay = watered;
        if (watered) {
            this.daysUnwatered = 0;
        }
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public Seed getSeed() {
        return seed;
    }

    public void growOneDay() {
        if (growthStage < seed.getDaysToHarvest()) {
            growthStage++;
        }
        if (growthStage >= seed.getDaysToHarvest()) {
            readyToHarvest = true;
        }
    }

    public void processEndOfDay(boolean isHotWeather, boolean isRainy) {
        if (readyToHarvest || seed == null || crop == null) {
            return;
        }

        boolean canGrowThisDay = false;

        if (isRainy) {
            setWateredThisDay(true);
        }
        if (wateredThisDay) {
            canGrowThisDay = true;
        } else {
            daysUnwatered++;

            if (isHotWeather) {
                if (daysUnwatered < 2) {
                    canGrowThisDay = true;
                } else {
                    canGrowThisDay = false;
                    needsWateringVisualCue = true;
                }
            } else {
                if (daysUnwatered < 1) {
                    canGrowThisDay = true;
                } else {
                    canGrowThisDay = false;
                    needsWateringVisualCue = true;
                }
            }
        }

        if (canGrowThisDay) {
            if (growthStage < seed.getDaysToHarvest()) {
                growthStage++;
            }
            needsWateringVisualCue = false;
        } else if (!readyToHarvest) {

        }

        if (growthStage >= seed.getDaysToHarvest()) {
            readyToHarvest = true;
            needsWateringVisualCue = false;
        }

        this.wateredThisDay = false;
    }

    public boolean isReadyToHarvest() {
        return readyToHarvest;
    }

    public void setReadyToHarvest(boolean readyToHarvest) {
        this.readyToHarvest = readyToHarvest;
    }

    public BufferedImage getCurrentImage() {
        if (this.seed == null) return null;

        if (this.readyToHarvest) {
            return this.fullCropImage;
        } else if (this.growthStage > 0 && this.seed.getDaysToHarvest() > 0 && this.growthStage >= (float)this.seed.getDaysToHarvest() / 2) {
            return this.halfCropImage;
        } else {
            return this.seedImage;
        }
    }

    public boolean needsWateringVisualCue() {
        return needsWateringVisualCue && !readyToHarvest;
    }

    public int getDaysUnwatered() {
        return daysUnwatered;
    }
}