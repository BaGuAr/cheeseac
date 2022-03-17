package co.n0rthyankt0n.cheeseac.checks.movements;


import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class AntiCactus implements Listener {

    HashMap<Player,Integer> detect = new HashMap<>();

    @EventHandler
    public void onMove(CheeseMoveEvent e){
        if(e.canBypass())return;
        Player p = e.getPlayer();

        String[] y = (p.getLocation().getY() + "").split( "\\." );
        if( y.length == 2){
            if(!y[1].startsWith( "9375" )){
                Block b1 = p .getWorld().getBlockAt( p.getLocation().subtract( 0,1,0 ) );
                //Block b1 = p.getLocation().subtract(0, 0.1, 0).getBlock();
                String b1s = b1.getType() + "";
                if(b1s.contains( "CACTUS" ) && e.isOnMathGround() && e.isOnAroundGround()){
                    if(detect.get( p ) != null){
                        detect.put( p , detect.getOrDefault( p , 0 ) + 1 );
                        if(detect.get( p ) >= 2){
                            e.makeAlert( "AntiCactus","a" );
                            detect.put( p , null );
                        }
                    }else {
                        detect.put( p , 0 );
                        new BukkitRunnable() {
                            public void run() {
                                detect.put( p, null );
                            }
                        }.runTaskLater( CheeseAC.plugin, 15L);
                    }

                }
            }
        }


    }

}
