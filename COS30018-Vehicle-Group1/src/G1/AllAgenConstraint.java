package G1;

public class AllAgenConstraint {
	AgentConstraint[] ac;
	
	AllAgenConstraint(AgentConstraint a,int b){
		ac[b] = a;
	}
	
	public void Print() {
		for(int i=0;i<ac.length;i++) {
			System.out.println(ac[i].getAgentName() + ac[i].getTotalItem());
		}
	}
}
