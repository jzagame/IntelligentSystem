package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class ClusterInformation {
	List<LocationDetail> locationRecord;
	String clusterName;
	int TotalParcelInCluster;
	
	
	ClusterInformation(){
		TotalParcelInCluster = 0;
		clusterName = null;
		locationRecord = new ArrayList<LocationDetail>();
	}
	
	public List<LocationDetail> getListLocationInCluster(){
		return locationRecord;
	}
	
	public String getClusterName() {
		return clusterName;
	}
	
	public int getTotalParcelInCluster() {
		return TotalParcelInCluster;
	}
	
	public void setClusterLocationDetail(LocationDetail temp) {
		locationRecord.add(temp);
	}
	
	public void setClusterName(String temp) {
		clusterName = temp;
	}
	
	public void CalculateClusterTotalItem() {
		int totalParcel =0;
		for(LocationDetail x:locationRecord) {
			if(x.getTotalParcel() != 0) {
				totalParcel += x.getTotalParcel();
			}
		}
		TotalParcelInCluster = totalParcel;
	}
	
	public void PrintClusterDetail() {
		System.out.println("Cluster Name : " + clusterName);
		System.out.println("Total Parcel In Cluster : " + TotalParcelInCluster);
		System.out.println("Total Customer In Cluster : " + locationRecord.size());
	}
	
}
