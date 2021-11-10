// We are implementing Region as an abstract class, since both methods will be overriden by the corresponding subclasses.
public abstract class Region {
    /************************ Methods ****************************/
    public abstract double getArea();
    public abstract void draw(java.awt.Graphics g);
}
