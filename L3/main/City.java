package main;
public class City extends GeoPoint {
    /************************ Attributes *************************/
    private int numHab;

    /************************ Constructor ************************/
    public City(double xInit, double yInit, String name, int numHab) {
        super(xInit, yInit, name);
        this.numHab = numHab;
    }

    /************************ Methods ****************************/
    public int getNumHab() {
        return numHab;
    }
}
