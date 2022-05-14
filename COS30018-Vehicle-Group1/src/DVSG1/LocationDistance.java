package DVSG1;

import java.util.List;

public class LocationDistance {
	int[][] distanceLocation;
	
	
	LocationDistance(){
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
	
	public void calculateLocationDistance(List<LocationDetail> fullLocation) {
		List<LocationDetail> fL = fullLocation;
	    LocationDetail[] aL = fL.toArray(new LocationDetail[fL.size()]);
	    for(int k=0;k<aL.length;k++) {
	    	int currentX = aL[k].getLocationX();
	    	int currentY = aL[k].getLocationY();
	    	for(int l=0;l<aL.length;l++) {
	    		int compareX = aL[l].getLocationX();
	    		int compareY = aL[l].getLocationY();
	    		int totalDistance = 0;
	    		if(currentX >= compareX) {
	    			totalDistance += (currentX-compareX);
	    		}else {
	    			totalDistance += (compareX-currentX);
	    		}
	    		if(currentY >= compareY) {
	    			totalDistance += (currentY - compareY);
	    		}else {
	    			totalDistance += (compareY - currentY);
	    		}
	    		this.setDistanLocation(totalDistance, k, l);
	    	}
	    }
	}
	
	public int[][] calculateDistanceMatrix(List<LocationDetail> fullLocation) {
		int[][] temp = new int[fullLocation.size()][fullLocation.size()];
		List<LocationDetail> fL = fullLocation;
	    LocationDetail[] aL = fL.toArray(new LocationDetail[fL.size()]);
	    
	    for(int k=0;k<aL.length;k++) {
	    	int currentX = aL[k].getLocationX();
	    	int currentY = aL[k].getLocationY();
	    	for(int l=0;l<aL.length;l++) {
	    		int compareX = aL[l].getLocationX();
	    		int compareY = aL[l].getLocationY();
	    		int totalDistance = 0;
	    		if(currentX >= compareX) {
	    			totalDistance += (currentX-compareX);
	    		}else {
	    			totalDistance += (compareX-currentX);
	    		}
	    		if(currentY >= compareY) {
	    			totalDistance += (currentY - compareY);
	    		}else {
	    			totalDistance += (compareY - currentY);
	    		}
	    		this.setDistanLocation(totalDistance, k, l);
	    	}
	    }
	    return temp;
	}
}
