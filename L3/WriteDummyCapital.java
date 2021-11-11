import java.util.*;
import java.io.*;

public class WriteDummyCapital{
    public static void main(String[] args) {
        BufferedWriter out = null;

        File folder = new File("resources");
        String contents[] = folder.list(); //We get the name of each sub-directory
        

        for(String directory: contents ){
            System.out.println(directory);
            try{
                File folder2 = new File("resources/" + directory);
                String contents2[] = folder2.list(); //We get the name of each sub-directory

                for(String country: contents2 ){
                    System.out.println(country);
                    FileWriter fstream = new FileWriter("resources/"+directory+"/"+country, true); //true tells to append data.
                    out = new BufferedWriter(fstream);
                    out.write("\nDummyCapital 1 -85 100");
                    out.close();
                }
                
            } catch (Exception e) {
                System.out.println("An exception occurred.");
                e.printStackTrace();
            }
        }
    }
}