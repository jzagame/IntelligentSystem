package DVSG1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratePossiblePath {
	List<PathAvailable> ga;
	
	GeneratePossiblePath(){
		ga = new ArrayList<PathAvailable>();
	}
	
	public List<PathAvailable> getLocationAvailableForEachCluster() {
		return ga;
	}
	
	public List<LocationAvailable> GenerateClusterPossiblePath(List<LocationDetail> outerLoopList,
			List<LocationDetail> InnerLoopList,int ParcelConstraint,int size) {
		int total = 0;
		
		for(LocationDetail ld:outerLoopList) {
			total += ld.getTotalParcel();
		}
//		System.out.println(total + " : " + ParcelConstraint); //unncommand this see the result
		if(total <= ParcelConstraint) {
			if(total <= ParcelConstraint) {
				if(outerLoopList.size() == size) {
					PathAvailable possiblePath = new PathAvailable();
					possiblePath.setPathDetail(outerLoopList);
					ga.add(possiblePath);
					System.out.println(ga.size()); //uncommand this see this result
					return null;
				}else {
					System.out.println(ga.size()); //uncommand this see this result
					return null;
				}
			}
		}
		GenerateClusterPossiblePath(outerLoopList,InnerGenerateClusterPossiblePath(outerLoopList,
				InnerLoopList,ParcelConstraint),ParcelConstraint,size);
		return null;
	}
	
	public List<LocationDetail> InnerGenerateClusterPossiblePath(List<LocationDetail> outerLoopList,
			List<LocationDetail> InnerLoopList,int ParcelConstraint){
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
				PathAvailable possiblePath = new PathAvailable();
				possiblePath.setPathDetail(temp);
				ga.add(possiblePath);
				InnerGenerateClusterPossiblePath(outerLoopList,InnerLoopList,ParcelConstraint);
			}
		}
		total = 0;
		for(LocationDetail ld:outerLoopList) {
			total += ld.getTotalParcel();
		}

		return null;
		
	}
	
	
}
