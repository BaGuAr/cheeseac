package co.n0rthyankt0n.cheeseac.values;

public class Delta {

    double x,y,z,xz;

    public Delta(double x,double y,double z,double xz){
        this.x = x;
        this.y = y;
        this.z = z;
        this.xz = xz;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    public double getXZ(){
        return xz;
    }
}
