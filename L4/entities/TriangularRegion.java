package entities;
import java.awt.*;

public class TriangularRegion extends PolygonalRegion{
    private Point p1;
    private Point p2;
    private Point p3;

    public TriangularRegion(Color lineColor, Color fillColor, Point p1, Point p2, Point p3) {
        super(lineColor, fillColor);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.addPoint(p1);
        this.addPoint(p2);
        this.addPoint(p3);
    }

    public double getArea() {
        double base = p1.difference(p2).magnitude() / 2.0; // We divide by two here for efficiency
        double hypotenuse = p3.difference(p1).magnitude();
        double height = Math.sqrt( Math.pow(hypotenuse, 2) - Math.pow(base, 2) );
        return base*height;
    }
}
