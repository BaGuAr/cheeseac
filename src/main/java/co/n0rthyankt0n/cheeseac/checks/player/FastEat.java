package co.n0rthyankt0n.cheeseac.checks.player;

import co.n0rthyankt0n.cheeseac.managers.Alert;
import co.n0rthyankt0n.cheeseac.utils.BypassUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.util.HashMap;

public class FastEat implements Listener {


    HashMap<Player,Long> lastEat = new HashMap<>();



    @EventHandler
    public void onEat(FoodLevelChangeEvent e){
        if( e.getEntity() instanceof Player ){
            Player p = ( (Player) e.getEntity() ).getPlayer();
            if( BypassUtil.canBypass( p ) )return;
            if( lastEat.get( p ) != null ){
                if( ( System.currentTimeMillis() - lastEat.get( p ) ) < 1490 ){
                    e.setCancelled( new Alert( p , "FastEat","A" ).send().getFlagStatus() );
                }
            }
            lastEat.put( p , System.currentTimeMillis() );
        }
    }


}
