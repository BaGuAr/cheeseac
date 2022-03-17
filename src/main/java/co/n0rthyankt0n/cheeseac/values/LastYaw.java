package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.entity.Player;

public class LastYaw {

    private double lastYaw;
    private Player p;

    public LastYaw(Player p,double yaw){
        this.p = p;
        this.lastYaw = yaw % 180;
    }

    public Player getPlayer(){
        return p;
    }

    public double getLastYaw(){
        return lastYaw;
    }


}
