package items;

import entity.player.Player;

public interface Edible {
    int getEnergy();
    void eat(Player player);
}
