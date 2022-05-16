package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathAvailable {
	List<LocationDetail> pathAvailable;
	int[][] distanceMatrix;
	
	PathAvailable(){
		pathAvailable = new ArrayList<LocationDetail>();
	}
	
	public void setPathDetail(List<LocationDetail> temp) {
		LocationDetail x = new LocationDetail();
		x.setLocationName("WareHouse");
		x.setXYLocation(400, 250);
		x.setTotalParcel(0);
		temp.add(0,x);
		distanceMatrix = new int[temp.size()][temp.size()];
		pathAvailable.addAll(temp);
	}
	
	public List<LocationDetail> getPathDetail(){
		return pathAvailable;
	}
	
	public int[][] getDistanceMatrix() {
		return distanceMatrix;
	}
	
	public void setDistanceMatrix(int[][] temp) {
		distanceMatrix = temp;
	}
	
	public void setDistanLocation(int distance,int locationFromIndex,int locationToIndex) {
		distanceMatrix[locationFromIndex][locationToIndex] = distance;
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
	
	public void PrintPathLocation() {
		for(LocationDetail x:pathAvailable) {
			System.out.print(x.getLocationName() + "[" + x.getTotalParcel() + "] ");
		}
//		System.out.println("");
//		for(int i=0;i<distanceMatrix.length;i++) {
//			for(int j=0;j<distanceMatrix[i].length;j++) {
//				System.out.print(" | " + distanceMatrix[i][j] + " | ");
//			}
//			System.out.println("");
//		}
	}
	
	
	
}
