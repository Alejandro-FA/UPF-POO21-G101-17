package entities;
import java.util.List;
import java.util.LinkedList;
import java.awt.*;

public class PolygonalRegion extends Region {
    /************************ Attributes *************************/
    protected List<Point> points;
    
    /************************ Constructor ************************/
    // Here we perform an upcast to allow the use of both ArrayList and LinkedList.
    public PolygonalRegion(Color lineColor, Color fillColor, List<Point> points) {
        super(lineColor, fillColor);
        this.points = points;
    }

    // Additional constructor to allow adding points one by one
    public PolygonalRegion(Color lineColor, Color fillColor) {
        super(lineColor, fillColor);
        this.points = new LinkedList<Point>();
    }

    /************************ Methods ****************************/
    // With the last modification the polygon can be convex or non-convex.
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

        // Set a different color for the border and the body.
        g.setColor(this.fillColor);
        g.fillPolygon(x, y, n);
        g.setColor(this.lineColor); // lineColor attribute inherited from Entity class
        g.drawPolygon(x, y, n);
    }

    public boolean isPointInside(Point p) {
        int last_idx = points.size() - 1; // Index of last point of the polygon

        // Last case is apart because we need the last and first point of the list
        // It is also used to determine the target sign of the following iterations
        Point qn = points.get(last_idx);
        Point q1 = points.get(0);
        Vector vec1 = q1.difference(qn); // (q1 - qn)
        Vector vec2 = p.difference(qn); // (p - qn)
        double result = vec1.crossProduct(vec2); // (q1 - qn) x (p - qn)
        int sign = (int) Math.signum(result); // The sign of all following iterations must be equal to this one

        // General case
        for (int i = 0; i < last_idx; i++) {
            Point qi = points.get(i);
            Point qj = points.get(i+1);
            Vector veci = qj.difference(qi); // (qi+1 - qi)
            Vector vecj = p.difference(qi); // (p - qi)
            double tmp = veci.crossProduct(vecj); // (qi+1 - qi) x (p - qi)
            if(Math.signum(tmp) != sign) return false; // If sign does not match, Point "p" is not inside
        }
        return true;
    }
    
    public void translate(int dx, int dy) {
        for(Point p: points) p.translate(dx, dy);
    }

    // Adds a point to the list of point. Note that no order control is performed.
    public void addPoint(Point p) {
        this.points.add(p);
    }
}