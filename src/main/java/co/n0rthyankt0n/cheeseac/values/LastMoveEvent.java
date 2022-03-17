package co.n0rthyankt0n.cheeseac.values;

import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class LastMoveEvent {
    private Player p;
    private long tick;
    private PlayerMoveEvent e1;
    private CheeseMoveEvent e2;

    public LastMoveEvent(Player p,long tick){
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
