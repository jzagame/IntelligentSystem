package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class ClusterGroupInfo {
	List<LocationDetail> nearestLocation;
	LocationDetail belongTo;
	LocationDistance locationDistance;
	
	ClusterGroupInfo(LocationDistance dl){
		nearestLocation = new ArrayList<LocationDetail>();
		belongTo = null;
		locationDistance = dl;
	}
	
	public void setNearestLocation(LocationDetail temp) {
		nearestLocation.add(temp);
	}
	
	public LocationDetail getLocationDetail() {
		return belongTo;
	}
	
	public int getOneLocationDistance(int from, int to) {
		return locationDistance.getOneDistanceLoation(from, to);
	}
	
	public List<LocationDetail> getNearestLocation(){
		return nearestLocation;
	}
	
	public void findNearestLocation(List<LocationDetail> l,LocationDetail point){
		belongTo = point;
		for(LocationDetail temp:l) {
			boolean flag = false;
			if(temp.getLocationX() >= point.getLocationX() - 150 && temp.getLocationX() <= point.getLocationX() + 150) {
				if(temp.getLocationY() >= point.getLocationY() - 80 && temp.getLocationY() <= point.getLocationY() + 150) {
					flag =true;
				}
			}
			if(flag == true) {
				this.setNearestLocation(temp);
			}
		}
		System.out.println(nearestLocation.size() +":"+ belongTo.getLocationName());
	}
} 
