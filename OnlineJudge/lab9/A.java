package lab9;

import java.util.*;

public class A {
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
        private int index;
        private long dis = infinite_dis;

        Vertex(int i) {
            index = i;
        }
    }

    private final static long infinite_dis = Long.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        Graph graph = new Graph();

        for (int i = 0; i < nodes; i++) {
            Vertex v = new Vertex(i);
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

        System.out.println(shortestPath(in.nextInt() - 1, graph, in.nextInt() - 1));
    }

    private static long shortestPath(int source, Graph graph, int target) {
        Vertex s = graph.vertexList.get(source);
        graph.vertexList.get(source).dis = 0;

        Queue<Vertex> Visit = new PriorityQueue<>(Comparator.comparingLong(o -> o.dis));

        Visit.addAll(graph.vertexList);
        Vertex min;
        while (!Visit.isEmpty()) {
            min = Visit.poll();
            if (min.dis == infinite_dis) return -1;
            else if (min.index == target) return min.dis;

            for (Edge edge : graph.edgeListMap.get(min)) {
                long newPath = min.dis + edge.w;
                if (edge.v.dis > newPath) {
                    Visit.remove(edge.v);
                    edge.v.dis = newPath;
                    Visit.add(edge.v);
                }
            }
        }
        return (graph.vertexList.get(target).dis == infinite_dis) ? -1 : graph.vertexList.get(target).dis;
    }
}
/*
9 13
1 2 1
2 3 1
4 1 6
3 4 2
3 5 10
5 4 5
5 6 1
6 7 2
7 8 1
4 8 3
7 9 1
9 6 2
4 7 1
1 9
 */
