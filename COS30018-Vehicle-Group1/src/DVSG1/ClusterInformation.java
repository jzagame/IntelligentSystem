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
	
	public void setSendListLocationStatus(List<LocationDetail> temp) {
		for(LocationDetail x:temp) {
			for(LocationDetail y:locationRecord) {
				if(x.getLocationName().equals(y.getLocationName())) {
					y.setSend(true);
					break;
				}
			}
		}
	}
	
	public List<LocationDetail> getUnsendListLocationInCluster(){
		List<LocationDetail> temp = new ArrayList<LocationDetail>();
		for(LocationDetail x:locationRecord) {
			if(x.getSend() == false) {
				temp.add(x);
			}
		}
		return temp;
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
		for(int i=0;i<locationRecord.size();i++) {
			System.out.print(locationRecord.get(i).getLocationName());
		}
		System.out.println("");
	}
	
}
