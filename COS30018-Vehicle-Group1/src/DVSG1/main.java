package DVSG1;




import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.Random;

import jade.core.AID;
import jade.core.Agent;

import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREInitiator;
import jade.domain.FIPANames;
import java.util.Date;
import java.util.Vector;

public class main extends Agent{
	private int nResponders;
	DeliveryAgent deliveryAgent = new DeliveryAgent(); 
	MasterRoutingAgent masterRoutingAgent = new MasterRoutingAgent();
	LocationAvailable fullLocation = new LocationAvailable();
	LocationDistance distanceLocation = new LocationDistance();
	@SuppressWarnings("serial")
	protected void setup() {
		Object[] args = getArguments(); // get Arguments pass from the command (DA name)
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(this){
			public void action() {
				
				ACLMessage msgg= receive();
				if(msgg == null) {
					System.out.println(getLocalName() + ": Waiting for message");
				}
				if (msgg!=null) {
					// Print out message content
					System.out.println(getLocalName() + " recieved Message from " + msgg.getSender().getLocalName());
					System.out.println("Message : " + msgg.getContent() );
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only after
				System.out.println("---------------------------------------------------------------------------");
			}
		});
	addBehaviour(msgReceivingBehaviour);
	for(int i=0;i<args.length;i++) {
		AgentConstraint acc = new AgentConstraint();
		acc.setAgentName(args[i].toString());
		deliveryAgent.setListAgentConstraint(acc);
	}
	AMSAgentDescription [] agents = null;
	try {
		SearchConstraints c = new SearchConstraints();
		Long l = Long.valueOf(-1);
		c.setMaxResults(l);
		agents = AMSService.search(this, new AMSAgentDescription(),c);
		for(int i=0;i < deliveryAgent.getListAgentConstraint().size();i++) {
			for(int j=0;j<agents.length;j++) {
				if(agents[j].getName().getLocalName().equals(deliveryAgent.getListAgentConstraint().get(i).getAgentName())) {
					deliveryAgent.getListAgentConstraint().get(i).setAgentAMSAgentDescription(agents[j]);
				}
			}
		}
		System.out.println(getAID().getName());
	}catch(Exception e) {
		System.out.println(e);
		e.printStackTrace();
	}
//	System.out.println(deliveryAgent.getListAgentConstraint().get(0).getAgentAMSAgentDescription().getName().getLocalName());
	masterRoutingAgent.setMRA(this);
	try {
	    // open file in read mode
	    RandomAccessFile file = new RandomAccessFile("lib/location.txt", "r");
	    // read until end of file
	    String line;
	    int i=0;
	    while ((line = file.readLine()) != null) {
	    	LocationDetail availableLocation = new LocationDetail();
	    	Random rand = new Random();
	        String[] first = line.toString().split(":");
	        availableLocation.setLocationName(first[0]);
	        String[] second = first[1].split(",");
//	        System.out.println(second[0] + ":" + second[1]);
	        availableLocation.setXYLocation(Integer.valueOf(second[0]),Integer.valueOf(second[1]));
	        i++;
	        fullLocation.setAvailableLocation(availableLocation);
	    }
	 // close the file
	    file.close();
	    distanceLocation.calculateLocationDistance(fullLocation.getAvailableLocationDetail());
//	    System.out.println(test1[4].getLocationName() + ":" + test1[4].getLocationX() + "," + test1[4].getLocationY());
	    
	    
//	    System.out.println(availableLocation.getOneLocationName(4));
//	    for(int ik=0;i<availableLocation.getOneXYLocation(4).length;ik++) {
//	    	System.out.println(availableLocation.getOneXYLocation(4)[ik]);
//	    }
	} catch (IOException ex) {
	    ex.printStackTrace();
	}
	new GUI(deliveryAgent,masterRoutingAgent,distanceLocation,fullLocation);
	//----------------------------------------------------------------------------------------------//
//	if(args!= null && args.length > 0) {
//		ACLMessage trigger = blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.CFP));   
//		if (trigger.getContent().equalsIgnoreCase("start")) {
//		    
//		    // Read names of responders as arguments
//		    if (args != null && args.length > 0) {
//		    	nResponders = args.length;
//		    	System.out.println("Requesting dummy‐action to "+nResponders+" responders.");
//		    	// Fill the REQUEST message
//		    	ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
//		    	for (int i = 0; i < args.length; ++i) {
//		    		msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
//		    	}
//		    	msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
//		    	// We want to receive a reply in 10 secs
//		    	msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
//		    	msg.setContent("dummy‐action");
//		    
//		    	addBehaviour(new AchieveREInitiator(this, msg) {
//		    		protected void handleInform(ACLMessage inform) {
//		    			System.out.println("Agent "+inform.getSender().getName()+"successfully performed the requested action");
//		    		}
//		    		protected void handleRefuse(ACLMessage refuse) {
//		    			System.out.println("Agent "+refuse.getSender().getName()+" refused to perform the requested action");
//		    		nResponders--;
//		    		}
//		    		protected void handleFailure(ACLMessage failure) {
//		    			if (failure.getSender().equals(myAgent.getAMS())) {
//		    				// FAILURE notification from the JADE runtime: the receiver
//		    				// does not exist
//		    				System.out.println("Responder does not exist");
//		    			}
//		    			else {
//		    				System.out.println("Agent "+failure.getSender().getName()+" failed to perform the requested action");
//		    			}
//		    		}
//		    		protected void handleAllResultNotifications(Vector notifications) {
//		    			if (notifications.size() < nResponders) {
//		    				// Some responder didn't reply within the specified timeout
//		    				System.out.println("Timeout expired: missing "+(nResponders - notifications.size())+" responses");
//		    			}
//		    		}
//		    	} );
//		    }
//		    else {
//		    	
//		    }
//		    
//		}
//	}
	// ------------------------------------------------------------------------------------------------//

		
		
		
	}
}
