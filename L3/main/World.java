package main;
import java.util.*;

public class World {
    /************************ Attributes *************************/
    private List<Continent> conts;

    /************************ Constructors ************************/
    public World(List<Continent> conts) {
        this.conts = conts;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g) {
        int i = 1;
        int iTotal = conts.size();
        double worldArea = 0.0;
        for (Continent c: conts) {
            worldArea += c.getTotalArea();
            System.out.println("Drawing continent... (" + i + "/" + iTotal + ")");
            c.draw(g);
            i++;
        }
        System.out.println(System.lineSeparator() + "Success! World drawn. Total world area: " + worldArea);
    }
}
