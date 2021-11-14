package main;
import java.util.*;

public class Continent {
    /************************ Attributes *************************/
    private List<Country> countries;

    /************************ Constructor ************************/
    public Continent(List<Country> countries){
        this.countries = countries;
    }

    /************************ Methods *************************/
    public void draw(java.awt.Graphics g){
        int i = 0;
        int iTotal = countries.size();
        for (Country c: countries) {
            System.out.println("    Drawing " + c.getName() + "... (" + i + "/" + iTotal + ")");
            c.draw(g);
            i++;
        }
    }

    public double getTotalArea(){
        double totalArea = 0.0;
        for (Country c: countries) totalArea += c.getArea();
        return totalArea;
    }
}
