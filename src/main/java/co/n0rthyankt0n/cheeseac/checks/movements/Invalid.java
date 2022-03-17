package co.n0rthyankt0n.cheeseac.checks.movements;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.events.CheeseMoveEvent;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.values.BetterValue;
import co.n0rthyankt0n.cheeseac.values.Delta;
import co.n0rthyankt0n.cheeseac.values.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class Invalid implements Listener {

    HashMap<Player, Integer> vl = new HashMap<>( );
    static boolean xz, y = true;

    public static void invalidB() {
        xz = CheeseAC.config.getBoolean( "settings.Invalid.B.settings.XZ" );
        y = CheeseAC.config.getBoolean( "settings.Invalid.B.settings.Y" );
    }

    @EventHandler
    public void onMove(CheeseMoveEvent e) {
        if ( e.canBypass( ) ) return;
        if ( e.isCancelled( ) ) return;
        if ( ! e.isMoving( ) ) return;
        PlayerData pd = e.getPlayerData( );
        Player p = e.getPlayer( );

        if ( vl.get( p ) == null ) {
            int s = 5 * 20;
            long time = s;
            new BukkitRunnable( ) {
                public void run() {
                    vl.put( p,null );
                }
            }.runTaskLater( CheeseAC.plugin,time );
        }

        if ( ! pd.onGroundClient && pd.onGroundServer && !e.isNearLastAboveBlock() && !e.isNearLastOnSlime() && !e.isNearDamage()) {
            vl.put( p,( vl.get( p ) != null ? vl.get( p ) : 0 ) + 1 );
            if ( vl.get( p ) > 5 ) {
                e.makeAlert( "Invalid","A" );
            }
        }
        if( e.isNearLastRespawn() )return;
        if( e.isNearFly() )return;
        if( e.isNearLastOnSlime() )return;

        Delta delta = e.getDelta( );
        long speedX = (long) ( delta.getX( ) - pd.x );
        long speedZ = (long) ( delta.getZ( ) - pd.z );
        long speedY = (long) ( delta.getY( ) - pd.y );

        if ( ( ( speedX != 0 || speedZ != 0 ) && xz ) ) {
            e.makeAlert( "Invalid","B 1" );
        }
        if ( ( speedY != 0 && y ) ) {
            e.makeAlert( "Invalid","B 2" );
        }

        //p.sendMessage( "SpeedY = " + speedY + " Y = " + pd.y + " DELTA Y = " + e.getDelta().getY() + " " + GroundUtil.isNearBlock( p.getLocation() ) );

    }

    boolean glide, badjump = true;
    static boolean debugE = false;
    public static void invalidE(){
        debugE = CheeseAC.config.getBoolean( "settings.invalid.e.settings.debug" );
    }


    @EventHandler
    public void onMoveInvalidE(CheeseMoveEvent e) {
        if ( e.canBypass( ) ) return;
        Player p = e.getPlayer( );
        double deltaY = e.getDelta( ).getY( );
//        if( deltaY < Jump.MIN_DELTA_Y && deltaY != 0
//                && !e.onGroundBoth() && !e.isNearFly()
//                && !e.isOnWaterAround() && !e.isNearLastOnSlime() && glide ){
//            failE( e ,1 );
//        }
        if ( deltaY == 0 ) return;
        if ( deltaY <= Jump.MAX_DELTA_Y && deltaY >= Jump.MIN_DELTA_Y ) {
            boolean valid = false;
            if ( deltaY == Jump.DELTA_2 || deltaY == Jump.DELTA_3 || deltaY == Jump.DELTA_4
                    || deltaY == Jump.DELTA_5 || deltaY == Jump.DELTA_6 || deltaY == Jump.DELTA_7
                    || deltaY == Jump.DELTA_8 || deltaY == Jump.DELTA_9 || deltaY == Jump.DELTA_10
                    || deltaY == Jump.DELTA_11 || deltaY == Jump.DELTA_12
                    || deltaY == 0.15523200451660557 || deltaY == 0.24813599859093927
                    || deltaY == 0.1040803780930375 || deltaY == 0.1647732818260721
                    || deltaY == 0.23052736891295922 || deltaY == 0.23152379758701613
                    || deltaY == 0.40739540236494065 || deltaY == 0.155521752943109 || deltaY == 0.1858420248976742) {
                valid = true;
            }
            if ( e.isNearLastOnSlime( ) ) return;
            if( e.isAboveWater() )return;
            if( e.isNearLastAboveBlock() )return;
            if ( e.isNearFly( ) ) return;
            if ( e.isOnWaterAround( ) ) return;
            if ( e.isCancelled( ) ) return;
            if ( GroundUtil.isBadBlockAround( e.getTo( ) ) ) return;
            if ( GroundUtil.isBadBlockAround( e.getFrom( ) ) ) return;
            if ( GroundUtil.isHeadUpGround( e.getTo( ) ) ) return;
            if ( e.isNearCactusDamage( ) ) return;
            if ( e.isNearDamage( ) ) return;
            if ( GroundUtil.isHeadUpGround( e.getFrom( ) ) ) return;
            if( e.isNearLastPlace() )return;
            if( e.getAirTick() > 11 ){
                valid = false;
            }
            if ( ! badjump ) return;
            //if( e.isNearLastFallDamage() )return;
            if ( ! valid ) {
                failE( e );
                if(debugE){
                    Bukkit.broadcastMessage( "deltaY = " + deltaY);
                }
            }
        }
    }

    void failE(CheeseMoveEvent e) {
        e.makeAlert( "Invalid","E" );
    }



    static HashMap<Player,Double> ticks = new HashMap<>();
    @EventHandler
    public void onMoveF(CheeseMoveEvent e){
        if(e.canBypass())return;
        Player p = e.getPlayer();
//        if(e.onGroundBoth()){
//            tick(p , Action.CLEAR);
//            return;
//        }
//        tick(p , Action.ADD);
        if( e.isNearLastRespawn() )return;
        if( e.isNearFly() )return;
        if( e.isAboveWater() )return;
        if( e.isOnAroundGround() )return;
        if( e.isNearLastPlace() )return;
        if(tick(p , Action.GET) > 6){
            if( e.getTo().getY() >= e.getFrom().getY() ) {
                if( e.isNearLastOnSlime() )return;
                if( GroundUtil.isBadBlockAround( e.getTo() ) || e.isOnWaterAround() || e.isFromWaterAround() )return;
                e.makeAlert( "Invalid","F" );
            }
        }
    }


    public enum Action{
        ADD,CLEAR,GET
    }

    public static double tick(Player p,Action action){
        if(action == Action.ADD){
            if(ticks.get( p ) != null){
                ticks.put( p ,ticks.get( p ) + 1 );
                return ticks.get( p );
            }else{
                ticks.put( p , 1.0 );
                return 1;
            }
        }else if(action == Action.GET){
            if(ticks.get( p ) != null){
                return ticks.get( p );
            }else{
                ticks.put( p , 0.0 );
                return 0;
            }
        }else if(action == Action.CLEAR){
            ticks.put( p , 0.0 );
            return 0;
        }
        return 0;
    }

    /*/
    ........................................
     */

    //HashMap<Player, BetterValue>lastPred = new HashMap<>();

    public static void InvalidH(){
        airtick_setting_invalid_h = CheeseAC.config.getDouble( "settings.Invalid.H.settings.illegalDeltaY.airtick" );
    }

    public static double airtick_setting_invalid_h;
    @EventHandler
    public void onMoveH(CheeseMoveEvent e){
        if(e.canBypass())return;
        if(e.isFirst())return;
        boolean over = e.getAirTick() > airtick_setting_invalid_h;
        if( GroundUtil.isHeadUpGround( e.getTo() ) )return;
        if( GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) )return;
        if( GroundUtil.isHeadUpGround( e.getTo().clone().subtract( 0,1,0 ) ) )return;
        if( e.isNearOnIce() )return;
        if( e.isNearLastOnSlime() )return;
        if( e.isNearFly() )return;
        if( GroundUtil.isBadBlockAround( e.getTo() ) )return;
        if( ( over || ( !over && e.getDelta().getY() != 0) )
                && e.getDelta().getY() == e.getPlayerData().y ){
            e.makeAlert( "Invalid","H" );
        }
    }



    boolean debugG = true;
    @EventHandler
    public void onMoveG(CheeseMoveEvent e){
        if(e.canBypass())return;
        Player p = e.getPlayer();
        if(!debugG)return;
        Location from = e.getFrom().clone();
        //p.sendMessage( e.getLastFallDistance() + " " + Math.abs(e.getPlayerData().serverSide) );
        if( !e.onGroundBoth() || e.getDelta().getY() == 0 )return;
        if( e.isOnWaterAround() || e.isFromWaterAround() )return;
        if( e.isNearLastOnSlime() )return;
        if( p.getGameMode() == GameMode.SPECTATOR || p.getGameMode() == GameMode.CREATIVE )return;
        if( e.isNearFly() )return;
        if( e.isAboveWater() )return;
        if( e.getLastFallDistance() > 3 || Math.abs(e.getPlayerData().serverSide) > 11 ){
            new BukkitRunnable( ) {
                public void run() {
                    if( p != null && p.isOnline() ){
                        if( !e.isNearLastFallDamage() && !Fly.onLadder( p ) && !GroundUtil.isBadBlockAround( e.getTo() ) ){
                            if(e.getAlert( "Invalid","G" ).send().getFlagStatus()){
                                from.setPitch( p.getLocation().getPitch() );
                                from.setYaw( p.getLocation().getYaw() );
                                p.teleport(from);
                            }
                        }
                    }
                }
            }.runTaskLater( CheeseAC.plugin,5 );
        }
    }


    static boolean pitchStatus, groundStatus, glideStatus = true;

    public static void invalidC() {
        pitchStatus = CheeseAC.config.getBoolean( "settings.Invalid.C.settings.pitch" );
        groundStatus = CheeseAC.config.getBoolean( "settings.Invalid.C.settings.ground" );
        glideStatus = CheeseAC.config.getBoolean( "settings.Invalid.C.settings.glide" );
    }

    public HashMap<Player, Integer> vl4Sprint = new HashMap<>( );

    public int getVL4Sprint(Player p,int val) {
        if ( vl4Sprint.get( p ) != null ) {
            vl4Sprint.put( p,vl4Sprint.get( p ) + val );
            return vl4Sprint.get( p );
        } else {
            vl4Sprint.put( p,val );
            reseter( p );
            return val;
        }
    }

    public void reseter(Player p) {
        int s = 5 * 20;
        long time = s;
        new BukkitRunnable( ) {
            public void run() {
                vl4Sprint.put( p,null );
            }
        }.runTaskLater( CheeseAC.plugin,time );
    }

    static boolean Omni = true;


    public boolean fail(Player p,CheeseMoveEvent e) {
        boolean r = getVL4Sprint( p,1 ) > 4;
        r = r && Omni;
        if ( r ) {
            e.makeAlert( "Sprint","A 6" );
            this.vl4Sprint.put( p,0 );
        }
        return r;
    }

    @EventHandler
    public void onEvent(CheeseMoveEvent e) {
        if ( e.canBypass( ) ) return;
        Player p = e.getPlayer( );
        float pitch = Math.abs( p.getLocation( ).getPitch( ) );
        if ( pitch > 90 ) {
            if ( pitchStatus ) {
                e.makeAlert( "Invalid","C 1" );
            }
        }
        PlayerData pd = e.getPlayerData( );

        //p.sendMessage( pd.onGroundServer + " " + e.onGroundBoth() + " | " + pd.onGroundClient + " " + p.isOnGround() );
        if ( ! pd.onGroundServer && e.onGroundBoth( ) && pd.onGroundClient && ! p.isOnGround( ) && !e.isNearLastOnSlime() ) {
            if ( groundStatus && !GroundUtil.isAround( e.getPlayer().getEyeLocation().clone().add( 0 ,1 ,0 ) ) && !GroundUtil.isBadBlockAround( e.getTo() ) ) {
                e.makeAlert( "Invalid","C 2" );
            }
        }


        if ( glideStatus && ! pd.onGroundServer && ! e.onGroundBoth( ) && !e.isNearDamage()
                && ! GroundUtil.isBadBlockAround( e.getFrom( ) ) && ! GroundUtil.isBadBlockAround( e.getTo( ) )
                && ! e.isOnWaterAround( ) && ! e.isFromWaterAround( ) && ! e.isNearLastOnSlime( )
                && e.getDelta( ).getY( ) < Jump.MIN_DELTA_Y && e.getDelta( ).getY( ) != 0 && !e.isNearFly() && !e.isAboveWater() && !e.isNearLastAboveBlock() ) {
            e.makeAlert( "Invalid","C 3" );
        }

        //p.sendMessage( pd.lastLocation.getDirection().getX() + " " + pd.lastLocation.getDirection().getY() + " " + pd.lastLocation.getDirection().getZ() );
        Vector direc = e.getTo( ).getDirection( );
        double fx = Math.abs( e.getFrom( ).getX( ) );
        double tx = Math.abs( e.getTo( ).getX( ) );
        double fz = Math.abs( e.getFrom( ).getZ( ) );
        double tz = Math.abs( e.getTo( ).getZ( ) );
        if ( e.isMovingHorizontally( ) && p.isSprinting( ) ) {
            if ( tx > fx ) {
                if ( direc.getX( ) < -0.56 ) {
                    fail( p,e );
                }
            }
            if ( tz > fz ) {
                if ( direc.getZ( ) < -0.56 ) {
                    fail( p,e );
                }
            }
            //
            if ( tx < fx ) {
                if ( direc.getX( ) > 0.56 ) {
                    fail( p,e );
                }
            }
            if ( tz < fz ) {
                if ( direc.getZ( ) > 0.56 ) {
                    fail( p,e );
                }
            }
        }

    }

// [Abandoned] Aim Check. For Very falsy.
//    HashMap<Player, Vals> deltaHead = new HashMap<>();
//    HashMap<Player, Vals> lastDiff = new HashMap<>();
//
//    class Vals{
//
//        public long a,b;
//
//        public Vals(long a,long b){
//            this.a = a;
//            this.b = b;
//        }
//    }
//
//    @EventHandler
//    public void onHit(EntityDamageByEntityEvent e){
//        if( e.getDamager() instanceof Player ){
//            Player p = ( (Player) e.getDamager() ).getPlayer();
//            PlayerData pd = PlayerMoveEventListener.last.get( p );
//
//            long lastYaw = (long) pd.lastLocation.getYaw();
//            long yaw = (long) p.getLocation().getYaw();
//            long lastPitch = (long) pd.lastLocation.getPitch();
//            long pitch = (long) p.getLocation().getPitch();
//            long deltaYaw = Math.abs( ( yaw > lastYaw ? yaw : lastYaw ) - ( yaw > lastYaw ? lastYaw : yaw ) );
//            long deltaPitch = Math.abs( ( pitch > lastPitch ? pitch : lastPitch ) - ( pitch > lastPitch ? lastPitch : pitch ) );
//
//            if(deltaHead.get( p ) != null){
//                Vals delta = deltaHead.get( p );
//                long yawDiff = ( delta.a > deltaYaw ? delta.a : deltaYaw ) - ( delta.a > deltaYaw ? deltaYaw : delta.a );
//                long pitchDiff = ( delta.b > deltaPitch ? delta.b : deltaPitch ) - ( delta.b > deltaPitch ? deltaPitch : delta.b );
//                p.sendMessage( deltaYaw + " " + deltaPitch + " | " + yawDiff + " " + pitchDiff );
//
//                if( lastDiff.get( p ) != null ){
//                    Vals diff = lastDiff.get( p );
//                    if( diff.a == yawDiff || diff.b == pitchDiff ){
//                        p.sendMessage( "§cMaybe you are hacking" );
//                    }
//                }
//                lastDiff.put( p , new Vals(yawDiff,pitchDiff) );
//            }
//
//
//
//            deltaHead.put( p ,new Vals(deltaYaw,deltaPitch) );
//        }
//    }


    //FAST LADDER(BETA)

    public double LADDER_Y_DIST_1 = 0.11760000228882461;
    public double LADDER_Y_DSIT_2 = 0.1544480052490229;
    public double LADDER_Y_DSIT_3 = 0.1176000022888175;
    public double LADDER_Y_DIST_4 = 0.11215904732696913;
    public double LADDER_Y_DIST_5 = 0.11215904732696202;

    static boolean max, min, val = true;

    public static void invalidD() {
        max = CheeseAC.config.getBoolean( "settings.Invalid.D.settings.max" );
        min = CheeseAC.config.getBoolean( "settings.Invalid.D.settings.min" );
        val = CheeseAC.config.getBoolean( "settings.Invalid.D.settings.val" );
    }

    @EventHandler
    public void onMove1(CheeseMoveEvent e) {
        if ( e.canBypass( ) ) return;
        Player p = e.getPlayer( );
        if ( ! Fly.onLadder( p ) ) return;
        if ( e.getTo( ).getY( ) <= e.getFrom( ).getY( ) ) return;
        Location to = e.getTo( ).clone( );
        to.setX( 0 );
        to.setZ( 0 );
        Location from = e.getFrom( ).clone( );
        from.setX( 0 );
        from.setZ( 0 );
        double distY = to.distance( from );
        if ( distY == 0 || LADDER_Y_DIST_1 == distY
                || LADDER_Y_DSIT_2 == distY || LADDER_Y_DSIT_3 == distY
                || LADDER_Y_DIST_4 == distY || LADDER_Y_DIST_5 == distY ) {
        } else {
            boolean detected = false;
            if ( distY > LADDER_Y_DSIT_2 && max ) {
                e.makeAlert( "Invalid","D 1" ); //max
                detected = true;
            }
            if ( distY < LADDER_Y_DIST_5 && min ) {
                e.makeAlert( "Invalid","D 2" ); //min
                detected = true;
            }
            if ( val && ! detected ) {
                e.makeAlert( "Invalid","D 3" ); //valid? val
            }
        }
    }
}