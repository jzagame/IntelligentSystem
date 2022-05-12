package DVSG1;

import jade.core.Agent;

public class AgentConstraint {
	Agent agent;
	String AgentName;
	int TotalItem;
	
	AgentConstraint(){
		AgentName = null;
		TotalItem = 0;
		agent = null;
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
