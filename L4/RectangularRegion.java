import java.util.LinkedList;
import java.awt.*;

public class RectangularRegion extends PolygonalRegion{
    private Point p1;
    private Point p2;

    public RectangularRegion(Color lineColor, Color fillColor, Point p1, Point p2) {
        super( lineColor, fillColor,  new LinkedList<Point>( new Point[] {p1, p2} ));
        this.p1 = p1;
        this.p2 = p2;
    }

    public double getArea(){
        Point p3 = new Point(p1.getX(), p2.getY());
        double base = p1.difference(p3).magnitude();
        double height = p2.difference(p3).magnitude();
        return base*height;
    }
}