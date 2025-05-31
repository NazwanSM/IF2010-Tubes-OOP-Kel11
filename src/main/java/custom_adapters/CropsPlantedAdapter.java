package custom_adapters;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import world.CropsPlanted;
import items.Seed;
import data.SeedData;
import main.GamePanel; // Needed for CropsPlanted constructor

import java.awt.Point;
import java.lang.reflect.Type;

public class CropsPlantedAdapter implements JsonSerializer<CropsPlanted>, JsonDeserializer<CropsPlanted> {

    private static GamePanel staticGp; // Static GamePanel reference

    // Method to set the GamePanel instance
    public static void setGamePanel(GamePanel gp) {
        staticGp = gp;
    }

    @Override
    public JsonElement serialize(CropsPlanted src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", src.getX());
        jsonObject.addProperty("y", src.getY());
        jsonObject.addProperty("seedName", src.getSeed().getName()); // Save seed by name
        jsonObject.addProperty("wateredThisDay", src.isWateredThisDay());
        jsonObject.addProperty("growthStage", src.getGrowthStage());
        jsonObject.addProperty("readyToHarvest", src.isReadyToHarvest());
        jsonObject.addProperty("daysUnwatered", src.getDaysUnwatered()); // Added daysUnwatered
        return jsonObject;
    }

    @Override
    public CropsPlanted deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (staticGp == null) {
            throw new JsonParseException("GamePanel not set for CropsPlantedAdapter, cannot deserialize CropsPlanted.");
        }

        JsonObject jsonObject = json.getAsJsonObject();
        int x = jsonObject.get("x").getAsInt();
        int y = jsonObject.get("y").getAsInt();
        String seedName = jsonObject.get("seedName").getAsString();
        boolean wateredThisDay = jsonObject.get("wateredThisDay").getAsBoolean();
        int growthStage = jsonObject.get("growthStage").getAsInt();
        boolean readyToHarvest = jsonObject.get("readyToHarvest").getAsBoolean();
        int daysUnwatered = jsonObject.get("daysUnwatered").getAsInt(); // Deserialize daysUnwatered

        Seed seed = SeedData.getSeedByName(seedName);
        if (seed == null) {
            throw new JsonParseException("Seed not found: " + seedName);
        }

        // Use the constructor that allows setting all states
        // Pass the staticGp here
        CropsPlanted cropsPlanted = new CropsPlanted(x, y, seed, wateredThisDay, growthStage, readyToHarvest, daysUnwatered);
        return cropsPlanted;
    }
}