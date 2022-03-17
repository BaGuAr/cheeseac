package co.n0rthyankt0n.cheeseac.listeners;

import co.n0rthyankt0n.cheeseac.utils.LastYawUtil;
import co.n0rthyankt0n.cheeseac.values.Damage;
import co.n0rthyankt0n.cheeseac.values.LastCactusDamage;
import co.n0rthyankt0n.cheeseac.values.LastFallDamage;
import co.n0rthyankt0n.cheeseac.values.LastYaw;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


import java.util.HashMap;

public class DamageListener implements Listener {

    public static HashMap<Player, LastCactusDamage> lastCactusDamageMap = new HashMap<>();
    public static HashMap<Player,Long> lastHit = new HashMap<>();
    public static HashMap<Player, Damage> lastDamageMap = new HashMap<>();
    public static HashMap<Player, LastFallDamage> lastFallDamageMap = new HashMap<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if ( e.getEntity( ) instanceof Player ) {
            Player p = ( (Player) e.getEntity() ).getPlayer();
            lastDamageMap.put( p , new Damage( p , System.currentTimeMillis() , e.getDamager() ) );

        }
        if( e.getDamager() instanceof Player){
            Player p = ( (Player) e.getDamager() ).getPlayer();
            LastYawUtil.lastYawHashMap.put( p , new LastYaw( p , p.getLocation().getYaw() ) );
            lastHit.put( p , System.currentTimeMillis() );
        }
    }

    @EventHandler
    public void onDamageCactus(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            if(e.getCause().equals( EntityDamageEvent.DamageCause.CONTACT )) {
                lastCactusDamageMap.put( ( (Player) e.getEntity() ).getPlayer() ,
                        new LastCactusDamage( ( (Player) e.getEntity() ).getPlayer() ,
                        System.currentTimeMillis() ));
            }
            if(e.getCause().equals( EntityDamageEvent.DamageCause.FALL )) {
                lastFallDamageMap.put( ( (Player) e.getEntity() ).getPlayer() , new LastFallDamage( ( (Player) e.getEntity() ).getPlayer() , System.currentTimeMillis() , e.isCancelled() ) );
            }
        }
    }

    public static HashMap<Player,Long> lastRespawn = new HashMap<>();

    @EventHandler
    public void respawn(PlayerRespawnEvent e){
        lastRespawn.put( e.getPlayer() , System.currentTimeMillis() );
    }

}
