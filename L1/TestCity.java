public class TestCity {
    public static void main(String args[]){

        // Creating the instances
        City p0 = new City(-1.3, 3.9,"name1");
        City p1 = new City(3.0, 4.0, "name2");

        // Testing methods: printing
        System.out.println("\nPrinting original Cities:");
        p0.printCity();
        p1.printCity();
        
        // Testing methods: setters
        p0.setX(6.0);
        p0.setY(8.0);
        p1.setX(2.3);
        p1.setY(-7.4);

        // Testing methods: getters
        System.out.println("\nPrinting the Cities after using getters:");
        System.out.println(p0.getX());
        System.out.println(p0.getY());
        System.out.println(p1.getX());
        System.out.println(p1.getY());
        
        // Testing methods: distanceTo
        System.out.println("\nTesting distanceTo method:");
        System.out.println(p0.distanceTo(p1));

      

    }
}
