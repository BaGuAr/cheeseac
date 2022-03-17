package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Player;

public class LastFallDamage {

    private Player p;
    private long tick;
    boolean can;

    public LastFallDamage(Player p,long tick,boolean can){
        this.p = p;
        this.tick = tick;
        this.can = can;
    }

    public long getTick(){
        return tick;
    }

    public boolean isCancelled(){ return can;}

    public Player getPlayer(){
        return p;
    }
}
