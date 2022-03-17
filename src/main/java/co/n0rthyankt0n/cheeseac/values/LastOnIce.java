package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Player;

public class LastOnIce {

    private Player p;
    private long tick;

    public LastOnIce(Player player,long tick){
        this.p = player;
        this.tick = tick;
    }

    public long getTick(){
        return tick;
    }

    public Player getPlayer(){
        return p;
    }
}
