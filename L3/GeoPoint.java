public class GeoPoint extends Point {
    protected String name;

    public GeoPoint(double xInit, double yInit, String name){
        super(xInit, yInit);
        this.name = name;
    }

    public void draw(java.awt.Graphics g){
        int xi = (int)x;
        int yi = (int)y;

        g.fillOval(xi,  yi,  2,  2);
        g.drawString(name, xi, yi);
    }
}
