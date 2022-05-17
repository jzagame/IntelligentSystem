package DVSG1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class main extends Agent{
	
	DeliveryAgent deliveryAgent = new DeliveryAgent(); 
	MasterRoutingAgent masterRoutingAgent = new MasterRoutingAgent();
	LocationAvailable fullLocation = new LocationAvailable();
	LocationDistance distanceLocation = new LocationDistance();
	protected void setup() {
		Object[] args = getArguments(); // get Arguments pass from the command (DA name)
		CyclicBehaviour msgReceivingBehaviour = (new CyclicBehaviour(this){
			public void action() {
				System.out.println(getLocalName() + ": Waiting for message");
				ACLMessage msg= receive();
				if (msg!=null) {
					// Print out message content
					System.out.println(getLocalName()+ ": Received response " +
						 msg.getContent() + " from " + msg.getSender().getLocalName());
				}
				// Continue listening
				block();
				// This line gets printed since the blocking effect is achieved only after
				System.out.println(getLocalName() + ": This line is printed");
			}
		});
	addBehaviour(msgReceivingBehaviour);
//		Runtime runtime = Runtime.instance();
//		Profile profile = new ProfileImpl();
//		ContainerController container = runtime.createAgentContainer( profile );
//		
//		AgentController ac = null;
//		
//		try{
//			for(int i=0;i<args.length;i++) {
//				Agent agent = new ReceiverAgent();
//				ac = container.acceptNewAgent(args[i].toString(), agent);
//				AgentConstraint agentConstraint = new AgentConstraint();
//				agentConstraint.setAgent(agent);
//				agentConstraint.setAgentName(args[i].toString());
//				System.out.println(agentConstraint.getAgentName() + ": " + agentConstraint.getAgent());
//				deliveryAgent.setListAgentConstraint(agentConstraint);
//			}
//			ac.start();
//		}catch(Exception e){
//			System.out.println(e);
//		}
		
		
//		System.out.println("Agent Created!!!!");
////		AID myID = getAID();
//		
////		System.out.println(myID.getName());
//		List<AgentConstraint> x = deliveryAgent.getListAgentConstraint();
//		AgentConstraint[] temp = x.toArray(new AgentConstraint[x.size()]);
//		System.out.print(masterRoutingAgent.getMRA().getLocalName());
//		for(int i=0;i< temp.length;i++) {
//			System.out.print(" | " + temp[i].getAgentName());
//		}
//		System.out.println();		
		
//		ReceiverAgent RA = new ReceiverAgent();
//		RA.setAgent(masterRoutingAgent.getMRA());
		
		
		
		
// this will let MRA start cyclic to keep listening
		
//		AgentListener agentListener = new AgentListener();
//		for(int j=0;j<temp.length;j++) {
//			ReceiverAgent2 tempRA = new ReceiverAgent2();
//			agentListener.setReceiverAgent(tempRA);
//		}
//		
//		List<ReceiverAgent2> x1 = agentListener.getReceiverAgent2();
//		ReceiverAgent2[] temp1 = x1.toArray(new ReceiverAgent2[x1.size()]);
//		for(int j=0;j<temp1.length;j++) {
//			temp1[j].setAgent(temp[j].getAgent());
//		}
		
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
					System.out.println(agents[j].getName());
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
		
		
	}
}
