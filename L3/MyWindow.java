import java.util.*;
import java.io.*;

public class MyWindow extends javax.swing.JFrame {

    public MyWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
    }

    public static void main( String[] args ) {
        // Map scaling
        // double yscale = 0.6;
        // double xscale = 1.0;

        // Input. Each continent is stored in a separate .txt file
        File folder = new File("resources");
        File[] files = folder.listFiles();
        List<Continent> continents = new LinkedList<Continent>();

        for (File continentFile: files) {
            try {
                Scanner scan = new Scanner(continentFile);

                // Read all countries of the continent
                List<Country> countries = new LinkedList<Country>();
                while (scan.hasNext()) { // Check if there is another country name
                    String name = scan.nextLine();

                    // Read all points of the country border
                    List<Point> points = new LinkedList<Point>();
                    while (scan.hasNextDouble()) { // Check if there are more points that define the border
                        double longitude = scan.nextDouble();
                        double latitude = scan.nextDouble();
                        points.add( MyMap.webMercatorProj(latitude, longitude) );
                    }

                    // Read capital of the country (It is the first city of the input file)
                    String capital_name = scan.next();
                    int capital_numhab = scan.nextInt();
                    double longitude = scan.nextDouble();
                    double latitude = scan.nextDouble();
                    Point p = MyMap.webMercatorProj(latitude, longitude);
                    City capital = new City(p.getX(), p.getY(), capital_name, capital_numhab);

                    // Add country to the list
                    if (points.isEmpty() == false) countries.add( new Country(points, name, capital) );

                    // Read the rest of the cities (if any), and add them to the country
                    // TODO
                }

                // Add new continent to the list of continents
                if (countries.isEmpty() == false) continents.add( new Continent(countries) );
                
                // Close file
                scan.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        // World
        World world = new World(continents);

        // Original content
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                MyWindow w = new MyWindow();
                MyMap m = new MyMap(world);
                w.add( m );
                w.setVisible( true );
                w.pack();
            }
        } );
    }
}

