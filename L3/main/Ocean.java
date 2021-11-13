package main;
import java.util.*;

public class Ocean {
    private List<Ocean> adj_ocean;
    private List<Continent> adj_cont;

    public Ocean(List<Ocean> adj_ocean, List<Continent> adj_cont) {
        this.adj_ocean = adj_ocean;
        this.adj_cont = adj_cont;
    }
}
