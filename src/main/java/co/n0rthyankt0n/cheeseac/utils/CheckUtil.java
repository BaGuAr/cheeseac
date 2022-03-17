package co.n0rthyankt0n.cheeseac.utils;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.managers.Check;

//import java.awt.*;
//import java.util.ArrayList;
//import java.util.HashMap;

import java.util.*;


public class CheckUtil {

//    public static List checklist = new List();
//    public static List cl_default = new List();
//    public static ArrayList cl_def_array = new ArrayList<>();
//    public static List checkCategorys = new List();
//    public static ArrayList checkCategoryArry = new ArrayList<>();
//    public static HashMap<String, Check> checks = new HashMap<>();
//    public static boolean firstSetup = false;
    public static List checklist = new ArrayList();
    public static List cl_default = new ArrayList();
    public static List cl_def_array = new ArrayList<>();
    public static List checkCategorys = new ArrayList();
    public static List checkCategoryArry = new ArrayList<>();
    public static HashMap<String, Check> checks = new HashMap<>();
    public static boolean firstSetup = false;

    public static void setup(){
        if(!firstSetup){
//            checklist.add( "Aim.a" );
//            checklist.add( "Aim.b" );
//            checklist.add( "Aim.c" );
            checklist.add( "Reach.a" );
//            checklist.add( "Reach.b" );

            checklist.add( "Fly.a" );
            checklist.add( "Fly.b" );
            checklist.add( "Fly.c" );
            checklist.add( "Fly.d" );
            checklist.add( "Fly.e" );
            checklist.add( "Fly.f" );
            checklist.add( "Fly.g" );
            checklist.add( "Fly.h" );
            checklist.add( "Fly.i" );
            checklist.add( "Fly.j" );
            checklist.add( "Fly.k" );
            checklist.add( "Fly.l" );
            checklist.add( "Speed.a" );
            checklist.add( "Speed.b" );
            checklist.add( "Speed.c" );
            checklist.add( "Speed.d" );
            checklist.add( "Speed.e" );
            checklist.add( "Sprint.a" );
            checklist.add( "Invalid.a" );
            checklist.add( "Invalid.b" );
            checklist.add( "Invalid.c" );
            checklist.add( "Invalid.d" );
            checklist.add( "Invalid.e" );
            checklist.add( "Invalid.f" );
            checklist.add( "Invalid.g" );
            checklist.add( "Invalid.h" );
            checklist.add( "Jump.a" );
            checklist.add( "Jump.b" );
            checklist.add( "Jump.c" );
            checklist.add( "GroundSpoof.a" );
            checklist.add( "Timer.a" );
            checklist.add( "InvMove.a" );
            checklist.add( "AntiCactus.a" );
            checklist.add( "FastEat.a" );


            cl_default = checklist;

            for(Object check : cl_default){
                cl_def_array.add( check.toString() );
            }
        }else {
            checkCategorys.clear();
            checkCategoryArry.clear();
            checks.clear();
        }





        ArrayList added = new ArrayList<>();
        for(Object check : checklist){
            String[] c = check.toString().split( "\\." );

            if(check.toString().startsWith( "Combat" )){
                String ch = check.toString().replace( "Combat.","" );
                c = ch.split( "\\." );
                //System.out.println( ch + " " + c[0] + " " + c[1] + " | " + check );

                boolean status = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".status");
                boolean flagStatus = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".flagStatus");

                checks.put( ch , new Check( c[0] , c[1].toUpperCase() , status,flagStatus,true) );
                checklist.remove( check );
                checklist.add( ch );
            }else {

                boolean status = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".status");
                boolean flagStatus = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".flagStatus");

                checks.put( check.toString() , new Check( c[0] , c[1].toUpperCase() , status,flagStatus) );
            }


            if(!added.contains( c[0] )){
                added.add( c[0] );
                checkCategorys.add( c[0] );
            }
        }
        checkCategoryArry = added;

        firstSetup = true;
    }


//    public static void setup(){
//        if(!firstSetup){
//            checklist.add( "Fly.a" );
//            checklist.add( "Fly.b" );
//            checklist.add( "Fly.c" );
//            checklist.add( "Fly.d" );
//            checklist.add( "Fly.e" );
//            checklist.add( "Speed.a" );
//            checklist.add( "Speed.b" );
//            checklist.add( "Speed.c" );
//            checklist.add( "Sprint.a" );
//            checklist.add( "Jump.a" );
//            checklist.add( "Jump.b" );
//            checklist.add( "GroundSpoof.a" );
//            checklist.add( "Timer.a" );
//            checklist.add( "InvMove.a" );
//            checklist.add( "AntiCactus.a" );
//
//
//            cl_default = checklist;
//
//            for(String check : cl_default.getItems()){
//                cl_def_array.add( check );
//            }
//        }else {
//            checkCategorys.removeAll();
//            checkCategoryArry.clear();
//            checks.clear();
//        }
//
//
//
//
//
//        ArrayList added = new ArrayList<>();
//        for(String check : checklist.getItems()){
//            String[] c = check.split( "\\." );
//
//            if(check.startsWith( "Combat" )){
//                String ch = check.replace( "Combat.","" );
//                c = ch.split( "\\." );
//                //System.out.println( ch + " " + c[0] + " " + c[1] + " | " + check );
//
//                boolean status = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".status");
//                boolean flagStatus = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".flagStatus");
//
//                checks.put( ch , new Check( c[0] , c[1].toUpperCase() , status,flagStatus,true) );
//                checklist.remove( check );
//                checklist.add( ch );
//            }else {
//
//                boolean status = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".status");
//                boolean flagStatus = CheeseAC.config.getBoolean( "settings." + c[0] + "." + c[1].toUpperCase() + ".flagStatus");
//
//                checks.put( check , new Check( c[0] , c[1].toUpperCase() , status,flagStatus) );
//            }
//
//
//            if(!added.contains( c[0] )){
//                added.add( c[0] );
//                checkCategorys.add( c[0] );
//            }
//        }
//        checkCategoryArry = added;
//
//        firstSetup = true;
//    }


    public static Check getChecks(String check,String type){
        for(Check theCheck : checks.values()){
            if(theCheck.getCheck().equalsIgnoreCase( check )){
                if(theCheck.getType().equalsIgnoreCase( type )){
                    return theCheck;
                }
            }
        }
        return null;
    }

    public static Check getChecks(String path){
        String[] c1 = path.split( "\\." );
        for(Check theCheck : checks.values()){
            if(theCheck.getCheck().equalsIgnoreCase( c1[0] )){
                if(theCheck.getType().equalsIgnoreCase( c1[1] )){
                    return theCheck;
                }
            }
        }
        return null;
    }

    public static int getCheckTypesAmount(String check){
        int a = 0;
        for(Check theCheck : checks.values()){
            if(theCheck.getCheck().equalsIgnoreCase( check )){
                a = a + 1;
            }
        }
        return a;
    }

    public static void setCheckStatus(String check,String type,boolean bol){
        for(Check theCheck : checks.values()){
            if(theCheck.getCheck().equalsIgnoreCase( check ) && theCheck.getType().equalsIgnoreCase( type )){
                theCheck.setCheckStatus( bol );
            }
        }
    }

    public static void setFlagStatus(String check,String type,boolean bol){
        for(Check theCheck : checks.values()){
            if(theCheck.getCheck().equalsIgnoreCase( check ) && theCheck.getType().equalsIgnoreCase( type )){
                theCheck.setFlagStatus( bol );
            }
        }
    }

    public static void checkSaver(){
        for(Check check : checks.values()){
            CheeseAC.config.set( "settings." + check.getCheck() + "." + check.getType().toUpperCase() + ".status" , check.getStatus());
            CheeseAC.config.set( "settings." + check.getCheck() + "." + check.getType().toUpperCase() + ".flagStatus" , check.getFlagStatus());
        }
        CheeseAC.ConfigSaveAndReload();
    }
}
