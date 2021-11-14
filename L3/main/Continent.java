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
        for (Country c: countries) {
            System.out.println("Drawing " + c.getName() + "...");
            c.draw(g);
        }
    }

    public double getTotalArea(){
        double totalArea = 0.0;
        for (Country c: countries) totalArea += c.getArea();
        return totalArea;
    }
}
