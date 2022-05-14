package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PossiblePathOfEachCluster {
	List<LocationAvailable> possiblePathOfEachCluster;
	
	PossiblePathOfEachCluster(){
		possiblePathOfEachCluster = new ArrayList<LocationAvailable>();
	}
	
	public void setPossiblePathOfEachCluster(LocationAvailable temp) {
		possiblePathOfEachCluster.add(temp);
	}
	
	public List<LocationAvailable> getPossiblePathOfEachCluster(){
		return possiblePathOfEachCluster;
	}
	
	
}
