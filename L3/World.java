import java.util.*;

public class World {
    /************************ Attributes *************************/
    private List<Continent> conts;
    
    /************************ Constructor ************************/
    public World(List<Continent> conts) {
        this.conts = conts;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g) {
        for (Continent c: conts) c.draw(g);
    }
}
