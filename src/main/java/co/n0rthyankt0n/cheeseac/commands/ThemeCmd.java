package co.n0rthyankt0n.cheeseac.commands;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.managers.InvGUI;
import co.n0rthyankt0n.cheeseac.utils.GUICreator;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ThemeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if(sender instanceof Player && sender.hasPermission( "cheeseac.setting" ) ){
            if(args.length == 0){
                sender.sendMessage( "§cTheme not found. Themes:" );
                for(int i = 0; i < CheeseAC.amount; i++){
                    sender.sendMessage( "§7[§cTHEME§7]§f<NEW" + ( i + 1 ) + "> " + CheeseAC.alertTheme( "NEW" + ( i + 1 ) ).replace( "%%player%%","Player" ).replace( "%%check-up%%","FLY" ).replace( "%%type-up%%","A" ).replace( "%%check%%","Fly" ).replace( "%%type%%","A" ).replace( "%%ping%%", "XXms" ).replace( "%%vio%%","XX" ).replace( "%%pingnoms%%","XX" ).replace( "%%type2%%", "D-1") );
                }
                sender.sendMessage( "§7[§cTHEME§7]§f<CUSTOM> " + CheeseAC.alertTheme( "custom" ).replace( "%%player%%","Player" ).replace( "%%check-up%%","FLY" ).replace( "%%type-up%%","A" ).replace( "%%check%%","Fly" ).replace( "%%type%%","A" ).replace( "%%ping%%", "XXms" ).replace( "%%vio%%","XX" ).replace( "%%pingnoms%%","XX" ).replace( "%%type2%%", "D-1") );
            }else{
                if( args[0].equalsIgnoreCase( "gui" ) ){
                    InvGUI invGUI =  new InvGUI().create( 9,3,"Alert-Themes" );
                    for(int i = 0; i < CheeseAC.amount; i++ ){
                        invGUI = invGUI.addItem( Material.BOOK,"NEW" + ( i + 1 ),1,i );
                    }
                    invGUI.addItem( Material.BOOK_AND_QUILL , "CUSTOM",1 , CheeseAC.amount );
                    invGUI.addItem( Material.REDSTONE_BLOCK,"OFF",1, 26 );
                    ( (Player) sender ).getPlayer().openInventory( invGUI.getInventory() );
                } else if( args[0].equalsIgnoreCase( "off" ) ){
                    CheeseAC.customAlert = false;
                    CheeseAC.config.set( "alert-theme",args[0].toUpperCase() );
                    CheeseAC.ConfigSaveAndReload();
                    sender.sendMessage( "§cTheme is disabled." );
                } else if( CheeseAC.alertTheme( args[0] ) != "NoFound" ){
                    CheeseAC.format = CheeseAC.alertTheme( args[0] ); //set it!
                    String frm = CheeseAC.format.replace( "%%player%%","Player" ).replace( "%%check-up%%","FLY" ).replace( "%%type-up%%","A" ).replace( "%%check%%","Fly" ).replace( "%%type%%","A" ).replace( "%%ping%%", "XXms" ).replace( "%%vio%%","XX" ).replace( "%%pingnoms%%","XX" ).replace( "%%type2%%", "D-1");
                    CheeseAC.config.set( "alert-theme",args[0].toUpperCase() );
                    CheeseAC.ConfigSaveAndReload();
                    CheeseAC.customAlert = true;
                    sender.sendMessage( "§cTheme is changed to " + args[0] + "\n§aExample:§r " + frm );
                } else {
                    sender.sendMessage( "§cTheme not found." );
                }
            }
        }
        return true;
    }
}
