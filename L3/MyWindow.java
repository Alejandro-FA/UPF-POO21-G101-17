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
        String point_pattern = "[+-]?[0-9]*[.]?[0-9]* [+-]?[0-9]*[.]?[0-9]*"; // Two doubles separated by a whitespace
        // City name (accepts composite names like South Africa) + num_inhabitants + point
        String city_pattern = "([A-Z][a-z]*[ ]?)+ [0-9]+ " + point_pattern;

        // Input. Each continent is stored in a separate .txt file
        File folder = new File("resources");
        File[] files = folder.listFiles();
        List<Continent> continents = new LinkedList<Continent>();

        for (File continentFile: files) {
            try {
                // Open scanner and change the delimiter to a newline
                Scanner file_scan = new Scanner(continentFile);
                file_scan.useDelimiter("\n");

                // Read all countries of the continent
                List<Country> countries = new LinkedList<Country>();
                while (file_scan.hasNext()) { // Check if there is another country name
                    String name = file_scan.nextLine();

                    // Read all points of the country border
                    List<Point> points = new LinkedList<Point>();
                    while ( file_scan.hasNext(point_pattern) ) { // Check if there are more points that define the border
                        String str = file_scan.nextLine();
                        Scanner str_scan = new Scanner(str);
                        double longitude = str_scan.nextDouble();
                        double latitude = str_scan.nextDouble();
                        str_scan.close();
                        points.add( MyMap.webMercatorProj(latitude, longitude) );
                    }

                    // Read cities
                    List<Point> cities = new LinkedList<Point>();
                    
                    // String capital_name = file_scan.next();
                    // int capital_numhab = file_scan.nextInt();
                    // double longitude = file_scan.nextDouble();
                    // double latitude = file_scan.nextDouble();
                    // Point p = MyMap.webMercatorProj(latitude, longitude);
                    // City capital = new City(p.getX(), p.getY(), capital_name, capital_numhab);

                    // Add country to the list. The capital is the first city
                    if (points.isEmpty() == false && cities.isEmpty() == false) countries.add( new Country(points, name, capital) );

                    // Read the rest of the cities (if any), and add them to the country
                    
                    // TODO
                }

                // Add new continent to the list of continents
                if (countries.isEmpty() == false) continents.add( new Continent(countries) );
                
                // Close file
                file_scan.close();

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

