import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.util.*;

import java.util.*;
import java.util.function.*;
import java.io.FileWriter;
import java.io.IOException;

public final class CompleteGraphDemo
{
    /**
     * Main entry point.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        int graphSize = 10;

    	for (int i=0; i<15; i++) {
    		if (i < 5) {
    			graphSize = (int)(Math.random() * Math.pow(10, (i+2)));
    		} else {
    			graphSize = (int)(Math.random() * Math.pow(10, 6));
    		}
    		int numEdges = (int)(Math.random() * graphSize);
    		Graph<Integer, DefaultEdge> graph = generateGraph(graphSize, numEdges);

    		long startTime = System.nanoTime();
            boolean isCycle = isCyclic(graph);
            long elapsedTime = System.nanoTime() - startTime;

            System.out.print("i = " + i + ". Time taken for " + graphSize + " nodes and " + numEdges + " edges was " + (elapsedTime/1000) + " microseconds");
            System.out.println("  Is cycle present? - " + isCycle);
            writeToFile(graphSize, numEdges, elapsedTime);
    	}
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
        Graph<Integer, DefaultEdge> simpleGraph =
            new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        // Create the GnmRandomGraphGenerator object
        GnmRandomGraphGenerator<Integer, DefaultEdge> simpleGenerator =
            new GnmRandomGraphGenerator<>(numVertices, numEdges);

        // Use the GnmRandomGraphGenerator object to make a simpleGraph with numVertices number of vertices
        simpleGenerator.generateGraph(simpleGraph);

    	// printGraph(simpleGraph);

        return simpleGraph;
    }
    
    /**
     * Helper function to print out the graph to analyse randomness
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
     * Helper function to execute the dfs algorithm to find a cycle starting from this node
     * 
     * @param v The current vertex for which we need to find links and cycle
     * @param visited The boolean array that contains a list of the vertices that have been traversed
     * @param parent The node from where we entered the vertex v into the graph (to prevent double checking the same edge)
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
    public static boolean isCyclicUtil(int v, boolean visited[], int parent, Graph<Integer, DefaultEdge> graph) {
        // Mark the current node as visited
        visited[v] = true;
        int i;
 
        // Recur for all the vertices
        // adjacent to this vertex
        List<Integer> neighborsOfV = Graphs.neighborListOf(graph, v);
        Iterator<Integer> it = neighborsOfV.iterator();
        while (it.hasNext())
        {
            i = it.next();
 
            // If an adjacent is not visited, then recur for that adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v, graph))
                    return true;
            }
 
            // If an adjacent is visited and not parent of current vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }
 
    /**
     * Returns true if the graph contains a cycle, else false.
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
    public static boolean isCyclic(Graph<Integer, DefaultEdge> g) {
         
        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
    	Set<Integer> VertexSet = (Set<Integer>) g.vertexSet();
    	int V = VertexSet.size();

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
 
        // Call the recursive helper function to detect cycle in different DFS trees
        for (Integer u: VertexSet) {
        	if (!visited[u])
                if (isCyclicUtil(u, visited, -1, g))
                    return true;
    	}
 
        return false;
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
    		FileWriter pw = new FileWriter("/Users/karanasthana/Documents/UFL/AOA/Assigments/Assignment/data.csv", true); 
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