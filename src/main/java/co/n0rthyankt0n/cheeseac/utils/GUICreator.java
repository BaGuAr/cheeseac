package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.managers.Check;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUICreator {
//    public static Inventory getChecks(String category){
//        Inventory myInventory = Bukkit.createInventory( null,9*5, "CheeseAC-Settings(CHECKS)" );
//        {
//            for(int a = 0; a < ( 9 * 5 ); a++){
//                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
//            }
//
//            int slot = -1;
//            for(String check : CheckUtil.checklist.getItems()) {
//                String[] c1 = check.split( "\\." );
//                if(c1[0].equalsIgnoreCase( category )){
//                    Check c = CheckUtil.getChecks( c1[0] , c1[1] );
//                    ItemStack stack;
//                    boolean a = c.getStatus();
//                    if(a){
//                        stack =  new ItemStack( Material.ENCHANTED_BOOK );
//                    }else{
//                        stack =  new ItemStack( Material.BOOK );
//                    }
//                    ItemMeta meta = stack.getItemMeta( );
//                    meta.setDisplayName( check.toUpperCase() );
//                    List<String> lore1 = new ArrayList<>();
//                    meta.setLore( lore1 );
//                    stack.setItemMeta( meta );
//                    slot = slot +1;
//
//
//
//                    myInventory.setItem( slot + 10 ,new ItemStack( stack ) );
//                }
//            }
//            ItemStack back;
//            back = new ItemStack(Material.ARROW);
//            ItemMeta backm = back.getItemMeta();
//            backm.setDisplayName( "BACK" );
//            back.setItemMeta(backm);
//            myInventory.setItem( 44 , new ItemStack( back ) );
//
//        }
//        return myInventory;
//    }
//    public static Inventory getCheckCategorys(){
//        Inventory myInventory = Bukkit.createInventory( null,9*5, "CheeseAC-Settings(CATEGORY)" );
//        {
//
//            for(int a = 0; a < ( 9 * 5 ); a++){
//                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
//            }
//
//            int slot = -1;
//            for(String check : CheckUtil.checkCategorys.getItems()) {
//                //String[] c1 = check.split( "\\." );
//                ItemStack stack;
//                stack =  new ItemStack( Material.BOOK );
//
//                int am = 0;
//
//                for(String amc1 : CheckUtil.checklist.getItems()){
//                    String[] amc2 = amc1.split( "\\." );
//                    if(amc2[0].equalsIgnoreCase( check )){
//                        am = am + 1;
//                    }
//                }
//
//                stack.setAmount( am );
//                ItemMeta meta = stack.getItemMeta( );
//                meta.setDisplayName( check );
//                List<String> lore1 = new ArrayList<>();
//                meta.setLore( lore1 );
//                stack.setItemMeta( meta );
//                slot = slot +1;
//                int s = slot + 10;
//
//                if(s == 17 || s == 26 || s == 35 || s == 45 || s == 53){
//                    slot = slot + 2;
//                }
//
//                myInventory.setItem( slot + 10 ,new ItemStack( stack ) );
//
//            }
//
//            ItemStack back;
//            back = new ItemStack(Material.ARROW);
//            ItemMeta backm = back.getItemMeta();
//            backm.setDisplayName( "BACK" );
//            back.setItemMeta(backm);
//            myInventory.setItem( 44 , new ItemStack( back ) );
//
//        }
//        return myInventory;
//    }
public static Inventory getChecks(String category){
    Inventory myInventory = Bukkit.createInventory( null,9*5, "CheeseAC-Settings(CHECKS)" );
    {
        for(int a = 0; a < ( 9 * 5 ); a++){
            myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
        }

        int slot = -1;
        for(Object check : CheckUtil.checklist) {
            String[] c1 = check.toString().split( "\\." );
            if(c1[0].equalsIgnoreCase( category )){
                Check c = CheckUtil.getChecks( c1[0] , c1[1] );
                ItemStack stack;
                boolean a = c.getStatus();
                if(a){
                    stack =  new ItemStack( Material.ENCHANTED_BOOK );
                }else{
                    stack =  new ItemStack( Material.BOOK );
                }
                ItemMeta meta = stack.getItemMeta( );
                meta.setDisplayName( check.toString().toUpperCase() );
                List<String> lore1 = new ArrayList<>();
                meta.setLore( lore1 );
                stack.setItemMeta( meta );
                slot = slot +1;



                myInventory.setItem( slot + 10 ,new ItemStack( stack ) );
            }
        }
        ItemStack back;
        back = new ItemStack(Material.ARROW);
        ItemMeta backm = back.getItemMeta();
        backm.setDisplayName( "BACK" );
        back.setItemMeta(backm);
        myInventory.setItem( 44 , new ItemStack( back ) );

    }
    return myInventory;
}
    public static Inventory getCheckCategorys(){
        Inventory myInventory = Bukkit.createInventory( null,9*5, "CheeseAC-Settings(CATEGORY)" );
        {

            for(int a = 0; a < ( 9 * 5 ); a++){
                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
            }

            int slot = -1;
            for(Object check : CheckUtil.checkCategorys) {
                //String[] c1 = check.split( "\\." );
                ItemStack stack;
                stack =  new ItemStack( Material.BOOK );

                int am = 0;

                for(Object amc1 : CheckUtil.checklist){
                    String[] amc2 = amc1.toString().split( "\\." );
                    if(amc2[0].equalsIgnoreCase( check.toString() )){
                        am = am + 1;
                    }
                }

                stack.setAmount( am );
                ItemMeta meta = stack.getItemMeta( );
                meta.setDisplayName( check.toString() );
                List<String> lore1 = new ArrayList<>();
                meta.setLore( lore1 );
                stack.setItemMeta( meta );
                slot = slot +1;
                int s = slot + 10;

                if(s == 17 || s == 26 || s == 35 || s == 45 || s == 53){
                    slot = slot + 2;
                }

                myInventory.setItem( slot + 10 ,new ItemStack( stack ) );

            }

            ItemStack back;
            back = new ItemStack(Material.ARROW);
            ItemMeta backm = back.getItemMeta();
            backm.setDisplayName( "BACK" );
            back.setItemMeta(backm);
            myInventory.setItem( 44 , new ItemStack( back ) );

        }
        return myInventory;
    }
    public static Inventory getCheck(String path){
        Check c;
        Inventory myInventory = Bukkit.createInventory( null,9*3, "CheeseAC-Settings(" + path + ")" );
        {

            Check check = CheckUtil.getChecks( path );
            if( check != null){

                for(int a = 0; a < ( 9 * 3 ); a++){
                    myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
                }

                c = check;
                ItemStack checkStatusStack;

                if(check.getStatus()){
                    checkStatusStack =  new ItemStack( Material.ENCHANTED_BOOK );
                }else{
                    checkStatusStack =  new ItemStack( Material.BOOK );
                }
                ItemMeta checkStatusMeta = checkStatusStack.getItemMeta( );
                if(check.getStatus()){
                    checkStatusMeta.setDisplayName( "(CHECK)Click to Disable" );
                }else {
                    checkStatusMeta.setDisplayName( "(CHECK)Click to Enable" );
                }
                List<String> checkStatusLore1 = new ArrayList<>();
                checkStatusMeta.setLore( checkStatusLore1 );
                checkStatusStack.setItemMeta( checkStatusMeta );
                myInventory.setItem( 11,new ItemStack( checkStatusStack ) );


                ItemStack flagStatusStack;
                if(check.getFlagStatus()){
                    flagStatusStack =  new ItemStack( Material.ENCHANTED_BOOK );
                }else{
                    flagStatusStack =  new ItemStack( Material.BOOK );
                }
                ItemMeta flagStatusMeta = flagStatusStack.getItemMeta( );
                if(check.getStatus()){
                    flagStatusMeta.setDisplayName( "(FLAG)Click to Enable" );
                }else {
                    flagStatusMeta.setDisplayName( "(FLAG)Click to Enable" );
                }
                List<String> checkStatusLore2 = new ArrayList<>();
                flagStatusMeta.setLore( checkStatusLore2 );
                flagStatusStack.setItemMeta( flagStatusMeta );
                myInventory.setItem( 15,new ItemStack( flagStatusStack ) );

                ItemStack back;
                back = new ItemStack(Material.ARROW);
                ItemMeta backm = back.getItemMeta();
                backm.setDisplayName( "BACK" );
                back.setItemMeta(backm);
                myInventory.setItem( 26 , new ItemStack( back ) );
            }else {
                return null;
            }

        }
        return myInventory;//new Setting( myInventory , c );
    }

    public static Inventory getMenu(){
        Inventory myInventory = Bukkit.createInventory( null,9*3, "CheeseAC-Settings(MENU)" );
        {
            for(int a = 0; a < ( 9 * 3 ); a++){
                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
            }


            int enabled = 0;
            for(Check c : CheckUtil.checks.values()){ if(c.getStatus()){ enabled = enabled + 1; } }

            List<String>lore = new ArrayList<>();
            lore.add( "Author: BaGuAr" );
            lore.add( "Version: " + CheeseAC.plugin.getDescription().getVersion() );
            lore.add( "Description: " + CheeseAC.plugin.getDescription().getDescription() );
            myInventory.setItem( 11 ,make( Material.BOOK_AND_QUILL , "Information" , 1 , lore ) );

            List<String>lore1 = new ArrayList<>();
            lore1.add( enabled + "/" + CheckUtil.checks.size() + " checks are enabled." );
            myInventory.setItem( 13 ,make( Material.BOOK_AND_QUILL , "Checks" , 1 , lore1 ) );

            List<String>lore2 = new ArrayList<>();
            if(CheeseAC.status){
                lore2.add( "Status : ENABLE | Click to disable anti-cheat" );
                myInventory.setItem( 15 ,make( Material.BOOK_AND_QUILL , "Click to Disable" , 1 , lore2 ) );
            }else {
                lore2.add( "Status : DISABLE | Click to enable anti-cheat" );
                myInventory.setItem( 15 ,make( Material.BOOK_AND_QUILL , "Click to Enable" , 1 , lore2 ) );
            }

            myInventory.setItem( 22 ,make( Material.EYE_OF_ENDER , "Change-Alert-Theme",1) );

        }
        return myInventory;
    }

    //24 is max since is 0
    public static Inventory getTestGUI_1(String check,int page){
        Inventory myInventory = Bukkit.createInventory( null,9*5, "CheeseAC-Settings(P: " + page + ")" );
        {

            for(int a = 0; a < ( 9 * 5 ); a++){
                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"",1) );
            }


            //java.awt.
            List checklist;
//            if(page == 1){
//                checklist = CheckListUtil.getList( 0, 24 , check );
//            }else {
//                checklist = CheckListUtil.getList( 24 * page, 24 , check );
//            }
            if(page == 1){
                checklist = CheckListUtil.getList( 0, 24 , check );
            }else {
                checklist = CheckListUtil.getList( 24 * page, 24 , check );
            }

            int slot = -1;
            for(Object cd : checklist){
                String c = cd.toString();
                String[] c1 = c.split( "\\." );
                if(c1[0].equalsIgnoreCase( check )){
                    Check ch = CheckUtil.getChecks( c1[0] , c1[1] );
                    ItemStack stack;
                    boolean a = ch.getStatus();
                    if(a){
                        stack =  new ItemStack( Material.ENCHANTED_BOOK );
                    }else{
                        stack =  new ItemStack( Material.BOOK );
                    }
                    ItemMeta meta = stack.getItemMeta( );
                    meta.setDisplayName( c.toUpperCase() );
                    List<String> lore1 = new ArrayList<>();
                    meta.setLore( lore1 );
                    stack.setItemMeta( meta );
                    slot = slot +1;
                    int s = slot + 10;

                    if(s == 17 || s == 26 || s == 35 || s == 45 || s == 53){
                        slot = slot + 2;
                    }
                    myInventory.setItem( slot + 10 ,new ItemStack( stack ) );
                }

                if(page != 1){
                    myInventory.setItem( 18 ,new ItemStack( make( Material.ARROW,"BACK(" + check + ")",1 ) ) );
                }

                boolean next = getTestGUI_1( check,page + 1 ).contains( Material.BOOK );
                if(!next){
                    next = getTestGUI_1( check,page + 1 ).contains( Material.ENCHANTED_BOOK );
                }
                if(next){
                    myInventory.setItem( 26 ,new ItemStack( make( Material.ARROW,"NEXT(" + check + ")",1 ) ) );
                }
            }
            ItemStack back;
            back = new ItemStack(Material.ARROW);
            ItemMeta backm = back.getItemMeta();
            backm.setDisplayName( "BACK" );
            back.setItemMeta(backm);
            myInventory.setItem( 44 , new ItemStack( back ) );
        }
        return myInventory;
    }

    public static Inventory getTestGUI_2(){
        Inventory myInventory = Bukkit.createInventory( null,9*3, "CheeseAC-Settings(CATEGORY2)" );
        {

            for(int a = 0; a < ( 9 * 3 ); a++){
                myInventory.setItem( a , make(Material.STAINED_GLASS_PANE,"Combat",1) );
            }
            myInventory.setItem( 11 , make(Material.DIAMOND_SWORD,"Combat",1) );
            myInventory.setItem( 15 , make(Material.FEATHER,"Movement",1) );


        }
        return myInventory;
    }
    public static ItemStack make(Material material,String name,int amount){
        ItemStack stack;
        stack = new ItemStack(material);
        stack.setAmount( amount );
        ItemMeta meta = stack.getItemMeta( );
        meta.setDisplayName( name );
        List<String> lore1 = new ArrayList<>();
        meta.setLore( lore1 );
        stack.setItemMeta( meta );
        return stack;
    }

    public static ItemStack make(Material material,String name,int amount,List list){
        ItemStack stack;
        stack = new ItemStack(material);
        stack.setAmount( amount );
        ItemMeta meta = stack.getItemMeta( );
        meta.setDisplayName( name );
        meta.setLore( list );
        stack.setItemMeta( meta );
        return stack;
    }
}
