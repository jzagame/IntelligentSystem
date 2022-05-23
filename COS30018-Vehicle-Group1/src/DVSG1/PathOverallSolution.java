package DVSG1;

import java.util.ArrayList;
import java.util.List;

public class PathOverallSolution {
	
	List<PathOverallSolutionInformation> pathOverallSolution;
	String clusterName;
	List<LocationDetail> bestPathInCluster;
	int BestFitness;
	
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
	
	public void setFitness(int temp) {
		BestFitness = temp;
	}
	
	public String getPathOverallSolutionClusterName() {
		return clusterName;
	}
	
	public String getBestPathString() {
		String temp = null;
		for(LocationDetail x:bestPathInCluster) {
			temp += x.getLocationName() + " -> ";
		}
		temp += "W";
		
		return temp;
	}
	
	public void CalculateBestPathForAgent(List<PathOverallSolutionInformation> temp) {
		int i = 0;
		int index = 0;
		int tempTotalParcel = temp.get(0).getTotalParcelInPath();
		int tempFitness = temp.get(0).getFitness();
		for(PathOverallSolutionInformation x:temp) {
			if( tempFitness > x.getFitness() || (tempFitness == x.getFitness() && tempTotalParcel < x.getTotalParcelInPath())) {
				tempTotalParcel = x.getTotalParcelInPath();
				tempFitness = x.getFitness();
				index = i;
			}
			i++;
		}
		System.out.println("Best Distance : " + tempFitness);
		this.setFitness(tempFitness);
		this.setBestPathInCluster(pathOverallSolution.get(index).getPathOverallSoluitionInformation());
	}
	
	public void PrintBestPathDetail() {
		for(LocationDetail x:bestPathInCluster) {
			System.out.print(x.getLocationName() + " ");
		}
		System.out.println("");
	}
	
	
}
