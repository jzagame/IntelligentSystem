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
	
	public void setListAvailableLocation(List<LocationDetail> temp) {
		availableLocation.addAll(temp);
	}
	
	public List<LocationDetail> getAvailableLocationDetail(){
		return availableLocation;
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
}
