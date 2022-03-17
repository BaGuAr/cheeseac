package co.n0rthyankt0n.cheeseac.utils;

//import java.awt.List;

import java.util.ArrayList;
import java.util.List;

public class CheckListUtil {

    public static List getList(int since,int max,String check){
        List l = new ArrayList();
        int a = 0;
        for(Object amc1 : CheckUtil.checklist){
            String[] amc2 = amc1.toString().split( "\\." );
            if(amc2[0].equalsIgnoreCase( check )){
                if( ( since <= a ) && !( a >= max ) ){
                    l.add( amc1.toString() );
                }
                a = a +1;
            }
        }
        return l;
    }

    public static List getMovement(){
        List l = new ArrayList();
        for(Object check : CheckUtil.checkCategorys){
            if(check.toString().contains( "Fly" ) || check.toString().contains( "Speed" ) || check.toString().contains( "InvMove" )){
                l.add( check.toString() );
            }
            if(check.toString().contains( "Reach" ) || check.toString().contains( "Reach" )){}
        }
        return l;
    }

    public static List getCombat(){
        List l = new ArrayList();
        for(Object check : CheckUtil.checkCategorys){
            if(check.toString().contains( "Reach" ) || check.toString().contains( "Reach" )){
                l.add( check.toString() );
            }
        }
        return l;
    }

//    public static List getList(int since,int max,String check){
//        List l = new List();
//        int a = 0;
//        for(Object amc1 : CheckUtil.checklist){
//            String[] amc2 = amc1.toString().split( "\\." );
//            if(amc2[0].equalsIgnoreCase( check )){
//                if( ( since <= a ) && !( a >= max ) ){
//                    l.add( amc1.toString() );
//                }
//                a = a +1;
//            }
//        }
//        return l;
//    }
//
//    public static List getMovement(){
//        List l = new List();
//        for(Object check : CheckUtil.checkCategorys){
//            if(check.toString().contains( "Fly" ) || check.toString().contains( "Speed" ) || check.toString().contains( "InvMove" )){
//                l.add( check.toString() );
//            }
//            if(check.toString().contains( "Reach" ) || check.toString().contains( "Reach" )){}
//        }
//        return l;
//    }
//
//    public static List getCombat(){
//        List l = new List();
//        for(Object check : CheckUtil.checkCategorys){
//            if(check.toString().contains( "Reach" ) || check.toString().contains( "Reach" )){
//                l.add( check.toString() );
//            }
//        }
//        return l;
//    }

//    public static List getList(int since,int max,String check){
//        List l = new List();
//        int a = 0;
//        for(String amc1 : CheckUtil.checklist.getItems()){
//            String[] amc2 = amc1.split( "\\." );
//            if(amc2[0].equalsIgnoreCase( check )){
//                if( ( since <= a ) && !( a >= max ) ){
//                    l.add( amc1 );
//                }
//                a = a +1;
//            }
//        }
//        return l;
//    }
//
//    public static List getMovement(){
//        List l = new List();
//        for(String check : CheckUtil.checkCategorys.getItems()){
//            if(check.contains( "Fly" ) || check.contains( "Speed" ) || check.contains( "InvMove" )){
//                l.add( check );
//            }
//            if(check.contains( "Reach" ) || check.contains( "Reach" )){}
//        }
//        return l;
//    }
//
//    public static List getCombat(){
//        List l = new List();
//        for(String check : CheckUtil.checkCategorys.getItems()){
//            if(check.contains( "Reach" ) || check.contains( "Reach" )){
//                l.add( check );
//            }
//        }
//        return l;
//    }
}
