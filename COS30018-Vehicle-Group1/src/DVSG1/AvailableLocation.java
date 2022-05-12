package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class AvailableLocation {
	String locationName;
	int x;
	int y;
	
	AvailableLocation(){
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
