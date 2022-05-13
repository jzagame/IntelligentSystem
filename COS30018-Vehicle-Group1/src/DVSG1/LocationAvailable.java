package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class LocationAvailable {
List<LocationDetail> availableLocation;
	
	LocationAvailable(){
		availableLocation = new ArrayList<LocationDetail>();
	}
	
	public void setAvailableLocation(LocationDetail temp) {
		availableLocation.add(temp);
	}
	
	public List<LocationDetail> getAvailableLocationDetail(){
		return availableLocation;
	}
}
