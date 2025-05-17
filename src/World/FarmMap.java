package World;

import java.util.Random;
import World.Object.DeployedObject;
import World.Environment.Weather;

public class FarmMap extends Map {
    private Point playerPosition;
    private final int SIZE = 32; // Ukuran peta (32x32 tiles)
    private int playerX, playerY;  // Posisi pemain di peta

    // Konstruktor FarmMap yang menerima nama peta, lebar, dan panjang peta
    public FarmMap(String name) {
        super(name, 32, 32);  // Memanggil konstruktor Map untuk menginisialisasi peta dengan ukuran default 32x32
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
        // Menempatkan objek-objek penting di peta seperti rumah, kolam, dan shipping bin
        placeDeployedObjectRandom(new House());
        placeDeployedObjectRandom(new Pond());
        placeShippingBinNearHouse();
        placePlayerNearHouse();  // Tempatkan pemain di dekat rumah
    }

    // Metode untuk menempatkan objek (seperti House, Pond, dll) secara acak pada peta
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
                if (getTiles()[x + i][y + j].isOccupied()) {
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
                getTiles()[x + i][y + j].setObject(obj);
            }
        }
    }

    // Menempatkan Shipping Bin dekat rumah
    private void placeShippingBinNearHouse() {
        for (int i = 0; i < getMapWidth(); i++) {
            for (int j = 0; j < getMapLength(); j++) {
                if (getTiles()[i][j].getObject() instanceof House) {
                    // Tempatkan Shipping Bin di sebelah kanan rumah
                    int x = i, y = j + 6;
                    if (y + 2 < getMapLength() && canPlaceObjectAt(x, y, new ShippingBin())) {
                        placeObjectAt(x, y, new ShippingBin());
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
                if (getTiles()[i][j].getObject() instanceof House) {
                    playerX = i + 2;  // Tempatkan pemain sedikit ke samping rumah
                    playerY = j + 2;
                    return;
                }
            }
        }
    }

    // Metode untuk menampilkan peta di konsol
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

    // Periksa apakah pemain sudah berada di tepi peta
    public boolean isAtEdge() {
        return playerX == 0 || playerY == 0 || playerX == getMapWidth() - 1 || playerY == getMapLength() - 1;
    }

    // Metode untuk memindahkan pemain di peta
    public void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        if (newX >= 0 && newY >= 0 && newX < getMapWidth() && newY < getMapLength() && !getTiles()[newX][newY].isOccupied()) {
            playerX = newX;
            playerY = newY;
        }
    }
}