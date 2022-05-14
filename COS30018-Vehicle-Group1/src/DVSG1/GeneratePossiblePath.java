package DVSG1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratePossiblePath {
	LocationAvailable ga;
	
	
	GeneratePossiblePath(){
		ga = new LocationAvailable();
	}
	
	public LocationAvailable getLocationAvailableForEachCluster() {
		return ga;
	}
	
	public List<LocationDetail> GenerateClusterPossiblePath(List<LocationDetail> outerLoopList,
			List<LocationDetail> InnerLoopList,List<LocationDetail> possiblePath,int ParcelConstraint,int size) {
		int total = 0;
		
		for(LocationDetail ld:outerLoopList) {
			total += ld.getTotalParcel();
		}
//		System.out.println(total + " : " + ParcelConstraint); //unncommand this see the result
		if(total <= ParcelConstraint) {
			if(total <= ParcelConstraint) {
				if(outerLoopList.size() == size) {
//					System.out.println(possiblePath.size() + " <-- this gay"); // check for this gay error
					ga.setListAvailableLocation(outerLoopList);
					return outerLoopList;
				}else {
//					System.out.println(possiblePath.size()); //uncommand this see this result
					ga.setListAvailableLocation(possiblePath);
					return possiblePath;
				}
			}
		}
		GenerateClusterPossiblePath(outerLoopList,InnerGenerateClusterPossiblePath(outerLoopList,
				InnerLoopList,possiblePath,ParcelConstraint),possiblePath,ParcelConstraint,size);
		return null;
	}
	
	public List<LocationDetail> InnerGenerateClusterPossiblePath(List<LocationDetail> outerLoopList,List<LocationDetail> InnerLoopList,List<LocationDetail> possiblePath,int ParcelConstraint){
		int total = 0;
		List<LocationDetail> temp = new ArrayList<LocationDetail>(); //create a temp list for storing current possible path
		for(LocationDetail ld:InnerLoopList) { // loop to calculate current list total path 
			total += ld.getTotalParcel();
		}
		if(total <= ParcelConstraint) { //if current list total parcel no more that constraint , return the list
			return InnerLoopList;
		}
		total = 0;
		for(int i=0;i<InnerLoopList.size();i++) {
			total += InnerLoopList.get(i).getTotalParcel();
			temp.add(InnerLoopList.get(i));
			if(total >= ParcelConstraint) {
				Random rand = new Random();
				if(total < 50) {
					temp.remove(i-1);
				}
				InnerLoopList.remove(rand.nextInt(i-1));
				possiblePath.addAll(temp);
				InnerGenerateClusterPossiblePath(outerLoopList,InnerLoopList,possiblePath,ParcelConstraint);
			}
		}
		total = 0;
		for(LocationDetail ld:outerLoopList) {
			total += ld.getTotalParcel();
		}

		return null;
		
	}
	
	
}
