package main;
import java.util.*;

public class World {
    /************************ Attributes *************************/
    private List<Continent> conts;
    private List<Ocean> oceans;

    /************************ Constructors ************************/
    public World(List<Continent> conts) {
        this.conts = conts;
        this.oceans = new LinkedList<Ocean>();
    }

    public World(List<Continent> conts, List<Ocean> oceans ) {
        this.conts = conts;
        this.oceans = oceans;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g) {
        int i = 0;
        int iTotal = conts.size();
        for (Continent c: conts) {
            System.out.println("Drawing continent... (" + i + "/" + iTotal + ")");
            c.draw(g);
            i++;
        }
    }
}
