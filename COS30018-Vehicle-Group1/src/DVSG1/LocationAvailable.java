package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class LocationAvailable {
	List<LocationDetail> availableLocation;
	int[][] distanceMatrix ;
	
	LocationAvailable(){
		availableLocation = new ArrayList<LocationDetail>();
	}
	
	public void setAvailableLocation(LocationDetail temp) {
		availableLocation.add(temp);
	}
	
	public int[][] getDistanceMatrix(){
		return distanceMatrix;
	}
	
	public void setDistanceMatrix(int[][] temp) {
		distanceMatrix = new int[availableLocation.size()][availableLocation.size()];
		for(int i=0;i< distanceMatrix.length;i++) {
			for(int j=0;j<distanceMatrix[i].length;i++) {
				temp[i][j] = distanceMatrix[i][j];
			}
		}
	}
	
	public void setListAvailableLocation(List<LocationDetail> temp) {
		
		availableLocation.addAll(temp);
	}
	
	public List<LocationDetail> getAvailableLocationDetail(){
		return availableLocation;
	}
	
	public void PrintDistanceMatrix() {
		for(int i=0;i< distanceMatrix.length;i++) {
			for(int j=0;j<distanceMatrix[i].length;i++) {
				System.out.print(distanceMatrix[i][j] + "");
			}
			System.out.println("");
		}
	}
	
	public List<LocationDetail> getSelectedLocationDetail(){
		List<LocationDetail> temp = new ArrayList<LocationDetail>();
		for(LocationDetail x:availableLocation) {
			if(x.getTotalParcel() > 0 ) {
				temp.add(x);
			}
		}
		return temp;
	}
	
	public void PrintLocationPath() {
		for(LocationDetail x:availableLocation) {
			System.out.print(x.getLocationName() + "");
		}
	}
}
