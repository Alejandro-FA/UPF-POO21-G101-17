package input;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;

public class CityFiles {
    public static final String csvDelimiter = ";";
    public static final String newline = System.lineSeparator();
    public static final String fs = File.separator;
    public static final String defaultFolder = "assets";

    // Write the specified city to the specified filePath with CSV format
    public static void writeTo(String filePath, boolean append, String cityName, int numInhab, double latitude, double longitude) {
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
            writeTo(defaultFolder + fs + dict.get(country) + fs + country + fs + "cities.csv", append, cityName, numInhab, latitude, longitude);
            System.out.println(cityName + " written to " + country + " successfully");
        }
    }
}