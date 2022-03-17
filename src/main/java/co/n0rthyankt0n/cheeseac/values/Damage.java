package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Damage {

    private long lastDamageTick;
    private Player p;
    private Entity entity;

    public Damage(Player player,long lastDamageTick,Entity damager){
        this.lastDamageTick = lastDamageTick;
        this.p = player;
        this.entity = damager;
    }

    public Entity getDamager(){
        return entity;
    }

    public long getTick(){
        return lastDamageTick;
    }

    public Player getPlayer(){
        return p;
    }

}
