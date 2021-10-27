import java.util.*;

public class Continent {

    /************************ Attributes *************************/
    private List<PolygonalRegion> countries;

    /************************ Constructor ************************/
    public Continent(List<PolygonalRegion> lst){
        countries = lst;
    }

    /************************ Methods *************************/
    public void draw(java.awt.Graphics g){
        for (PolygonalRegion c: countries) c.draw(g);
    }
}
