package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PossiblePathOfEachCluster {
	List<PathAvailable> possiblePathOfEachCluster;
	String clusterName;
	
	PossiblePathOfEachCluster(){
		possiblePathOfEachCluster = new ArrayList<PathAvailable>();
		clusterName = null;
	}
	
	public String getClusterName() {
		return clusterName;
	}
	
	public void setPossiblePathOfEachCluster(List<PathAvailable> temp,String temp1) {
		possiblePathOfEachCluster.addAll(temp);
		clusterName = temp1;
	}
	
	public List<PathAvailable> getPossiblePathOfEachCluster(){
		return possiblePathOfEachCluster;
	}
	
	public void PrintLocationAvailableEachLocationDetail() {
		System.out.println("Cluster Name : " + clusterName);
		System.out.println("Available Path : " + possiblePathOfEachCluster.size());
	}
	
}
