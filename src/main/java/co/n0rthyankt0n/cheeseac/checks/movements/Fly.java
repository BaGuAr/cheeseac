package co.n0rthyankt0n.cheeseac.checks.movements;


import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.listeners.DamageListener;
import co.n0rthyankt0n.cheeseac.listeners.PlayerMoveEventListener;
import co.n0rthyankt0n.cheeseac.managers.Alert;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.values.BetterValue;
import co.n0rthyankt0n.cheeseac.values.Delta;
import co.n0rthyankt0n.cheeseac.values.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;


public class Fly implements Listener {

    public static boolean onLadder(Player player) {
        Material b = player.getLocation().subtract(0, 0.1, 0).getBlock().getType();
        return b == Material.LADDER || b == Material.VINE  || b == Material.WEB;
    }

    @EventHandler
    public void onMove(CheeseMoveEvent e) {
        if ( e.canBypass( ) ) return;
        if ( onLadder( e.getPlayer( ) ) ) return;
        if ( e.getPlayer( ).isFlying( ) ) return;
        if ( GroundUtil.isWaterAround( e.getTo( ),2,2 ) ) return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
//        if ( e.getLastDamage( ) != null && e.getLastDamage( ).getDamager( ) instanceof Player ){
//            System.out.println(  e.isNearDamage( ) + " " + ( System.currentTimeMillis( ) - e.getLastDamage( ).getTick( ) ) + " " + ( (Player) e.getLastDamage( ).getDamager( ) ).getPlayer( ).getName( )  );
//        }
//        Block b0 = e.getPlayer().getLocation().subtract(0, 0.1, 0).getBlock();
//        Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );
//        Block b2 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,2,0 ) );
//        String b0s = b0.getType() + "";
//        String b1s = b1.getType() + "";
//        String b2s = b2.getType() + "";
//        if(b1s.contains( "SLIME" ) || b2s.contains( "SLIME" )
//                || b1s.contains( "LADDER" ) || b2s.contains( "LADDER" )
//                || b1s.contains( "VINE" ) || b2s.contains( "VINE" )
//                || b1s.contains( "WEB" ) || b2s.contains( "WEB" )
//                || b1s.contains( "LILY" ) || b2s.contains( "LILY" ) || b0s.contains( "LILY" )
//                || b1s.contains( "SNOW" ) || b2s.contains( "SNOW" ) || b0s.contains( "SNOW" )
//                || b1s.contains( "CARPET" ) || b2s.contains( "CARPET" ) || b0s.contains( "CARPET" ))return;y
        Block b0a  =  e.getPlayer().getLocation().subtract( 0 ,  0,0 ).getBlock();
        Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );
        Block b2 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,2,0 ) );
        String b0s = b0a.getType() + "";
        String b1s = b1.getType() + "";
        String b2s = b2.getType() + "";
        //e.msg( b1a.getType() + " " + b0a.getType()  );
        if(b1s.contains( "SLIME" ) || b2s.contains( "SLIME" )
                || b1s.contains( "LADDER" ) || b2s.contains( "LADDER" )
                || b1s.contains( "VINE" ) || b2s.contains( "VINE" )
                || b1s.contains( "WEB" ) || b2s.contains( "WEB" )
                || b1s.contains( "LILY" ) || b2s.contains( "LILY" ) || b0s.contains( "LILY" )
                || b1s.contains( "SNOW" ) || b2s.contains( "SNOW" ) || b0s.contains( "SNOW" )
                || b1s.contains( "CARPET" ) || b2s.contains( "CARPET" ) || b0s.contains( "CARPET" ))return;
        if(GroundUtil.isAroundSlime( e.getTo() ))return;
        if(e.isNearLastOnSlime())return;
        if(e.isNearFly())return;
        if(e.isNearCactusDamage())return;
        if( e.isAboveWater() )return;
        if(!e.isNearDamage()){
            Location to = e.getTo();
            Location from = e.getFrom();
            if(GroundUtil.isHeadUpGround( to ))return;
            double yDiff = from.getY() - to.getY();
            if(to.getY() > from.getY())return;
            if(!e.onGroundBoth() && yDiff < 0.15  && yDiff != 0.07840000152587834 && yDiff != 0 ){
                e.makeAlert( "fly","a" );
            }
        }

    }

    @EventHandler
    public void onMove2(CheeseMoveEvent e){
        if(e.canBypass())return;
        //e.msg( GroundUtil.isHeadUpGround( e.getTo() ) +"" );
        if(onLadder( e.getPlayer() ))return;
        if(e.isNearFly())return;
        if(e.getPlayer().isFlying())return;
        Block b = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getEyeLocation().add( 0,1,0 ) );
        if(b.getType().isSolid())return;
        if( GroundUtil.isHeadUpGround( e.getTo() ) )return;
        if( GroundUtil.isWaterAround( e.getTo() , 2 ,2 ) )return;
        if(GroundUtil.isAroundSlime( e.getTo() ))return;
        if(e.isNearLastOnSlime())return;
        if(e.isNearOnIce())return;
        if(e.isNearLastAboveBlock())return;
        if(GroundUtil.isBadBlockAround( e.getTo() ))return;
        if(!e.isNearDamage()) {
            double xz = e.getDelta( ).getXZ( );
            double y = e.getDelta( ).getY( );
            boolean isFly = ! e.isCancelled( ) && ! e.onGroundBoth( ) && xz > 0.5 && y < 0.05;
            boolean isFlyPatch = ! e.isCancelled( ) && ! e.onGroundBoth( ) && xz > 0 && y < 0.05 && e.getTo( ).getY( ) > e.getFrom( ).getY( );
            if ( isFly || isFlyPatch ) {
                e.makeAlert( "fly","b" );
            }
        }
    }
    @EventHandler
    public void onMove3(CheeseMoveEvent e){
        Player p = e.getPlayer();
        if(e.canBypass())return;
        if(e.isNearDamage())return;
        if(p.isFlying())return;
        if( e.isNearLastPlace() )return;
        if(onLadder( p ))return;
//        Block b0 = e.getPlayer().getLocation().subtract(0, 0.1, 0).getBlock();
//        Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );
//        Block b2 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,2,0 ) );
//        String b0s = b0.getType() + "";
//        String b1s = b1.getType() + "";
//        String b2s = b2.getType() + "";
//        if(b1s.contains( "SLIME" ) || b2s.contains( "SLIME" )
//                || b1s.contains( "LADDER" ) || b2s.contains( "LADDER" )
//                || b1s.contains( "VINE" ) || b2s.contains( "VINE" )
//                || b1s.contains( "WEB" ) || b2s.contains( "WEB" )
//                || b1s.contains( "LILY" ) || b2s.contains( "LILY" ) || b0s.contains( "LILY" )
//                || b1s.contains( "SNOW" ) || b2s.contains( "SNOW" ) || b0s.contains( "SNOW" )
//                || b1s.contains( "CARPET" ) || b2s.contains( "CARPET" ) || b0s.contains( "CARPET" ))return;
        Block b0a  =  e.getPlayer().getLocation().subtract( 0 ,  0,0 ).getBlock();
        Block b1 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,1,0 ) );
        Block b2 = e.getPlayer().getWorld().getBlockAt( e.getPlayer().getLocation().subtract( 0,2,0 ) );
        String b0s = b0a.getType() + "";
        String b1s = b1.getType() + "";
        String b2s = b2.getType() + "";
        //e.msg( b1a.getType() + " " + b0a.getType()  );
        if(b1s.contains( "SLIME" ) || b2s.contains( "SLIME" )
                || b1s.contains( "LADDER" ) || b2s.contains( "LADDER" )
                || b1s.contains( "VINE" ) || b2s.contains( "VINE" )
                || b1s.contains( "WEB" ) || b2s.contains( "WEB" )
                || b1s.contains( "LILY" ) || b2s.contains( "LILY" ) || b0s.contains( "LILY" )
                || b1s.contains( "SNOW" ) || b2s.contains( "SNOW" ) || b0s.contains( "SNOW" )
                || b1s.contains( "CARPET" ) || b2s.contains( "CARPET" ) || b0s.contains( "CARPET" ))return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if(e.isSpoofing() && e.getDelta().getY() == 0){
            e.makeAlert( "fly","c" );
        }
    }

    @EventHandler
    public void onMove4(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isCancelled())return;
        //if(e.isNearDamage())return;
        Location to = e.getTo();
        Location from = e.getFrom();
        if(GroundUtil.isBadBlockAround( to ))return;
        Delta delta = e.getDelta();
        if(e.onGroundBoth())return;
        if(to.getY() == from.getY())return;
        if(delta.getY() > 150){
            e.makeAlert( "fly","d" );
        }
    }

    @EventHandler
    public void onMove5(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isCancelled())return;
        if(e.isNearDamage())return;
        if(e.isNearOnIce())return;
        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();
        if(GroundUtil.isBadBlockAround( to ))return;
        if(GroundUtil.isAroundSlime( to ))return;
        if(p.isFlying())return;
        if(e.isNearFly())return;
        if(e.isOnWaterAround())return;
        if(GroundUtil.isWaterAround( to , 2 , 3 ))return;
        if(e.isNearLastOnGround())return;
        if(e.onGroundBoth())return;
        if(GroundUtil.isStandingBoat( p ))return;
        if(GroundUtil.isOnGroundAround2( to , 3 ))return;
        if(to.getY() >= from.getY()){
            e.makeAlert( "fly","e" );
        }
    }


    public static double airtick_F = 2;
    @EventHandler
    public void onMove6(CheeseMoveEvent e){
        if( e.canBypass() )return;
        Player p = e.getPlayer();
        if( e.isAboveWater() )return;
        if( e.isNearLastPlace() )return;
        if( e.isOnWaterAround() )return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if( e.isNearLastOnSlime() )return;
        if( e.isOnAroundGround() )return;
        double airtick = e.getAirTick();
        if( airtick > airtick_F && p.isOnGround() ){
            e.makeAlert( "fly","f" );
        }
    }




    public static HashMap<Player,Double> lastGroundY = new HashMap<>();
    public static HashMap<Player,Location> lastGroundLoc = new HashMap<>();

    public enum Action{
        ADD,GET
    }

    public static double lastGY(Player p,Action ac , double y){
        if(ac == Action.GET){
            return lastGroundY.getOrDefault( p  , y);
        }else {
            lastGroundY.put( p,  y );
            return y;
        }
    }
    public static Location lastGLOC(Player p,Action ac , Location y){
        if(ac == Action.GET){
            return lastGroundLoc.getOrDefault( p  , y);
        }else {
            lastGroundLoc.put( p,  y );
            return y;
        }
    }




    public static boolean G_1,G_2,G_3,G_4 = true;
    public static double GV_1,GV_2,GV_3;

    public static void FlyG(){
        G_1 = CheeseAC.config.getBoolean( "settings.Fly.G.settings.Type1.status" );
        GV_1 = CheeseAC.config.getDouble( "settings.Fly.G.settings.Type1.value" );
        G_2 = CheeseAC.config.getBoolean( "settings.Fly.G.settings.Type2.status" );
        GV_2 = CheeseAC.config.getDouble( "settings.Fly.G.settings.Type2.value" );
        G_3 = CheeseAC.config.getBoolean( "settings.Fly.G.settings.Type3.status" );
        GV_3 = CheeseAC.config.getDouble( "settings.Fly.G.settings.Type3.value" );
        G_4 = CheeseAC.config.getBoolean( "settings.Fly.G.settings.Type4" );
    }
    @EventHandler
    public void onMove7(CheeseMoveEvent e){
        if( e.canBypass() )return;
        Player p = e.getPlayer();
        if( e.getAirTick() == 0 || e.getAirTick() == 1 ){
            lastGY( p , Action.ADD ,e.getTo().getY() );
            lastGLOC( p , Action.ADD, e.getTo() );
        }
        if( e.isNearLastOnSlime() )return;
        if( e.isNearLastRespawn() )return;
        if( e.isOnWaterAround() )return;
        if( e.isNearFly() )return;
        if( p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR )return;
        if( e.isCancelled() )return;
        if( onLadder( p ) )return;
        if( e.isAboveWater() )return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if( e.getTo().getY() < 0 )return;
        if( e.getAirTick() > 11 ){
            double y = lastGY( p , Action.GET , e.getTo().getY() ) - e.getTo().getY();
            if( y < GV_1 || e.getTo().getY() == e.getFrom().getY() ){ //0.69
                if( y < 0 && Math.abs( y ) > GV_2 ){ //0.9999
                    if( G_1 ){
                        if( !e.isNearLastPlace() && !e.isOnAroundGround() && !e.isNearLastOnSlime() ) {
                            e.makeAlert( "Fly","G 1" );
                        }
                    }
                }else if( G_2 && !e.isOnWaterAround() && !e.isNearDamage() && !e.isAboveBlock() && !e.isNearLastOnSlime() ){
                    e.makeAlert( "Fly","G 2" );
                }
                //e.msg( "y = " + y );
            }else if( y < GV_3 && G_3 ){ //0.759
                e.makeAlert( "Fly","G 3" );
            }
            if( e.getTo().getY() > e.getFrom().getY() && G_4 && !e.isNearLastPlace() && !e.isOnAroundGround() ){
                e.makeAlert( "Fly","G 4" ); // hard moing
            }
        }
    }

    @EventHandler
    public void onVelocity(PlayerVelocityEvent e){
        Player p = e.getPlayer();
        if(CheeseAC.bypass && p.hasPermission( CheeseAC.bypassPerm ))return;
        double airtick = Invalid.tick( p , Invalid.Action.GET );
        double diff = p.getLocation().getY() - lastGY( p , Action.GET , p.getLocation().getY() );
        //if( GroundUtil.isBadBlockAround( lastGLOC( p , Action.GET , p.getLocation() ) ) )return;
        if( GroundUtil.isBadBlockAround( p.getLocation() ) )return;

        if( DamageListener.lastDamageMap.get( p ) != null && ( System.currentTimeMillis() - DamageListener.lastDamageMap.get( p ).getTick() ) < 1100 )return;
        if( DamageListener.lastCactusDamageMap.get( p ) != null && ( System.currentTimeMillis() - DamageListener.lastCactusDamageMap.get( p ).getTick() ) < 1100 )return;

        if( airtick > 6 && ( diff > 0.419 || ( Math.abs( diff ) > 2.9 && p.getLocation().getY() > 0 ) ) ){
            if(new Alert(p , "Fly","H").send().getFlagStatus()){
                p.teleport( lastGLOC( p , Action.GET , p.getLocation() ) );
            }
        }
    }

    HashMap<Player,Integer> flyI = new HashMap<>();

    @EventHandler
    public void onMove8(CheeseMoveEvent e){
        if(e.canBypass())return;
        Player p = e.getPlayer();
        double toY = e.getTo().getY() - p.getLocation().getBlockY();
        double fromY = e.getFrom().getY() - e.getPlayerData().lastLocation.getBlockY();
        double diff = ( toY - fromY ) * 100000;
        diff = Math.abs( diff );
        if( diff == 0 )return;
        if( diff < 500 ) {
            if(flyI.get( p ) == null){
                flyI.put( p , 1 );
                new BukkitRunnable() {
                    public void run() {
                        flyI.put( p , null );
                    }
                }.runTaskLater( CheeseAC.plugin, 20L);
            }
            if( flyI.get(p) > 2 ){
                e.makeAlert( "Fly","I" );
            }
            flyI.put( p , flyI.getOrDefault( p , 0 ) + 1 );
        }
    }

    HashMap<Player,String> action = new HashMap<>();

    public static boolean J_UP,J_DOWN,J_SAME;

    @EventHandler
    public void onMove9(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isNearFly())return;
        if(e.isNearLastOnSlime())return;
        if(e.isNearLastRespawn())return;
        if(e.isOnWaterAround())return;
        double airtick = e.getAirTick();

        Player p = e.getPlayer();
        Location to = e.getTo();
        Location from = e.getFrom();

        if( airtick == 1 ){
            if( to.getY() > from.getY() ){
                action.put( p , "UP" );
            }
            if( to.getY() < from.getY() ){
                action.put( p , "DOWN" );
            }
            if( to.getY() == from.getY() ){
                action.put( p , "SAME" );
            }
        }

        if( airtick > 1 ){
            if( action.get( p ) != null ){
                String dataY = action.get( p );

                if( dataY == "UP" && J_UP && !e.isNearDamage() ){
                    if( to.getY() < from.getY() ){
                        if( !GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) ){
                            if( airtick > 5 )return;
                            e.makeAlert( "Fly","J 1" );
                        }
                    }
                }
                if( dataY == "DOWN" && J_DOWN ){
                    if( to.getY() > from.getY() ){
                        if( !GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) ){
                            e.makeAlert( "Fly","J 2" );
                        }
                    }
                }
                if( dataY == "SAME" && J_SAME ){
                    if( to.getY() == from.getY() ){
                        if( !GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) ){
                            e.makeAlert( "Fly","J 3" );
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove10(CheeseMoveEvent e){
        if( e.canBypass() )return;
        if( e.isFirst() )return;
        if( e.isNearFly() )return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if( e.isAboveBlock() )return;
        if( e.isNearLastPlace() )return;
        PlayerData pd = e.getPlayerData();
        if( e.getAirTick() > 1 ){
            if( pd.y == e.getDelta().getY() && e.getTo().getY() >= e.getFrom().getY() ){
                e.makeAlert( "Fly","K" );
            }
        }
    }


    HashMap<Player,Integer> l_vl = new HashMap<>();

    @EventHandler
    public void onMove11(CheeseMoveEvent e){
        if( e.canBypass() )return;
        if( e.isFirst() )return;
        if( e.onGroundBoth() )return;
        if( e.isAboveBlock() )return;
        if( e.isNearLastOnSlime() )return;
        if( e.isOnWaterAround() )return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) || GroundUtil.isBadBlockAround( e.getFrom() ) )return;
        if( e.getAirTick() > 11 ){
            if( e.getDelta().getY() < 0.4 && e.getTo().getY() <= e.getFrom().getY() ){
                if( l_vl.get( e.getPlayer() ) != null ){
                    l_vl.put( e.getPlayer() , l_vl.get( e.getPlayer() ) + 1 );
                    if( l_vl.get( e.getPlayer() ) > 2 ){
                        e.makeAlert( "Fly","L" );
                        l_vl.put( e.getPlayer() , 0 );
                    }
                } else{
                    l_vl.put( e.getPlayer() , 1 );
                }
            }
        }
    }
}
