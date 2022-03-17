package co.n0rthyankt0n.cheeseac.commands;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.utils.FlagCmdMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class FlagCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if(sender instanceof Player ){
            if(sender.hasPermission( CheeseAC.flagLogAndCmdPerm )){
                Player p = ( (Player) sender ).getPlayer();
                FlagCmdMap.flagStatus.put( p , !FlagCmdMap.flagStatus.getOrDefault( p , false ) );
                if(FlagCmdMap.flagStatus.get( p )){
                    p.sendMessage( "§bCheese§9AC§8 >§e Alert is now §aENABLED" );
                }else {
                    p.sendMessage( "§bCheese§9AC§8 >§e Alert is now §cDISABLED" );

                }

            }
        }
        return true;
    }
}
