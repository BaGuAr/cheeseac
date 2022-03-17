package co.n0rthyankt0n.cheeseac.events;

import co.n0rthyankt0n.cheeseac.CheeseAC;
import co.n0rthyankt0n.cheeseac.checks.movements.Invalid;
import co.n0rthyankt0n.cheeseac.listeners.ClickListener;
import co.n0rthyankt0n.cheeseac.listeners.DamageListener;
import co.n0rthyankt0n.cheeseac.listeners.PlayerMoveEventListener;
import co.n0rthyankt0n.cheeseac.managers.Alert;
import co.n0rthyankt0n.cheeseac.utils.GroundUtil;
import co.n0rthyankt0n.cheeseac.utils.LastFlyUtil;
import co.n0rthyankt0n.cheeseac.utils.LastOnGroundUtil;
import co.n0rthyankt0n.cheeseac.utils.LastOnIceUtil;
import co.n0rthyankt0n.cheeseac.values.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheeseMoveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    PlayerMoveEvent e;
    Location from,to;
    Delta delta;
    double deltax,deltay,deltaz,deltaxz;
    Player p;
    boolean onGround,onMathGround,onAroundGround,onWaterAround,onGroundBoth;
    boolean fromMathGround,fromAroundGround,fromWaterAround,fromGroundBoth;
    boolean isStandingBoat;
    boolean cancelled;
    Damage last;
    double tick;
    Diff diff;
    int timeTick;
    Location first;
    boolean isFirst = true;

    public CheeseMoveEvent(PlayerMoveEvent e){
        this.timeTick = 0;
        this.e = e;
        this.p = e.getPlayer();
        this.to = e.getTo();
        this.from = e.getFrom();
        this.deltax = Math.sqrt(Math.pow(Math.abs(to.getX() - from.getX()), 2.0));
        this.deltay = Math.abs( from.getY() - to.getY() );
        this.deltaz = Math.pow(Math.abs(to.getZ() - from.getZ()), 2.0);
        //this.deltaxz = Math.sqrt((deltax * deltax) + (deltaz * deltaz));
        //this.deltaxz = Math.hypot( deltax,deltaz );
        this.deltaxz = deltax + deltaz; // i don't know correct
        this.delta = new Delta( deltax , deltay ,deltaz , deltaxz );
        this.onGround = p.isOnGround();
        this.onGroundBoth = GroundUtil.OnGround( to );
        this.fromGroundBoth = GroundUtil.OnGround( from );
        this.isStandingBoat = isStandingBoat( p );
        this.onMathGround = to.getY() % 0.015625 < 0.0001;
        this.fromMathGround = from.getY() % 0.015625 < 0.0001;
        this.onAroundGround = GroundUtil.isOnGroundAround( to );
        this.fromAroundGround = GroundUtil.isOnGroundAround( from );
        this.onWaterAround = isWaterAround( to , 1 ,1  );
        this.fromWaterAround = isWaterAround( from , 1 ,1  );
        this.cancelled = e.isCancelled();
        this.diff = new Diff( to , from );

        this.last = DamageListener.lastDamageMap.getOrDefault( p , null );

//        if(LastOnGroundUtil.lastGroundHashMap.get( p ) != null){
//            this.tick = (System.currentTimeMillis() - LastOnGroundUtil.lastGroundHashMap.get( p ).getTick());
//        }else {
//            tick = 0;
//        }
//        if(PlayerMoveEventListener.lastMoveEventHashMap.get( p ) != null){
//            tick = (System.currentTimeMillis() - PlayerMoveEventListener.lastMoveEventHashMap.get( p ).getTick());
//        }else{
//            tick = System.currentTimeMillis();
//        }

        this.isFirst = PlayerMoveEventListener.last.get( p ) == null;
    }

    public PlayerData getPlayerData(){
        return (PlayerMoveEventListener.last.get( p ) != null ? PlayerMoveEventListener.last.get( p ) : PlayerMoveEventListener.playerDatas.get( p ));
    }

    public boolean isAboveWater(){
        return ( GroundUtil.isAround( p.getEyeLocation().clone().add( 0 ,1 ,0 ) , "water" ) );
    }
    public boolean isAboveBlock(){
        return ( GroundUtil.isAround( p.getEyeLocation().clone().add( 0 ,1 ,0 ) ) );
    }

    public boolean isNearLastAboveBlock(){
        if(PlayerMoveEventListener.lastAboveBlock.get( p ) != null){
            if( System.currentTimeMillis() - PlayerMoveEventListener.lastAboveBlock.get( p ) < 1100 ){
                return true;
            }
        }

        return false;
    }


    public boolean isNearLastRespawn(){
        if(DamageListener.lastRespawn.get( p ) != null){
            if( System.currentTimeMillis() - DamageListener.lastRespawn.get( p ) < 1100 ){
                return true;
            }
        }

        return false;
    }


    public boolean isFirst(){
        return isFirst;
    }

    public double getAirTick(){
        return Invalid.tick( p , Invalid.Action.GET );
    }

    public double getPredictionY(){
        double lastDeltaY = getPlayerData().y;
        double gravity = 0.9800000190734863;
        double fallMotion = 0.08;
        return  ( lastDeltaY - fallMotion ) * gravity;
    }

    public double getLastFallDistance(){
        return getPlayerData().falldist;
    }

    public double getDiffFallDistance(){
        return Math.abs( ( getLastFallDistance() > p.getFallDistance() ? getLastFallDistance() : p.getFallDistance() ) - ( getLastFallDistance() > p.getFallDistance() ? p.getFallDistance() : getLastFallDistance() ) );
    }

    public boolean isNearLastFallDamage(){

        if(DamageListener.lastFallDamageMap.get( p ) != null){
            if( System.currentTimeMillis() - DamageListener.lastFallDamageMap.get( p ).getTick() < 1100 ){
                return true;
            }
        }

        return false;

    }

    public boolean isNearLastHit(){
        if( DamageListener.lastHit.get( p ) != null ){
            if( ( System.currentTimeMillis() - DamageListener.lastHit.get( p ) ) < 500 ){
                return true;
            }
        }
        return false;
    }


    public boolean isNearLastFallDamageIsCancelled(){

        if(DamageListener.lastFallDamageMap.get( p ) != null){
            if( System.currentTimeMillis() - DamageListener.lastFallDamageMap.get( p ).getTick() < 1100 ){
                return DamageListener.lastFallDamageMap.get( p ).isCancelled();
            }
        }

        return false;

    }


    public double getVDIST(){
        return Math.abs( Math.sqrt( deltay * deltay ) );
    }

    public boolean isMoving(){
        return (diff.getX() != 0 || diff.getY() != 0 || diff.getZ() != 0);
    }

    public boolean isMovingHead(){
        return (to.getPitch() != from.getPitch() || to.getYaw() != from.getYaw());
    }

    public boolean isMovingHorizontally(){
        return diff.getX() != 0 || diff.getZ() != 0;
    }

    public boolean isMovingVertically(){
        return diff.getY() != 0;
    }


    public int func_T20(){
        return timeTick;
    }

    public void func_T20(int a){
        timeTick = a;
    }

    public Location func_FLC(){
        return first;
    }

    public void func_FLC(Location loc){
        first = loc;
    }


    public Diff getDiff(){
        return diff;
    }


    public double getValueTickDebug(){return tick;}

    public static boolean isWaterAround(Location loc ,int radius ,int radiusY ) {
        for (int x = -radius; x < radius; x++) {
            for (int y = -radiusY; y < radiusY; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.isLiquid() || block.equals( Material.WATER ) ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isStandingBoat(Player p){
        boolean r = false;
        for(Entity e : p.getWorld().getEntities()){
            double distance = e.getLocation().distance( p.getLocation() );
            if(distance <= 1.5 && e.getName().toLowerCase().contains( "boat" ) && !(e instanceof Player )){
                r = true;
            }
        }
        return r;
    }

    public Damage getLastDamage(){
        return last;
    }

    public boolean isNearDamage(){
        if(last != null){
            if( (System.currentTimeMillis() - last.getTick()) < 1100 ){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public long getLastPlace(){
        return ( ClickListener.lastBlockPlace.get( p ) != null ? ClickListener.lastBlockPlace.get( p ) : 0 );
    }

    public long getDiffOfLastPlace(){
        return System.currentTimeMillis() - getLastPlace();
    }

    public boolean isNearLastPlace(){
        if(getLastPlace() != 0){
            if( getDiffOfLastPlace() < 1100){
                return true;
            }
        }
        return false;
    }


    public boolean isVeryNearDamage(){
        if(last != null){
            if( (System.currentTimeMillis() - last.getTick()) < 250 ){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Deprecated
    public PlayerMoveEvent getPlayerMoveEvent(){
        return e;
    }
    public void msg(String msg){
        p.sendMessage( "[DEBUG] " + msg );
    }
    public Location getFrom(){
        return from;
    }

    public Location getTo(){
        return to;
    }

    public Delta getDelta(){
        return delta;
    }

    public Player getPlayer(){
        return p;
    }

    public boolean canBypass(){
        return CheeseAC.bypass && p.hasPermission( CheeseAC.bypassPerm );
    }

    public boolean isOnGround(){
        return onGround;
    }

    public boolean isOnMathGround(){
        return onMathGround;
    }

    public boolean isOnAroundGround(){
        return onAroundGround;
    }


    public boolean isFromMathGround(){
        return fromMathGround;
    }

    public boolean isFromAroundGround(){
        return fromAroundGround;
    }

    @Deprecated
    public boolean isSpoofing(){
        return onGround && (!onMathGround || !onAroundGround);
    }

    public boolean onGroundBoth(){
        return onGroundBoth;
    }

    public boolean fromGroundBoth(){
        return fromGroundBoth;
    }

    public boolean OnGround(){
        return onMathGround || onAroundGround;
    }

    public boolean FromGround(){
        return onMathGround || onAroundGround;
    }

    public boolean isOnWaterAround(){
        return onWaterAround;
    }

    public boolean isFromWaterAround(){
        return fromWaterAround;
    }

    public void flagback(){

        p.teleport(from);
    }
    public boolean isNearCactusDamage(){
        if( DamageListener.lastCactusDamageMap.get( p ) != null){
            if((System.currentTimeMillis() - DamageListener.lastCactusDamageMap.get( p ).getTick()) < 1100){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isNearFly(){
        if( LastFlyUtil.lastFlyHashMap.get( p ) != null){
            if((System.currentTimeMillis() - LastFlyUtil.lastFlyHashMap.get( p ).getTick()) < 1100){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isNearOnIce(){
        if( LastOnIceUtil.lastOnIceHashMap.get( p ) != null){
            if((System.currentTimeMillis() - LastOnIceUtil.lastOnIceHashMap.get( p ).getTick()) < 1100){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isNearLastOnGround(){
        if( LastOnGroundUtil.lastGroundHashMap.get( p ) != null){
            if((System.currentTimeMillis() - LastOnGroundUtil.lastGroundHashMap.get( p ).getTick()) < 1100){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isNearLastOnSlime(){
        if( PlayerMoveEventListener.lastSlimeTick.get( p ) != null){
            if((System.currentTimeMillis() - PlayerMoveEventListener.lastSlimeTick.get( p ).getTick()) < 1100){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isLittleNearLastOnGround(){
        if( LastOnGroundUtil.lastGroundHashMap.get( p ) != null){
            if((System.currentTimeMillis() - LastOnGroundUtil.lastGroundHashMap.get( p ).getTick()) < 1900){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }


    @Deprecated
    public Alert getAlert(String check,String type){
        return new Alert( p , check , type );
    }

    @Deprecated
    public void makeAlert(String check,String type){
        if(new Alert( p , check , type ).send().getFlagStatus() && CheeseAC.status ){
            p.teleport(from);
        }
    }
    public boolean isCancelled(){
        return cancelled;
    }

    public HandlerList getHandlers() {
        return handlers;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }
}
