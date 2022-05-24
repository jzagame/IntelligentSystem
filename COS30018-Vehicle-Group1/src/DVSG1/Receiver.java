package DVSG1;

import java.util.Arrays;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import jade.tools.sniffer.Message;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import java.util.Vector;

public class Receiver extends Agent{
	protected void setup(){
	 // Create behaviour that receives messages
		
	// ----------------------------------------------------------------- //
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(this){
			public void action() {
				
				ACLMessage msg= receive();
				if ( msg != null && ACLMessage.getPerformative(msg.getPerformative()) == "REQUEST") {
					// Print out message content
					System.out.println(getLocalName() + " : New Request From " + msg.getSender().getLocalName());
					System.out.println("Message : " + msg.getContent() );
//					ACLMessage reply = new ACLMessage(Message.INFORM);
//					reply.addReceiver(msg.getSender());
//					send(reply);
//					System.out.println("Please wait my capacity constraint");
					
				}
				if(msg != null && ACLMessage.getPerformative(msg.getPerformative()) == "INFORM") {
					System.out.println(getLocalName() + " : Received Route From " + msg.getSender().getLocalName());
					System.out.println("Route : " + msg.getContent());
//					ACLMessage reply = msg.createReply();
//					reply.setPerformative(Message.INFORM);
//					reply.setContent("Acccepted");
//					send(reply);
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only after
				System.out.println("----------------------------------------------------------------");
			}
		});
		addBehaviour(msgReceivingBehaviour);
	//------------------------------------------------------------------------------------------------//
		//hide cyclic if want to test sniff
	//------------------------------------------------------------------------------------------------//
//		MessageTemplate template = MessageTemplate.and(
//				MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
//				MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
//		       
//		addBehaviour(new AchieveREResponder(this, template) {
//			protected ACLMessage prepareResponse(ACLMessage request) throws
//				NotUnderstoodException, RefuseException {
//		     		System.out.println("Agent "+getLocalName()+": REQUEST received from "+request.getSender().getName()+". Action is "+request.getContent());
//		     		if (checkAction()) {
//		      // We agree to perform the action. Note that in the FIPA‐Request
//		      // protocol the AGREE message is optional. Return null if you
//		      // don't want to send it.
//		     			System.out.println("Agent "+getLocalName()+": Agree");
//		     			ACLMessage agree = request.createReply();
//		     			agree.setPerformative(ACLMessage.AGREE);
//		     			agree.setContent("agree");
//		     			return agree;
//		     		}
//		     		else {
//		      // We refuse to perform the action
//		     			System.out.println("Agent "+getLocalName()+": Refuse");
//		     			throw new RefuseException("check‐failed");
//		     		}
//		    	}
//		    
//		    	protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
//		    		if (performAction()) {
//		    			System.out.println("Agent "+getLocalName()+": Action successfully performed");
//		    			ACLMessage inform = request.createReply();
//		    			inform.setPerformative(ACLMessage.INFORM);
//		    			return inform;
//		    		}
//		    		else {
//		    			System.out.println("Agent "+getLocalName()+": Action failed");
//		    			throw new FailureException("unexpected‐error");
//		    		}   
//		    	}
//		});
	}
		   
//	private boolean checkAction() {
//		    // Simulate a check by generating a random number
//		return (Math.random() > 0.2);
//	}
//		   
//	private boolean performAction() {
//		    // Simulate action execution by generating a random number
//		return (Math.random() > 0.2);
//	}	
	
	
}
