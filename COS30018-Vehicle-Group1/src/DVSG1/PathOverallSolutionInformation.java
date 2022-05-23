package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathOverallSolutionInformation {
	List<LocationDetail> pathOverallSoluitionInformation;
	int Fitness;
	int TotalParcelInPath;
	int[][] DistanceMatrix;
	
	PathOverallSolutionInformation(){
		pathOverallSoluitionInformation = new ArrayList<LocationDetail>();
		Fitness = 0;
		TotalParcelInPath = 0;
	}
	
	public void setFitness(int temp) {
		Fitness = temp;
	}
	
	public int getFitness() {
		return Fitness;
	}
	
	public int getTotalParcelInPath() {
		return TotalParcelInPath;
	}
	
	public void setDistanLocation(int distance,int locationFromIndex,int locationToIndex) {
		DistanceMatrix[locationFromIndex][locationToIndex] = distance;
	}
	
	public void setPathOverallSoluitionInformation(List<LocationDetail> temp) {
		DistanceMatrix = new int[temp.size()][temp.size()];
		pathOverallSoluitionInformation.addAll(temp);
	}
	
	public List<LocationDetail> getPathOverallSoluitionInformation(){
		return pathOverallSoluitionInformation;
	}
	
	public void calculateLocationDistance() {
		List<LocationDetail> fL = pathOverallSoluitionInformation;
		LocationDetail x = new LocationDetail();
	    LocationDetail[] aL = fL.toArray(new LocationDetail[fL.size()]);
	    int temp = 0;
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
	
	public void calculateTotalParcel() {
		int total = 0;
		for(LocationDetail x:pathOverallSoluitionInformation) {
			total += x.getTotalParcel();
		}
		TotalParcelInPath = total;
	}
}
