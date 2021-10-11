import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import java.util.*;

public class Cycle {
    static ArrayList cycle = new ArrayList();
    static LinkedList DFSStack = new LinkedList();
    static Set<Integer> visited = new HashSet<Integer>();
    static Graph graph;

    public static Boolean DFS(int currentV, int parent){
        visited.add(currentV);
        DFSStack.add(currentV);
        for(Object neighbor: Graphs.neighborListOf(graph, currentV)){
            int child = (int)neighbor;
            if(!visited.contains(child)){
                if(DFS(child, currentV))return true;
            }
            else if(child != parent){
                DFSStack.add(child);
                return true;
            }
        }
        DFSStack.pollLast();
        return false;
    }

    public static List<Integer> ReturnCycle(Graph g){
        graph = g;
        Set vSet = graph.vertexSet();
        visited.clear();
        cycle.clear();

        for(Object v: vSet){
            if(!visited.contains((int)v)){
                DFSStack.clear();
                if(DFS((int)v, -1)){
//                    System.out.println(DFSStack);
                    Integer start = (Integer)DFSStack.pollLast();
                    cycle.add(start);
//                    System.out.print(start + ", ");
                    while(!DFSStack.isEmpty()){
                        if((int)DFSStack.peekLast() == start)break;
//                        System.out.print((Integer)DFSStack.peekLast() + ", ");
                        cycle.add((Integer)DFSStack.pollLast());
                    }
                    cycle.add(start);
                    return cycle;
                }
            }
        }

        return cycle;
    }
}