package G1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClusteringCalculation {
	List<ClusterGroupInfo> clusterInfo ;
	
	
	ClusteringCalculation(){
		clusterInfo = new ArrayList<ClusterGroupInfo>();
		
	}
	
	public void setClusterGroupInfoList(List<ClusterGroupInfo> c) {
		clusterInfo = c;
	}
	
	public void setClusterGroupInfo(ClusterGroupInfo c) {
		clusterInfo.add(c);
	}
	
	public List<ClusterGroupInfo> getClusterGroupInfor() {
		return clusterInfo;
	}
	
//	public void test(List<ClusterGroupInfo> c,int removed ) {
//		ClusterGroupInfo[] abc = c.toArray(new ClusterGroupInfo[c.size()]);
//		int ini = 0;
//		for(ClusterGroupInfo m1:c) {
//			int i=0;
//			boolean flag = false;
//			for(ClusterGroupInfo m2:c) {
//				int total = 0;	
////				System.out.println(m1.getLocationDetail().getLocationName() + m2.getLocationDetail().getLocationName());
//				if(m1.getLocationDetail().getLocationName().equals(m2.getLocationDetail().getLocationName()) == false) {
//					List<LocationDetail> l1 = m1.getNearestLocation();
//					List<LocationDetail> l2 = m2.getNearestLocation();
//					for(LocationDetail x:l1) {
//						if(l2.contains(x)) {
//							total += 1;
//						}
//					}
//				}
//				if(total >= 10) {
//					flag = true;
//					abc = removeTheElement(abc,i);
//					System.out.println("inner" + abc.length);
//				}
//				i++;
//			}
//			if(flag == true) {
//				List<ClusterGroupInfo> h = Arrays.asList(abc);
//				System.out.println("break" + h.size());
//				test(h,i+1);
//			}else {
//				List<ClusterGroupInfo> h = Arrays.asList(abc);
//				this.setClusterGroupInfoList(h);
//				break;
//			}
//			
//		}
//	}
//	
	public static ClusterGroupInfo[] removeTheElement(ClusterGroupInfo[] arr, int index)
    {
 
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
            || index >= arr.length) {
 
            return arr;
        }
 
        // Create another array of size one less
        ClusterGroupInfo[] anotherArray = new ClusterGroupInfo[arr.length - 1];
 
        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {
 
            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
 
            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }
 
        // return the resultant array
        return anotherArray;
    }
	
	
	
	
}
