package World;

import java.util.Random;
import World.Object.DeployedObject;
import World.Object.House;
import World.Object.Pond;
import World.Object.ShippingBin;
import World.Environment.Weather;

public class FarmMap extends Map {
    // Remove unused field or use it
    // private Point playerPosition;
    private final static int MAP_SIZE = 32; // Renamed and will use instead of hardcoded values
    private int playerX, playerY;  // Posisi pemain di peta

    // Konstruktor FarmMap yang menerima nama peta, lebar, dan panjang peta
    public FarmMap(String name) {
        super(name, MAP_SIZE, MAP_SIZE);  // Use the constant instead of hardcoded values
        initializeFarm();  // Inisialisasi peta dengan objek dan tempatkan pemain
    }

    // Inisialisasi peta dengan objek yang diperlukan (seperti rumah, kolam, dll)
    private void initializeFarm() {
        // Inisialisasi seluruh tile dengan objek default
        for (int i = 0; i < getMapWidth(); i++) {
            for (int j = 0; j < getMapLength(); j++) {
                getTiles()[i][j] = new Tiles();  // Mengisi semua tiles dengan objek default
            }
        }
        
        // Fix: Provide proper constructor arguments for House
        House house = new House("Player House");
        placeDeployedObject(house, 6, 6);
        
        // Fix: Provide proper constructor arguments for Pond
        Pond pond = new Pond("Farm Pond");
        placeDeployedObjectRandom(pond);
        
        placeShippingBinNearHouse();
        placePlayerNearHouse();  // Tempatkan pemain di dekat rumah
    }

    // Modified method to place object at specific location
    private void placeDeployedObject(DeployedObject obj, int x, int y) {
        if (canPlaceObjectAt(x, y, obj)) {
            placeObjectAt(x, y, obj);
        }
    }

    // Metode untuk menempatkan objek (seperti Pond, dll) secara acak pada peta
    private void placeDeployedObjectRandom(DeployedObject obj) {
        Random rand = new Random();
        boolean placed = false;
        while (!placed) {
            int x = rand.nextInt(getMapWidth() - obj.getWidth());
            int y = rand.nextInt(getMapLength() - obj.getLength());
            if (canPlaceObjectAt(x, y, obj)) {
                placeObjectAt(x, y, obj);
                placed = true;
            }
        }
    }

    // Memeriksa apakah objek bisa ditempatkan di posisi tertentu pada peta
    private boolean canPlaceObjectAt(int x, int y, DeployedObject obj) {
        for (int i = 0; i < obj.getWidth(); i++) {
            for (int j = 0; j < obj.getLength(); j++) {
                if (x + i >= getMapWidth() || y + j >= getMapLength() || getTiles()[x + i][y + j].isOccupied()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Menempatkan objek di posisi yang diberikan pada peta
    private void placeObjectAt(int x, int y, DeployedObject obj) {
        for (int i = 0; i < obj.getWidth(); i++) {
            for (int j = 0; j < obj.getLength(); j++) {
                if (x + i < getMapWidth() && y + j < getMapLength()) {
                    getTiles()[x + i][y + j].setObject(obj);
                }
            }
        }
    }

    // Menempatkan Shipping Bin dekat rumah
    private void placeShippingBinNearHouse() {
        for (int i = 0; i < getMapWidth(); i++) {
            for (int j = 0; j < getMapLength(); j++) {
                DeployedObject obj = getTiles()[i][j].getObject();
                if (obj != null && obj instanceof House) {
                    // Tempatkan Shipping Bin di sebelah kanan rumah
                    int x = i, y = j + 6;
                    // Needs to make ShippingBin extend DeployedObject
                    ShippingBin bin = new ShippingBin();
                    // Assuming ShippingBin inherits from DeployedObject and has proper dimensions
                    if (y + 2 < getMapLength() && canPlaceObjectAt(x, y, bin)) {
                        placeObjectAt(x, y, bin);
                        return;
                    }
                }
            }
        }
    }

    // Menempatkan pemain di dekat rumah
    private void placePlayerNearHouse() {
        for (int i = 0; i < getMapWidth(); i++) {
            for (int j = 0; j < getMapLength(); j++) {
                DeployedObject obj = getTiles()[i][j].getObject();
                if (obj != null && obj instanceof House) {
                    playerX = i + 2;  // Tempatkan pemain sedikit ke samping rumah
                    playerY = j + 2;
                    return;
                }
            }
        }
    }

    // Rest of the class remains the same
    public void display() {
        for (int i = 0; i < getMapWidth(); i++) {
            for (int j = 0; j < getMapLength(); j++) {
                if (i == playerX && j == playerY) {
                    System.out.print("p ");  // Tampilkan 'p' untuk posisi pemain
                } else {
                    System.out.print(getTiles()[i][j].getDisplaySymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    public void makeLandWetIfRainy(Weather weather) {
        if (weather.isRainy()) {
            for (int i = 0; i < getMapWidth(); i++) {
                for (int j = 0; j < getMapLength(); j++) {
                    getTiles()[i][j].setWet();  // Set tile menjadi basah jika hujan
                }
            }
        }
    }

    public boolean isAtEdge() {
        return playerX == 0 || playerY == 0 || playerX == getMapWidth() - 1 || playerY == getMapLength() - 1;
    }

    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX >= 0 && newY >= 0 && newX < getMapWidth() && newY < getMapLength() && !getTiles()[newX][newY].isOccupied()) {
            playerX = newX;
            playerY = newY;
        }
    }
}