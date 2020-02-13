package lab9;

import java.util.*;

public class C {
    private static class Graph {
        private List<Vertex> vertexList = new ArrayList<>();
        private Map<Vertex, List<Edge>> edgeListMap = new HashMap<>();
    }

    static class Edge {
        private Vertex v;

        Edge(Vertex v) {
            this.v = v;
        }
    }

    private static class Vertex {
        private int index;

        Vertex(int i) {
            index = i;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        Graph graph1 = new Graph();
        Graph graph2 = new Graph();
        for (int i = 0; i < nodes; i++) {
            Vertex v = new Vertex(i);
            graph1.vertexList.add(v);
            graph1.edgeListMap.put(v, new ArrayList<>());
            graph2.vertexList.add(v);
            graph2.edgeListMap.put(v, new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            Vertex u1 = graph1.vertexList.get(u);
            Vertex v1 = graph1.vertexList.get(v);
            Vertex u2 = graph2.vertexList.get(u);
            Vertex v2 = graph2.vertexList.get(v);
            graph1.edgeListMap.get(u1).add(new Edge(v1));
            graph2.edgeListMap.get(v2).add(new Edge(u2));
        }
        System.out.println(isAllConnected(graph1, graph2) ? "Bravo" : "ovarB");
    }

    private static boolean isAllConnected(Graph graph1, Graph graph2) {
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        int v = (int) (Math.random() * (graph1.vertexList.size() - 1));
        DFS(v, graph1, visited1);

        DFS(v, graph2, visited2);

        return visited1.size() == graph2.vertexList.size() && graph1.vertexList.size() == visited2.size();
    }

    private static void DFS(int source, Graph graph, Set<Integer> visited) {
        visited.add(source);
        Vertex v = graph.vertexList.get(source);
        int tmp;
        for (int i = 0; i < graph.edgeListMap.get(v).size(); i++) {
            tmp = graph.edgeListMap.get(v).get(i).v.index;
            if (!visited.contains(tmp)) {
                DFS(tmp, graph, visited);
            }
        }
    }

}
