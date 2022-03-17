package co.n0rthyankt0n.cheeseac.values;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerData {


    public boolean onGroundServer,onGroundClient;
    public Location lastLocation;
    public double x,y,z,xz,falldist,serverSide;
    PlayerData pd;


    public PlayerData(boolean server,boolean client,Location loc,double x,double y,double z,double xz,PlayerData bef,Player p,double ssf){
        this.onGroundServer = server;
        this.onGroundClient = client;
        this.lastLocation = loc;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pd = bef;
        this.xz = xz;
        this.falldist = p.getFallDistance();
        this.serverSide = ssf;
    }

}
