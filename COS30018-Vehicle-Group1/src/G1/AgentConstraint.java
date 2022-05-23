package G1;

public class AgentConstraint {
	String AgentName;
	int TotalItem;
	
	AgentConstraint(){
		AgentName = null;
		TotalItem = 0;
	}
	
	AgentConstraint(String a){
		AgentName = a;
	}
	
	AgentConstraint(String a,int b){
		AgentName = a;
		TotalItem = b;
	}
	
	public int getTotalItem() {
		return TotalItem;
	}
	
	public void setAgentName(String a) {
		AgentName = a;
	}
	
	public String getAgentName() {
		return AgentName;
	}
	
	public void setTotalItem(int a) {
		TotalItem = a;
	}
}
