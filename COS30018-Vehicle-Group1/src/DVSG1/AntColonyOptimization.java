package DVSG1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class AntColonyOptimization {

	// original number of trails
    private double c = 1.0;
    // pherom
    private double alpha = 1;
    private double beta = 5;
    // percentage of pheromone be evaporated
    private double evaporation = 0.5;
    // total amount of pheromone left on the trail
    private double Q = 500;
    // how many ant used per city
    private double antFactor = 0.8;
    // randomness in simulation
    private double randomFactor = 0.01;

    private int maxIterations = 500;

    private int numberOfCities;
    private int numberOfAnts;
    private int graph[][];
    private double trails[][];
    private List<Ant> ants = new ArrayList<>();
    private Random random = new Random();
    private double probabilities[];

    private int currentIndex;

    private int[] bestTourOrder;
    private double bestTourLength;
    
	public Solution bestSolution;
	
	private int startingCity;
	private int targetFitness;

    public AntColonyOptimization(int[][] travelPrices, int startingCity, int targetFitness){
        graph = travelPrices;
        // graph length equals no of cities
        numberOfCities = graph.length;
        numberOfAnts = (int) (numberOfCities * antFactor);
        
        this.startingCity = startingCity;
        this.targetFitness = targetFitness;

        trails = new double[numberOfCities][numberOfCities];
        probabilities = new double[numberOfCities];
        IntStream.range(0, numberOfAnts)
            .forEach(i -> ants.add(new Ant(numberOfCities)));
    }
    
    /**
     * Perform ant optimization
     */
    public void startAntOptimization() {
    	IntStream.rangeClosed(0, maxIterations)
	        .forEach(i -> {
	            solve();
	        });
        System.out.println("Best tour order: " + Arrays.toString(bestTourOrder));
    }

    /**
     * Use this method to run the main logic
     */
    public int[] solve() {
        setupAnts();
        clearTrails();
        IntStream.range(0, maxIterations)
            .forEach(i -> {
            	// deciding what is the next city the agent should move
                moveAnts();
                // update pheromone and trails
                updateTrails();
                // update best solution
                updateBest();
            });
        return bestTourOrder.clone();
    }

    /**
     * Prepare ants for the simulation
     */
    private void setupAnts() {
        IntStream.range(0, numberOfAnts)
            .forEach(i -> {
                ants.forEach(ant -> {
                    ant.clear();
                    ant.visitCity(-1, startingCity);
                });
            });
        currentIndex = startingCity;
    }

    /**
     * At each iteration, move ants
     */
    private void moveAnts() {
        IntStream.range(currentIndex, numberOfCities - 2)
            .forEach(i -> {
            	if(currentIndex != numberOfCities - 2)
            		ants.forEach(ant -> ant.visitCity(currentIndex, selectNextCity(ant)));
            	else ants.forEach(ant -> ant.visitCity(currentIndex, targetFitness));
            	
            	//  System.out.println(currentIndex + "\t" + numberOfCities);
                currentIndex++;
            });
    }

    /**
     * Select next city for each ant
     */
    private int selectNextCity(Ant ant) {
    	// First solution : select based on probability
    	
    	// Generates random integers between 0 to number of city needs to be visited
        int t = random.nextInt(numberOfCities - currentIndex);
        
        if (random.nextDouble() < randomFactor) {
            OptionalInt cityIndex = IntStream.range(0, numberOfCities - 2)
                .filter(i -> i == t && !ant.visited(i))
                .findFirst();
            if (cityIndex.isPresent()) {
                return cityIndex.getAsInt();
            }
        }
        calculateProbabilities(ant);
        
        // After calculate probability, decide which city to go
        double r = random.nextDouble();
        double total = 0;
        for (int i = 0; i < numberOfCities; i++) {
            total += probabilities[i];
            if(total < 0) break;
            if (total >= r) {
                return i;
            }
        }
//
//        return 123;
        throw new RuntimeException("There are no other cities");
    }

    /**
     * Calculate the next city picks probabilities by shorter trails
     */
    public void calculateProbabilities(Ant ant) {
        int i = ant.trail[currentIndex];
        double pheromone = 0.0;	
        for (int l = 0; l < numberOfCities; l++) {
            if (!ant.visited(l)) {
            	// Equation of add pheromone
                pheromone += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }
        for (int j = 0; j < numberOfCities; j++) {
            if (ant.visited(j)) {
                probabilities[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probabilities[j] = numerator / pheromone;
            }
        }
    }

    /**
     * Update trails that ants used
     */
    private void updateTrails() {
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                trails[i][j] *= evaporation;
            }
        }
        
        // Section which add deposition of pheromone
        for (Ant a : ants) {
        	// Q is 500 pheromone / total trail length of particular ant
        	// Lower trail length, higher contribution 
            double contribution = Q / a.trailLength(graph);
            for (int i = 0; i < numberOfCities - 1; i++) {
                trails[a.trail[i]][a.trail[i + 1]] += contribution;
            }
            trails[a.trail[numberOfCities - 1]][a.trail[0]] += contribution;
        }
    }

    /**
     * Update the best solution
     */
    private void updateBest() {
        if (bestTourOrder == null) {
            bestTourOrder = ants.get(0).trail;
            bestTourLength = ants.get(0)
                .trailLength(graph);
        }
        for (Ant a : ants) {
            if (a.trailLength(graph) < bestTourLength) {
                bestTourLength = a.trailLength(graph);
                bestTourOrder = a.trail.clone();
            }
        }
    }

    /**
     * Clear trails after simulation
     */
    private void clearTrails() {
        IntStream.range(0, numberOfCities)
            .forEach(i -> {
                IntStream.range(0, numberOfCities)
                    .forEach(j -> trails[i][j] = c);
            });
    }
    
    /**
     * Return index of location (route)
     */
    public int[] getBest() {
    	// truncate first and last element of array
    	int[] original = Arrays.copyOfRange(bestTourOrder, 1, bestTourOrder.length);
    	int[] modifiedArray = Arrays.copyOf(original, original.length-1);
    	return modifiedArray;
    }
    
    /**
     * Return distance 
     */
    public double getBestTour() {
    	return bestTourLength - numberOfCities;
    }

}