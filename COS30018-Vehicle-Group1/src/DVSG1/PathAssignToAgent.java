package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathAssignToAgent {
	String clusterName;
	List<LocationDetail> location;
	String agentName;
	boolean status;
	
	PathAssignToAgent(){
		clusterName = null;
		location = new ArrayList<LocationDetail>();
		agentName = null;
		status = false;
	}
	
	PathAssignToAgent(String tempName,List<LocationDetail> tempLocation,String tempAgent,boolean tempStatus){
		clusterName = tempName;
		location = tempLocation;
		agentName = tempAgent;
		status = tempStatus;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public String getClusterName() {
		return clusterName;
	}
	
	public String getAgentName() {
		return agentName;
	}
	
	public List<LocationDetail> getListLocation(){
		return location;
	}
	
	public void setLocationStatus(boolean temp) {
		for(LocationDetail x:location) {
			x.setSend(temp);
		}
	}
}
