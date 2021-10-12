import java.util.function.Supplier;
import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.generate.PruferTreeGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;

public class WeightedUndirectedGraphs {
	
	public static void main(String ...args) {

        boolean testCasesSuccessful = MSTTest.runTestCases();
        if (testCasesSuccessful) {
        	System.out.println();
        	System.out.println("All Test cases run successfully!");        	
        } else {
        	System.out.println();
        	System.out.println("Test Case Failure!");
        	return;
        }

    	for (int i = 0; i < 0; i++) {
			int numVertices = 0;
    		if (i < 5) {
    			numVertices = (int)(Math.random() * Math.pow(10, (i+2)));
    		} else {
    			numVertices = (int)(Math.random() * Math.pow(10, 6));
    		}

    		Graph<Integer, DefaultWeightedEdge> graph;
    		try {
    			graph = generateWeightedGraphs(numVertices);
			} catch (Exception e) {
				System.out.println("Created a self looped graph! Not allowed, Ignore! i = " + i);
				continue;
			}


    		performMSTFind(graph, numVertices, graph.edgeSet().size());

    		if (i%100 == 0)
    		System.out.println("Computed " + i + " values");
    	}
//    	System.out.print("Added all the data in the csv.");
	}

	public static Set<DefaultWeightedEdge> performMSTFind(Graph<Integer, DefaultWeightedEdge> graph, int numVertices, int numEdges) {
		long startTime = System.nanoTime();

		// HERE WE NEED TO DO THE MST LOGIC
		for (int j = numVertices; j <= numEdges; j++) {
			List<Integer> cycle = Cycle.returnCycle(graph);
			DefaultWeightedEdge heaviestEdge = removeHeaviestEdge(cycle, graph.edgeSet(), graph);
			graph.removeEdge(heaviestEdge);
		}

//		List<Integer> lastCycle = Cycle.returnCycle(graph);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		
//		System.out.println("Found the MST after removing " + (numEdges - numVertices + 1) + " edges!");

		FindCycles.writeToFile(numVertices, numEdges, elapsedTime, "q2_data.csv");
		
		return graph.edgeSet();
	}

	public static Graph<Integer, DefaultWeightedEdge> generateWeightedGraphs(int verticesNum) {
		Supplier<Integer> vSupplier = new Supplier<Integer>()
	    {
	        private int id = 0;
	
	        @Override
	        public Integer get()
	        {
	            return id++;
	        }
	    };

//      Initialize graph
        Graph<Integer, DefaultWeightedEdge> sparseGraph =
                new SimpleWeightedGraph<>(vSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());

//      Initialize TreeGenerator
        PruferTreeGenerator<Integer, DefaultWeightedEdge> pruferTreeGenerator = new PruferTreeGenerator<>(verticesNum);

//      Generating the base tree structure for the graph
        pruferTreeGenerator.generateGraph(sparseGraph);
        sparseGraph = addRandomEdges(sparseGraph);

	    Set<DefaultWeightedEdge> edges = sparseGraph.edgeSet();
	    int numEdges = edges.size();
	    for (DefaultWeightedEdge e: edges) {
	    	sparseGraph.setEdgeWeight(e, (Math.ceil(Math.random() * numEdges * 5)));
	    }

		return sparseGraph;
	}
	
	public static Graph<Integer, DefaultWeightedEdge> addRandomEdges(Graph<Integer, DefaultWeightedEdge> graph) {
		int numEdgesToAdd = (int)(Math.random() * 8);
//		System.out.println("Number of edges being added: " + numEdgesToAdd);
		for (int i=0; i<numEdgesToAdd; i++) {
			graph = addRandomEdge(graph);
		}

		return graph;
	}

	public static Graph<Integer, DefaultWeightedEdge> addRandomEdge(Graph<Integer, DefaultWeightedEdge> graph) {
		boolean isAdded = false;
		Object[] allVertices = graph.vertexSet().toArray();
		int vertex1 = (int) (allVertices)[((int)(Math.random() * allVertices.length - 1))];
		int vertex2 = (int) (allVertices)[((int)(Math.random() * allVertices.length - 1))];
		

		while (!isAdded) {
			if (graph.containsEdge(vertex1, vertex2) || graph.containsEdge(vertex2, vertex1)) {
				vertex1 = (int) (allVertices)[((int)(Math.random() * allVertices.length - 1))];
				vertex2 = (int) (allVertices)[((int)(Math.random() * allVertices.length - 1))];
			} else {
				DefaultWeightedEdge e = graph.addEdge(vertex1, vertex2);
				graph.setEdgeWeight(e, (Math.ceil(Math.random() * (graph.vertexSet().size()* 5))));
				// System.out.println("Adding edge between " + vertex1 + " and " + vertex2);
				isAdded = true;
			}
		}

		return graph;
	}

	public static DefaultWeightedEdge removeHeaviestEdge(List<Integer> verticesList, Set<DefaultWeightedEdge> edgesSet, Graph<Integer, DefaultWeightedEdge> graph) {
		double max = -1;
		DefaultWeightedEdge maxEdge = null;
		// int sourceF = -1, targetF = -1;

		for(DefaultWeightedEdge edge : edgesSet) {
			double edgeWeight = graph.getEdgeWeight(edge);
			int source = graph.getEdgeSource(edge);
			int target = graph.getEdgeTarget(edge);

			if (verticesList.contains(source) && verticesList.contains(target)) {
				if (edgeWeight > max) {
					max = edgeWeight;
					maxEdge = edge;
					// sourceF = source;
					// targetF = target;
				}
			}
		}
		// System.out.println("Removing the edge between " + sourceF + " and " + targetF + " which weighed " + max);
		return maxEdge;
	}
}