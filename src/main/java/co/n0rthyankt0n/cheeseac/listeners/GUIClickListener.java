package co.n0rthyankt0n.cheeseac.listeners;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.managers.Check;
import co.n0rthyankt0n.cheeseac.utils.CheckUtil;
import co.n0rthyankt0n.cheeseac.utils.GUICreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIClickListener implements Listener {
    @EventHandler
    public void onPickUp(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory open = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if(open == null){
            return;
        }
        if(player.hasPermission( "cheeseac.setting" )) {
            String openname = open.getName( );
            if ( openname.contains( "CheeseAC-Settings(CATEGORY)" ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String name = item.getItemMeta( ).getDisplayName( );
                        if ( name.contains( "BACK" ) ) {
                            player.openInventory( GUICreator.getMenu( ) );
                        } else {
                            //player.openInventory(GUICreator.getChecks( name ));
                            player.openInventory( GUICreator.getTestGUI_1( name,1 ) );
                        }
                    }
                }
            } else if ( openname.contains( "CheeseAC-Settings(CHECKS)" ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String name = item.getItemMeta( ).getDisplayName( );
                        if ( name.contains( "BACK" ) ) {
                            player.openInventory( GUICreator.getCheckCategorys( ) );
                        } else {
                            Inventory inv = GUICreator.getCheck( name );
                            if ( inv != null ) {
                                player.openInventory( inv );
                            }
                        }
                    }
                }
            } else if ( openname.contains( "CheeseAC-Settings(MENU)" ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String name = item.getItemMeta( ).getDisplayName( );
                        if ( name.contains( "Click to " ) ) {
                            CheeseAC.status = ! CheeseAC.status;
                            player.openInventory( GUICreator.getMenu( ) );
                        } else if ( name.contains( "Checks" ) ) {
                            player.openInventory( GUICreator.getCheckCategorys( ) );
                        } else if(name.contains( "Change-Alert-Theme" )) {
                            Bukkit.dispatchCommand( player , "cheesetheme gui" );
                        }
                    }
                }
            } else if ( openname.contains( "CheeseAC-Settings" ) && openname.contains( "(" ) && openname.contains( ")" ) && ! openname.contains( "(P: " ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String path = openname.replace( "CheeseAC-Settings(","" ).replace( ")","" );
                        Check c = CheckUtil.getChecks( path );
                        if ( c != null ) {
                            String name = item.getItemMeta( ).getDisplayName( );
                            if ( name.contains( "(FLAG)" ) ) {
                                c.setFlagStatus( ! c.getFlagStatus( ) );
                                player.openInventory( GUICreator.getCheck( path ) );
                            } else if ( name.contains( "(CHECK)" ) ) {
                                c.setCheckStatus( ! c.getStatus( ) );
                                player.openInventory( GUICreator.getCheck( path ) );
                            } else if ( name.contains( "BACK" ) ) {
                                //player.openInventory(GUICreator.getChecks( c.getCheck() ));
                                //player.openInventory(GUICreator.getTestGUI_1( name ,1 ));
                                //player.openInventory(GUICreator.getCheckCategorys());
                                player.openInventory( GUICreator.getTestGUI_1( path.split( "\\." )[ 0 ],1 ) );
                            }
                            CheckUtil.checkSaver( );
                        }
                    }
                }
            } else if ( openname.contains( "CheeseAC-Settings(CATEGORY2)" ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String name = item.getItemMeta( ).getDisplayName( );
                        if ( name.contains( "BACK" ) ) {
                            player.openInventory( GUICreator.getMenu( ) );
                        } else if ( name.contains( "Movement" ) ) {

                        } else if ( name.contains( "Combat" ) ) {

                        } else {
                            //player.openInventory(GUICreator.getTestGUI_1( name ,1 ));
                        }
                    }
                }
            } else if ( openname.contains( "CheeseAC-Settings(P: " ) && openname.contains( ")" ) ) {
                e.setCancelled( true );
                if ( item == null ) return;
                if ( item.getItemMeta( ) != null ) {
                    if ( item.getItemMeta( ).getDisplayName( ) != null ) {
                        String name = item.getItemMeta( ).getDisplayName( );
                        String pagestr = openname.replace( "CheeseAC-Settings(P: ","" ).replace( ")","" );
                        int page = Integer.parseInt( pagestr );
                        if ( name.contains( "BACK" ) && e.getSlot( ) != 18 ) {
                            player.openInventory( GUICreator.getCheckCategorys( ) );
                        } else if ( name.contains( "BACK" ) && e.getSlot( ) == 18 ) {
                            player.openInventory( GUICreator.getTestGUI_1( name.replace( "BACK(","" ).replace( ")","" ),page - 1 ) );
                        } else if ( name.contains( "NEXT" ) ) {
                            player.openInventory( GUICreator.getTestGUI_1( name.replace( "NEXT(","" ).replace( ")","" ),page + 1 ) );
                        } else {
                            Inventory inv = GUICreator.getCheck( name );
                            if ( inv != null ) {
                                player.openInventory( inv );
                            }
                        }
                    }
                }
            } else if( openname.contains( "Alert-Themes" ) ){
                e.setCancelled( true );
                if( item == null )return;
                if( item.getItemMeta() == null )return;
                if( item.getItemMeta().getDisplayName() == null )return;
                if( item.getType() == Material.AIR )return;
                String name = item.getItemMeta().getDisplayName();
                player.closeInventory();
                Bukkit.dispatchCommand( player , "cheesetheme " + name );
            }
        }
    }


}
