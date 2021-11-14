package main;
import java.util.List;

public class PolygonalRegion {
    /************************ Attributes *************************/
    protected List<Point> points;
    
    /************************ Constructor ************************/
    // Here we perform an upcast to allow the use of both ArrayList and LinkedList.
    public PolygonalRegion(List<Point> points) {
        super();
        this.points = points;
    }

    /************************ Methods ****************************/
    // For now we are going to assume that the polygon is convex.
    public double getArea() {
        int last_idx = points.size() - 1;
        double area = 0;

        // Sum of the all the elements except the last one
        for (int i = 0; i < last_idx; i++) {
            Point pi = points.get(i);
            Point pj = points.get(i+1);

            area += -pi.getY()*pj.getX() + pi.getX()*pj.getY();
        }

        // Last element is computed separately, since it is different
        Point p1 = points.get(0);
        Point pn = points.get(last_idx);
        area += -pn.getY()*p1.getX() + pn.getX()*p1.getY();
        return 0.5 * Math.abs(area);
    }

    /* For now we are going to assume that the points are ordered in order to draw the 
    polygon correctly. We could not assume this, but then we should implement some 
    procedure to order the list of pointss in such a way that the polygon is always drawn
    as it should.*/ 
    public void draw(java.awt.Graphics g) {
        int n = points.size();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            Point p = points.get(i);
            x[i] = (int) p.getX();
            y[i] = (int) p.getY();
        }

        g.drawPolygon(x, y, n);
    }
}