import java.util.*;

public class MyWindow extends javax.swing.JFrame {

    public MyWindow() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
    }

    public static void main( String[] args ) {
        // Regions coordinates
        Point[] can_points = {new Point(278,436/2), new Point(125,452/2), new Point(59,288/2), new Point(259,295/2)};
        Point[] usa_points = {new Point(278, 436/2), new Point(125, 452/2), new Point(204, 634/2)};
        Point[] bra_points = {new Point(204,634/2), new Point(250,808/2), new Point(316,798/2)};
        Point[] arg_points = {new Point(250, 808/2), new Point(316, 798/2), new Point(258, 992/2)};
        Point[] sudaf_points = {new Point(493, 804/2), new Point(454, 630/2), new Point(547, 605/2)};
        Point[] alger_points = {new Point(454, 630/2), new Point(547, 605/2), new Point(423, 489/2), new Point(542, 511/2)};
        Point[] cat_points = {new Point(429,477/2), new Point(527,454/2), new Point(517, 276/2)};
        Point[] russ_points = {new Point(527, 454/2), new Point(517, 276/2), new Point(875, 237/2), new Point(700, 632/2)};

        List<Point> can_coords = Arrays.asList(can_points);
        List<Point> usa_coords = Arrays.asList(usa_points);
        List<Point> bra_coords = Arrays.asList(bra_points);
        List<Point> arg_coords = Arrays.asList(arg_points);
        List<Point> sudaf_coords = Arrays.asList(sudaf_points);
        List<Point> alger_coords = Arrays.asList(alger_points);
        List<Point> cat_coords = Arrays.asList(cat_points);
        List<Point> russ_coords = Arrays.asList(russ_points);

        // Regions or countries
        PolygonalRegion can = new PolygonalRegion(can_coords);
        PolygonalRegion usa = new PolygonalRegion(usa_coords);
        PolygonalRegion bra = new PolygonalRegion(bra_coords);
        PolygonalRegion arg = new PolygonalRegion(arg_coords);
        PolygonalRegion sudaf = new PolygonalRegion(sudaf_coords);
        PolygonalRegion alger = new PolygonalRegion(alger_coords);
        PolygonalRegion cat = new PolygonalRegion(cat_coords);
        PolygonalRegion russ = new PolygonalRegion(russ_coords);
        
        // Continents
        LinkedList<PolygonalRegion> na_regions = new LinkedList<PolygonalRegion>();
        LinkedList<PolygonalRegion> sa_regions = new LinkedList<PolygonalRegion>();
        LinkedList<PolygonalRegion> af_regions = new LinkedList<PolygonalRegion>();
        LinkedList<PolygonalRegion> euas_regions = new LinkedList<PolygonalRegion>();

        na_regions.add(can);
        na_regions.add(usa);
        sa_regions.add(bra);
        sa_regions.add(arg);
        af_regions.add(sudaf);
        af_regions.add(alger);
        euas_regions.add(cat);
        euas_regions.add(russ);
        
        Continent nAmerica = new Continent(na_regions);
        Continent sAmerica = new Continent(sa_regions);
        Continent africa = new Continent(af_regions);
        Continent euAsia = new Continent(euas_regions);
        
        // World
        LinkedList<Continent> world_conts = new LinkedList<Continent>();
        world_conts.add(nAmerica);
        world_conts.add(sAmerica);
        world_conts.add(africa);
        world_conts.add(euAsia);
        World world = new World(world_conts);

        // Original content
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                MyWindow w = new MyWindow();
                MyMap m = new MyMap(world);
                w.add( m );
                w.setVisible( true );
                w.pack();
            }
        } );
    }

}

