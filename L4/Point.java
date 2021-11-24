public class Point {
    /************************ Attributes *************************/
    protected double x;
    protected double y;

    /************************ Constructor ************************/
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /************************ Methods ****************************/
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public Vector difference(Point p) {
        return new Vector(this.x - p.getX(), this.y - p.getY());
    }
}
