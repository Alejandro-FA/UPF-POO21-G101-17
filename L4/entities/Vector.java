package entities;
public class Vector {
    private double vx;
    private double vy;
 
    public Vector (double vx, double vy){
        this.vx = vx;
        this.vy = vy;
    }

    public double crossProduct(Vector v2){
        return vx * v2.getVy() - vy * v2.getVx();
    }

    public double getVx() {
        return vx;
    }
    
    public double getVy() {
        return vy;
    }

    public double magnitude() {
        return Math.sqrt( Math.pow(vx,2) + Math.pow(vy,2) );
    }
}