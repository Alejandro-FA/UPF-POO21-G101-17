import java.util.*;
import java.io.*;

public class CleanInput {
    public static void main(String[] args) {
        File geojson = new File("resources/countries.geojson");

        try {
            Scanner file_scan = new Scanner(geojson);

            for (int i = 0; i < 3; i++) {
                // Read line to move to new file
                String input_str = file_scan.nextLine();

                // Determine name of the country
                String file_name = input_str.substring(input_str.indexOf('"') + 1);
                file_name = file_name.substring(0, file_name.indexOf('"'));
                
                // Create new file
                FileWriter writer = new FileWriter("resources/test/" + file_name + ".txt");

                // Parse string to desired format
                String output_str = input_str.replaceFirst("[ ][\"]", "");
                output_str = output_str.replaceFirst("[\"]", "");
                output_str = output_str.replaceFirst("[:][ ]", "\n");
                output_str = output_str.replace(" ],", "\n");
                output_str = output_str.replace(" [ ", "");
                output_str = output_str.replace(",", "");
                writer.write(output_str);
                writer.close();
                System.out.println(file_name + " file succesfully written.");
            }

            file_scan.close();

        } catch (Exception e) {
            System.out.println("An exception occurred.");
            e.printStackTrace();
        }
    }
}
