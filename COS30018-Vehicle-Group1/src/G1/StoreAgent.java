package G1;

import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.AID;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.wrapper.ContainerController;



public class StoreAgent{
	static AMSAgentDescription[] AllAgent;
	static AID MRAID;
	static Object[] DAName;
	static String localhost;
	static String localport;
	static String MRAAddress;
	static String[] DAAddress;
	
	StoreAgent(){
		AllAgent = null;
		MRAID = null;
		DAName = null;
		localport = null;
		localhost = null;
		MRAAddress = null;
	}
	
	StoreAgent(AMSAgentDescription[] a,AID b,String c,String d){
		AllAgent = a;
		MRAID = b;
		localport = c;
		localhost = d;


		
	}
	
	public void StoreMasterAgent() {
		for (int i=0; i<AllAgent.length;i++) {
			AID agentID = AllAgent[i].getName();
			if(agentID.equals(MRAID)){
				MRAAddress = agentID.getName();
			}
		}
	}
	
	public void setDAName(Object[] a) {
		DAName = a;
	}
	
	public Object[] getDAName() {
		return DAName;
	}
	
	public String getlocalhost() {
		return localhost;
	}
	
	public String getlocalport() {
		return localport;
	}
	
	public String getAddressInfo() {
		return "@" + localhost + ":" + localport + "/JADE";
	}
	
	public String getMRAAddress() {
		return MRAAddress;
	}


	
}
