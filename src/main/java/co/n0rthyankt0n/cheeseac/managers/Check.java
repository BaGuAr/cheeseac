package co.n0rthyankt0n.cheeseac.managers;

public class Check {
    String check,type;
    boolean checkStatus,flagStatus,combat;

    public Check(String check,String type,boolean checkStatus,boolean flagStatus){
        this.check = check;
        this.type = type;
        this.checkStatus = checkStatus;
        this.flagStatus = flagStatus;
        this.combat = false;
    }

    public Check(String check,String type,boolean checkStatus,boolean flagStatus,boolean combat){
        this.check = check;
        this.type = type;
        this.checkStatus = checkStatus;
        this.flagStatus = flagStatus;
        this.combat = combat;
    }

    @Deprecated
    public Check clone(){
        return new Check(this.check,this.type,this.checkStatus,this.flagStatus,this.combat);
    }

    public boolean isCombat(){
        return combat;
    }

    public boolean getStatus(){
        return checkStatus;
    }

    public boolean getFlagStatus(){
        return flagStatus;
    }

    public String getCheck(){
        return check;
    }

    public String getType(){
        return type;
    }

    public String getAsTheme1(){
        return check + " " + type;
    }

    public void setCheckStatus(boolean cs){
        this.checkStatus = cs;
    }

    public void setFlagStatus(boolean fs){
        this.flagStatus = fs;
    }

}
