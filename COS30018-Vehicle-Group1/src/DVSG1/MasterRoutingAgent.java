package DVSG1;

import jade.core.Agent;

public class MasterRoutingAgent {
	Agent mra;
	
	MasterRoutingAgent(){
		mra = null;
	}
	
	public Agent getMRA() {
		return mra;
	}
	
	public void setMRA(Agent temp) {
		mra = temp;
	}
	
	public void PrintMRADetail() {
		System.out.println("MRA Name :" + mra.getLocalName());
		System.out.println("MRA GUID Name :" + mra.getName());
	}
}
