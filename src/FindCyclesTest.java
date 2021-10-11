import java.util.function.Supplier;

import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.util.SupplierUtil;

public class FindCyclesTest {
	
	//for normal addition 
	@Test
	public void testGraphSize4() 
	{
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
		
	    graph.addVertex(0);
	    graph.addVertex(1);
	    graph.addVertex(2);
	    graph.addVertex(3);
	    
	    graph.addEdge(0, 1);
	    graph.addEdge(1, 2);
	    graph.addEdge(2, 3);
	    graph.addEdge(3, 1);
	    
	    assert FindCycles.isCyclic(graph);
	}

}
