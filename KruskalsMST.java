import java.util.*;
import java.util.HashMap;



public class KruskalsMST {

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

    public List<Edge> findMST(List<Edge> edges){
        List<Edge> res = new ArrayList<>();

        class UnionFind{

            public HashMap<String,String> parent = new HashMap<>();
            public UnionFind(){
                for (Edge e :
                        edges) {
                    parent.putIfAbsent(e.from,e.from);
                    parent.putIfAbsent(e.to,e.to);
                }
            }

            public String find(String vertex){
                if(!parent.get(vertex).equals(vertex)){
                    parent.put(vertex,find(parent.get(vertex)));
                }
                return parent.get(vertex);
            }

            public void union(String vertexA,String vertexB){
                String rootA = find(vertexA);
                String rootB = find(vertexB);
                if (!rootA.equals(rootB)){
                    parent.put(rootA,rootB);
                }
            }

            public boolean isConnected(String vertexA,String vertexB){
                return find(vertexA).equals(find(vertexB));
            }
        }

        UnionFind uf = new UnionFind();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(x->x.weight));
        pq.addAll(edges);

        while (!pq.isEmpty()){
            Edge currentEdge = pq.poll();
            String from = currentEdge.from;
            String to = currentEdge.to;

            if (!uf.isConnected(from,to)){
                res.add(currentEdge);
                uf.union(from,to);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge("A","B",5),
                new Edge("B","C",4),
                new Edge("B","D",2),
                new Edge("A","D",4),
                new Edge("A","E",1),
                new Edge("E","D",2),
                new Edge("E","F",1),
                new Edge("F","D",5),
                new Edge("F","G",7),
                new Edge("D","G",11),
                new Edge("D","H",2),
                new Edge("C","H",4),
                new Edge("G","H",1),
                new Edge("I","G",4),
                new Edge("I","C",1),
                new Edge("I","H",6),
                new Edge("C","J",2),
                new Edge("I","J",0)
        );

        KruskalsMST mst = new KruskalsMST();
        for (Edge e :
                mst.findMST(edges)) {
            System.out.println(e.toString());
        }

    }

}


