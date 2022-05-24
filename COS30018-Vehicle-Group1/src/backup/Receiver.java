package backup;

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
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import java.util.Vector;

public class Receiver extends Agent{
	protected void setup(){
	 // Create behaviour that receives messages
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(this){
			public void action() {
				
				ACLMessage msg= receive();
				if(msg == null) {
					System.out.println(getLocalName() + ": Waiting for message");
				}
				if (msg!=null) {
					// Print out message content
					System.out.println(getLocalName() + " recieved Message from " + msg.getSender().getLocalName());
					System.out.println("Message : " + msg.getContent() );
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only after
				System.out.println("----------------------------------------------------------------");
			}
		});
	addBehaviour(msgReceivingBehaviour);
	// ----------------------------------------------------------------- //
//	Object[] args = getArguments();
//    if (args != null && args.length > 0) {
////      responder = new AID((String) args[0], AID.ISLOCALNAME);
//     
//    	System.out.println("Agent "+getLocalName()+" waiting for requests...");
//    	MessageTemplate template = MessageTemplate.and(
//        MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
//        MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
//         
//    	AchieveREResponder arer = new AchieveREResponder(this, template) {
//    		protected ACLMessage prepareResponse(ACLMessage request)throws NotUnderstoodException, RefuseException {
//    			System.out.println("Agent "+getLocalName()+": REQUEST received from "+
//    					request.getSender().getName()+ " Action is "+request.getContent());
//    			ACLMessage agree = request.createReply();
//    			agree.setPerformative(ACLMessage.AGREE);
//    			return agree;
//    		}
//    	};
//    	// Register an AchieveREInitiator in the PREPARE_RESULT_NOTIFICATION state
//    	arer.registerPrepareResultNotification(new AchieveREInitiator(this, null) {
//    		// Since we don't know what message to send to the responder
//    		// when we construct this AchieveREInitiator, we redefine this
//    		// method to build the request on the fly
//    		protected Vector prepareRequests(ACLMessage request) {
//    			// Retrieve the incoming request from the DataStore
//    			String incomingRequestKey = (String) ((AchieveREResponder)parent).REQUEST_KEY;
//    			ACLMessage incomingRequest = (ACLMessage)getDataStore().get(incomingRequestKey);
//    			// Prepare the request to forward to the responder
//    			ACLMessage outgoingRequest = new ACLMessage(ACLMessage.REQUEST);
//    
//    			outgoingRequest.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
////         		outgoingRequest.addReceiver(responder);
//    			for (int i = 0; i < args.length; i++) {
//    				AID r = new AID((String) args[i], AID.ISLOCALNAME);
//    				outgoingRequest.addReceiver(r);
//    				System.out.println("Agent "+getLocalName()+": Forward the request to "+r.getName());
//    			}
//    			outgoingRequest.setContent(incomingRequest.getContent());
//    			outgoingRequest.setReplyByDate(incomingRequest.getReplyByDate());
//    			Vector v = new Vector(1);
//    			v.addElement(outgoingRequest);
//    			return v;
//    		}
//     
//    		protected void handleInform(ACLMessage inform) {
//    			storeNotification(ACLMessage.INFORM);
//    		}
//     
//    		protected void handleRefuse(ACLMessage refuse) {
//    			storeNotification(ACLMessage.FAILURE);
//    		}
//     
//    		protected void handleNotUnderstood(ACLMessage notUnderstood) {
//    			storeNotification(ACLMessage.FAILURE);
//    		}
//     
//    		protected void handleFailure(ACLMessage failure) {
//    			storeNotification(ACLMessage.FAILURE);
//    		}
//     
//    		protected void handleAllResultNotifications(Vector notifications) {
//    			if (notifications.size() == 0) {
//    				  storeNotification(ACLMessage.FAILURE);
//    		    }
//    		}
//    		     
//    		private void storeNotification(int performative) {
//    			if (performative == ACLMessage.INFORM) {
//    				System.out.println("Agent "+getLocalName()+": brokerage successful");
//    		    }
//    		    else {
//    		    	System.out.println("Agent "+getLocalName()+": brokerage failed");
//    		    }
//    		       
//    		      // Retrieve the incoming request from the DataStore
//    		    String incomingRequestkey = (String) ((AchieveREResponder)parent).REQUEST_KEY;
//    		    ACLMessage incomingRequest = (ACLMessage)getDataStore().get(incomingRequestkey);
//    		      // Prepare the notification to the request originator and store it in the DataStore
//    		    ACLMessage notification = incomingRequest.createReply();
//    		    notification.setPerformative(performative);
//    		    String notificationkey = (String) ((AchieveREResponder)parent).RESULT_NOTIFICATION_KEY;
//    		      	getDataStore().put(notificationkey, notification);
//    		}
//    	} );
//    		    
//    		addBehaviour(arer);
//    	}
////    	else {
////    		System.out.println("No agent to forward requests to specified.");
////    	}
//       // Timeout
	//------------------------------------------------------------------------------------------------//
	MessageTemplate template = MessageTemplate.and(
	MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
	MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
		       
	addBehaviour(new AchieveREResponder(this, template) {
		protected ACLMessage prepareResponse(ACLMessage request) throws
			NotUnderstoodException, RefuseException {
		     	System.out.println("Agent "+getLocalName()+": REQUEST received from "+request.getSender().getName()+". Action is "+request.getContent());
		     	if (checkAction()) {
		      // We agree to perform the action. Note that in the FIPA‐Request
		      // protocol the AGREE message is optional. Return null if you
		      // don't want to send it.
		     		System.out.println("Agent "+getLocalName()+": Agree");
		     		ACLMessage agree = request.createReply();
		     		agree.setPerformative(ACLMessage.AGREE);
		     		return agree;
		     	}
		     	else {
		      // We refuse to perform the action
		     		System.out.println("Agent "+getLocalName()+": Refuse");
		     		throw new RefuseException("check‐failed");
		     	}
		    }
		    
		    protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
		    	if (performAction()) {
		    		System.out.println("Agent "+getLocalName()+": Action successfully performed");
		    		ACLMessage inform = request.createReply();
		    		inform.setPerformative(ACLMessage.INFORM);
		    		return inform;
		    	}
		    	else {
		    		System.out.println("Agent "+getLocalName()+": Action failed");
		    		throw new FailureException("unexpected‐error");
		    	}   
		    }
		});
	}
		   
		  private boolean checkAction() {
		    // Simulate a check by generating a random number
		    return (Math.random() > 0.2);
		  }
		   
		  private boolean performAction() {
		    // Simulate action execution by generating a random number
		    return (Math.random() > 0.2);
		  }	
	
	
}
