package lab9;

import java.util.*;

public class B {
    private static class Graph {
        private List<Vertex> vertexList = new ArrayList<>();
        private Map<Vertex, List<Edge>> edgeListMap = new HashMap<>();
    }

    static class Edge {
        private Vertex v;
        private int w;

        Edge(Vertex v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private static class Vertex {
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        Graph graph = new Graph();

        for (int i = 0; i < nodes; i++) {
            Vertex v = new Vertex();
            graph.vertexList.add(v);
            graph.edgeListMap.put(v, new ArrayList<>());
        }

        for (int i = 0; i < edges; i++) {
            Vertex u = graph.vertexList.get(in.nextInt() - 1);
            Vertex v = graph.vertexList.get(in.nextInt() - 1);
            int w = in.nextInt();
            graph.edgeListMap.get(u).add(new Edge(v, w));
            graph.edgeListMap.get(v).add(new Edge(u, w));

        }
        System.out.println(shortestSpanTree(graph));
    }

    private static long shortestSpanTree(Graph graph) {
        long len = 0;
        int nodes = graph.vertexList.size();
        Vertex rv = graph.vertexList.get((int) (Math.random() * (nodes - 1)));

        Set<Vertex> mst = new HashSet<>();
        PriorityQueue<Edge> visit = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));

        mst.add(rv);
        visit.addAll(graph.edgeListMap.get(rv));

        while (!visit.isEmpty()) {
            Edge minEdge = visit.remove();
            Vertex vertex = minEdge.v;
            visit.removeIf(o -> o.v.equals(vertex));
            if (mst.contains(vertex)) continue;
            visit.addAll(graph.edgeListMap.get(vertex));
            mst.add(vertex);
            len += minEdge.w;
        }
        return len;
    }

}
