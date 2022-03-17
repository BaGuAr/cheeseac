package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.Location;

public class Diff {

    private double x,y,z;

    public Diff(Location to,Location from){
        this.x = (from.getX() > to.getX() ? from.getX() : to.getX()) - (from.getX() < to.getX() ? from.getX() : to.getX());
        this.y = (from.getY() > to.getY() ? from.getY() : to.getY()) - (from.getY() < to.getY() ? from.getY() : to.getY());
        this.z = (from.getZ() > to.getZ() ? from.getZ() : to.getZ()) - (from.getZ() < to.getZ() ? from.getZ() : to.getZ());
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }

}
