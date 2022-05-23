package G1;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;


public class CounterAgent extends Agent implements AgentInterface{
	
	public CounterAgent() {
		registerO2AInterface(AgentInterface.class,this);
	}

	@Override
	public void deactivateCounter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activateCOunter() {
		// TODO Auto-generated method stub
		
	}

}
