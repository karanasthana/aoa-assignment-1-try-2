import java.util.function.Supplier;
import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.util.SupplierUtil;

public class WeightedUndirectedGraphs {
	
	public static void main(String ...args) {
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
	    GnmRandomGraphGenerator<Integer, DefaultWeightedEdge> simpleGenerator = new GnmRandomGraphGenerator<>(10, 10);
	
	    // Use the GnmRandomGraphGenerator object to make a simpleGraph with numVertices number of vertices
	    simpleGenerator.generateGraph(simpleWeightedGraph);
	    
	    Set<DefaultWeightedEdge> edges = simpleWeightedGraph.edgeSet();
	    int numEdges = edges.size();
	    for (DefaultWeightedEdge e: edges) {
	    	simpleWeightedGraph.setEdgeWeight(e, (Math.ceil(Math.random() * numEdges * 5)));
	    }

		FindCycles.printWeightedGraph(simpleWeightedGraph);
	
//	    return simpleGraph;
	}
}