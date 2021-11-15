package main;
import java.util.List;
import java.util.LinkedList;

public class Country{
    /************************ Attributes *************************/
    private String name;
    private List<City> cities;
    private List<Country> neighbors;
    private City capital;
    private List<PolygonalRegion> regions;

    /************************ Constructor ************************/
    public Country(List<PolygonalRegion> regions, String name, City capital) {
        this.regions = regions;
        this.name = name;
        this.capital = capital;

        // Initialize lists
        this.cities = new LinkedList<City>();
        this.neighbors = new LinkedList<Country>();
    }

    /************************ Methods ****************************/
    public void addCity(City c){
        cities.add(c);
    }

    public void addNeighbor(Country c){
        neighbors.add(c);
    }

    public void draw(java.awt.Graphics g){
        for(PolygonalRegion region: regions) region.draw(g);
        for(City city: cities) city.draw(g);
    }

    public double getArea(){
        double area = 0;
        for(PolygonalRegion region: regions){
            area += region.getArea();
        }
        return area;
    }

    public String getName() {
        return name;
    }

    public City getCapital() {
        return capital;
    }
}
