package input;
import java.io.*;
import java.util.*;
import main.City;
import main.Point;
import main.MyMap;

public final class CityFiles {
    /************************ Attributes *************************/
    public static final String csvDelimiter = ";";
    public static final String newline = System.lineSeparator();
    public static final String fs = File.separator;
    public static final String defaultFolder = "assets";
    public static final String defaultPath = "cities.csv";

    /************************ Constructor ************************/
    private CityFiles(){}; // Private constructor to avoid instantiation

    /************************ Methods ****************************/
    // Write the specified city to the specified country with CSV format
    private static void writeTo(String filePath, boolean append, String cityName, int numInhab, double latitude, double longitude) {
        try {
            FileWriter file = new FileWriter(filePath, append); // true tells to append data.
            file.write(cityName + csvDelimiter + numInhab + csvDelimiter + latitude + csvDelimiter + longitude + newline);
            file.close();
        } catch (IOException e) {
            System.out.println("Error. Could not write to " + filePath);
            e.printStackTrace();
        }
    }

    // Write the specified city to all the countries of the Continents Dictionary.
    // It uses the following structure: <continent>/<country>/cities.csv
    public static void writeToAll(boolean append, String cityName, int numInhab, double latitude, double longitude) {
        // Read dictionary that classifies each country into certain continent.
        // If the dictionary is not found in the default path, it asks for an alternative path.
        Map<String, String> dict;
        try {
            dict = ContinentsDict.read(); // Read dictionary from defualt path (continents-dict.csv)
        } catch (Exception e) {
            System.out.println("Could not find " + ContinentsDict.defaultPath);
            System.out.println("Please specify the correct path of the continents dictionary: ");
            Scanner input = new Scanner(System.in);
            dict = ContinentsDict.read(input.nextLine());
            input.close();
        }
        
        // Write city to the corresponding files
        for(String country: dict.keySet()) {
            writeTo(defaultFolder + fs + dict.get(country) + fs + country + fs + defaultPath, append, cityName, numInhab, latitude, longitude);
            System.out.println(cityName + " written to " + country + " successfully");
        }
    }

    // Read files that contains City information
    public static List<City> read(File file) {
        try {
            Scanner city_scan = new Scanner(file);
            city_scan.useDelimiter("[" + csvDelimiter + newline + "]");
            List<City> cities = new LinkedList<City>();
            while (city_scan.hasNext()){
                String name = city_scan.next();
                int habitants = city_scan.nextInt();
                double latitude = city_scan.nextDouble();
                double longitude = city_scan.nextDouble();
                Point p = MyMap.webMercatorProj(latitude, longitude);
                cities.add(new City(p.getX(), p.getY(), name, habitants) );
            }
            city_scan.close();
            return cities;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error when reading " + file.getAbsolutePath());
            e.printStackTrace();
        }

        return null;
    }
}