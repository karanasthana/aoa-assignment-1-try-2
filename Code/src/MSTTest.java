import java.util.Set;
import java.util.function.Supplier;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

public class MSTTest {
	
	/**
     * Helper function to run all the test cases and return true if all the test cases were passed successfully.
     * 
     */
	public static boolean runTestCases() {
    	Supplier<Integer> vSupplier = new Supplier<Integer>()
        {
            private int id = 0;

            @Override
            public Integer get()
            {
                return id++;
            }
        };

        Graph<Integer, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(vSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier());
	    
	    boolean isCorrect = false;

	    isCorrect = runTestCase1(graph);
	    if (!isCorrect) {
	    	return false;
	    }

	    graph = new SimpleWeightedGraph<>(vSupplier, SupplierUtil.createDefaultWeightedEdgeSupplier()); 
 	    isCorrect = runTestCase2(graph);
	    if (!isCorrect) {
	    	return false;
	    }
	    
	    System.out.println("Successfully passed both Test Cases!");
		return true;

    }
	
	/**
     * Returns true if the MST found via code is equal to the MST that was expected
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	@SuppressWarnings("unused")
	public static boolean runTestCase1(Graph<Integer, DefaultWeightedEdge> graph) {
		System.out.println();
		System.out.println("------Test Case 1-----");

		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    
	    DefaultWeightedEdge e1 = graph.addEdge(1, 2);
	    DefaultWeightedEdge e2 = graph.addEdge(2, 3);
	    DefaultWeightedEdge e3 = graph.addEdge(3, 4);
	    DefaultWeightedEdge e4 = graph.addEdge(4, 1);
	    DefaultWeightedEdge e5 = graph.addEdge(4, 2);
	    
	    Set<DefaultWeightedEdge> edges = graph.edgeSet();
	    
	    int i=0;
	    for (DefaultWeightedEdge e: edges) {
	    	graph.setEdgeWeight(e, 100+i);
	    	i++;
	    }
	    
	    // Expected Output
	    Graph<Integer, DefaultWeightedEdge> graph2 = graph;
	    graph2.removeEdge(e5); // Because it is the heaviest edge
	    graph2.removeEdge(e4); // Because it is the 2nd most heavy edge
	    Set<DefaultWeightedEdge> expectedEdges = graph2.edgeSet();
	    
	    Set<DefaultWeightedEdge> outputEdges = FindMST.performMSTFind(graph, 4, 5);

	    if (outputEdges == (expectedEdges)) {
	    	System.out.println("Successfully Passed Test Case 1.");
	    	System.out.println("The MST has the edges - " + outputEdges);
	    	return true;
	    } else {
	    	System.out.println("Test Case Failed for Graph 1");
	    	return false;
	    }
	}
	
	/**
     * Returns true if the MST found via code is equal to the MST that was expected
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	@SuppressWarnings("unused")
	public static boolean runTestCase2(Graph<Integer, DefaultWeightedEdge> graph) {
		System.out.println();
		System.out.println("------Test Case 1-----");

		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    graph.addVertex(5);
	    graph.addVertex(6);
	    graph.addVertex(7);
	    graph.addVertex(8);
	    
	    
	    DefaultWeightedEdge e1 = graph.addEdge(1, 2);
	    DefaultWeightedEdge e2 = graph.addEdge(2, 3);
	    DefaultWeightedEdge e3 = graph.addEdge(3, 4);
	    DefaultWeightedEdge e4 = graph.addEdge(4, 5);
	    DefaultWeightedEdge e5 = graph.addEdge(5, 6);
	    DefaultWeightedEdge e6 = graph.addEdge(6, 7);
	    DefaultWeightedEdge e7 = graph.addEdge(7, 8);
	    DefaultWeightedEdge e8 = graph.addEdge(1, 7);
	    DefaultWeightedEdge e9 = graph.addEdge(2, 4);
	    DefaultWeightedEdge e10 = graph.addEdge(3, 5);
	    DefaultWeightedEdge e11 = graph.addEdge(1, 8);
	    
	    Set<DefaultWeightedEdge> edges = graph.edgeSet();
	    
	    int i=0;
	    for (DefaultWeightedEdge e: edges) {
	    	graph.setEdgeWeight(e, 100+i);
	    	i++;
	    }
	    
	    // Expected Output
	    Graph<Integer, DefaultWeightedEdge> graph2 = graph;
	    graph2.removeEdge(e11); // Because it is the heaviest edge
	    graph2.removeEdge(e10); // Because it is the 2nd most heavy edge
	    graph2.removeEdge(e9); // Because it is the 3rd most heavy edge
	    graph2.removeEdge(e8); // Because it is the 4th most heavy edge
	    Set<DefaultWeightedEdge> expectedEdges = graph2.edgeSet();
	    
	    Set<DefaultWeightedEdge> outputEdges = FindMST.performMSTFind(graph, 4, 5);

	    if (outputEdges == (expectedEdges)) {
	    	System.out.println("Successfully Passed Test Case 2.");
	    	System.out.println("The MST has the edges - " + outputEdges);
	    	return true;
	    } else {
	    	System.out.println("Test Case Failed for Graph 2");
	    	return false;
	    }
	}
}
