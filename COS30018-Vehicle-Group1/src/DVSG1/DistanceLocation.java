package DVSG1;

public class DistanceLocation {
	
	int[][] distanceLocation;
	
	
	DistanceLocation(){
		distanceLocation = null;
	}
	
	public int[][] getDistanceLoation(){
		return distanceLocation;
	}
	
	public int getOneDistanceLocation(int locationFromIndex,int locationToIndex) {
		return distanceLocation[locationFromIndex][locationToIndex];
	}
	
	public void setDistanLocation(int distance,int locationFromIndex,int locationToIndex) {
		distanceLocation[locationFromIndex][locationToIndex] = distance;
	}
}
