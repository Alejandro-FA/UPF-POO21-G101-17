package input;
public class CreateFiles {
    public static void main(String[] args) {
        // BorderFiles.json2CSV("assets/countries.geojson");
        boolean append = false;
        CityFiles.writeToAll(append, "Dummy Capital City", 12, -85.0, 179.9);
    }
}
