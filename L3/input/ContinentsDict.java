package input;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class ContinentsDict {
    public static final String fs = File.separator;
    public static final String defaultFolder = "assets";
    public static final String defaultPath = defaultFolder + fs + "continents-dict.csv";
    public static final String csvDelimiter = ";";
    public static String newline = System.lineSeparator();

    // Read CSV file and return a HashMap that assigns each country to a continent
    public static Map<String, String> read(String filePath) {
        Map<String,String> dict = new HashMap<String,String>();

        try {
            File dictFile = new File(filePath);
            Scanner fileScan = new Scanner(dictFile);
            fileScan.useDelimiter("[" + csvDelimiter + newline + "]"); // Specify possible delimiters with regex notation
            while (fileScan.hasNext()) {
                String countryName = fileScan.next();
                String continentName = fileScan.next();
                dict.put(countryName, continentName);
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find " + filePath + ". Output map is empty.");
        }

        return dict;
    }

    public static Map<String, String> read() {
        return read(defaultPath);
    }

    @Deprecated
    public static void main(String[] args) {
        try {
            FileWriter dictFile = new FileWriter(defaultPath, false); // false is for overriding instead of appending data
            String continents[] = {"Africa", "Antartica", "Asia", "Europe", "NorthAmerica", "Oceania", "SouthAmerica"};
    
            for(String continent: continents) {
                File continent_folder = new File(defaultFolder + fs + continent);
                String countries[] = continent_folder.list(); //We get the name of each sub-directory
    
                for(String country: countries)
                    dictFile.write(country + csvDelimiter + continent + newline);
            }
            dictFile.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
