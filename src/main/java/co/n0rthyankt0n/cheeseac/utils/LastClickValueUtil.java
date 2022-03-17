package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.listeners.ClickListener;
import co.n0rthyankt0n.cheeseac.values.LastClick;
import org.bukkit.entity.Player;

public class LastClickValueUtil {

    public static boolean isNearLastClick(Player p){
        LastClick lc = ClickListener.lastClickHashMap.getOrDefault( p , null );
        if( lc != null ){
            if((System.currentTimeMillis() - lc.getTick()) <= 45){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }


}
