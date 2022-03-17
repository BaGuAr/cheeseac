package co.n0rthyankt0n.cheeseac.commands;

import co.n0rthyankt0n.cheeseac.utils.GUICreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCmd1 implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if ( sender instanceof Player ) {
            //( (Player) sender ).getPlayer().openInventory( GUICreator.getTestGUI_1( "Fly",1 ) );
            sender.sendMessage( "This command is disabled." );
        }
        return true;
    }
}
