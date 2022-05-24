package DVSG1;

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
				System.out.println(AgentName.getLocalName() + ": Waiting for message");
				ACLMessage msg= AgentName.receive();
				if (msg!=null) {
					// Print out message content
					System.out.println(AgentName.getLocalName() + ": Received Message From " + 
					msg.getSender().getLocalName() + " [ Total Capacity (num) : " + msg.getContent() + " ]");
					
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
