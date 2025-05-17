package World.Object;
import World.Point;

public class House {
    private String name;
    private String type;
    private int width;
    private int length;
    private Point position;

    public House(String name, String type, int width, int length, Point position) {
        this.name = name;
        this.type = type;
        this.width = width;
        this.length = length;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}
