package main;
import gui.EntityDrawer;
import input.*;
import entities.Entity;
import java.util.*;
import java.io.File;

public class TestGUI {
    public static void main(String[] args) {
        String assetsPath = "assets" + File.separator; // Name of the resources root folder
        String csvDelimiter = ";";
        List<Entity> entitiesList = new LinkedList<Entity>();
        
        // Read EllipsoidalRegions
        EllipsesFile ellipsesFile = new EllipsesFile(assetsPath + "ellipses.csv", csvDelimiter);
        EllipsesFile circlesFile = new EllipsesFile(assetsPath + "circles.csv", csvDelimiter);
        entitiesList.addAll(ellipsesFile.read());
        entitiesList.addAll(circlesFile.read());

        // Read PolygonalRegions
        String pointDelimiter = ",";
        PolygonsFile polygonsFile = new PolygonsFile(assetsPath + "polygons.csv", csvDelimiter, pointDelimiter);
        PolygonsFile rectanglesFile = new PolygonsFile(assetsPath + "rectangles.csv", csvDelimiter, pointDelimiter);
        PolygonsFile trianglesFile = new PolygonsFile(assetsPath + "triangles.csv", csvDelimiter, pointDelimiter);
        entitiesList.addAll(polygonsFile.read());
        entitiesList.addAll(rectanglesFile.read());
        entitiesList.addAll(trianglesFile.read());

        // Create GUI
        EntityDrawer drawer = new EntityDrawer();
        for (Entity e: entitiesList) drawer.addDrawable(e);
    }
}