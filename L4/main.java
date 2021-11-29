import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

import javax.swing.text.html.parser.Entity;

public class main {
    public static void main(String[] args) {
        // Creation of the drawer instance
        //EntityDrawer drawer = new EntityDrawer();

        EntityDrawer drawer = new EntityDrawer();

        String slash = "/";
        // Reading all the entities from `assets` 
        File assets = new File("assets"); // We open the folder assets
        File[] folders = assets.listFiles();  // We list all the folders
        String[] correct_regions = new String[]{"EllipsoidalRegion", "PolygonalRegion"};

        List<EllipsoidalRegion> ellipsoidalRegions = new LinkedList<EllipsoidalRegion>();
        List<CircularRegion> circularRegions = new LinkedList<CircularRegion>();
        List<PolygonalRegion> polygRegions = new LinkedList<PolygonalRegion>();
        List<TriangularRegion> triangularRegions = new LinkedList<TriangularRegion>();
        List<RectangularRegion> rectangularRegions = new LinkedList<RectangularRegion>();
        
        for (File region_kind: folders){ //We iterate over all the folders of assets
            String folder_name = region_kind.getName();
            if(correct_regions[0].equals(folder_name) || correct_regions[1].equals(folder_name)){ // We check being in the correct folder
                System.out.println("Entered folder: "+folder_name);
                File regions = new File("assets/"+folder_name);
                File[] files = regions.listFiles();  // We list all the files inside the folder
                for (File file: files){   // We iterate over all the files of the folder
                    String file_name = file.getName();
                    System.out.println("Analyzing file: "+file_name);
                    if(folder_name.equals("EllipsoidalRegion")){ // If the folder is EllipsoidalRegion
                        try{
                            System.out.println("Analyzing ellipse : "+file_name);
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
                            System.out.println("Adding an EllipsoidalRegion with colors: "+lineColor+", "+fillColor);
                            if (len == 3){ // If it is a circle
                                double centerX = (double) coordinates.get(0);
                                double centerY = (double) coordinates.get(1);
                                double radius = (double) coordinates.get(2);

                                circularRegions.add(new CircularRegion(Color.getColor(lineColor), Color.getColor(fillColor), new Point(centerX, centerY), radius ));
                            }
                            if (len == 4){ // If it is an ellipse
                                double centerX = (double) coordinates.get(0);
                                double centerY = (double) coordinates.get(1);
                                double radius1 = (double) coordinates.get(2);
                                double radius2 = (double) coordinates.get(3);

                                ellipsoidalRegions.add(new EllipsoidalRegion(Color.getColor(lineColor), Color.getColor(fillColor), new Point(centerX, centerY), radius1, radius2 ));
                            } 
                            sc.close();
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("File "+file_name+" not found!");
                        } 
                    }
                    else if(folder_name.equals("PolygonalRegion")){ // If the folder is EllipsoidalRegion
                        try{
                            System.out.println("Analyzing polygon : "+file_name);
                            Scanner sc = new Scanner(new File("assets/"+folder_name+slash+file_name));  
                            sc.useDelimiter(";");
                            List<Double> coordinates = new ArrayList<>();
                            while (sc.hasNextDouble()){  // We read the coordinates
                                coordinates.add(sc.nextDouble());
                            }
                            int len = coordinates.size(); // We save the length of coordinates in order to know if it is a polygon, a triangle or a rectangle
                            List<String> colors = new ArrayList<>();
                            while (sc.hasNext()){  // We read the colors
                                colors.add(sc.next());
                            }
                            
                            String lineColor = colors.get(0);
                            String fillColor = colors.get(1);
                            
                            
                            
                            if(len == 4){ //If it is a rectangle
                                double point1X = (double) coordinates.get(0);
                                double point1Y = (double) coordinates.get(1);
                                double point2X = (double) coordinates.get(2);
                                double point2Y = (double) coordinates.get(3);

                                rectangularRegions.add(new RectangularRegion(Color.getColor(lineColor), Color.getColor(fillColor), new Point(point1X, point1Y), new Point(point2X, point2Y)));
                            }
                            else if(len == 6){ //If it is a triangle
                                double point1X = (double) coordinates.get(0);
                                double point1Y = (double) coordinates.get(1);
                                double point2X = (double) coordinates.get(2);
                                double point2Y = (double) coordinates.get(3);
                                double point3X = (double) coordinates.get(4);
                                double point3Y = (double) coordinates.get(5);

                                triangularRegions.add(new TriangularRegion(Color.getColor(lineColor), Color.getColor(fillColor), new Point(point1X, point1Y), new Point(point2X, point2Y), new Point(point3X, point3Y)));
                            }

                            else if(len > 6){ //If it is a polygon
                                
                                polygRegions.add(new PolygonalRegion(Color.getColor(lineColor), Color.getColor(fillColor)));

                                int index_added_polygon = polygRegions.size()-1;

                                for(int i = 0; i<len; i = i+2){
                                    double coordX = coordinates.get(i);
                                    double coordY = coordinates.get(i+1);

                                    polygRegions.get(index_added_polygon).addPoint(new Point(coordX, coordY));
                                }
                            }

                            sc.close();
                        }
                        catch (FileNotFoundException e) {
                            System.out.println("File "+file_name+" not found!");
                        } 
                    }
                    
                }
            }
        } 
        for (EllipsoidalRegion ellipse: ellipsoidalRegions) drawer.addDrawable(ellipse);
        for (CircularRegion circle: circularRegions) drawer.addDrawable(circle);
        for (RectangularRegion rectangle: rectangularRegions) drawer.addDrawable(rectangle);
        for (TriangularRegion triangle: triangularRegions) drawer.addDrawable(triangle);
        for (PolygonalRegion polygonalRegion: polygRegions) drawer.addDrawable(polygonalRegion);

    }
}
