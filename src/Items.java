public abstract class Items {
    protected String name;
    protected String type;
    protected int quantity;

    public Items(String name, String type, int quantity) {
        this.name = name;
        this.type = type;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Items items = (Items) obj;
        return name.equals(items.name); // Bandingkan berdasarkan nama
    }

    @Override
    public int hashCode() {
        return name.hashCode(); // Gunakan hashCode dari nama
    }
}