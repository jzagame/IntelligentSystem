package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathInformation {
	List<LocationDetail> pathInfo;
	
	
	PathInformation(){
		pathInfo = new ArrayList<LocationDetail>();
	}
	
	public void setPathInformation(LocationDetail temp) {
		pathInfo.add(temp);
	}
	
	public List<LocationDetail> getPathInformation() {
		return pathInfo;
	}
	
	public void PrintPathInfo() {
		for(LocationDetail x:pathInfo) {
			System.out.print(x.getLocationName() + " ");
		}
	}
}
