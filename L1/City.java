

public class City {
    private double x;
    private double y;
    private String name;
    
    // Constructor method
    public City(double initX, double initY, String initName){
        x = initX;
        y = initY;
        name = initName;
    }
    
    // Getters and setters
    public double getX(){
        return x;
	}
    public double getY(){
        return y;
	}
    public void setX(double newX){
       x = newX;
	}
    public void setY(double newY){
        y = newY;
	}

    public String getName(){
        return name;
    }
    
    // Other methods
    public double distanceTo(City city2){
        double xDist = city2.x - x;
        double yDist = city2.y - y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    
    public void printCity(){
         System.out.println("(" + x + ", " + y + ")");
    }
}
