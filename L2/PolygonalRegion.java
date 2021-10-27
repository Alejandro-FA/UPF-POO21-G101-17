import java.util.*;

public class PolygonalRegion {
    /************************ Attributes *************************/
    private List<Point> points;
    
    /************************ Constructor ************************/
    public PolygonalRegion(List<Point> lst) {
        points = lst;
    }

    /************************ Methods ****************************/
    public double getArea() {
        // For now we are going to assume that the polygon is convex.
        int last_idx = points.size() - 1;
        double area = 0.0;

        // Sum of the all the elements except the last one
        for (int i = 0; i < last_idx; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);

            area += p1.getX()*p2.getY() - p1.getY()*p2.getX();
        }

        // Last element is computed separately, since it is different
        Point first_p = points.get(0);
        Point last_p = points.get(last_idx);
        return 0.5 * ( area + last_p.getX()*first_p.getY() - last_p.getY()*first_p.getX() );
    }
}