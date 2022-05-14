package DVSG1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClusterAvailable {
	List<ClusterInformation> clusterAvailable;
	
	ClusterAvailable(){
		clusterAvailable = new ArrayList<ClusterInformation>();
	}
	
	public void setClusterAvaiable(ClusterInformation temp) {
		clusterAvailable.add(temp);
	}
	
	public List<ClusterInformation> getClusterAvailable() {
		return clusterAvailable;
	}
	
	public List<ClusterInformation> getClusterAvaiableSorted() {
		List<ClusterInformation> x = this.getClusterAvailable();
		Collections.sort(x, new Comparator<ClusterInformation>() {
			@Override
			public int compare(ClusterInformation o1, ClusterInformation o2) {
				// TODO Auto-generated method stub
				return o1.getTotalParcelInCluster() > o2.getTotalParcelInCluster() ? -1 : o1.getTotalParcelInCluster() == o2.getTotalParcelInCluster() ? 0:1;
			}	
		});
		return x;
	}
	
	public void CreateDefaultClusterAvailable(List<LocationDetail> temp) {
		ClusterInformation c1 = new ClusterInformation();
		ClusterInformation c2 = new ClusterInformation();
		ClusterInformation c3 = new ClusterInformation();
		ClusterInformation c4 = new ClusterInformation();
		for(LocationDetail x:temp) {			
			if((x.getLocationX() >= 0 && x.getLocationX() <= 400) && (x.getLocationY() >=0 && x.getLocationY() <=250)) {
				c1.setClusterLocationDetail(x);
			}else if((x.getLocationX() > 400) && (x.getLocationY() >=0 && x.getLocationY() <=250)) {
				c2.setClusterLocationDetail(x);
			}else if((x.getLocationX() >= 0 && x.getLocationX() <= 400) && (x.getLocationY() > 250)) {
				c3.setClusterLocationDetail(x);
			}else {
				c4.setClusterLocationDetail(x);
			}
			
		}
		
		c1.setClusterName("Cluster A");
		c1.CalculateClusterTotalItem();
		c2.setClusterName("Cluster B");
		c2.CalculateClusterTotalItem();
		c3.setClusterName("Cluster C");
		c3.CalculateClusterTotalItem();
		c4.setClusterName("Cluster D");
		c4.CalculateClusterTotalItem();
		this.setClusterAvaiable(c1);
		this.setClusterAvaiable(c2);
		this.setClusterAvaiable(c3);
		this.setClusterAvaiable(c4);
		
	}
}

