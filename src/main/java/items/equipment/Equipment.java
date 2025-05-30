package items.equipment; 

import items.Items;       

public abstract class Equipment extends Items {

    private String description;

    public Equipment(String name, String description) {
        super(name, "Equipment");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}