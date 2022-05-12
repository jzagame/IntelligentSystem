package DVSG1;

import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class main extends Agent{
	
	DeliveryAgent deliveryAgent = new DeliveryAgent(); 
	MasterRoutingAgent masterRoutingAgent = new MasterRoutingAgent();
	protected void setup() {
		Object[] args = getArguments(); // get Arguments pass from the command (DA name)
		Runtime runtime = Runtime.instance();
		Profile profile = new ProfileImpl();
		ContainerController container = runtime.createAgentContainer( profile );
		AgentController ac = null;
		
		try{
			for(int i=0;i<args.length;i++) {
				Agent agent = new ReceiverAgent();
				ac = container.acceptNewAgent(args[i].toString(), agent);
				AgentConstraint agentConstraint = new AgentConstraint();
				agentConstraint.setAgent(agent);
				agentConstraint.setAgentName(args[i].toString());
				deliveryAgent.setListAgentConstraint(agentConstraint);
			}
			ac.start();
		}catch(Exception e){
			System.out.println(e);
		}
		
//		AMSAgentDescription [] agents = null;
//		try {
//			SearchConstraints c = new SearchConstraints();
//			Long l = Long.valueOf(-1);
//			c.setMaxResults(l);
//			agents = AMSService.search(this, new AMSAgentDescription(),c);
//			
//		}catch(Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//
		System.out.println("Agent Created!!!!");
//		AID myID = getAID();
		masterRoutingAgent.setMRA(this);
//		System.out.println(myID.getName());
		List<AgentConstraint> x = deliveryAgent.getListAgentConstraint();
		AgentConstraint[] temp = x.toArray(new AgentConstraint[x.size()]);
		System.out.print(masterRoutingAgent.getMRA().getLocalName());
		for(int i=0;i< temp.length;i++) {
			System.out.print(" | " + temp[i].AgentName);
		}
		System.out.println();
//		deliveryAgent.PrintAllAgentConstraint();
//		masterRoutingAgent.PrintMRADetail();
		ReceiverAgent mra = new ReceiverAgent();
		mra.setAgent(this);
		new GUI(deliveryAgent,masterRoutingAgent);
	}
}