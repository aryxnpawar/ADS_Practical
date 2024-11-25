import java.util.*;
import java.util.HashMap;

public class DijkstrasAlgorithm {
    static class Edge {
        final String from;
        final String to;
        final double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("Edge: %s -> %s, cost: %.2f", from, to, weight);
        }
    }

    public Map<String, Double> findShortestPath(String startVertex, List<Edge> edges) {
        // Adjacency list representation
        HashMap<String, List<Edge>> adjList = new HashMap<>();
        for (Edge edge : edges) {
            adjList.computeIfAbsent(edge.from, k -> new ArrayList<>()).add(edge);
        }

        // Min-heap to prioritize nodes with smaller distances
        PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));
        HashMap<String, Double> distances = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        // Initialize distances
        for (Edge edge : edges) {
            distances.put(edge.from, Double.MAX_VALUE);
            distances.put(edge.to, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);

        pq.offer(new AbstractMap.SimpleEntry<>(startVertex, 0.0));

        while (!pq.isEmpty()) {
            Map.Entry<String, Double> current = pq.poll();
            String currentVertex = current.getKey();
            double currentDistance = current.getValue();

            if (visited.contains(currentVertex)) continue;
            visited.add(currentVertex);

            for (Edge edge : adjList.getOrDefault(currentVertex, Collections.emptyList())) {
                String neighbor = edge.to;
                double newDistance = currentDistance + edge.weight;

                if (newDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);
                    pq.offer(new AbstractMap.SimpleEntry<>(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 5),
                new Edge("B", "C", 1),
                new Edge("A", "C", 10),
                new Edge("C", "D", 2),
                new Edge("B", "D", 9),
                new Edge("D", "E", 3)
        );

        DijkstrasAlgorithm algorithm = new DijkstrasAlgorithm();
        Map<String, Double> shortestPaths = algorithm.findShortestPath("A", edges);

        System.out.println("Shortest paths from A:");
        for (Map.Entry<String, Double> entry : shortestPaths.entrySet()) {
            System.out.printf("To %s: %.2f\n", entry.getKey(), entry.getValue());
        }
    }
}
