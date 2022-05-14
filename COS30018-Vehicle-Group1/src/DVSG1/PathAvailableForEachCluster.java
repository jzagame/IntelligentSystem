package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathAvailableForEachCluster {
	List<PossiblePathOfEachCluster> pathAvailableForEachCluster;
	
	PathAvailableForEachCluster(){
		pathAvailableForEachCluster = new ArrayList<PossiblePathOfEachCluster>();
	}
	
	public void setAllPossiblePathForCluster(PossiblePathOfEachCluster temp) {
		pathAvailableForEachCluster.add(temp);
	}
	
	public List<PossiblePathOfEachCluster> getAllPossiblePathForCluster(){
		return pathAvailableForEachCluster;
	}
	
	public void PrintAllPossiblePathForClusterDetail() {
		for(PossiblePathOfEachCluster x:pathAvailableForEachCluster) {
			System.out.println("Cluster Name : " + x.getClusterName());
			System.out.println("Available Path : " +
			x.getPossiblePathOfEachCluster().size());
			for(int i=0;i<x.getPossiblePathOfEachCluster().size();i++) {
				x.getPossiblePathOfEachCluster().get(i).PrintPathLocation();
				System.out.println("");
			}
			
		}
	}
}
