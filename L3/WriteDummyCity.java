import java.util.*;
import java.io.*;

public class WriteDummyCity{
    public static void main(String[] args) {
        BufferedWriter out = null;

        File countries_folder = new File("resources/countries");
        String conts[] = countries_folder.list(); //We get the name of each sub-directory
        

        for(String directory: conts) {
            System.out.println(directory);
            try{
                File continent_folder = new File("resources/countries/" + directory);
                String countries[] = continent_folder.list(); //We get the name of each sub-directory

                for(String country: countries) {
                    System.out.println(country);
                    FileWriter fstream = new FileWriter("resources/cities/" + directory + "/" + country, true); //true tells to append data.
                    out = new BufferedWriter(fstream);
                    out.write("DummyCity 1 -85 100");
                    out.close();
                }
                
            } catch (Exception e) {
                System.out.println("An exception occurred.");
                e.printStackTrace();
            }
        }
    }
}