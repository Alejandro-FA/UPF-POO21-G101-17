import java.util.*;

public class DistanceMatrix implements Matrix{
    // Attributes
    private LinkedList<City> CitiesList;
    private LinkedList<LinkedList<Double>> matrix;
    private int size;
    
    // Constructor method
    public DistanceMatrix(){
        CitiesList = new LinkedList<City>();
        matrix = new LinkedList<LinkedList<Double>>(); 
		size = 0;
    }
     
    // Getters
    public double getDistance(int row, int col){
        return matrix.get(row).get(col);
    }

    public int getNoOfCities(){
        return size;
    }

    public String getCityName(int index){
        return CitiesList.get(index).getName();
    }
    
    // Other methods
    private void createDistanceMatrix(){
    	matrix.add(new LinkedList<Double>());
        for(int i = 0; i < size; i++){
            double distance = CitiesList.get(size).distanceTo(CitiesList.get(i));
            matrix.get(size).add(distance);
            matrix.get(i).add(distance);
		}
		matrix.get(size).add(0.0);
    }
    
    public void addCity(double x, double y, String name){
        City newCity = new City(x,y,name);
    	CitiesList.add(newCity);
        createDistanceMatrix();
		size++;
    }

    public void printMatrix(){
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(matrix.get(i).get(j));
                System.out.print("  ");
            }
            System.out.println("");
        }
    }

    
}