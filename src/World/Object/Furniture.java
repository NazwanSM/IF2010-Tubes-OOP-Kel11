package World.Object;
public abstract class Furniture {
    protected String type;

    public Furniture(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void interact();
}