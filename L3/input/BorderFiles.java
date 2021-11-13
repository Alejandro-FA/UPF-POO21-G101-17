package input;
import java.util.Scanner;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BorderFiles {
    public static final String csvDelimiter = ";";
    public static final String newline = System.lineSeparator();
    public static final String fs = File.separator;
    public static final String defaultFolder = "assets";

    // Convert the countries.geojson file available in https://datahub.io/core/geo-countries to multiple .csv files
    public static void json2CSV(String geojsonPath) {
        Map<String, String> dict = ContinentsDict.read(); // Read continentds-dict.csv and store it as a Map.
        File geojson = new File(geojsonPath);

        try {
            Scanner fileScan = new Scanner(geojson);
            while (fileScan.hasNextLine()) {
                String s = fileScan.nextLine();
                // TODO: Parallelize process for better performance
                String[] sArray = s.split("\"coordinates\": ", 2);

                if (sArray.length > 1) { // True if the line contains the substring <"coordinates": >
                    String countryName = sArray[0].split("\"ADMIN\": \"", 2)[1].split("\"", 2)[0];
                    System.out.println("Writing " + countryName + " files...");
                    String polygonType = sArray[0].split("\"geometry\": [{] \"type\": \"", 2)[1].split("\"", 2)[0];
                    String rawCoords = sArray[1]; // Get coordinates
                    String basePath = defaultFolder + fs + dict.get(countryName) + fs + countryName + fs + countryName;
                    
                    // Parse coordinates and write them to a new file with CSV format
                    if ( polygonType.equals("MultiPolygon") ) {
                        String polygons[] = rawCoords.split(Pattern.quote("] ],")); // Split each separate polygon
                        for (int i = 0; i < polygons.length; i++) {
                            String parsedCoords = parseCoord(rawCoords);
                            writeCoords(basePath + "-" + i + ".csv", parsedCoords);
                        }
                    } else {
                        String parsedCoords = parseCoord(rawCoords);
                        writeCoords(basePath + ".csv", parsedCoords);
                    }
                }
            }
            fileScan.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error. Could not find " + geojsonPath);
            e.printStackTrace();
        }
    }

    // Auxiliary function to convert array of Coords to CSV-like format
    private static String parseCoord(String coord) {
        // Remove undesired trailing characters
        int len = coord.length();
        int trailingChars = 0;
        while ( coord.charAt(len - 1 - trailingChars) != ']' ) trailingChars++;
        coord = coord.substring(0, coord.length() - trailingChars);

        // Remove undesired beginning characters
        len = coord.length();
        int beginningChars = 0;
        while ( coord.charAt(0 + beginningChars) != '[' ) beginningChars++;
        coord = coord.substring(beginningChars);

        // Separate each point into a different line
        coord = coord.replace(" ], ", newline); // If ']' is followed by a ',' -> new point

        // Remove the rest of the undesired characters
        coord = coord.replace("[ ", ""); // Remove "[ "
        coord = coord.replace(" ]", ""); // Remove " ]"

        // Change separate latitude and longitude with a semiclon
        coord = coord.replace(", ", ";");
        return coord;
    }

    // Auxiliary function to write coordinates to .csv file
    private static void writeCoords(String filePath, String coords) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create parent directories if missing
        try {
            FileWriter output = new FileWriter(file, false); // false is for overriding instead of appending data
            output.write(coords);
            output.write(newline); // Adds a trailing newline for CSV format recommendations compliance
            output.close();
        } catch (IOException e) {
            System.out.println("Error. Could not write to " + filePath);
            e.printStackTrace();
        }
    }
}
