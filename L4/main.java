import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.parser.Entity;

public class main {
    public static void main(String[] args) {
        // Creation of the drawer instance
        //EntityDrawer drawer = new EntityDrawer();

        String slash = "/";
        // Reading all the entities from `assets` 
        File assets = new File("assets"); // We open the folder assets
        File[] folders = assets.listFiles();  // We list all the folders
        String[] correct_regions = new String[]{"EllipsoidalRegion", "PolygonalRegion"};

        List<Entity> entities = new ArrayList<>();
        
        for (File region_kind: folders){ //We iterate over all the folders of assets
            String folder_name = region_kind.getName();
            if(correct_regions[0].equals(folder_name) || correct_regions[1].equals(folder_name)){ // We check being in the correct folder
                File regions = new File("assets/"+folder_name);
                File[] files = regions.listFiles();  // We list all the files inside the folder
                for (File file: files){   // We iterate over all the files of the folder
                    String file_name = file.getName();
                    if(folder_name.equals("EllipsoidalRegion")){ // If the folder is EllipsoidalRegion
                        try{
                            Scanner sc = new Scanner(new File("assets/"+folder_name+slash+file_name));  
                            sc.useDelimiter(";");
                            List<Double> coordinates = new ArrayList<>();
                            while (sc.hasNextDouble()){  // We read the coordinates
                                coordinates.add(sc.nextDouble());
                            }
                            int len = coordinates.size(); // We save the length of coordinates in order to know if it is a circle or an ellipse
                            System.out.println(len);
                            List<String> colors = new ArrayList<>();
                            while (sc.hasNext()){  // We read the colors
                                colors.add(sc.next());
                            }
                            String lineColor = colors.get(0);
                            String fillColor = colors.get(1);

                            if (len == 3){ // If it is a circle
                                double centerX = (double) coordinates.get(0);
                                double centerY = (double) coordinates.get(1);
                                double radius = (double) coordinates.get(2);

                                entities.add(new CircularRegion(Color.lineColor, Color.fillColor, new Point(centerX, centerX), radius ));
                            }
                            if (len == 4){ // If it is an ellipse
                                double centerX = (double) coordinates.get(0);
                                double centerY = (double) coordinates.get(1);
                                double radius1 = (double) coordinates.get(2);
                                double radius2 = (double) coordinates.get(3);

                                entities.add(new EllipsoidalRegion(Color.lineColor, Color.fillColor, new Point(centerX, centerX), radius1, radius2 ));
                            } 
                            sc.close();
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("File "+file_name+" not found!");
                        } 
                    }
                    else if(folder_name.equals("PolygonalRegion")){ // If the folder is EllipsoidalRegion
                        try{
                            Scanner sc = new Scanner(new File("assets/"+folder_name+slash+file_name));  
                            sc.useDelimiter(";");
                            //Entity  = ...
                            
                            sc.close();
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("File "+file_name+" not found!");
                        } 
                    }
                    
                }
            }
        } 

    }
}
