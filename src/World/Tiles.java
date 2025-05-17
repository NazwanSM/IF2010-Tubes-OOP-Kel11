package World;

import Items.Seed;
import Enum.TileState;
import World.Object.DeployedObject;

public class Tiles {
    private TileState state; // "tillable", "tilled", "planted", "wet"
    private Seed plantedSeed;
    private DeployedObject object;
    private boolean isWet; // status tile basah karena Rainy

    public Tiles() {
        this.state = TileState.TILLABLE;
        this.plantedSeed = null;
        this.object = null;
        this.isWet = false;
    }

    // --- State ---
    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }

    // --- Seed ---
    public Seed getPlantedSeed() {
        return plantedSeed;
    }

    public void setPlantedSeed(Seed plantedSeed) {
        this.plantedSeed = plantedSeed;
    }

    // --- Object ---
    public DeployedObject getObject() {
        return object;
    }

    public void setObject(DeployedObject object) {
        this.object = object;
    }

    public boolean isOccupied() {
        return object != null;
    }

    // --- Wet Status (digunakan saat cuaca Rainy) ---
    public boolean isWet() {
        return isWet;
    }

    public void setWet() {
        if (state.equals(TileState.TILLABLE) || state.equals(TileState.TILLED) || state.equals(TileState.PLANTED)) {
            this.state = TileState.WET;  // Mengubah status menjadi "wet" jika kondisi cocok
        }
    }

    public void dry() {
        this.isWet = false;
    }

    // --- Interaksi validasi ---
    public boolean isTillable() {
        return state.equals(TileState.TILLABLE) && !isOccupied();
    }

    public boolean isTilled() {
        return state.equals(TileState.TILLED);
    }

    public boolean isPlantable() {
        return isTilled();
    }

    public boolean isPlanted() {
        return state.equals(TileState.PLANTED) && plantedSeed != null;
    }

    public boolean isHarvestable() {
        return isPlanted() && plantedSeed.isReadyToHarvest();
    }

    // --- Untuk rendering peta (FarmMap.display()) ---
    public char getDisplaySymbol() {
        if (object != null) return object.getSymbol().charAt(0);
        if (isPlanted()) return 'l';
        if (isTilled()) return 't';
        return '.';
    }
}
