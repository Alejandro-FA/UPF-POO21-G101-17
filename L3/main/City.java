package main;
public class City extends GeoPoint {
    private int numhab;

    public City(double xInit, double yInit, String name, int habitants){
        super(xInit, yInit, name);
        numhab = habitants;
    }
}
