package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathOverallSolutionForEachCluster {
	
	List<PathOverallSolution> pathOverallSolutionForEachCluster;
	
	PathOverallSolutionForEachCluster(){
		pathOverallSolutionForEachCluster = new ArrayList<PathOverallSolution>();
	}
	
	public void setPathOverallSolutionForEachCluster(PathOverallSolution temp) {
		pathOverallSolutionForEachCluster.add(temp);
	}
	
	public List<PathOverallSolution> getPathOverallSolutionForEachCluster(){
		return pathOverallSolutionForEachCluster;
	}
	
}
