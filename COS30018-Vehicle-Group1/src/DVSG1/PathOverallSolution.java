package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathOverallSolution {
	
	List<PathOverallSolutionInformation> pathOverallSolution;
	String clusterName;
	List<LocationDetail> bestPathInCluster;
	
	PathOverallSolution(){
		pathOverallSolution = new ArrayList<PathOverallSolutionInformation>();
		bestPathInCluster = new ArrayList<LocationDetail>();
	}
	
	public List<PathOverallSolutionInformation> getPathOverallSolution(){
		return pathOverallSolution;
	}
	
	public void setBestPathInCluster(List<LocationDetail> temp) {
		bestPathInCluster.addAll(temp);
	}
	
	public void setPathOverallSolution(PathOverallSolutionInformation temp) {
		pathOverallSolution.add(temp);
	}
	
	public List<LocationDetail> getBestPathInCluster() {
		return bestPathInCluster;
	}
	
	public void setPathOverallSolutionClusterName(String name) {
		clusterName = name;
	}
	
	public String getPathOverallSolutionClusterName() {
		return clusterName;
	}
	
	public void CalculateBestPathForAgent() {
		int i = 0;
		int index = 0 ;
		int tempTotalParcel = 0;
		int tempFitness = 0;
		for(PathOverallSolutionInformation x:pathOverallSolution) {
			if( tempFitness > x.getFitness() || (tempFitness == x.getFitness() && tempTotalParcel < x.getTotalParcelInPath())) {
				tempTotalParcel = x.getTotalParcelInPath();
				tempFitness = x.getFitness();
				index = i;
			}
			i++;
		}
		this.setBestPathInCluster(pathOverallSolution.get(index).getPathOverallSoluitionInformation());
	}
	
	
}
