package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.utils.LastClickValueUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InvMove implements Listener {

    @EventHandler
    public void onMove(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isNearDamage())return;

        if( LastClickValueUtil.isNearLastClick( e.getPlayer() ) ){
            if(e.getPlayer().isSprinting() || e.getPlayer().isSneaking()){
                e.makeAlert( "invmove" , "A" );
            }
            if( !GroundUtil.isWaterAround( e.getTo() , 4 ,2 ) ){
                e.makeAlert( "invmove" , "A" );
            }
        }
    }
}
