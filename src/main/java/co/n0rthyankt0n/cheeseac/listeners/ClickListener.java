package co.n0rthyankt0n.cheeseac.listeners;

import co.n0rthyankt0n.cheeseac.values.LastClick;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class ClickListener implements Listener {


    public static HashMap<Player, LastClick> lastClickHashMap = new HashMap<>();
    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        lastClickHashMap.put( p , new LastClick( p , System.currentTimeMillis() ) );
    }

    public static HashMap<Player,Long> lastBlockPlace = new HashMap<>();

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        lastBlockPlace.put( p, System.currentTimeMillis() );
    }
}
