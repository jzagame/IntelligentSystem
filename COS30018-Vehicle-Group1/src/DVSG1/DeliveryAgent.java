package DVSG1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jade.core.Agent;
public class DeliveryAgent extends Agent{
	List<AgentConstraint> ac ;
	
	
	DeliveryAgent(){
		ac = new ArrayList<AgentConstraint>();
	}
	
	DeliveryAgent(List<AgentConstraint> temp){
		ac = temp;
	}
	
	public List<AgentConstraint> getListAgentConstraint(){
		return ac;
	}
	
	public List<AgentConstraint> getFreeListAgentConstraint(){
		List<AgentConstraint> temp = new ArrayList<AgentConstraint>();
		for(AgentConstraint x:this.getListAgentConstraint()) {
			if(x.getAgentStatus() == true) {
				temp.add(x);
			}
		}
		return temp;
	}
	
	public void setListAgentConstraint(AgentConstraint temp) {
		ac.add(temp);
	}
	
	public AgentConstraint getOneAgenConstraint(int n) {
		AgentConstraint[] temp = ac.toArray(new AgentConstraint[ac.size()]);
		return temp[n];
	}
	
	public void PrintAllAgentConstraint() {
		AgentConstraint[] temp = ac.toArray(new AgentConstraint[ac.size()]);
		for(int i=0;i<temp.length;i++) {
			System.out.println("Agent Name : " + temp[i].getAgentName());
			System.out.println("Agent GUID Name : " + temp[i].getAgent().getName());
			System.out.println("Agent GUID Name : " + temp[i].getTotalItem());
		}
	}
	
	public List<AgentConstraint> getAgentConstraintSorted() {
		List<AgentConstraint> x = this.getListAgentConstraint();
		Collections.sort(x, new Comparator<AgentConstraint>() {
			@Override
			public int compare(AgentConstraint o1, AgentConstraint o2) {
				// TODO Auto-generated method stub
				return o1.getTotalItem() > o2.getTotalItem() ? -1 : o1.getTotalItem() == o2.getTotalItem() ? 0:1;
			}	
		});
		return x;
	}
}
