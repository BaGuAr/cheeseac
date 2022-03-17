package co.n0rthyankt0n.cheeseac;

import co.n0rthyankt0n.cheeseac.checks.combat.Reach;
import co.n0rthyankt0n.cheeseac.checks.player.FastEat;
import co.n0rthyankt0n.cheeseac.commands.ThemeCmd;
import co.n0rthyankt0n.cheeseac.commands.ViolationsCmd;
import co.n0rthyankt0n.cheeseac.listeners.PlayerMoveEventListener;
import co.n0rthyankt0n.cheeseac.checks.movements.*;
import co.n0rthyankt0n.cheeseac.commands.FlagCmd;
import co.n0rthyankt0n.cheeseac.commands.GUIOpenCmd;
import co.n0rthyankt0n.cheeseac.listeners.ClickListener;
import co.n0rthyankt0n.cheeseac.listeners.DamageListener;
import co.n0rthyankt0n.cheeseac.listeners.GUIClickListener;
import co.n0rthyankt0n.cheeseac.utils.CheckUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class CheeseAC extends JavaPlugin {

    public static FileConfiguration config;
    public static CheeseAC plugin;
    public static boolean status,alertToLog = true;
    public static String bypassPerm,flagLogAndCmdPerm;
    public static boolean bypass;
    public static int themeType = 0;
    public static String theme = "§bCheese§8";
    public static String consTheme = "Cheese";
    public static boolean ping = true;
    //public static String format = "§8(§c§l!§r§8)§e %%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";
    public static String format = "§b§l" + theme + "§r§8≫§e %%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";
    public static boolean customAlert = true;

    @Override
    public void onEnable() {
        long startTick = System.currentTimeMillis(); //save onEnable called tick.

        plugin = this;
        saveDefaultConfig();
        config = getConfig();

        CheckUtil.setup( ); //check util setup. ( load check and setting up )
        Invalid.invalidB(); //InvalidB Config.
        InvalidSprint.InvalidSprintA(); //SprintA Config.
        Invalid.invalidC(); //pitch check :)
        Invalid.invalidD(); //InvalidD( FastLadder ) check.
        Invalid.invalidE(); //debug???xd
        Invalid.InvalidH(); //InvalidH(1/2) setupper
        Fly.airtick_F = config.getDouble( "settings.Fly.F.settings.airtick" );
        Fly.FlyG(); //setup FlyG
        Fly.J_UP = config.getBoolean( "settings.Fly.J.settings.UP" );
        Fly.J_DOWN = config.getBoolean( "settings.Fly.J.settings.DOWN" );
        Fly.J_SAME = config.getBoolean( "settings.Fly.J.settings.SAME" );

        bypass = config.getBoolean( "permissions.bypass.status" );
        bypassPerm = config.getString( "permissions.bypass.permission" );
        flagLogAndCmdPerm = config.getString( "permissions.flag-log-enable-command.permission" );
        themeType = config.getInt( "alert-prefix-theme" );
        alertToLog = config.getBoolean( "alert-to-log" );
        status = true;

        if( !config.getString( "alert-theme" ).equalsIgnoreCase( "OFF" ) ){
            if( alertTheme( config.getString( "alert-theme" ) ) != "NoFound" ){
                format = alertTheme( config.getString( "alert-theme" ) );
            }else {
                customAlert = false;
            }
        }else {
            customAlert = false;
        }




//        if(config.getString( "alert-theme" ).equalsIgnoreCase( "NEW1" )){
//            customAlert = true;
//        }else if(config.getString( "alert-theme" ).equalsIgnoreCase( "NEW2" )){
//            format = "§8(§c§l!§r§8)§e %%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";
//            customAlert = true;
//        }else if(config.getString( "alert-theme" ).equalsIgnoreCase( "NEW3" )){
//            format = "§8[§bCHEESE§8] player:§6 %%player%%§8 | check: §6%%check%%§8 | type: §6%%type%%§8 | vl: §6%%vio%%§8 | ping: §6%%ping%%";
//            customAlert = true;
//        }else if(config.getString( "alert-theme" ).equalsIgnoreCase( "NEW4" )){
//            format = "§bCHEESE§8≫§6 %%player%%§8 failed §6%%check%%§8[§6Type%%type%%§8] [VL: §6%%vio%%§8] [PING: §6%%ping%%§8]";
//            customAlert = true;
//        }else if(config.getString( "alert-theme" ).equalsIgnoreCase( "NEW5" )){
//            format = "§8[§cCAC§8]§5 %%player%%§7 is using§a%%check-up%%§8(§b%%type-up%%§8) (§b%%vio%%§8) (§b%%ping%%§8)";
//            customAlert = true;
//        }else {
//            customAlert = false;
//        }

        register( new PlayerMoveEventListener() ); //for CheeseMoveEvent
        register( new DamageListener() ); //for e.nearDamage etc.
        register( new GUIClickListener() ); //for ClickGUI
        register( new ClickListener() ); //for get Last(Inv)Click

        register( new InvMove() );
        register( new Fly() );
        register( new Speed() );
        register( new GroundSpoof() );
        register( new Timer() );
        register( new AntiCactus() );
        register( new InvalidSprint() );
        register( new Jump() );
        register( new Invalid() );
        register( new FastEat() );
        //register( new Aim() );
        register( new Reach() );



        getCommand( "cheesegui" ).setExecutor( new GUIOpenCmd() );
        getCommand( "cheeseac" ).setExecutor( new co.n0rthyankt0n.cheeseac.commands.CheeseAC() );
        getCommand( "cheeseflag" ).setExecutor( new FlagCmd() );
        getCommand( "cheesetheme" ).setExecutor( new ThemeCmd() );
        getCommand( "cheeseviolations" ).setExecutor( new ViolationsCmd() );

        if(themeType == 0){
            theme = "§bCheese§8";
            consTheme = "Cheese";
        }else if(themeType == 1){
            theme = "§8[§bCheese§8]";
            consTheme = "[Cheese]";
        }else if(themeType == 2){
            theme = "§8(§bCheese§8)";
            consTheme = "(Cheese)";
        }

        if(!Bukkit.getBukkitVersion().contains( "1.8." )){
            ping = false;
            System.out.println( "[CheeseAC] Warning: NO SUPPORTING SERVER VERSION.  " );
        }

        System.out.println( "[CheeseAC] CheeseAC " + plugin.getDescription().getVersion() + " successfully loaded in " + (System.currentTimeMillis() - startTick) + "ms" );
    }

    public static int amount = 8; // 4 gui new1 - new.. xd

    public static String alertTheme(String str){
        if(str.equalsIgnoreCase( "NEW1" )){
            format = "§b§l" + theme + "§r§8≫§e %%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW2" )){
            format = "§8(§c§l!§r§8)§e %%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW3" )){
            format = "§8[§bCHEESE§8] player:§6 %%player%%§8 | check: §6%%check%%§8 | type: §6%%type%%§8 | vl: §6%%vio%%§8 | ping: §6%%ping%%";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW4" )){
            format = "§bCHEESE§8≫§6 %%player%%§8 failed §6%%check%%§8[§6Type%%type%%§8] [VL: §6%%vio%%§8] [PING: §6%%ping%%§8]";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW5" )){
            format = "§8[§cCAC§8]§5 %%player%%§7 is using §a%%check-up%%§8(§b%%type-up%%§8) (§b%%vio%%§8) (§b%%ping%%§8)";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW6" )){
            format = "§8[§cCAC§8]§5 %%player%%§7 violated §a%%check-up%%§8(§b%%type-up%%§8) (§b%%vio%%vl§8) (§b%%ping%%§8)";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW7" )){
            format = "§cC§8>§5 %%player%%§7 violated §a%%check-up%%§8(§b%%type-up%%§8) (§b%%vio%%vl§8) (§b%%ping%%§8)";
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "NEW8" )){ //BetterBubble theme
            format = "§7(§c!§7)§c %%player%%§f failed §6%%check%%§8 (§eVL§a %%vio%%§8) (§eType:§a %%type2%%§8) (§e%%pingnoms%%§ams§8)" ;
            //customAlert = true;
        }else if(str.equalsIgnoreCase( "CUSTOM" )){
            return config.getString( "alert-format" ).replace( "&","§" ).replace( "§§","&" );
        }else if(str.equalsIgnoreCase( "OFF" )){
            //..
        }else {
            //customAlert = false;
            return "NoFound";
        }
        return format;
    }

    @Override
    public void onDisable(){
//        long tick = System.currentTimeMillis();
//        String file = "" + LocalDate.now() + ".txt";
//        System.out.println( "[CheeseAC] Saving Alerts since server start to " + file + "!");
//        boolean saved = TextUtil.saveTxt( file,TextUtil.log );
//        System.out.println( "[CheeseAC] " + ( saved ? "Alerts is successfully saved in (" + (System.currentTimeMillis() - tick) + "ms)" : "Failed to save Alerts..." ) );
    }

    public static void ConfigSaveAndReload(){
        plugin.saveConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
    }

    public void register(Listener listener){
        Bukkit.getPluginManager().registerEvents( listener , this );
    }
}
