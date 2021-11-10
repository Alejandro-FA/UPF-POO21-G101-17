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
        double yscale = 1.8;

        Point[] can_points = {new Point(278,436/yscale), new Point(125,452/yscale), new Point(59,288/yscale), new Point(259,295/yscale)};
        Point[] usa_points = {new Point(278, 436/yscale), new Point(125, 452/yscale), new Point(204, 634/yscale)};
        Point[] bra_points = {new Point(204,634/yscale), new Point(250,808/yscale), new Point(316,798/yscale)};
        Point[] arg_points = {new Point(250, 808/yscale), new Point(316, 798/yscale), new Point(258, 992/yscale)};
        Point[] sudaf_points = {new Point(493, 804/yscale), new Point(454, 630/yscale), new Point(547, 605/yscale)};
        Point[] alger_points = {new Point(454, 630/yscale), new Point(547, 605/yscale), new Point(542, 511/yscale), new Point(423, 489/yscale)};
        Point[] cat_points = {new Point(429,477/yscale), new Point(527,454/yscale), new Point(517, 276/yscale)};
        Point[] russ_points = {new Point(527, 454/yscale), new Point(517, 276/yscale), new Point(875, 237/yscale), new Point(700, 632/yscale)};

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

