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
					possiblePath.calculateLocationDistance(outerLoopList);
					ga.add(possiblePath);
					System.out.println(ga.size()); //uncommand this see this result
					return null;
				}else {
					System.out.println(ga.size()); //uncommand this see this result
					return null;
				}
			}
		}
		InnerGenerateClusterPossiblePath(outerLoopList,InnerLoopList,ParcelConstraint,size);
//		GenerateClusterPossiblePath(outerLoopList,InnerGenerateClusterPossiblePath(outerLoopList,
//				InnerLoopList,ParcelConstraint),ParcelConstraint,size);
		return null;
	}
	
	public List<LocationDetail> InnerGenerateClusterPossiblePath(List<LocationDetail> outerLoopList,
			List<LocationDetail> InnerLoopList,int ParcelConstraint,int size){
//		System.out.println(outerLoopList.size() + " : " + InnerLoopList.size());
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
			if(total <= ParcelConstraint) {
				temp.add(InnerLoopList.get(i));
			}
			if(total >= ParcelConstraint) {
				InnerLoopList.remove(1);
				PathAvailable possiblePath = new PathAvailable();
				possiblePath.setPathDetail(temp);
				possiblePath.calculateLocationDistance(temp);
				ga.add(possiblePath);
				InnerGenerateClusterPossiblePath(outerLoopList,InnerLoopList,ParcelConstraint,size);
			}
			
		}
		total = 0;
		for(LocationDetail ld:outerLoopList) {
			total += ld.getTotalParcel();
		}
		if(total > ParcelConstraint) {
			outerLoopList.remove(0);
			List<LocationDetail> newInner = new ArrayList<LocationDetail>(outerLoopList);
			GenerateClusterPossiblePath(outerLoopList,newInner,ParcelConstraint,size);
		}

		return null;
		
	}
	
	
}
