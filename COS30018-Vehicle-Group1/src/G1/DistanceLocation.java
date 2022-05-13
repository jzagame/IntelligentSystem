package G1;

public class DistanceLocation {
	
	int[][] distanceLocation;
	
	
	DistanceLocation(){
		distanceLocation = new int[100][100];
	}
	
	public int[][] getDistanceLoation(){
		return distanceLocation;
	}
	
	public int getOneDistanceLoation(int locationFrom, int LocationTo){
		return distanceLocation[locationFrom][LocationTo];
	}
	
	public int getOneDistanceLocation(int locationFromIndex,int locationToIndex) {
		return distanceLocation[locationFromIndex][locationToIndex];
	}
	
	public void setDistanLocation(int distance,int locationFromIndex,int locationToIndex) {
		distanceLocation[locationFromIndex][locationToIndex] = distance;
	}
}
