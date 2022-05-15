package DVSG1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Random;

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
	LocationAvailable fullLocation = new LocationAvailable();
	LocationDistance distanceLocation = new LocationDistance();
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
		mra.setAgent(this); // this will let MRA start cyclic to keep listening
		
		
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
//		        System.out.println(second[0] + ":" + second[1]);
		        availableLocation.setXYLocation(Integer.valueOf(second[0]),Integer.valueOf(second[1]));
		        i++;
		        fullLocation.setAvailableLocation(availableLocation);
		    }
		 // close the file
		    file.close();
		    distanceLocation.calculateLocationDistance(fullLocation.getAvailableLocationDetail());
//		    System.out.println(test1[4].getLocationName() + ":" + test1[4].getLocationX() + "," + test1[4].getLocationY());
		    
		    
//		    System.out.println(availableLocation.getOneLocationName(4));
//		    for(int ik=0;i<availableLocation.getOneXYLocation(4).length;ik++) {
//		    	System.out.println(availableLocation.getOneXYLocation(4)[ik]);
//		    }
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		new GUI(deliveryAgent,masterRoutingAgent,distanceLocation,fullLocation);
	}
}
