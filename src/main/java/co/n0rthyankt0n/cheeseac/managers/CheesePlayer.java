package co.n0rthyankt0n.cheeseac.managers;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import org.bukkit.entity.Player;

public class CheesePlayer{

    private Player player;
    public CheesePlayer(Player p){
        this.player = p;
    }

    public Player getPlayer(){
        return player;
    }

    public boolean canBypass(){
        return CheeseAC.bypass && player.hasPermission( CheeseAC.bypassPerm );
    }

}
