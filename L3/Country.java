import java.util.List;
import java.util.LinkedList;

public class Country extends PolygonalRegion{
    /************************ Attributes *************************/
    private String name;
    private List<City> cities;
    private List<Country> neighbors;
    private City capital;

    /************************ Constructor ************************/
    public Country(List<Point> points, String name, City capital) {
        super(points);
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

    // No need to write a draw() method. Right? Because we directly use the one of the super class.
}
