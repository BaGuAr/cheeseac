package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class InvalidSprint implements Listener {


    static double SPEED_BLOCKING = 0.21589;
    static boolean sneaking,blocking,distance,food,blindness = true;

    public static void InvalidSprintA(){
        sneaking = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.sneaking" );
        blocking = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.blocking" );
        distance = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.distance.status" );
        SPEED_BLOCKING = CheeseAC.config.getDouble( "settings.Sprint.A.settings.distance.value" );
        food = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.food" );
        blindness = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.blindness" );
        Invalid.Omni = CheeseAC.config.getBoolean( "settings.Sprint.A.settings.omni" );
    }


    @EventHandler
    public void onMove(CheeseMoveEvent e){
        Player p = e.getPlayer();
        if(e.canBypass())return;
//        if( ( p.isSprinting() || ( e.getTo().distance( e.getFrom() ) > SPEED_BLOCKING && distance ) ) &&
//                ( ( p.isSneaking() && sneaking )
//                        || ( p.isBlocking() && blocking )
//                        || ( p.getFoodLevel() < 7 && food )
//                        || ( p.hasPotionEffect( PotionEffectType.BLINDNESS ) && blindness ) ) ){
//            e.makeAlert( "Sprint","a" );
//        }
        if( p.isSprinting() ){
            if( p.isBlocking() && blocking ){
                e.makeAlert( "Sprint","a 1" );
            }
            if( p.isSneaking() && sneaking ){
                e.makeAlert( "Sprint","a 2" );
            }
            if( p.getFoodLevel() < 7 && food ){
                e.makeAlert( "Sprint","a 3" );
            }
            if( p.hasPotionEffect( PotionEffectType.BLINDNESS ) && blindness ){
                e.makeAlert( "Sprint","a 4" );
            }
        }
        if( p.isBlocking() && e.getTo().distance( e.getFrom() ) > SPEED_BLOCKING && distance ){
            e.makeAlert( "Sprint","a 5" );
        }
    }
}
