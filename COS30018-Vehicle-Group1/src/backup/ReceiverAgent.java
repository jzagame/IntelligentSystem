package backup;

import java.util.Iterator;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class ReceiverAgent extends Agent{
	static Agent AgentName;
	ReceiverAgent(){
		AgentName = null;
	}
	
	public void setAgent(Agent a) {
		AgentName = a;
//		System.out.println(AgentName.getLocalName());
		StartAgentCyclic();
	}
	
	protected void StartAgentCyclic(){
//		System.out.println(AgentName.getLocalName());
		// Create behaviour that receives messages
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(AgentName){	
			public void action() {
//				System.out.println("here");
				
				ACLMessage msg= AgentName.receive();
				if(msg == null) {
					System.out.println(AgentName.getLocalName() + ": Waiting for message");
				}
				if (msg!=null) {
					// Print out message content
					System.out.println(AgentName.getLocalName() + " received Message From " + 
					msg.getSender().getLocalName() );
					System.out.println( " || Message : " + msg.getContent());
					
					System.out.println(AgentName.getLocalName() + " : Waiting for message");
					
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only afterthe action() method returns
				System.out.println("-----------------------------------------------");
			}
		});
		AgentName.addBehaviour(msgReceivingBehaviour);
	}
}
