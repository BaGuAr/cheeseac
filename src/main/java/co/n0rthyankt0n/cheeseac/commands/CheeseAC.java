package co.n0rthyankt0n.cheeseac.commands;

import co.n0rthyankt0n.cheeseac.managers.Check;
import co.n0rthyankt0n.cheeseac.utils.CheckUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CheeseAC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        int enabled = 0;
        for(Check c : CheckUtil.checks.values()){ if(c.getStatus()){ enabled = enabled + 1; } }
        String msg = "CheeseAC Version : " + co.n0rthyankt0n.cheeseac.CheeseAC.plugin.getDescription().getVersion() +
                "\nDescription: " + co.n0rthyankt0n.cheeseac.CheeseAC.plugin.getDescription().getDescription() + "\nAuthor: BaGuAr" +
                "\n" + enabled + "/" + CheckUtil.checks.size() + " checks are enabled. ";
        sender.sendMessage( msg );

        return true;
    }
}
