public class TestDistanceMatrix {
    public static void main(String args[]){
        System.out.println("Testing DistanceMatrix.java...");

        // We build the matrix with the constructor
        DistanceMatrix matrix = new DistanceMatrix();
        
        
        // We add the Cities to the matrix.
        // Note that the addCity method also calls the buildMatrix private method and updates
        // the size of the matrix.
        matrix.addCity(-1.3, 3.9, "name1");
        matrix.addCity(3.0, 4.0, "name2");
        matrix.addCity(-9.2, 1.0, "name3");
        matrix.addCity(4.8, -5.5, "name4");
        
        // We get the Distance of two random Cities (which is stored in the matrix)
        System.out.println("\nDistances between p0 and p3");
        System.out.println(matrix.getDistance(0,3));
        System.out.println("\nDistance between p1 and p2");
        System.out.println(matrix.getDistance(1,2));

        // We get the size of the matrix
        System.out.println(matrix.getNoOfCities());
        
        // We print the matrix with an auxiliary function created by us
        System.out.println("Printing the Distance Matrix");
        matrix.printMatrix();
    }
}
