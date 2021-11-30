package input;
import entities.Entity;
import java.util.*;
import java.io.*;
import java.awt.Color;

public abstract class EntitiesFile {
    /************************ Attributes *************************/
    protected String filePath;
    protected File file;
    protected String csvDelimiter;

    /************************ Constructor ************************/
    public EntitiesFile(String filePath, String csvDelimiter) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.csvDelimiter = csvDelimiter;
    }

    /************************ Methods ****************************/
    public List<Entity> read() {
        System.out.println("Reading file: " + this.filePath);
        try {
            Scanner sc = new Scanner(this.file); // Open file
            List<Entity> entitiesList = new LinkedList<Entity>();

            while (sc.hasNext()) {  // We read all lines of the file
                String fields = sc.nextLine();
                Entity e = this.parseFields(fields);
                if (e != null) entitiesList.add(e);
            }
            sc.close();
            return entitiesList;

        } catch (FileNotFoundException e) {
            System.out.println("ERROR. Could not read from " + file.getAbsolutePath() + "!");
            return null;
        }
    }

    protected Color parseColor(Scanner sc) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        return new Color(red, green, blue);
    }

    protected abstract Entity parseFields(String fields); // WARNING! Assumes csv format
}
