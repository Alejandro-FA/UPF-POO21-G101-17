import java.util.*;

public class MatrixDistance{
    // Attributes
    private List<Point> pointsList;
    // private double[10][10] matrix;
    private List<List<Double>> matrix;
    private int size;
    
    // Constructor method
    public MatrixDistance(){
        pointsList = new ArrayList<Point>();
        matrix = new ArrayList<ArrayList<Double>>(); 
		size = 0;
    }
     
    // Other methods
    public double getDistance(int row, int col){
        return matrix.get(row).get(col);
    }
    
    private void buildMatrix(){
    	pointsList.add(new ArrayList<Double>());
        for(int i = 0; i < size; i++){
            int distance = pointsList.get(size).distanceTo(pointsList.get(i));
            matrix.get(size).add(distance);
            matrix.get(i).add(distance);
		}
		pointList.get(size).add(0.0);
    }
    
    public void addPoint(Point newPoint){
    	pointsList.add(newPoint);
        buildMatrix();
		size++;
    }
}