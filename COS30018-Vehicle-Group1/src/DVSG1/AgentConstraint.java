package DVSG1;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;

public class AgentConstraint {
	Agent agent;
	AMSAgentDescription agentDetail;
	String AgentName;
	int TotalItem;
	
	AgentConstraint(){
		AgentName = null;
		TotalItem = 0;
		agent = null;
	}
	
	public AMSAgentDescription getAgentAMSAgentDescription() {
		return agentDetail;
	}
	
	public void setAgentAMSAgentDescription(AMSAgentDescription temp) {
		agentDetail = temp;
	}
	
	public int getTotalItem() {
		return TotalItem;
	}
	
	public void setTotalItem(int a) {
		TotalItem = a;
	}
	
	public void setAgentName(String a) {
		AgentName = a;
	}
	
	public String getAgentName() {
		return AgentName;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent a) {
		agent = a;
	}
	
	
}
