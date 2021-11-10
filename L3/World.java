import java.util.*;

public class World {
    /************************ Attributes *************************/
    private List<Continent> conts;

    /************************ Constructor ************************/
    public World(List<Continent> conts_list) {
        conts = conts_list;
    }

    /************************ Methods ****************************/
    public void draw(java.awt.Graphics g) {
        for (Continent c: conts) c.draw(g);
    }
}
