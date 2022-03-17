package co.n0rthyankt0n.cheeseac.checks.combat;

import co.n0rthyankt0n.cheeseac.managers.Alert;
import co.n0rthyankt0n.cheeseac.utils.BypassUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class Reach implements Listener {

    @EventHandler
    public void onEntityHitEvent(EntityDamageByEntityEvent e){
        if( e.getDamager() instanceof Player) {
            Player p = ( (Player) e.getDamager() ).getPlayer();
            if ( BypassUtil.canBypass( p ) )return;
            double diff = -0.5;
            double distX = Math.abs( p.getLocation().getX() - e.getEntity().getLocation().getX() ) - diff;
            double distY = Math.abs( p.getLocation().getY() - e.getEntity().getLocation().getY() ) - diff;
            double distZ = Math.abs( p.getLocation().getZ() - e.getEntity().getLocation().getZ() ) - diff;
             //Using DISTANCE for reach check is outdated idea :(
            if( distX > 3.1 || distY > 3.1 || distZ > 3.1 ){
                e.setCancelled( new Alert(p,"Reach","A").send().getFlagStatus() );
            }
        }
    }

}
