package input;
import entities.*;
import java.util.*;
import java.awt.Color;

public class PolygonsFile extends EntitiesFile {
    /**
     * The expected format of PolygonalRegion files is as follows:
     * Rline;Gline;Bline;Rfill;Gfill;Bfill;points
     * 
     * The expetect format of points is as follows:
     * point1X,point1Y,point2X,point2Y,...,pointNX,pointNY
    */
    /************************ Attributes *************************/
    protected String pointDelimiter;

    /************************ Constructor ************************/
    public PolygonsFile(String filePath, String csvDelimiter, String pointDelimiter) {
        super(filePath, csvDelimiter);
        this.pointDelimiter = pointDelimiter;
    }

    /************************ Methods ****************************/
    protected Entity parseFields(String fields) {
        Scanner sc = new Scanner(fields);  
        sc.useDelimiter(csvDelimiter);

        Color lineColor = parseColor(sc);
        Color fillColor = parseColor(sc);
        List<Point> points = parsePoints(sc.next());
        sc.close();

        switch(points.size()) {
            case 2: return new RectangularRegion(lineColor, fillColor, points.get(0), points.get(1));
            case 3: return new TriangularRegion(lineColor, fillColor, points.get(0), points.get(1), points.get(2));
            default: return new PolygonalRegion(lineColor, fillColor, points);
        }
    }

    private List<Point> parsePoints(String rawPoints) {
        Scanner sc = new Scanner(rawPoints);
        sc.useDelimiter(pointDelimiter);
        List<Point> points = new LinkedList<Point>();
        while (sc.hasNextDouble()) {
            Point p = new Point(sc.nextDouble(), sc.nextDouble());
            points.add(p);
        }
        sc.close();
        return points;
    }
}
