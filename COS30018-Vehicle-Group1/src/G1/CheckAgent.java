package G1;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

public class CheckAgent extends Agent{
	protected void setup() {
		AMSAgentDescription [] agents = null;
		try {
			SearchConstraints c = new SearchConstraints();
			Long l = Long.valueOf(-1);
			c.setMaxResults(l);
			agents = AMSService.search(this, new AMSAgentDescription(),c);
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("asdasds");
		AID myID = getAID();
		for (int i=0; i<agents.length;i++) {
			AID agentID = agents[i].getName();
			System.out.println((agentID.equals(myID)?"*** " : "    ") + i + " : " + agentID.getName());
		}
	}
}
