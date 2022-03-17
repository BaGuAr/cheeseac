package co.n0rthyankt0n.cheeseac.commands;

import co.n0rthyankt0n.cheeseac.utils.CheckUtil;
import co.n0rthyankt0n.cheeseac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ViolationsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if( sender instanceof Player && sender.hasPermission( "cheeseac.setting" ) ){
            if( args.length == 0 ){
                sender.sendMessage( "§c/<cheeseviolations/cacviolations/cacvio> <player>" );
            } else if( args.length == 1 ){
                Player p = Bukkit.getPlayer( args[0] );
                if( p != null ){
                    sender.sendMessage( "§a====================================\n§9" + p.getName() + "§a's violations (§9 " + Violations.vioJustGet( p ) + " )." );
                    for(Object ob : CheckUtil.checklist){
                        String check = ob.toString();
                        sender.sendMessage( "§a" + check.toUpperCase().replace( "."," " ) + ":§1 " + Violations.getVL( p , check.toUpperCase() ) );
                    }
                    sender.sendMessage( "§a====================================" );
                }
            }
        }
        return true;
    }
}
