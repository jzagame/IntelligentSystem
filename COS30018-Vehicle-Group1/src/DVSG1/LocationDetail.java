package DVSG1;

public class LocationDetail {
	String locationName;
	int x;
	int y;
	
	LocationDetail(){
		locationName = null;
		x = 0;
		y = 0;
	}
	
	public int getLocationX(){
		return x;
	}
	
	public int getLocationY(){
		return y;
	}
	
	public void setXYLocation(int tempx,int tempy) {
		x = tempx;
		y = tempy;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String temp) {
		locationName = temp;
	}
}
