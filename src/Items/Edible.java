package Items;

import Player.Player;

public interface Edible {
    int getEnergy();
    void eat(Player player);
}
