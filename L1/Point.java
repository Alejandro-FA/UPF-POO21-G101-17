public class Point {
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
        double xDist = point2.x - x;
        double yDist = point2.y - y;
        return Math.sqrt(xDist*xDist + yDist*yDist);
    }
    
    public void printPoint(){
         System.out.println("(" + x + ", " + y + ")");
    }
}
