package World.Object;
import Actions.Fishing;
import Player.Player;
import World.Environment.GameClock;
import World.Environment.Season;
import World.Environment.Weather;



public class Pond extends DeployedObject {
    public Pond(String name) {
        super(name, "Pond", 4, 3);
    }

    public void interact(Player player) {
        System.out.println("You interact with the pond.");
        Season season = Season.getInstance();
        Weather weather = Weather.getInstance();
        GameClock gameClock = GameClock.getInstance();
        
        Fishing fishing = new Fishing(season, weather, player, gameClock);
        fishing.startFishing();
    }
}
