public class Airline {
    private String name;
    private int nPlanes;

    public Airline(String initName, int initPlanes){
        name = initName;
        nPlanes = initPlanes;
    }

    public String getName(){
        return name;
    }
    
    public int getNPlanes(){
        return nPlanes;
    }

    public void printName(){
        System.out.println(name);
    }
    
    public void printAirline(){
        System.out.println("Name: " + name + "\nNumber of planes: " + nPlanes);
    }
}
