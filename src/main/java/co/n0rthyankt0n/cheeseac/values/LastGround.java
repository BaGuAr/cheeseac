package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Player;

public class LastGround {
    private Player p;
    private long tick;

    public LastGround(Player p,long tick){
        this.p = p;
        this.tick = tick;
    }

    public long getTick(){
        return tick;
    }

    public Player getPlayer(){
        return p;
    }
}
