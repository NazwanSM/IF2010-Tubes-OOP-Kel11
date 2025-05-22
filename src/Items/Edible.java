package Items;

import entity.player.Player;

public interface Edible {
    int getEnergy();
    void eat(Player player);
}
