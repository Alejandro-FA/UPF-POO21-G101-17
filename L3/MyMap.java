public class MyMap extends javax.swing.JPanel {
    /************************ Attributes *************************/
    private World world;
    public static final int xdim = 1000;
    public static final int ydim = 1000;


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
}

