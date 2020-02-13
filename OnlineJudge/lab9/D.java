package lab9;


import java.io.*;
import java.util.*;

public class D {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

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
        private int v;
        private int index;

        Vertex(int i, int value) {
            v = value;
            index = i;
        }
    }


    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Graph graph = new Graph();
        int nodes = n * m;

        for (int i = 0; i < nodes; i++) {
            Vertex v = new Vertex(i, in.nextInt());
            graph.vertexList.add(v);
            graph.edgeListMap.put(v, new ArrayList<>());
        }

        int[] direction = new int[4];
        Vertex u, v;
        for (int i = 0; i < nodes; i++) {
            u = graph.vertexList.get(i);
            direction[0] = (i + 1) % m == 0 ? -1 : i + 1;
            direction[1] = i % m == 0 ? -1 : i - 1;
            direction[2] = i - m;
            direction[3] = i + m;
            for (int j = 0; j < 4; j++) {
                if (direction[j] > -1 && direction[j] < n * m) {
                    v = graph.vertexList.get(direction[j]);
                    graph.edgeListMap.get(u).add(new Edge(v, v.v * u.v));
                }
            }
        }
        out.println(shortestSpanTree(graph));
        out.close();
    }

    private static long shortestSpanTree(Graph graph) {
        long len = 0;
        int nodes = graph.vertexList.size();
        Vertex rv = graph.vertexList.get((int) (Math.random() * (nodes - 1)));

        Set<Integer> mst = new HashSet<>();
        PriorityQueue<Edge> visit = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);

        mst.add(rv.index);
        visit.addAll(graph.edgeListMap.get(rv));
        Edge minEdge;
        while (!visit.isEmpty()) {
            minEdge = visit.remove();
            Vertex vertex = minEdge.v;
           /* while ((!visit.isEmpty()) && (!mst.contains(vertex))) {
                vertex = visit.remove().v;
            }*/
            if (mst.size() == nodes) return len;

            if (!mst.contains(vertex.index)) {
                visit.addAll(graph.edgeListMap.get(vertex));
                mst.add(vertex.index);
                len += minEdge.w;
                //System.out.println(minEdge.w + "   " + vertex.index);
            }

        }
        return len;
    }

}
