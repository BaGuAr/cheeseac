package co.n0rthyankt0n.cheeseac.listeners;

import co.n0rthyankt0n.cheeseac.checks.movements.Invalid;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.utils.LastFlyUtil;
import co.n0rthyankt0n.cheeseac.utils.LastOnGroundUtil;
import co.n0rthyankt0n.cheeseac.utils.LastOnIceUtil;
import co.n0rthyankt0n.cheeseac.values.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerMoveEventListener implements Listener {

    public static HashMap<Player,Long> lastMovedTick = new HashMap<>();
    public static HashMap<Player, LastOnSlime> lastSlimeTick = new HashMap<>();
    public static HashMap<Player,PlayerData>playerDatas = new HashMap<>();
    public static HashMap<Player,PlayerData>last = new HashMap<>();
    public static HashMap<Player,Long> lastAboveBlock = new HashMap<>();


    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if( playerDatas.get( e.getPlayer() ) != null ){
            last.put( e.getPlayer() , playerDatas.get( e.getPlayer() ) );
        }
        Location to = e.getTo();
        Location from = e.getFrom();
        double deltax = Math.sqrt(Math.pow(Math.abs(to.getX() - from.getX()), 2.0));
        double deltay = Math.abs( from.getY() - to.getY() );
        double deltaz = Math.pow(Math.abs(to.getZ() - from.getZ()), 2.0);
        //double deltaxz = Math.sqrt((deltax * deltax) + (deltaz * deltaz));
        //double deltaxz = Math.hypot( deltax,deltaz );
        double deltaxz = deltax + deltaz;
        Delta delta = new Delta( deltax , deltay ,deltaz , deltaxz );

        Player p = e.getPlayer();

        double ssf = (((PlayerMoveEventListener.last.get( p ) != null ? PlayerMoveEventListener.last.get( p ) : PlayerMoveEventListener.playerDatas.get( p ))) != null ? ((PlayerMoveEventListener.last.get( p ) != null ? PlayerMoveEventListener.last.get( p ) : PlayerMoveEventListener.playerDatas.get( p ))).serverSide : 0 ) ;
        if( !GroundUtil.OnGround( e.getFrom() ) || !GroundUtil.OnGround( e.getTo() ) ) {
            ssf = ssf + 1;
        }

        //double ssf = Math.abs( from1.getY() ) - Math.abs( to.getY() );
        if( e.getFrom().getY() <= e.getTo().getY() ){
            ssf = 0;
        }
        playerDatas.put( e.getPlayer() ,
                new PlayerData( GroundUtil.OnGround( e.getTo() ) , e.getPlayer().isOnGround() , e.getTo()
                        , delta.getX() , delta.getY() , delta.getZ(), delta.getXZ()
                , playerDatas.get( e.getPlayer() ) , e.getPlayer() , ssf ));

        if( GroundUtil.OnGround( e.getTo() ) ){
            Invalid.tick( p , Invalid.Action.CLEAR );
        }else{
            Invalid.tick( p , Invalid.Action.ADD );
        }

        if(e.getPlayer().isFlying()){
            LastFlyUtil.lastFlyHashMap.put( e.getPlayer() , new LastFly( e.getPlayer() ,System.currentTimeMillis( ) ) );
        }
        if( GroundUtil.isAroundIce( e.getTo() ) ){
            LastOnIceUtil.lastOnIceHashMap.put( e.getPlayer() , new LastOnIce(e.getPlayer() , System.currentTimeMillis() ) );
        }
        if( GroundUtil.OnGround( e.getTo() ) ){
            LastOnGroundUtil.lastGroundHashMap.put( e.getPlayer() , new LastGround( e.getPlayer() , System.currentTimeMillis() ) );
        }
        lastMovedTick.put( e.getPlayer() , System.currentTimeMillis() );
        if(GroundUtil.isAroundSlime( e.getTo() )){
            lastSlimeTick.put( e.getPlayer(), new LastOnSlime( e.getPlayer() , System.currentTimeMillis() ));
        }
        if( GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) ){
            lastAboveBlock.put( e.getPlayer() , System.currentTimeMillis() );
        }


        Bukkit.getPluginManager( ).callEvent( new CheeseMoveEvent( e ) );
    }

//    @EventHandler
//    public void onMove1(CheeseMoveEvent e){
//        if(!e.onGroundBoth())return;
//        if(e.isNearMoveEvent()){
//
//        }else{
//            if(e.fromGroundBoth()){
//                lastMoveEventHashMap.put( e.getPlayer() , new LastMoveEvent( e.getPlayer() , System.currentTimeMillis() - 2 ) );
//            }
//        }
//    }
}
