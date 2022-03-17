package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.values.Delta;
import co.n0rthyankt0n.cheeseac.values.Diff;
import co.n0rthyankt0n.cheeseac.values.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;


public class Speed implements Listener {
    @EventHandler
    public void onMove(CheeseMoveEvent e){
        Player p = e.getPlayer();
        if(e.canBypass())return;
        if(e.isNearDamage())return;
        if(e.isNearFly())return;
        if(e.isNearOnIce())return;
        if(p.isFlying())return;
        double distance = e.getTo().distance(e.getFrom());
        if(p.hasPotionEffect( PotionEffectType.SPEED))return;
        Block b = p .getWorld().getBlockAt( p.getEyeLocation().add( 0,1,0 ) );
        Block b1 = p .getWorld().getBlockAt( p.getLocation().subtract( 0,1,0 ) );
        Block b2 = p .getWorld().getBlockAt( p.getLocation().subtract( 0,2,0 ) );
        String b1s = b1.getType() + "";
        String b2s = b2.getType() + "";
        if(distance > 0.6 && e.getDelta().getY() < 0.399 && !b.getType().isSolid() && !b1s.contains( "ICE" ) && !b2s.contains( "ICE" ) ){
            //e.msg( e.getDelta().getY() + "" );
            e.makeAlert( "speed","a" );
        }
    }

    @EventHandler
    public void onMove1(CheeseMoveEvent e){
        Player p = e.getPlayer();
        if(e.canBypass())return;
        if(e.isNearDamage())return;
        if(e.isNearFly())return;
        if(e.isNearOnIce())return;
        if(p.isFlying())return;
        if(e.isOnWaterAround())return;
        if(e.isFromWaterAround())return;
        if(p.hasPotionEffect( PotionEffectType.SPEED ))return;
        Diff diff = e.getDiff();
        double d = (diff.getX() > diff.getZ() ? diff.getX() : diff.getZ());
        if(!(d > 0.66))return;
        e.makeAlert( "speed","b" );
    }


    @EventHandler
    public void onMove2(CheeseMoveEvent e){
        Player p = e.getPlayer();
        if(e.canBypass())return;
        if(e.isNearFly())return;
        if(e.isOnWaterAround())return;
        if(e.isNearOnIce())return;
        if(e.isNearDamage())return;
        if(!e.onGroundBoth())return;
        if(e.isNearLastAboveBlock())return;
        if(p.hasPotionEffect( PotionEffectType.SPEED ))return;
        if( GroundUtil.isHeadUpGround( e.getTo() ) )return;
        if(GroundUtil.isAround( e.getTo() , "stairs" ))return;
        if(GroundUtil.isAround( e.getTo() , "wall" ))return;
        double dist = (e.getDiff().getX() > e.getDiff().getZ() ? e.getDiff().getX() : e.getDiff().getZ()) - (e.getDiff().getX() > e.getDiff().getZ() ? e.getDiff().getZ() : e.getDiff().getX());
        if(dist > 0.39395891342053346 ){
            e.makeAlert( "speed","c" );
        }
    }

    HashMap<Player,Delta> lastDelta = new HashMap<>();

    @EventHandler
    public void onMove3(CheeseMoveEvent e){

        /*
        my first friction check :) still bad
         */
        if(e.canBypass())return;
        Player p = e.getPlayer();
        double airTick = Invalid.tick( p , Invalid.Action.GET );
        Delta delta = e.getDelta();

        if(lastDelta.get( p ) == null){
            lastDelta.put( p, delta );
            return;
        }
        Delta last = lastDelta.get( p );

        if( airTick > 1 ){

            double speedX = Math.abs( delta.getX() - last.getX() );
            double speedZ = Math.abs( delta.getZ() - last.getZ() );

            double SPEED_AIR = 0.24101699571551063;
            double DELTA_XZ_AIR = 0.041016995715510624;
            DELTA_XZ_AIR += 0.03;
            SPEED_AIR += 0.06999;
            //SPEED_AIR =  0.245321;
//            if( speedX > SPEED_AIR || speedZ > SPEED_AIR ){
//                e.msg( "§cairtick = " + airTick + " " + speedX + " " + speedZ + " | " + );
//            }else{
//                e.msg( "airtick = " + airTick + " " + speedX + " " + speedZ );
//            }

            double deltaX = speedX - SPEED_AIR;
            double deltaZ = speedZ - SPEED_AIR;

            if( e.isNearDamage() )return;

            if( speedX > SPEED_AIR ){
                //ukkit.broadcastMessage( "§cairtick = " + airTick + " " + speedX + " | " + deltaX );
                e.makeAlert( "Speed","D" );
            }else if( speedZ > SPEED_AIR ){
                //Bukkit.broadcastMessage( "§cairtick = " + airTick + " " + speedZ + " | " + deltaZ );
                e.makeAlert( "Speed","D" );
            }else {
                //System.out.println( "AirTick = " + airTick + " " + speedX + " " + speedZ );
            }
        }
        lastDelta.put(p, e.getDelta());
    }


    @EventHandler
    public void onMove4(CheeseMoveEvent e){
        if(e.canBypass())return;
//        double accelX = e.getDelta().getX() - e.getPlayerData().x;
//        double accelZ = e.getDelta().getZ() - e.getPlayerData().z;
//        double accel = ( accelX > accelZ ? accelX : accelZ );
        double accel = e.getDelta().getXZ() - e.getPlayerData().xz;
        if( e.isNearDamage() )return;
        if( Math.abs( accel ) > ( 0.359 * e.getAirTick() ) && e.getAirTick() != 0 ){
            e.makeAlert( "Speed","E" );
        }
    }



    public static double DeltaMaker(double a,double b){
        return Math.abs((a > b ? a : b) - (a < b ? a : b ));
    }
}
