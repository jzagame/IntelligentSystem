package G1;

import jade.core.Runtime;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class main{
	public static void main(String[] args) throws StaleProxyException,InterruptedException{
		Runtime rt = Runtime.instance();
		
		Profile pMain = new ProfileImpl(null,8801,null);
		pMain.setParameter(Profile.GUI, "true");
		ContainerController mainCtrl= rt.createMainContainer(pMain);
		Profile pMain2 = new ProfileImpl(null,8801,null);
		pMain2.setParameter(Profile.GUI, "true");
		ContainerController secondCtrl = rt.createAgentContainer(pMain2);
		
		AgentController agentCtrl = mainCtrl.createNewAgent("MasterAgent", CounterAgent.class.getName(), new Object[0]);				
		AgentController daCtrl = secondCtrl.createNewAgent("DA1", CounterAgent.class.getName(), new Object[0]);
		daCtrl = secondCtrl.createNewAgent("DA2", CounterAgent.class.getName(), new Object[0]);
		daCtrl = secondCtrl.createNewAgent("DA3", CounterAgent.class.getName(), new Object[0]);
		daCtrl = secondCtrl.createNewAgent("DA4", CounterAgent.class.getName(), new Object[0]);
		agentCtrl.start();
		daCtrl.start();
		new gui();
		
	} 
}
