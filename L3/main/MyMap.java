package main;
public class MyMap extends javax.swing.JPanel {
    /************************ Attributes *************************/
    private World world;
    public static final int xdim = 800;
    public static final int ydim = 800;

    /************************ Constructor ************************/
    public MyMap(World world) {
        initComponents();
        this.world = world;
    }

    /************************ Methods ****************************/
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, xdim, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, ydim, Short.MAX_VALUE)
        );
    }

    public void paint( java.awt.Graphics g ) {
        super.paint( g );
        world.draw( g );
    }

    // Formulas obtained from https://en.wikipedia.org/wiki/Web_Mercator_projection
    public static Point webMercatorProj(double latitude, double longitude) {
        int zoomLevel = 0;
        latitude = (latitude / 180) * Math.PI;
        longitude = (longitude / 180) * Math.PI;

        double x = Math.floor( xdim/(2*Math.PI) * Math.pow(2, zoomLevel) * (longitude + Math.PI) );
        double y = Math.floor( ydim/(2*Math.PI) * Math.pow(2, zoomLevel) * (Math.PI - Math.log( Math.tan(Math.PI/4 + latitude/2)) ) );

        /* Because the Mercator projects the poles at infinity, a map using the Web Mercator projection cannot show the poles.
        Services such as Google Maps cut off coverage at 85.051129° north and south. We have decided to set the maximum value according to the window dimensions.
        The value 85.051129° is the latitude at which the full projected map becomes a square. (https://en.wikipedia.org/wiki/Web_Mercator_projection) */
        y = Math.min(y, ydim);
        return new Point(x, y);
    }
}

