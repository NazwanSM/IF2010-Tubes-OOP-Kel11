package actions;

import main.GamePanel;

public class VisitingAction extends Action {
    
    String location;
    public VisitingAction(String location, GamePanel gp) {
        super("Visit ", 10, 15, gp );
        this.location = location;
    }

    @Override
    public boolean executeAction() {

        if (gp.playerData.getEnergy() < -10) {
            gp.ui.addMessage("You don't have enough energy to visit " + location + "!");
            return false;
        }
        else if (gp.playerData.getEnergy() == -10) {
            gp.playerData.decreaseEnergy(energyRequired);
            return false;
        }
        gp.playerData.decreaseEnergy(energyRequired);
        gp.farm.getGameClock().advance(timeRequired);

        if (location.equals("Forest River")){
            gp.eHandler.teleport(2, 18, 14);
            gp.ui.addMessage("You are visiting the Forest River.");
            return true;
        } else if (location.equals("Mountain Lake")) {
            gp.eHandler.teleport(3, 16, 11);
            gp.ui.addMessage("You are visiting the Mountain Lake.");
            return true;
        } else if (location.equals("Ocean")) {
            gp.eHandler.teleport(4, 16, 11);
            gp.ui.addMessage("You are visiting the Ocean.");
            return true;
        } else if (location.equals("Store")) {
            gp.eHandler.teleport(5, 13, 11);
            gp.ui.addMessage("You are visiting the Store.");
            return true;
        } else if (location.equals("Mayor Tadi House")) {
            gp.eHandler.teleport(6, 16, 16);
            gp.ui.addMessage("You are visiting Mayor Tadi's House.");
            gp.manager.trackNPCVisit("Mayor Tadi");
            return true;
        } else if (location.equals("Caroline House")) {
            gp.eHandler.teleport(7, 13, 15);
            gp.ui.addMessage("You are visiting Caroline's House.");
            gp.manager.trackNPCVisit("Caroline");
            return true;
        } else if (location.equals("Perry House")) {
            gp.eHandler.teleport(8, 18, 15);
            gp.ui.addMessage("You are visiting Perry's House.");
            gp.manager.trackNPCVisit("Perry");
            return true;
        } else if (location.equals("Dasco House")) {
            gp.eHandler.teleport(9, 18, 14);
            gp.ui.addMessage("You are visiting Dasco's House.");
            gp.manager.trackNPCVisit("Dasco");
            return true;
        } else if (location.equals("Abigail House")) {
            gp.eHandler.teleport(10, 16, 16);
            gp.ui.addMessage("You are visiting Abigail's House.");
            gp.manager.trackNPCVisit("Abigail");
            return true;
        } else {
            gp.ui.addMessage("Unknown location: " + location);
            return false;
        }
    }
}
