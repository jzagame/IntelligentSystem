package DVSG1;

import java.util.Arrays;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

public class Receiver extends Agent{
	protected void setup(){
	 // Create behaviour that receives messages
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(this){
			public void action() {
				System.out.println(getLocalName() + ": Waiting for message");
				ACLMessage msg= receive();
				if (msg!=null) {
					// Print out message content
					System.out.println(getLocalName() + ": Received response " +
						 msg.getContent() + " from " + msg.getSender().getLocalName());
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only after
				System.out.println(getLocalName() + ": This line is printed");
			}
		});
	addBehaviour(msgReceivingBehaviour);
	}
	
	public void sendMessage(String content,MasterRoutingAgent mra) {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent(content);
//		msg.setSender(temp[i].getAgentAMSAgentDescription().getName());
		msg.addReceiver(mra.getMRA().getAID());
		this.send(msg);
	}
	
}
