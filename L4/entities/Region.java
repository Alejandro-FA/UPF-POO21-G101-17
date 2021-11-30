package entities;
import java.awt.*;

public abstract class Region extends Entity {
    /************************ Attributes *************************/
    protected Color fillColor;

    /************************ Constructor ************************/
    public Region(Color lineColor, Color fillColor) {
        super(lineColor);
        this.fillColor = fillColor;
    }
    
    /************************ Methods ****************************/
    public abstract double getArea();
    public abstract boolean isPointInside(Point p);

    public void setFillColor(Color color){
        this.fillColor = color;
    }
}