import java.util.*;

public class MyMap extends javax.swing.JPanel {
    /************************ Attributes *************************/
    World world;

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
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
    }

    public void paint( java.awt.Graphics g ) {
        super.paint( g );
        world.draw( g );
    }

}

