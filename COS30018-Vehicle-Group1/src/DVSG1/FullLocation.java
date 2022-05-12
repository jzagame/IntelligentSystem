package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class FullLocation {
	List<AvailableLocation> availableLocation;
	
	FullLocation(){
		availableLocation = new ArrayList<AvailableLocation>();
	}
	
	public void setAvailableLocation(AvailableLocation temp) {
		availableLocation.add(temp);
	}
	
	public List<AvailableLocation> getAvailableLocationDetail(){
		return availableLocation;
	}
	
}
