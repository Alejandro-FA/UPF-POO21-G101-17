import java.awt.*;

public class CircularRegion extends EllipsoidalRegion {
    private double radius;

    public CircularRegion(Color lineColor, Color fillColor, Point centre, double radius) {
        super(lineColor, fillColor, centre, radius, radius);
        this.radius = radius;
    }
}
