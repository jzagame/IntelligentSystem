package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathAvailable {
	List<LocationDetail> pathAvailable;
	
	PathAvailable(){
		pathAvailable = new ArrayList<LocationDetail>();
	}
	
	public void setPathDetail(List<LocationDetail> temp) {
		pathAvailable.addAll(temp);
	}
	
	public List<LocationDetail> getPathDetail(){
		return pathAvailable;
	}
	
	public void PrintPathLocation() {
		for(LocationDetail x:pathAvailable) {
			System.out.print(x.getLocationName() + " ");
		}
	}
}
