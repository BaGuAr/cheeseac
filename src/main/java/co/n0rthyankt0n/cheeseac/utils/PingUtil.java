package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PingUtil {

    public static int getPing(Player p){
        if( CheeseAC.ping) {
            return ( (CraftPlayer) p ).getHandle( ).ping;
        }
        return 0;
    }
}
