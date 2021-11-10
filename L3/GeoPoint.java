public class GeoPoint extends Point {
    protected String name;

    public GeoPoint(double xInit, double yInit, String name){
        super(xInit, yInit);
        this.name = name;
    }

    public void draw(java.awt.Graphics g){
        int x = (int)x;
        int y = (int)y;

        g.fillOval(x,  y,  2,  2);
        g.drawString(name, x, y);
    }
}
