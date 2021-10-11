import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.*;

import java.util.*;
import java.util.function.*;
import java.io.FileWriter;
import java.io.IOException;

public final class FindCycles
{
    /**
     * Main entry point.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int graphSize = 0;

        FindCyclesTest.runTestCases();
        System.out.println("All Test cases run successfully!");

    	for (int i = 0; i < 5; i++) {
    		if (i < 5) {
    			graphSize = (int)(Math.random() * Math.pow(10, (i+2)));
    		} else {
    			graphSize = (int)(Math.random() * Math.pow(10, 6));
    		}
    		int numEdges = (int)(Math.random() * graphSize * 2); // creating the number of edges from 0 to (2 * number of Vertices)

    		Graph<Integer, DefaultEdge> graph = generateGraph(graphSize, numEdges);

    		long startTime = System.nanoTime();
    		@SuppressWarnings("unused")
			List<Integer> cycle = Cycle.returnCycle(graph);
            long elapsedTime = System.nanoTime() - startTime;

            writeToFile(graphSize, numEdges, elapsedTime);
    	}
    	System.out.print("Added all the data in the csv.");
    }
    
    /**
     * Helper function to generate a graph with random vertices and edges.
     * 
     * @param numVertices number of vertices expected in the graph
     * @param numEdges number of edges expected in the graph
     */
    public static Graph<Integer, DefaultEdge> generateGraph(int numVertices, int numEdges) {
    	// Create the VertexFactory so the generator can create vertices
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
        Graph<Integer, DefaultEdge> simpleGraph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        // Create the GnmRandomGraphGenerator object
        GnmRandomGraphGenerator<Integer, DefaultEdge> simpleGenerator = new GnmRandomGraphGenerator<>(numVertices, numEdges);

        // Use the GnmRandomGraphGenerator object to make a simpleGraph with numVertices number of vertices
        simpleGenerator.generateGraph(simpleGraph);
    	// printGraph(simpleGraph);

        return simpleGraph;
    }
    
    /**
     * Helper function to print out the graph to analyze randomness
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
    public static void printGraph(Graph<Integer, DefaultEdge> graph) {
      Iterator<Integer> iter = new DepthFirstIterator<>(graph);
      while (iter.hasNext()) {
          Integer vertex = iter.next();
          System.out.println("Vertex " + vertex + " is connected to: " + graph.edgesOf(vertex).toString());
      }
    }

    /**
     * Writes the parameters into a CSV file (for further analysis)
     * 
     * @param graphSize The number of nodes in the graph
     * @param numEdges The number of edges in the graph
     * @param elapsedTime The time elapsed to find if the graph has a cycle
     */
    public static void writeToFile(int graphSize, int numEdges, long elapsedTime) {
    	try {
    		FileWriter pw = new FileWriter("data.csv", true); 
    		pw.append(graphSize + "," + numEdges + "," + elapsedTime);
    		pw.append("\n");
    		pw.flush();
    		pw.close();    		
    	} catch (IOException e) {
    		System.out.println("Exception occurred e: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
}