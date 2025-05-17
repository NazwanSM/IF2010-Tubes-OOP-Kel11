public class PLayer {
    private String name;
    private String gender;
    private int energy;
    private String famName;
    private NPC partner; // Blom dibkin class NPCnya
    private int gold;
    private Inventory inventory;
    private Point location;
    private static final int MAX_ENERGY = 100;
    private static final int MIN_ENERGY = -20;

    public Player(String name, String gender, String famName, int gold) {
        this.name = name;
        this.gender = gender;
        this.energy = MAX_ENERGY;
        this.famName = famName;
        this.partner = null;
        this.gold = gold;
        this.inventory = new Inventory(); // Blom dibkin class inventorynya
        this.location = new Point(0, 0); // Blom dibkin class pointnya
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getEnergy() {
        return energy;
    }

    public String getFamName() {
        return famName;
    }

    public NPC getPartner() {
        return partner;
    }

    public int getGold() {
        return gold;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Point getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEnergy(int energy) { // ini masih bisa diganti nyesuain sama sleeping dll
        if (energy > MAX_ENERGY) {
            this.energy = MAX_ENERGY;
        } else if (energy < MIN_ENERGY) {
            this.energy = MIN_ENERGY;
        } else {
            this.energy = energy;
        }
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public void setPartner(NPC partner) {
        this.partner = partner;
    }

    public void setGold(int gold) {  
        if (gold < 0) {
            this.gold = 0;
        } else {
            this.gold = gold;
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void increaseEnergy(int amount) {
        setEnergy(this.energy + amount);
    }

    public void decreaseEnergy(int amount) {
        setEnergy(this.energy - amount);
    }

    public void addGold(int amount) { // ini masih harus disesuain sama statistik akhir
        setGold(this.gold + amount);
    }

    public void subtractGold(int amount) { // ini masih harus disesuain sama statistik akhir
        setGold(this.gold - amount);
    }

    public boolean hasItem(Items item) {
        return inventory.hasItem(item); // harus diimplementasi di class inventory juga
    }

    public void addItemToInventory(Items item) {
        inventory.addItem(item); // harus diimplementasi di class inventory juga
    }

    public void removeItemFromInventory(Items item) {
        inventory.removeItem(item); // harus diimplementasi di class inventory juga
    }

    public void eating (Items eadible) {
        if (eadible instanceof Edible) { 
            Edible edibleItem = (Edible) eadible;
            if (hasItem(edibleItem)) {
                edibleItem.eat(this); // harus diimplementasi di class edible 
                removeItemFromInventory(edibleItem);
                increaseEnergy(eadibleItem.getEnergy()); // harus diimplementasi di class edible
            } else {
                System.out.println("You don't have this item in your inventory."); // implementasi ini blom tentu dipake
            }
        } 
        else {
            System.out.println("This item is not edible."); // ini juga
        }   
    }

    public void chatting(NPC npc) {
        System.out.println("Chatting with " + npc.getName() + ": " + npc.getDialogue()); // harus diimplementasi di class NPC
        npc.increseaseHeartPoint(10); // harus diimplementasi di class NPC
        decreaseEnergy(10); // ini juga harus disesuain sama class NPC
        // abis ini harus ada fungsi buat ngitung statistik berapa kali chatting dll
    }

    public void gifting(NPC npc, Items gift) {
        if (hasItem(gift)) {
            if (npc.getLovedItems().contains(gift)) {
                npc.increaseHeartPoint(25); 
            }
            else if (npc.getLikedItems().contains(gift)) {
                npc.increaseHeartPoint(20); 
            }
            else if (npc.getHatedItems().contains(gift)) {
                npc.decreaseHeartPoint(25); 
            }

            System.out.println("Giving " + gift.getName() + " to " + npc.getName() + "."); // harus diimplementasi di class NPC
            removeItemFromInventory(gift);
            decreaseEnergy(5);
        } else {
            System.out.println("You don't have this item in your inventory."); // implementasi ini blom tentu dipake
        }
    }

    public void moving(Point newLocation) {
        this.location = newLocation; 
        System.out.println("Moved to new location: " + newLocation); // implementasi ini blom tentu dipake
    }


    public void playerSleeping() { // ini cuman buat ngatur energy doang, waktu dan lain lain diimplementasi di class lain
        if (energy <= 0){
            setEnergy(10);
        }
        else if (energy < MAX_ENERGY/10) {
            setEnergy(MAX_ENERGY/2);
        }
        else {
            setEnergy(MAX_ENERGY);
        }
        System.out.println("Player is sleeping. Energy restored to: " + energy); // implementasi ini blom tentu dipake
    }

    public void openInventory() {
        System.out.println("Opening inventory: " + inventory); // implementasi ini blom tentu dipake
        inventory.displayItems(); // harus diimplementasi di class inventory
    }

    public void showTime() {
        System.out.println("Current time: " + gameTime.getTime()); // harus diimplementasi di class GameTime
    }

    public void showLocation() {
        System.out.println("Current location: " + location); // implementasi ini blom tentu dipake
    }


    public boolean isProposeable(NPC npc) {
        int heartPoint = npc.getHeartPoint(); // harus diimplementasi di class NPC
        String relationshipStatus = npc.getRelationshipStatus(); // harus diimplementasi di class NPC

        if (partner == null && heartPoint == 150 && relationshipStatus.equals("single")) {
            return true; // bisa diimplementasi di class NPC
        } else {
            System.out.println("You are already in a relationship with " + partner.getName() + "."); // implementasi ini blom tentu dipake
            return false;
        }
    }

    public void propose(NPC npc) {
        if (isProposeable(npc)) {
            partner = npc; // harus diimplementasi di class NPC
            npc.setRelationshipStatus("fiance"); // harus diimplementasi di class NPC
            gifting(npc, new Ring("Wedding Ring", "ring")); // harus disesuain sama class ring nanti
            decreaseEnergy(10);
            System.out.println("You proposed to " + npc.getName() + "."); // implementasi ini blom tentu dipake
        } else {
            decreaseEnergy(20);
            System.out.println("You are rejected"); // implementasi ini blom tentu dipake
        }
    }

    public boolean isMarriable(NPC npc) {
        if (partner != null && partner.equals(npc) && partner.getRelationshipStatus().equals("fiance")) {
            return true; // bisa diimplementasi di class NPC
        } else {
            System.out.println("You are not engaged to " + npc.getName() + "."); // implementasi ini blom tentu dipake
            return false;
        }
    }

    public void marry(NPC npc) { // ini masih banyak yang harus disesuain kyk mekanik menghabiskan waktu sampe time skip
        if (isMarriable(npc)) {
            partner.setRelationshipStatus("married"); // harus diimplementasi di class NPC
            decreaseEnergy(80);
            System.out.println("You are now married to " + partner.getName() + "."); // implementasi ini blom tentu dipake
        } else {
            System.out.println("You are not engaged to " + npc.getName() + "."); // implementasi ini blom tentu dipake
        }
    }   
}