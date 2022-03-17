package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import org.bukkit.entity.Player;

public class BypassUtil {

    public static boolean canBypass(Player p ){
        return CheeseAC.bypass && p.hasPermission( CheeseAC.bypassPerm );
    }
}
