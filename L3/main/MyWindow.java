package main;
import input.ContinentsDict;
import input.CityFiles;
import input.BorderFiles;
import java.util.*;
import java.io.File;

import javax.swing.border.Border;

import java.io.*;

public class MyWindow extends javax.swing.JFrame {
    private static final String fs = File.separator;
    private static final String csv_Border = BorderFiles.csvDelimiter;
    private static final String csv_City = CityFiles.csvDelimiter;
    private static final String newline = System.lineSeparator();

    public MyWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
    }

    public static void main( String[] args ) {
        /* Input. All the country names (and its corresponding continent) are stored in
        a separate file for convenience. */
        Map<String, String> dict = ContinentsDict.read();
        Map<String, LinkedList<Country>> continents = new HashMap<String, LinkedList<Country>>();
        for (String cont: dict.values()) {
            continents.put(cont, new LinkedList<Country>()); 
        }
        
        for (String countryName: dict.keySet()) {
            String continentName = dict.get(countryName);
            String basePath = ContinentsDict.defaultFolder + fs + continentName + fs + countryName;
            File country_folder = new File(basePath);
            File[] regionsFiles = country_folder.listFiles();
            
            List<City> cities = new LinkedList<City>();
            List<PolygonalRegion> regions = new LinkedList<PolygonalRegion>();

            for (File region: regionsFiles) {
                // If file contains city data:
                if ( region.getName() == CityFiles.defaultPath ) {
                    try {
                        Scanner city_scan = new Scanner(basePath + fs + region);
                        city_scan.useDelimiter("[" + csv_City + newline + "]");
                        while (city_scan.hasNext()){
                            String name = city_scan.next();
                            int habitants = city_scan.nextInt();
                            double latitude = city_scan.nextDouble();
                            double longitude = city_scan.nextDouble();
                            Point p = MyMap.webMercatorProj(latitude, longitude);
                            cities.add(new City(p.getX(), p.getY(), name, habitants) );
                        }
                        city_scan.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error when reading " + basePath + fs + CityFiles.defaultPath);
                        e.printStackTrace();
                    }

                // Else file contains border data:
                } else {
                    try {
                        Scanner scan = new Scanner(basePath + region);
                        scan.useDelimiter("[" + csv_Border + newline + "]"); // Specify possible delimiters
                        List<Point> points = new LinkedList<Point>(); // Initialize list of points;
                        while (scan.hasNext()) {
                            double longitude = scan.nextDouble();
                            double latitude = scan.nextDouble();
                            Point p = MyMap.webMercatorProj(latitude, longitude);
                            points.add(p);
                        }
                        scan.close();
                        regions.add( new PolygonalRegion(points) );
                        
                    } catch (Exception e) {
                        System.out.println("Error when reading " + basePath + fs + region.getName());
                        e.printStackTrace();
                    }
                }
            }

            // Add country to the list. The capital is the first city
            if (regions.isEmpty() == false && cities.isEmpty() == false) {
                City capital = cities.get(0);
                Country country = new Country(regions, countryName, capital);
                continents.get(continentName).add(country);
                for(City city: cities) country.addCity(city);
            }
        }

        List<Continent> continentsList = new LinkedList<Continent>();
        for ( List<Country> countriesList: continents.values() ) {
            continentsList.add( new Continent(countriesList) );
        }
            
        // World
        World world = new World(continentsList);

        // Original content
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                MyWindow w = new MyWindow();
                MyMap m = new MyMap(world);
                w.add( m );
                w.setVisible( true );
                w.pack();
            }
        }
    }
}

