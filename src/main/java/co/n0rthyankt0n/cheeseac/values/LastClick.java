package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Player;

public class LastClick {


    private long tick;
    private Player p;

    public LastClick(Player p,long tick){
        this.tick = tick;
        this.p = p;
    }

    public long getTick(){
        return tick;
    }

    public Player getPlayer(){
        return p;
    }
}
