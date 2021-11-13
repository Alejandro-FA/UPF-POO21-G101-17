public class CreateInputFiles {
    public static void main(String[] args) {
        // BorderFiles.json2CSV("countries.geojson");
        CityFiles.writeToAll(false, "Capital City", 12, -85.0, 179.9);
    }
}
