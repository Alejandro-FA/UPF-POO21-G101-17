# Seminar 1 OOP

## Geometric Point class

#### UML diagram

![](/Users/alejandro/Sync/Computer_Engineering/Object_Oriented_Programming/oop_labs/S1/uml-point.svg)

#### Implementation

````java
public class Point{
    // Attributes
    private double x;
    private double y;
    
    // Constructor method
    public Point(double initX, double initY){
        x = initX;
        y = initY;
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
    
    // Other methods
    public double distanceTo(Point point2){
        xDist = point2.x - x;
        yDist = point2.y - y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    
    public void printPoint(){
         System.out.println("(" + x + ", " + y + ")");
    }
}
````

## Matrix Distance class

#### UML diagram

![uml-MatrixDistance](/Users/alejandro/Sync/Computer_Engineering/Object_Oriented_Programming/oop_labs/S1/uml-MatrixDistance.svg)

#### Implementation

```java
public class MatrixDistance{
    // Attributes
    private List<Point> pointsList;
    // private double[10][10] matrix;
    private List<List<Double>> matrix;
    
    // Constructor method
    public MatrixDistance(){
        pointsList = new ArrayList<Point>();
        matrix = new ArrayList<ArrayList<Double>>(); 
    }
     
    // Other methods
	public double getDistance(Point p1, Point p2){
        xDist = p2.x - p1.x;
        yDist = p2.y - p1.y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    
    private void buildMatrix(Point newPoint){
        // TO DO
    }
    
    public void addPoint(Point newPoint){
        // TO DO
    }
}
```



