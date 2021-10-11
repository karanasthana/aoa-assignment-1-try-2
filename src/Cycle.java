import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import java.util.*;

public class Cycle {
    @SuppressWarnings("rawtypes")
	static ArrayList cycle = new ArrayList();
    @SuppressWarnings("rawtypes")
	static Stack CycleList = new Stack();
    static Set<Integer> visited = new HashSet<Integer>();
    @SuppressWarnings("rawtypes")
	static Graph graph;
    
    
    /**
     * Helper function to execute the Depth First Search (DFS) algorithm to find a cycle starting from this node
     * 
     * @param currentV - The current vertex for which we need to find links and cycle
     * @param parent - The node from where we entered the vertex v into the graph (to prevent double checking the same edge)
     */
    @SuppressWarnings("unchecked")
	public static Boolean CyclicUtil(int currentV, int parent){
        visited.add(currentV);
        
        CycleList.add(currentV); // adding the vertex to the DFS Stack (to store the cycle path)

        for(Object neighbor: Graphs.neighborListOf(graph, currentV)){
            int child = (int)neighbor;
            if(!visited.contains(child)) {
                if(CyclicUtil(child, currentV)) {
                	return true;
                }
            }
            else if(child != parent){
                CycleList.add(child);
                return true;
            }
        }
        CycleList.pop();
        return false;
    }

    /**
     * Returns a non-empty List of vertices (integers) if the graph contains a cycle, else an empty list.
     * 
     * @param graph The Graph object instance which has been created with random vertices and edges
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Integer> returnCycle(Graph g){
        graph = g;
        Set<Integer> vSet = graph.vertexSet();
        visited.clear();
        cycle.clear();

        for(Object v: vSet) {
        	if (visited.contains((int)v)) {
        		continue;
        	}
            CycleList.clear();
            if (CyclicUtil((int)v, -1)) {
                Integer start = (Integer)CycleList.pop();

                cycle.add(start);

                while (!CycleList.isEmpty()){
                    if ((int)CycleList.peek() == start) {
                    	break;
                    }
                    cycle.add((Integer)CycleList.pop());
                }
                
                cycle.add(start);
                
                return cycle;
            }
        }

        return cycle;
    }
}