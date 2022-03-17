package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class GroundSpoof implements Listener {

    @EventHandler
    public void onMove(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isNearDamage())return;
        if(e.isNearFly())return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if(e.isNearCactusDamage())return;
        if(GroundUtil.isHeadUpGround( e.getTo() ))return;
        if(e.isNearLastPlace())return;
        if( GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) )return;
        Player p = e.getPlayer();
        Block b0 = e.getPlayer().getLocation().subtract(0, 0 , 0).getBlock();
        Block b1a  =  e.getPlayer().getLocation().subtract( 0 ,  1,0 ).getBlock();
        Block b0a  =  e.getPlayer().getLocation().subtract( 0 ,  0,0 ).getBlock();
        Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );
        Block b2 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,2,0 ) );
        String b0s = b0a.getType() + "";
        String b1s = b1.getType() + "";
        String b2s = b2.getType() + "";
        if(b1s.contains( "SLIME" ) || b2s.contains( "SLIME" )
                || b1s.contains( "LADDER" ) || b2s.contains( "LADDER" )
                || b1s.contains( "VINE" ) || b2s.contains( "VINE" )
                || b1s.contains( "WEB" ) || b2s.contains( "WEB" )
                || b1s.contains( "LILY" ) || b2s.contains( "LILY" ) || b0s.contains( "LILY" )
                || b1s.contains( "SNOW" ) || b2s.contains( "SNOW" ) || b0s.contains( "SNOW" )
                || b1s.contains( "CARPET" ) || b2s.contains( "CARPET" ) || b0s.contains( "CARPET" ))return;

        boolean isClientOnGround = p.isOnGround();
        boolean isOnGround = GroundUtil.isOnGroundAround( e.getTo() );
        boolean already = false;

        if(isClientOnGround && !isOnGround && ! GroundUtil.isStandingBoat( p )){
            already = true;
            e.makeAlert( "GroundSpoof","a" );
        }
        boolean isOnGround2 = p.getLocation().getY() % 0.015625 < 0.0001;

        if(isClientOnGround && !isOnGround2 && ! GroundUtil.isStandingBoat( p ) && !already){
            e.makeAlert( "GroundSpoof","a" );
        }
    }
}
