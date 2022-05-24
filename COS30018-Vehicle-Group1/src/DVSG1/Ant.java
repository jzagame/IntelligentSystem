package DVSG1;

public class Ant {

	protected int trailSize;
	protected int trail[];
	protected boolean visited[];

	public Ant(int numCity) {
		this.trailSize = numCity;
		this.trail = new int[numCity];
		this.visited = new boolean[numCity];
	}

	// function of keep track of city that visited 
	protected void visitCity(int currentIndex, int city) {
		// All the visited city for that particular ant object will be store into trail array
		trail[currentIndex + 1] = city;
		// All the visited city for that particular ant object will be set to true
		visited[city] = true;
	}

	protected boolean visited(int i) {
		return visited[i];
	}

	// function of keep track of total trail length of particular ant
	protected double trailLength(int graph[][]) {
		double length = graph[trail[trailSize - 1]][trail[0]];
		for (int i = 0; i < trailSize - 1; i++) {
			length += graph[trail[i]][trail[i + 1]];
		}
		return length;
	}

	protected void clear() {
		for (int i = 0; i < trailSize; i++)
			visited[i] = false;
	}

}