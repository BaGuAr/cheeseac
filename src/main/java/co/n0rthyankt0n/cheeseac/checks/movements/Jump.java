package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;


public class Jump implements Listener {

    public static double MAX_DELTA_Y = 0.41999998688697815;
    public static double MIN_DELTA_Y = 0.07840000152587834;
    public static double DELTA_1 = 0.0;
    public static double DELTA_2 = 0.41999998688697815; //max
    public static double DELTA_3 = 0.33319999363422426;
    public static double DELTA_4 = 0.24813599859094637;
    public static double DELTA_5 = 0.164773281826065;
    public static double DELTA_6 = 0.08307781780646906;
    public static double DELTA_7 = 0.07840000152587834;
    public static double DELTA_8 = 0.15523200451659847;
    public static double DELTA_9 = 0.23052736891296632;
    public static double DELTA_10 = 0.30431682745754074;
    public static double DELTA_11 = 0.37663049823865435;
    public static double DELTA_12 = 0.1040803780930446;

    public static double DELTA_13 = 0.5;
    public static double DELTA_14 = 0.1176000022888175;
    public static double DELTA_15 = 0.036848002960205406;
    public static double DELTA_16 = 0.20000004768371582;
    public static double DELTA_17 = 0.1875;
    public static double DELTA_18 = 0.20000004768372293;
    public static double DELTA_19 = 0.11760000228882461;
    public static double DELTA_20 = 0.012500047683730031;
    public static double DELTA_21 = 0.20000004768373003;
    public static double DELTA_22 = 0.24813599859093927;
    public static double DELTA_23 = 0.1647732818260721;
    public static double DELTA_24 = 0.125;



    HashMap<Player,Long> lastJump = new HashMap<>( );

    @EventHandler
    public void onMove(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isCancelled())return;
        if(e.getTo().getY() < e.getFrom().getY())return;
        if(e.isNearFly())return;
        double yDiff = e.getDiff().getY();
        if( e.isNearDamage() )return;

        if( yDiff == MAX_DELTA_Y ){
            if( lastJump.get( e.getPlayer() ) != null ){
                long diff = Math.abs( System.currentTimeMillis() - lastJump.get( e.getPlayer() ) );
                if( diff < 450 ) {
                    e.makeAlert( "Jump","C" );
                }else {
                    //e.msg( "diff between now and lastJump: " + diff );
                }
            }
            lastJump.put(e.getPlayer() , System.currentTimeMillis() );
        }
        if(e.isNearLastOnSlime())return;
        if(GroundUtil.isAround( e.getTo() , "stairs" ))return;
        if(GroundUtil.isAround( e.getTo() , "slab" ))return;
        if(GroundUtil.isAround( e.getTo() , "wall" ))return;
        if( GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) )return;
        //Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );

        if(yDiff > MAX_DELTA_Y){
            e.makeAlert( "Jump","a" );
        }
        Location to = e.getTo();
        Location from = e.getFrom();
        if(GroundUtil.isHeadUpWater( e.getTo() ) || GroundUtil.isHeadUpWater( e.getFrom() ))return;
        if(yDiff < MIN_DELTA_Y && yDiff != 0.0 && !e.isOnWaterAround() &&
                !GroundUtil.isHeadUpGround( to ) && !GroundUtil.isHeadUpGround( from )
                && !GroundUtil.isBadBlockAround( to ) && !GroundUtil.isBadBlockAround( from ) &&
                !GroundUtil.isStandingBoat( e.getPlayer() ) ){
            e.makeAlert( "Jump","b" );
        }

//        if( yDiff == DELTA_1 || yDiff == DELTA_2 || yDiff == DELTA_3
////                || yDiff == DELTA_4 || yDiff == DELTA_5 || yDiff == DELTA_6
////                || yDiff == DELTA_7 || yDiff == DELTA_8 || yDiff == DELTA_9
////                || yDiff == DELTA_10 || yDiff == DELTA_11 || yDiff == DELTA_12
////                || yDiff == DELTA_13 || yDiff == DELTA_14 || yDiff == DELTA_15
////                || yDiff == DELTA_16 || yDiff == DELTA_17 || yDiff == DELTA_18
////                || yDiff == DELTA_19 || yDiff == DELTA_20 || yDiff == DELTA_21
////                || yDiff == DELTA_22 || yDiff == DELTA_23 || yDiff == DELTA_24 ){
////            e.getPlayer().sendMessage( "§aVALID! => " + yDiff );
////        }else{
////            Bukkit.broadcastMessage( "§c" + yDiff );
////        }
    }



}
