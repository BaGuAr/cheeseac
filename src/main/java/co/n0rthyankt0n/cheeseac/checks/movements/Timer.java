package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Timer implements Listener {
    public static HashMap<Player,Integer> timer = new HashMap<>();
    HashMap<Player,Boolean> already = new HashMap<>();
    HashMap<Player, Location> first = new HashMap<>();
    HashMap<Player,Integer> vl4timerb = new HashMap<>();

    @EventHandler
    public void onMove(CheeseMoveEvent e){
        if(e.canBypass())return;
        Player p = e.getPlayer();
        if(timer.get( p ) != null){
            timer.put( p , timer.getOrDefault( p , 0 ) + 1 );
            e.func_T20( timer.get( p) );
            if(timer.get( p ) > 20){
                if(already.get( p ) == null) {
                    e.makeAlert( "Timer","a" );
                    already.put( p , true );
                }
            }
//            if(timer.get( p ) > 19){
//                if(first.get( p ) != null){
//                    Location from = first.get( p ).clone();
//                    Location to = e.getTo().clone();
//
//                    double y = (to.getY() > from.getY() ? to.getY() : from.getY()) - (to.getY() > from.getY() ? from.getY() : to.getY());
//                    String dir = (to.getY() > from.getY() ? "UP":"DOWN");
//                    from.setY( 0 );
//                    to.setY( 0 );
//                    double dist = to.distance(from);
//
//                    if(vl4timerb.get( p ) != null){
//                        vl4timerb.put( p , vl4timerb.get( p ) + 1 );
//                        p.sendMessage( vl4timerb.get( p ).toString() );
//                        if(vl4timerb.get( p ) > 3){
//                            e.makeAlert( "Timer","B" );
//                        }
//                    }else {
//                        vl4timerb.put( p ,0 );
//                        new BukkitRunnable() {
//                            public void run() {
//                                vl4timerb.put( p ,null );
//                            }
//                        }.runTaskLater( CheeseAC.plugin, 100L);
//                    }
//
//                }
//            }
        }else {
            e.func_FLC( e.getTo() );
            e.func_T20(0 );
            timer.put( p , 0 );
            already.put( p , null  );
            first.put( p, p.getLocation() );
            new BukkitRunnable() {
                public void run() {
                    e.func_T20( 0 );
                    timer.put( p, null );
                    already.put( p , null );
                }
            }.runTaskLater( CheeseAC.plugin, 20L);
        }
    }

}
