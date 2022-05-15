package DVSG1;


public class Print {
	
	private PathAvailableForEachCluster pafec;
	private DeliveryAgent da;
	private AlgorithmType algoType;

	Print(PathAvailableForEachCluster pafec, DeliveryAgent da, AlgorithmType algoType){
		this.pafec = pafec;
		this.da = da;
		this.algoType = algoType;
	}

	public void Output () {
		int sizeCluster = pafec.getAllPossiblePathForCluster().size();
		int sizeAgent = da.getListAgentConstraint().size();
		int loop = sizeCluster;
		if(sizeCluster > sizeAgent) {
			loop = sizeAgent;
		}

		try {
			for(int i=0;i<loop;i++) {
			    int id = 0;
			    
				System.out.println("Cluster Name : " + pafec.getAllPossiblePathForCluster().get(i).getClusterName());
				System.out.println("Agent Name : " + da.getAgentConstraintSorted().get(i).getAgentName());
				System.out.println("-------------------------------------------");
				
				for(PathAvailable x:pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster()) {
				    if(algoType == AlgorithmType.GA){
				    	UberSalesmensch geneticAlgorithm = new UberSalesmensch(x.getPathDetail().size(), 
								SelectionType.TOURNAMENT, x.getDistanceMatrix(), 0, 0);
				        SalesmanGenome result = geneticAlgorithm.optimize();
				        System.out.println("Possible Solution : " + pafec.getAllPossiblePathForCluster().get(i).getPossiblePathOfEachCluster().size());
				        id++;
			            System.out.print("No." + id + " : W -> ");
				        for(int j=0;j<result.getGenome().size();j++) {
				        	System.out.print(x.getPathDetail().get(j).getLocationName()+ " -> ");	
				        }
				        System.out.println(" W");
				        System.out.println("Distance : " + result.getFitness());
				        System.out.println("-------------------------------------------");
				    }
				    else if(algoType == AlgorithmType.ACO){
				    	AntColonyOptimization antColony = new AntColonyOptimization(x.getPathDetail().size());
						antColony.startAntOptimization();
				    }
				    
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
