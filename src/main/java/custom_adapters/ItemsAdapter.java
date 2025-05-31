package custom_adapters;

import java.lang.reflect.Type;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext; // Added import for TypeToken
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import data.CropsData; // Import all data managers
import data.EquipmentData;
import data.FishData;
import data.FishRarity;
import data.FoodData;
import data.MiscData;
import data.SeedData;
import items.ConcreteItem;
import items.Crops;
import items.Fish;
import items.Food;
import items.Items;
import items.Misc;
import items.Seed;
import items.equipment.Equipment;
import items.equipment.FishingRod;
import items.equipment.Hoe;
import items.equipment.Pickaxe;
import items.equipment.WateringCan;
public class ItemsAdapter implements JsonSerializer<Items>, JsonDeserializer<Items> {

    @Override
    public JsonElement serialize(Items src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("type", src.getType());

        // Add type-specific properties for correct deserialization
        if (src instanceof Crops) {
            Crops crop = (Crops) src;
            jsonObject.addProperty("buyPrice", crop.getBuyPrice());
            jsonObject.addProperty("sellPrice", crop.getSellPrice());
            jsonObject.addProperty("quantityPerHarvest", crop.getQuantityPerHarvest());
        } else if (src instanceof Fish) {
            Fish fish = (Fish) src;
            jsonObject.addProperty("rarity", fish.getRarity().toString());
            jsonObject.add("seasons", context.serialize(fish.getSeason()));
            jsonObject.addProperty("startHour", fish.getStartHour());
            jsonObject.addProperty("endHour", fish.getEndHour());
            jsonObject.add("weathers", context.serialize(fish.getWeather()));
            jsonObject.add("locations", context.serialize(fish.getLocation()));
        } else if (src instanceof Food) {
            Food food = (Food) src;
            jsonObject.addProperty("energy", food.getEnergy());
            jsonObject.addProperty("buyPrice", food.getBuyPrice());
            jsonObject.addProperty("sellPrice", food.getSellPrice());
        } else if (src instanceof Misc) {
            Misc misc = (Misc) src;
            jsonObject.addProperty("buyPrice", misc.getBuyPrice());
            jsonObject.addProperty("sellPrice", misc.getSellPrice());
        } else if (src instanceof Seed) {
            Seed seed = (Seed) src;
            jsonObject.add("seasons", context.serialize(seed.getSeason()));
            jsonObject.addProperty("daysToHarvest", seed.getDaysToHarvest());
            jsonObject.addProperty("buyPrice", seed.getBuyPrice());
            jsonObject.addProperty("sellPrice", seed.getSellPrice());
        } else if (src instanceof Equipment) {
            Equipment equipment = (Equipment) src;
            jsonObject.addProperty("description", equipment.getDescription());
        } else if (src instanceof ConcreteItem) {
            // No extra properties for ConcreteItem needed for now
        }
        return jsonObject;
    }

    @Override
    public Items deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        String type = jsonObject.get("type").getAsString();

        return getItemInstance(name, type, jsonObject, context);
    }

    public static Items getItemInstance(String name, String type) {
        // This is a simplified version for getting item instances without full context
        // Used by InventoryAdapter
        switch (type) {
            case "Crop": return CropsData.getCropByName(name);
            case "Fish": return FishData.getFishByName(name);
            case "Food": return FoodData.getFoodByName(name);
            case "Misc": return MiscData.getMiscByName(name);
            case "Seed": return SeedData.getSeedByName(name);
            case "Equipment": return EquipmentData.getEquipmentByName(name);
            case "ConcreteItem": return new ConcreteItem(name, type);
            default: return null;
        }
    }

    private Items getItemInstance(String name, String type, JsonObject jsonObject, JsonDeserializationContext context) {
        switch (type) {
            case "Crop":
                int cropBuyPrice = jsonObject.get("buyPrice").getAsInt();
                int cropSellPrice = jsonObject.get("sellPrice").getAsInt();
                int quantityPerHarvest = jsonObject.get("quantityPerHarvest").getAsInt();
                return new Crops(name, cropBuyPrice, cropSellPrice, quantityPerHarvest);
            case "Fish":
                FishRarity rarity = FishRarity.valueOf(jsonObject.get("rarity").getAsString());
                Set<String> seasons = context.deserialize(jsonObject.get("seasons"), new TypeToken<Set<String>>(){}.getType());
                int startHour = jsonObject.get("startHour").getAsInt();
                int endHour = jsonObject.get("endHour").getAsInt();
                Set<String> weathers = context.deserialize(jsonObject.get("weathers"), new TypeToken<Set<String>>(){}.getType());
                Set<String> locations = context.deserialize(jsonObject.get("locations"), new TypeToken<Set<String>>(){}.getType());
                return new Fish(name, rarity, seasons, startHour, endHour, weathers, locations);
            case "Food":
                int energy = jsonObject.get("energy").getAsInt();
                int foodBuyPrice = jsonObject.get("buyPrice").getAsInt();
                int foodSellPrice = jsonObject.get("sellPrice").getAsInt();
                return new Food(name, energy, foodBuyPrice, foodSellPrice);
            case "Misc":
                int miscBuyPrice = jsonObject.get("buyPrice").getAsInt();
                int miscSellPrice = jsonObject.get("sellPrice").getAsInt();
                return new Misc(name, miscBuyPrice, miscSellPrice);
            case "Seed":
                Set<String> seedSeasons = context.deserialize(jsonObject.get("seasons"), new TypeToken<Set<String>>(){}.getType());
                int daysToHarvest = jsonObject.get("daysToHarvest").getAsInt();
                int seedBuyPrice = jsonObject.get("buyPrice").getAsInt();
                int seedSellPrice = jsonObject.get("sellPrice").getAsInt();
                return new Seed(name, seedSeasons, daysToHarvest, seedBuyPrice, seedSellPrice);
            case "Equipment":
                String description = jsonObject.get("description").getAsString();
                // Need to instantiate specific equipment types
                if ("Hoe".equals(name)) return new Hoe(name, description);
                if ("Watering Can".equals(name)) return new WateringCan(name, description);
                if ("Pickaxe".equals(name)) return new Pickaxe(name, description);
                if ("Fishing Rod".equals(name)) return new FishingRod(name, description);
                return null; // Fallback for unknown equipment
            case "ConcreteItem": return new ConcreteItem(name, type);
            default: return null;
        }
    }
}