package actions;

import main.GamePanel;;
public abstract class Action {
    protected String name;
    protected int energyRequired;
    protected int timeRequired;
    protected GamePanel gp;

    public Action(String name, int energyRequired, int timeRequired, GamePanel gp) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.timeRequired = timeRequired;
        this.gp = gp;
    }

    public String getName() {
        return name;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergyRequired(int energyRequired) {
        if (energyRequired < 0) {
            this.energyRequired = 0;
        } else {
            this.energyRequired = energyRequired;
        }
    }

    public void setTimeRequired(int timeRequired) {
        if (timeRequired < 0) {
            this.timeRequired = 0;
        } else {
            this.timeRequired = timeRequired;
        }
    }

    public abstract boolean executeAction();
}
