package co.n0rthyankt0n.cheeseac.events;

import co.n0rthyankt0n.cheeseac.utils.Violations;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PreFlagEvent extends Event implements Cancellable {


    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Player player;
    private String check;
    private String type;
    private String checkPath;

    public PreFlagEvent(Player p,String checkPath){
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


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
