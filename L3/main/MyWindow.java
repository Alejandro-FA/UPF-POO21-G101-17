package main;
import input.ContinentsDict;
import input.CityFiles;
import input.BorderFiles;
import java.util.*;
import java.io.*;
// import javax.swing.border.Border;


public class MyWindow extends javax.swing.JFrame {
    /************************ Attributes *************************/
    private static final String fs = File.separator;
    private static final String newline = System.lineSeparator();

    /************************ Constructor ************************/
    public MyWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
    }

    /************************ Methods ****************************/
    public static void main( String[] args ) {
        Map<String, String> dict = selectInput(args); // Create dictionary with the countries that have to be drawn and their corresponding continent
        Map<String, LinkedList<Country>> continents = new HashMap<String, LinkedList<Country>>(); // Dictionary to store the continent name as keys and a list of Country instances as values
        int i = 1; // Iteration variable to control the the state of the progress of the program.
        int iTotal = dict.size();

        // Read all the countries specified in "dict", and store them in the corresponding list of "continents"
        for (String countryName: dict.keySet()) {
            String continentName = dict.get(countryName);
            String basePath = ContinentsDict.defaultFolder + fs + continentName + fs + countryName;
            File country_folder = new File(basePath);
            File[] regionsFiles = country_folder.listFiles(); // One of this files contains cities data instead of border coordinates
            
            // Read border files and city file of the corresponding country
            List<City> cities = null;
            List<PolygonalRegion> regions = new LinkedList<PolygonalRegion>();
            for (File region: regionsFiles) {
                if ( region.getName().equals(CityFiles.defaultPath) ) cities = CityFiles.read(region);  // If file contains city data
                else { // Else file contains border data
                    PolygonalRegion r = BorderFiles.read(region);
                    if (r != null) regions.add(r);
                }
            }

            // Add country to the list. The capital is the first city
            if ( !regions.isEmpty() && cities != null ) {
                City capital = cities.get(0);
                Country country = new Country(regions, countryName, capital);
                if (continents.containsKey(continentName) == false) continents.put(continentName, new LinkedList<Country>()); 
                continents.get(continentName).add(country);
                for(City city: cities) country.addCity(city);
                System.out.println(countryName + " country instance created (" + i + "/" + iTotal + ")");
                i++;
            }
        }

        // Create list of continents and a world instance
        List<Continent> continentsList = new LinkedList<Continent>();
        for ( List<Country> countriesList: continents.values() )
            continentsList.add( new Continent(countriesList) );
        World world = new World(continentsList);
        System.out.println(newline + "World instance created. Drawing map..." + newline);

        // Original content: window event
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

    // Method to specify execution mode and read corresponding input file
    private static Map<String, String> selectInput(String[] args) {
        /* Input. All the country names (and its corresponding continent) are stored in
        a separate file for convenience. */
        if (args.length > 0) {
            if ( args[0].equals("-t") || args[0].equals("--test") ) {
                String inputPath = "assets" + fs + "continents-dict-test.csv";
                System.out.println("Executing test case. Reading dictionary of countries from " + inputPath + newline);
                return ContinentsDict.read(inputPath);
            }
            else if ( args[0].equals("-c") || args[0].equals("--complex") ) {
                String inputPath = "assets" + fs + "continents-dict.csv";
                System.out.println("Executing test case. Reading dictionary of countries from " + inputPath + newline);
                return ContinentsDict.read(inputPath);
            }
            else {
                System.out.println("ERROR. Incorrect usage.");
                System.out.println("Pass -t or --test for executing a test case (just draws Italy).");
                System.out.println("Pass -c or --complex for drawing the whole World.");
                System.exit(1);
            }
        }
        System.out.println("Warning. Execution mode not specified.");
        System.out.println("Pass -t or --test for executing a test case (just draws a few countries).");
        System.out.println("Pass -c or --complex for drawing the whole World.");
        System.out.println("Executing complex case. Reading dictionary of countries from " + ContinentsDict.defaultPath + newline);
        return ContinentsDict.read();
    }
}

