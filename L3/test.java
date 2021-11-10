import java.util.*;

public class test {
    public static void main(String[] args) {
        String point_pattern = "[+-]?[0-9]*[.]?[0-9]* [+-]?[0-9]*[.]?[0-9]*"; // Two doubles separated by a whitespace
        String city_pattern = "([A-Z][a-z]*[ ]?)+ [0-9]+ " + point_pattern;

        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        double d = 0.0;
        if (scan.hasNext(point_pattern)) {
            String s = scan.nextLine();
            Scanner scan2 = new Scanner(s);
            d = scan2.nextDouble();
            scan2.close();
            System.out.println(d);
        }

        if (scan.hasNext(city_pattern)) {
            String s = scan.nextLine();
            System.out.println(s);
        }
        scan.close();
        
    }
}
