package input;
import java.util.Map;
import java.util.Scanner;
import java.io.File;

// Checks that the input files corresponding to the country borders have the appropiate format.
public class CheckFormat {
    public static final String fs = File.separator;
    public static final String defaultFolder = "assets";

    public static void main(String[] args) {
        String point_pattern = "[+-]?[0-9]*[.]?[0-9]*[;][+-]?[0-9]*[.]?[0-9]*";
        Map<String, String> dict = ContinentsDict.read(); // Read continentds-dict.csv and store it as a Map.

        for ( String country: dict.keySet() ) {
            File folder = new File(defaultFolder + fs + dict.get(country) + fs + country);
            File[] polygFiles = folder.listFiles();
            boolean testPassed = true;
            int polygTested = 0;

            for (File pf: polygFiles) { 
                try {
                    Scanner scan = new Scanner(pf);
                    while ( scan.hasNext() && testPassed == true) {
                        String s = scan.nextLine();
                        testPassed = s.matches(point_pattern);
                    }
                    scan.close();
                    if (testPassed) polygTested++;
                    else break;
                } catch (Exception e) {
                    System.out.println("Error when checking format of polygon " + polygTested + " of country " + country);
                    e.printStackTrace();
                }
            }

            if (testPassed) System.out.println(country + " passed the test succesfully.");
            else {
                System.out.println("Polygon " + polygTested + " of " + country + " did not pass the test.");
                break;
            }
        }
    }
}
