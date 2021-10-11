import java.util.function.Supplier;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

public class FindCyclesTest {
	
	public static void runTestCases() {
    	Supplier<Integer> vSupplier = new Supplier<Integer>()
        {
            private int id = 0;

            @Override
            public Integer get()
            {
                return id++;
            }
        };

	    Graph<Integer, DefaultEdge> graph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false); 
	    
	    boolean isCorrect = false;

	    isCorrect = FindCyclesTest.runTestCase1(graph);
	    if (!isCorrect) {
	    	System.exit(0);
	    }

 	    graph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
 	    isCorrect = FindCyclesTest.runTestCase2(graph);
	    if (!isCorrect) {
	    	System.exit(0);
	    }

	    graph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
	    isCorrect = FindCyclesTest.runTestCase3(graph);
	    if (!isCorrect) {
	    	System.exit(0);
	    }
	    
	    graph = new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);
	    isCorrect = FindCyclesTest.runTestCase4(graph);
	    if (!isCorrect) {
	    	System.exit(0);
	    }
	    
	    System.out.println("Graph now has - " + graph.vertexSet().size() + " vertices");
	    System.out.println("Graph now has - " + graph.edgeSet().size() + " edges");
	    System.out.println("Successfully passed all Test Cases!");

    }
	
	/**
     * Returns true if the graph contains the cycle, else false.
     * This graph contains a cycle (1->2->3->4->1)
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	public static boolean runTestCase1(Graph<Integer, DefaultEdge> graph) {
		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    
	    graph.addEdge(1, 2);
	    graph.addEdge(2, 3);
	    graph.addEdge(3, 4);
	    graph.addEdge(4, 1);
	    
	    if (Cycle.returnCycle(graph).size() == 0) {
	    	System.out.println("Test Case Failed for Graph 1 (4 vertex, 4 edge)");
	    	return false;
	    } else {
	    	System.out.println("Successfully Passed Test Case 1");
	    	return true;
	    }
	}
	
	/**
     * Returns true if the graph does NOT contain a cycle, else false.
     * This graph does not contain a cycle
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	public static boolean runTestCase2(Graph<Integer, DefaultEdge> graph) {
		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    
	    graph.addEdge(1, 2);
	    graph.addEdge(2, 3);
	    graph.addEdge(3, 4);
	    
	    if (Cycle.returnCycle(graph).size() != 0) {
	    	System.out.println("Test Case Failed for Graph 1 (4 vertex, 3 edge, No Cycle)");
	    	return false;
	    } else {
	    	System.out.println("Successfully Passed Test Case 2");
	    	return true;
	    }
	}
	
	/**
     * Returns true if the graph contains a cycle, else false.
     * This graph contains a cycle (1->2->8->5->4->1)
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	public static boolean runTestCase3(Graph<Integer, DefaultEdge> graph) {
		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    graph.addVertex(5);
	    graph.addVertex(6);
	    graph.addVertex(7);
	    graph.addVertex(8);
	    
	    graph.addEdge(1, 2);
	    graph.addEdge(2, 3);
	    graph.addEdge(1, 4);
	    graph.addEdge(4, 5);
	    graph.addEdge(5, 8);
	    graph.addEdge(8, 2);
	    graph.addEdge(6, 7);
	    
	    if (Cycle.returnCycle(graph).size() == 0) {
	    	System.out.println("Test Case Failed for Graph 3 (8 vertex, 7 edges)");
	    	return false;
	    } else {
	    	System.out.println("Successfully Passed Test Case 3");
	    	return true;
	    }
	}
	
	/**
     * Returns true if the graph does NOT contain a cycle, else false.
     * This graph does not contain a cycle
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
	public static boolean runTestCase4(Graph<Integer, DefaultEdge> graph) {
		graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    graph.addVertex(4);
	    graph.addVertex(5);
	    graph.addVertex(6);
	    graph.addVertex(7);
	    graph.addVertex(8);
	    
	    graph.addEdge(1, 2);
	    graph.addEdge(1, 3);
	    graph.addEdge(1, 7);
	    graph.addEdge(1, 8);
	    graph.addEdge(2, 5);
	    graph.addEdge(5, 6);
	    graph.addEdge(5, 4);
	    
	    if (Cycle.returnCycle(graph).size() != 0) {
	    	System.out.println("Test Case Failed for Graph 4 (8 vertex, 7 edges, No Cycle)");
	    	return false;
	    } else {
	    	System.out.println("Successfully Passed Test Case 4");
	    	return true;
	    }
	}

}
