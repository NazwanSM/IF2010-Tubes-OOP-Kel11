public class Tiles {
    private String state; // "land", "tilled", "planted", "wet"
    private Seed plantedSeed;
    private DeployedObject object;
    private boolean isWet; // status tile basah karena Rainy

    public Tiles() {
        this.state = "land";
        this.plantedSeed = null;
        this.object = null;
        this.isWet = false;
    }

    // --- State ---
    public String getState() {
        return state;
    }

    public void setState(String state) {
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

    public void setWet(boolean wet) {
        this.isWet = wet;
    }

    public void dry() {
        this.isWet = false;
    }

    // --- Interaksi validasi ---
    public boolean isTillable() {
        return state.equals("land") && !isOccupied();
    }

    public boolean isTilled() {
        return state.equals("tilled");
    }

    public boolean isPlantable() {
        return isTilled();
    }

    public boolean isPlanted() {
        return state.equals("planted") && plantedSeed != null;
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
