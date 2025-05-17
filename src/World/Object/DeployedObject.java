public abstract class DeployedObject {
    protected String name;       
    protected String type;       
    protected int width;         
    protected int length;        

    public DeployedObject(String name, String type, int width, int length) {
        this.name = name;
        this.type = type;
        this.width = width;
        this.length = length;
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

    public abstract void interact();
}