import java.util.*;

public class World {
    /************************ Attributes *************************/
    private List<Continent> conts;
    private List<Ocean> oceans;

    /************************ Constructor ************************/
    public World(List<Continent> conts, List<Ocean> oceans ) {
        this.conts = conts;
        this.oceans = oceans;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g) {
        for (Continent c: conts) c.draw(g);
    }
}
