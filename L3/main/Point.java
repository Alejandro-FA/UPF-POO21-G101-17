package main;
public class Point {
    /************************ Attributes *************************/
    protected double x;
    protected double y;

    /************************ Constructor ************************/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /************************ Methods ****************************/
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
