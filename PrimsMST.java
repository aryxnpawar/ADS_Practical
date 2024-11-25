import java.util.*;
import java.util.HashMap;

public class PrimsMST {
    static class Edge{
    final String from;
    final String to;
    final double weight;

    public Edge(String from,String to,double weight){
        this.from=from;
        this.to=to;
        this.weight=weight;
    }

    @Override
    public String toString(){
        return String.format("Edge : %s -> %s, cost : %.2f",from,to,weight);
    }
}

    public List<Edge> findMST(String startVertex,List<Edge> edges){
        List<Edge> res = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String,List<Edge>> adjList = new HashMap<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.weight));

        for (Edge e :
                edges) {
        adjList.computeIfAbsent(e.from,k->new ArrayList<>()).add(e);
        adjList.computeIfAbsent(e.to,k->new ArrayList<>()).add(new Edge(e.to,e.from,e.weight));
        }

        visited.add(startVertex);
        pq.addAll(adjList.getOrDefault(startVertex,Collections.emptyList()));
        
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            String  from = edge.from;
            String  to = edge.to;
            
            if(visited.contains(to))
                continue;

            res.add(edge);
            visited.add(to);

            for (Edge adjacentEdge :
                    adjList.getOrDefault(edge.to, Collections.emptyList())) {
                if (!visited.contains(adjacentEdge.to))
                    pq.add(adjacentEdge);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge("1","4",0),
                new Edge("4","7",8),
                new Edge("1","0",10),
                new Edge("1","2",3),
                new Edge("0","2",1),
                new Edge("0","3",4),
                new Edge("2","3",2),
                new Edge("2","5",8),
                new Edge("3","5",2),
                new Edge("3","6",7),
                new Edge("5","6",6),
                new Edge("5","4",1),
                new Edge("5","7",9),
                new Edge("6","7",12)
        );

        PrimsMST mst = new PrimsMST();
        for (Edge e :
                mst.findMST("0",edges)) {
            System.out.println(e.toString());
        }

    }

}


