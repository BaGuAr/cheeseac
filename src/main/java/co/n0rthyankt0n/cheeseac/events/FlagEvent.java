package co.n0rthyankt0n.cheeseac.events;

import co.n0rthyankt0n.cheeseac.utils.Violations;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FlagEvent extends Event {


    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private Player player;
    private String check;
    private String type;
    private String checkPath;

    public FlagEvent(Player p,String checkPath,boolean c){
        this.cancelled = c;
        this.player = p;
        this.checkPath = checkPath;
        this.check = this.checkPath.split( "\\." )[0];
        this.type = this.checkPath.split( "\\." )[1];
    }

    public int getVL(){
        return Violations.vioJustGet( player );
    }

    @Deprecated
    public void resetVL(){
        Violations.vioReset( player );
    }

    public Player getPlayer(){
        return player;
    }

    public String getCheck(){
        return check;
    }

    public String getType(){
        return type;
    }

    public String getCheckPath(){
        return checkPath;
    }


    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

}
