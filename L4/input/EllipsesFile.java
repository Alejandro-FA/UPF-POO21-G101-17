package input;
import entities.*;
import java.util.*;
import java.awt.Color;

public class EllipsesFile extends EntitiesFile {
    /**
     * The expected format of EllipsoidalRegion files is as follows:
     * Rline;Gline;Bline;Rfill;Gfill;Bfill;centerX;centerY;radius1;radius2
     * 
     * The exptected format of CircularRegion files is as follows:
     * Rline;Gline;Bline;Rfill;Gfill;Bfill;centerX;centerY;radius
     */

    /************************ Constructor ************************/
    public EllipsesFile(String filePath, String csvDelimiter) {
        super(filePath, csvDelimiter);
    }

    /************************ Methods ****************************/
    protected Entity parseFields(String fields) {
        Scanner sc = new Scanner(fields);  
        sc.useDelimiter(csvDelimiter);

        Color lineColor = parseColor(sc);
        Color fillColor = parseColor(sc);
        Point centre = new Point(sc.nextDouble(), sc.nextDouble());
        double a = sc.nextDouble();

        Entity e;
        if (sc.hasNextDouble()) {
            double b = sc.nextDouble();
            e = new EllipsoidalRegion(lineColor, fillColor, centre, a, b);
        }
        else e = new CircularRegion(lineColor, fillColor, centre, a);

        sc.close();
        return e;
    }
}
