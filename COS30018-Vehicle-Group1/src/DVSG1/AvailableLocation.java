package DVSG1;

public class AvailableLocation {
	String[] locationName;
	int[][] xAndY;
	
	AvailableLocation(){
		locationName = null;
		xAndY = null;
	}
	
	public int[][] getXYLocation(){
		return xAndY;
	}
	
	public void setXYLocation(int[] temp,int n) {
		for(int i=0;i<temp.length;i++) {
			xAndY[n][i] = temp[i];
		}
	}
	
	public String[] getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String temp,int n) {
		locationName[n] = temp;
	}
}
