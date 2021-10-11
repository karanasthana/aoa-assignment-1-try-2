import java.util.function.Supplier;
import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;

public class WeightedUndirectedGraphs {
	
	public static void main(String ...args) {
        FindCyclesTest.runTestCases();
        System.out.println("All Test cases run successfully!");

    	for (int i = 0; i < 1; i++) {
			int numVertices = 0;
    		if (i < 5) {
    			numVertices = (int)(Math.random() * Math.pow(10, (i+2)));
    		} else {
    			numVertices = (int)(Math.random() * Math.pow(10, 6));
    		}
    		int numEdges = numVertices + Math.min((int)(Math.random() * 8), numVertices); // creating the number of edges from n to (n + 8)

    		Graph<Integer, DefaultWeightedEdge> graph = generateWeightedGraphs(numVertices, numEdges);

    		performMSTFind(graph, numVertices, numEdges);
    	}
    	System.out.print("Added all the data in the csv.");
	}

	public static void performMSTFind(Graph<Integer, DefaultWeightedEdge> graph, int numVertices, int numEdges) {
		long startTime = System.nanoTime();

		// HERE WE NEED TO DO THE MST LOGIC
		for (int j = numVertices; j <= numEdges; j++) {
			List<Integer> cycle = Cycle.returnCycle(graph);
			DefaultWeightedEdge heaviestEdge = removeHeaviestEdge(cycle, graph.edgeSet(), graph);
			graph.removeEdge(heaviestEdge);
		}

		List<Integer> lastCycle = Cycle.returnCycle(graph);
		if (lastCycle.size() > 0) {
			System.out.println("The graph is not connected. Hence, MST is not possible!");
		} else {
			System.out.println("Found the MST!");
			FindCycles.printWeightedGraph(graph);
		}
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		FindCycles.writeToFile(numVertices, numEdges, elapsedTime);
	}

	public static Graph<Integer, DefaultWeightedEdge> generateWeightedGraphs(int verticesNum, int edgesNum) {
		Supplier<Integer> vSupplier = new Supplier<Integer>()
	    {
	        private int id = 0;
	
	        @Override
	        public Integer get()
	        {
	            return id++;
	        }
	    };

	    // Create the graph object
	    Graph<Integer, DefaultWeightedEdge> simpleWeightedGraph = new SimpleWeightedGraph<>(vSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
	    // Create the GnmRandomGraphGenerator object
	    GnmRandomGraphGenerator<Integer, DefaultWeightedEdge> simpleGenerator = new GnmRandomGraphGenerator<>(verticesNum, edgesNum);
	
	    // Use the GnmRandomGraphGenerator object to make a simpleGraph with numVertices number of vertices
	    simpleGenerator.generateGraph(simpleWeightedGraph);
	    
	    Set<DefaultWeightedEdge> edges = simpleWeightedGraph.edgeSet();
	    int numEdges = edges.size();
	    for (DefaultWeightedEdge e: edges) {
	    	simpleWeightedGraph.setEdgeWeight(e, (Math.ceil(Math.random() * numEdges * 5)));
	    }

		FindCycles.printWeightedGraph(simpleWeightedGraph);

		return simpleWeightedGraph;
	
//	    return simpleGraph;
	}

	public static DefaultWeightedEdge removeHeaviestEdge(List<Integer> verticesList, Set<DefaultWeightedEdge> edgesSet, Graph<Integer, DefaultWeightedEdge> graph) {
		double max = -1;
		DefaultWeightedEdge maxEdge = null;

		for(DefaultWeightedEdge edge : edgesSet) {
			double edgeWeight = graph.getEdgeWeight(edge);
			int source = graph.getEdgeSource(edge);
			int target = graph.getEdgeTarget(edge);

			if (verticesList.contains(source) && verticesList.contains(target)) {
				if (edgeWeight > max) {
					max = edgeWeight;
					maxEdge = edge;
				}
			}
		}
		return maxEdge;
	}
}