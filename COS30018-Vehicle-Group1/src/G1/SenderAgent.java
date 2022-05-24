package G1;

import java.util.Iterator;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class SenderAgent extends Agent{
//	SenderAgent(AgentConstraint[] a,StoreAgent b){
//		super();
//		setup( a, b);
//	}
	public void setup(){
//		String[] MRAadd = b.getMRAAddress().split("@");
		
		CyclicBehaviour messageListeningBehaviour = new CyclicBehaviour(this){
			public void action() {
				ACLMessage msg= receive();
				if (msg!=null) {
					System.out.println(getLocalName()+ ": Received response " +
					msg.getContent() + " from " + msg.getSender().getLocalName());
				}
				block();
			}
		};
		
		addBehaviour(messageListeningBehaviour);
		// Send messages to two agents whose names are "a1" and "a2" (hard-coded)
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.setContent( "Ping" );
		for (int i = 4; i<=6; i++) {
			msg.addReceiver(new AID("DA" + i, AID.ISLOCALNAME) );
		}

		 // Send Message (only once)
		System.out.println(getLocalName()+ ": Sending message " + msg.getContent() + " to ");
		Iterator receivers = msg.getAllIntendedReceiver();
		while(receivers.hasNext()) {
			System.out.println(((AID)receivers.next()).getLocalName());
		}
		send(msg);
	}
}
