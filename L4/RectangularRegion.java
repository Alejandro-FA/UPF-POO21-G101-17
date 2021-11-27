import java.awt.*;

public class RectangularRegion extends PolygonalRegion{
    private Point p1;
    private Point p2;

    public RectangularRegion(Color lineColor, Color fillColor, Point p1, Point p2) {
        super(lineColor, fillColor);
        this.p1 = p1;
        this.p2 = p2;
        Point p3 = new Point(p1.getX(), p2.getY()); // We deduce the remaining points of the rectangle
        Point p4 = new Point(p2.getX(), p1.getY());

        // Note that the order is important
        this.addPoint(p1);
        this.addPoint(p3);
        this.addPoint(p2);
        this.addPoint(p4);
    }

    public double getArea(){
        Point p3 = new Point(p1.getX(), p2.getY());
        double base = p1.difference(p3).magnitude();
        double height = p2.difference(p3).magnitude();
        return base*height;
    }
}