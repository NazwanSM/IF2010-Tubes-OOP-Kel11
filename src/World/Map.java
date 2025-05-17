package World;

public abstract class Map {
    protected String name;         // Nama peta
    protected Tiles[][] tiles;     // Matriks tiles untuk peta
    protected int mapWidth;        // Lebar peta
    protected int mapLength;       // Panjang peta
 
    // Konstruktor untuk menginisialisasi peta dengan nama dan dimensi
    public Map(String name, int mapWidth, int mapLength) {
        this.name = name;
        this.mapWidth = mapWidth;
        this.mapLength = mapLength;
        this.tiles = new Tiles[mapWidth][mapLength];  // Inisialisasi matriks tiles sesuai dimensi
    }

    // Getter dan Setter untuk atribut peta
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tiles[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tiles[][] tiles) {
        this.tiles = tiles;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapLength() {
        return mapLength;
    }

    public void setMapLength(int mapLength) {
        this.mapLength = mapLength;
    }
}