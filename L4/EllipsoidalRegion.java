import java.awt.*;

public class EllipsoidalRegion extends Region {
    /************************ Attributes *************************/
    private Point centre;
    private double a; // Radius 1 of the Ellipse
    private double b; // Radius 2 of the Ellipse
    
    /************************ Constructor ************************/
    public EllipsoidalRegion(Color lineColor, Color fillColor, Point centre, double a, double b) {
        super(lineColor, fillColor);
        this.centre = centre;
        this.a = a;
        this.b = b;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g){
        g.drawOval( (int) centre.getX(), (int) centre.getY(), (int) a, (int) b );
    }

    public void translate(int dx, int dy){
        this.centre.translate(dx, dy);
    }

    public boolean isPointInside(Point p){
        double px = p.getX();
        double py = p.getY();
        double cx = centre.getX();
        double cy = centre.getY();
        double result =  (Math.pow(px-cx, 2)) / (Math.pow(a,2)) + (Math.pow(py-cy, 2)) / (Math.pow(b, 2));

        if (result <= 1.0) return true;
        else return false;
    }

    public double getArea(){
        return Math.PI*a*b;
    }
}