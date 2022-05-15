package G1;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class CheckAgent extends Agent{
	static StoreAgent sa = new StoreAgent();

	protected void setup() {
		Object[] args = getArguments();
		Runtime rt = Runtime.instance();
		Profile pMain2 = new ProfileImpl(null,8801,null);
		pMain2.setParameter(Profile.GUI, "true");
		ContainerController secondCtrl = rt.createAgentContainer(pMain2);
		ContainerController daCtrl = null;
		for(int i=0;i<args.length;i++) {	
			try {
				secondCtrl.createNewAgent(args[i].toString(), CounterAgent.class.getName(), new Object[0]);
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		AMSAgentDescription [] agents = null;
		try {
			SearchConstraints c = new SearchConstraints();
			Long l = Long.valueOf(-1);
			c.setMaxResults(l);
			agents = AMSService.search(this, new AMSAgentDescription(),c);
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		AID myID = getAID();
		
		sa = new StoreAgent(agents,myID,"1100","192.168.68.109");
		
		main(args);
	}
	
	public static void main(Object[] args) {
		AgentConstraint[] ac = new AgentConstraint[args.length];
		sa.setDAName(args);
		sa.StoreMasterAgent();

		for(int i=0;i < ac.length;i++) {
			ac[i] = new AgentConstraint(sa.getDAName()[i].toString());
		}
		new gui(sa,ac);
	}
}
