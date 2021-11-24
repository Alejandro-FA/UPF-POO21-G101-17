import java.util.LinkedList;
import java.awt.*;

public class TriangularRegion extends PolygonalRegion{
    private Point p1;
    private Point p2;
    private Point p3;

    public TriangularRegion(Color lineColor, Color fillColor, Point p1, Point p2, Point p3) {
        super( lineColor, fillColor, new LinkedList<Point>( new Point[] {p1, p2, p3} ) );
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // public double getArea() {
    //     Vector side1 = p1.difference(p2);
    //     Vector side2 = p3.difference(p3);
    //     double height = 

    //     return p1.getX()*p2.getY();
    // }
}
