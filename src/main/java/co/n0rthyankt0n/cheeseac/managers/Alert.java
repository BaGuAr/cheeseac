package co.n0rthyankt0n.cheeseac.managers;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.FlagEvent;
import co.n0rthyankt0n.cheeseac.events.PreFlagEvent;
import co.n0rthyankt0n.cheeseac.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Alert {



    Player player;
    CheesePlayer cp;
    String log,consLog,check,type,theme1;
    boolean alreadysend,status;
    Check c;

    public Alert(CheesePlayer p,String check,String type){
        this.alreadysend = false;
        this.player = p.getPlayer();
        this.cp = p;
        this.check = check;
        this.type = type;
        Check c = CheckUtil.getChecks( check, type);
        this.c = c;
        if(c != null){
            this.status = c.getStatus();
            this.theme1 = c.getAsTheme1();
            //this.log = "§bCheese§8 >§f " + player.getName() + " §7failed§f " + theme1 + "§7 VL[§9%%vio%%§7]";
            this.log = CheeseAC.theme + "§8 >§f " + player.getName() + " §7failed§f " + theme1 + "§7 VL[§9%%vio%%§7]";
            this.consLog = CheeseAC.consTheme + " > " + player.getName() + " failed " + theme1 + " VL[%%vio%%]";
        }else {
            log = "";
        }
    }


    public String perType = "";
    public Alert(Player p,String check,String type){
        this.alreadysend = false;
        this.player = p;
        this.cp = new CheesePlayer( p );
        this.check = check;
        this.type = (type.contains( " " ) ? type.split( " " )[0] : type);
        this.perType = (type.split( " " ).length == 2 ? type.split( " " )[1] : "");
        Check c = CheckUtil.getChecks( check, this.type );
        this.c = c;
        if(c != null){
            this.status = c.getStatus();
            this.theme1 = c.getAsTheme1();
            //this.log = "§bCheese§8 >§f " + player.getName() + " §7failed§f " + theme1 + "§7 VL[§9%%vio%%§7]";
            //this.log = CheeseAC.theme + "§8 >§f " + player.getName() + " §7failed§f " + theme1 + "§7 VL[§9%%vio%%§7]";
            //this.log = "§e§l[§r§6Cheese§e§l]§f ➤ §r§6" + player.getName() + "§r§f failed:§r §6" + theme1.split( " " )[0] + " (" + type.toUpperCase() + ") §8(§7VL:§c %%vio%%§8) (§7PING:§c " + PingUtil.getPing( p ) + "§8) ";
            //this.log = CheeseAC.theme + "§f ➤ §r§6" + player.getName() + "§r§f failed:§r §6" + theme1.split( " " )[0] + " (" + type.toUpperCase() + ") §8(§7VL:§c %%vio%%§8) (§7PING:§c " + PingUtil.getPing( p ) + "§8) ";
            if(CheeseAC.ping) {
                if(CheeseAC.customAlert){
                    this.log = getFormattedLog( player.getName() , theme1.split( " " )[0],(theme1.split( " " )[ 1 ] + ( perType.equals( "" ) ? "" : " " + perType )),PingUtil.getPing( p ) );
                }else{
                    this.log = CheeseAC.theme + "§f ➤ §r§6" + player.getName( ) + "§r§f failed:§r §6" + theme1.split( " " )[ 0 ] + " (" + theme1.split( " " )[ 1 ] + ( perType.equals( "" ) ? "" : " " + perType ) + ") §8(§7VL:§c %%vio%%§8) (§7PING:§c " + PingUtil.getPing( p ) + "§8) ";
                }
            }else {
                this.log = CheeseAC.theme + "§f ➤ §r§6" + player.getName() + "§r§f failed:§r §6" + theme1.split( " " )[0] + " (" + theme1.split( " " )[1] + (perType.equals( "" ) ? "" : " " + perType) + ") §8(§7VL:§c %%vio%%§8)";
            }
            this.consLog = CheeseAC.consTheme + " > " + player.getName() + " failed " + theme1 + (perType.equals( "" ) ? "" : " " + perType) + " VL[%%vio%%]";
        }else {
            log = "";
        }
    }

    //public String format = "§8(§c§l!§r§8)§e%%player%%§7 failed§d %%check%%§8[§dType%%type%%§8] [VL:§d%%vio%%§8] [PING:§d%%ping%%§8]";

    public String getFormattedLog(String name,String check,String type,int ping){
        return CheeseAC.format.replace( "%%player%%",name ).replace( "%%check-up%%",check.toUpperCase() ).replace( "%%type-up%%",type.toUpperCase() ).replace( "%%check%%",check ).replace( "%%type%%",type ).replace( "%%type2%%",type.replace( " ","-" ) ).replace( "%%ping%%", ping + "ms" ).replace( "%%pingnoms%%", ping + "" );
    }

//    public void send(){
//        if(!alreadysned){
//            for(Player op : Bukkit.getOnlinePlayers( )){
//                if(op.hasPermission( "cheeseac.notify" )){
//                    op.sendMessage( log );
//                }
//            }
//        }
//
//        this.alreadysned = true;
//    }


    public Check send(){
        if(c.getStatus() && CheeseAC.status ){
            if(!alreadysend){
                PreFlagEvent e = new PreFlagEvent( player, c.getCheck() + "." + c.getType() );
                Bukkit.getPluginManager().callEvent(e);
                boolean cancel = e.isCancelled();
                if(!cancel){
                    int vio = Violations.vioAddGet( player );
                    for(Player op : Bukkit.getOnlinePlayers( )){
                        if(op.hasPermission( CheeseAC.flagLogAndCmdPerm ) && FlagCmdMap.flagStatus.getOrDefault( op , false ) ){
                            op.sendMessage( log.replace( "%%vio%%" ,vio + "" ) );
                        }
                    }
                    if(CheeseAC.alertToLog){
                        System.out.println( consLog.replace( "%%vio%%",vio + "" ) );
                    }
                    TextUtil.log = TextUtil.log + "\n" + consLog.replace( "%%vio%%",vio + "" );
                    //CheeseViolations:
                    Violations.addVL( player,e.getCheckPath().toUpperCase() );
                }
                FlagEvent e1 = new FlagEvent( player, c.getCheck() + "." + c.getType() , cancel );
                Bukkit.getPluginManager().callEvent(e1);
            }
            this.alreadysend = true;
            //return c;
        }
        return c;

    }
}
