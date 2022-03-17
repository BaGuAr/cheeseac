package co.n0rthyankt0n.cheeseac.utils;

import net.minecraft.server.v1_8_R3.Scoreboard;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GroundUtil {
    public static boolean isOnGroundAround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isHeadUpWater(Location loc) {
        loc = loc.clone().subtract( 0 , 2 , 0 );
        int radius = 2;
        for (int x = -radius; x < radius ; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    x = Math.abs( x );
                    y = Math.abs( y );
                    z = Math.abs( z );
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.isLiquid() ||  block.getType().equals( Material.WATER) ||  block.getType().equals( Material.LAVA )) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public static boolean isHeadUpGround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius ; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    x = Math.abs( x );
                    y = Math.abs( y );
                    z = Math.abs( z );
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isAroundSlime(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().toString().contains( "SLIME" )) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isAround(Location loc,String str) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().toString().toLowerCase().contains( str.toLowerCase() )) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isAround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isAroundIce(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().toString().contains( "ICE" )) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isBadBlockAround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    String b = block.getType() + "";
                    if(b.contains( "LADDER" ) || b.contains( "VINE" ) || b.contains( "WEB" )
                            || b.contains( "LILY" ) || b.contains( "SNOW" ) || b.contains( "CARPET" ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isOnGroundAround2(Location loc,int yv) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -yv; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isNearBlock(Location loc) {

        return false;
    }
    public static boolean OnGround(Location loc){
        boolean onMathGround =  loc.getY() % 0.015625 < 0.0001;
        boolean onAroundGround =  isOnGroundAround( loc );
        if(onMathGround && !onAroundGround){
            return false;
        }else if(!onMathGround){
            return false;
        }
        return true;
    }
    public static boolean isWaterAround(Location loc ,int radius ,int radiusY ) {
        for (int x = -radius; x < radius; x++) {
            for (int y = -radiusY; y < radiusY; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.isLiquid() || block.equals( Material.WATER ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isStandingBoat(Player p){
        boolean r = false;
        for(Entity e : p.getWorld().getEntities()){
            double distance = e.getLocation().distance( p.getLocation() );
            if(distance <= 1.5 && e.getName().toLowerCase().contains( "boat" ) && !(e instanceof Player )){
                r = true;
            }
        }
        return r;
    }


}
