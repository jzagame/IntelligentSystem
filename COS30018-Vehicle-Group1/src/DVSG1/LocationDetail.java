package DVSG1;

public class LocationDetail {
	String locationName;
	int x;
	int y;
	int TotalParcel;
	boolean used;
	
	LocationDetail(){
		locationName = null;
		x = 0;
		y = 0;
		TotalParcel = 0;
		used = false;
	}
	
	public boolean getSend() {
		return used;
	}
	
	public void setSend(boolean temp) {
		used = temp;
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
	
	public void setTotalParcel(int temp) {
		TotalParcel = temp;
	}
	
	public int getTotalParcel() {
		return TotalParcel;
	}
}
