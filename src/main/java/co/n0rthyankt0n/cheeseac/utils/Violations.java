package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.managers.Check;
import org.bukkit.entity.Player;
import java.util.*;



public class Violations {

    public static HashMap<Player,Integer > vl = new HashMap<>();


    public static int vioJustGet(Player p){
        return vl.getOrDefault( p , 0 );
    }

    public static int vioAddGet(Player p){
        vl.put( p , vl.getOrDefault( p , 0 ) + 1);
        return vl.get( p );
    }

    public static int vioReset(Player p){
        vl.put( p, 0 );
        return vioJustGet( p );
    }


    public static HashMap<Player, HashMap<String, Integer>> vls = new HashMap<>();

    public  static void setupPlayer(Player p){
        if( vls.get( p ) == null ){
            HashMap<String,Integer> def = new HashMap<>();
            for(Object obj : CheckUtil.checklist) {
                String path = obj.toString( ).toUpperCase();
                def.put( path ,0 );
            }
            vls.put( p , def );
        }
    }

    public static void addVL(Player p,String path){
        setupPlayer( p );
        vls.get( p ).put( path,vls.get( p ).getOrDefault( path , 0 ) + 1 );
    }

    public static int getVL(Player p,String path){
        setupPlayer( p );
        if( vls.get( p ) != null ){
            return vls.get( p ).get( path );
        } else {
            vls.get( p ).put( path,0 );
            return 0;
        }
    }
}
