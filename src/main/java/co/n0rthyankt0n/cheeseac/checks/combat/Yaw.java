package co.n0rthyankt0n.cheeseac.checks.combat;

public class Yaw {

    double from,to;

    public Yaw(float f,float t){
        from = f % 180;
        to = t % 180;
    }

    public double getTo(){
        return to;
    }

    public double getFrom(){
        return from;
    }

    public double getDiff(){
        double t = Math.abs(to);
        double f = Math.abs( from );
        if((to + "").contains( "-" ) != (from + "").contains( "-" )){
            return 0;
        }
        if(t > f){
            return t - f;
        }else if(f > t){
            return f - t;
        }else{
            return 0;
        }
    }
}
