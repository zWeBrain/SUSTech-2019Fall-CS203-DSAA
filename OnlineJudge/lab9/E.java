package lab9;

import java.io.*;
import java.util.*;

public class E {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }
    }

    static class Graph {
        private long[][] minList;
        private List<Vertex> vertexList = new ArrayList<>();
        private Map<Integer, List<Edge>> edgeList = new HashMap<>();
        private Map<Integer, List<Edge>> fathersEdgeList = new HashMap<>();
        private Map<Integer, List<Integer>> special = new HashMap<>();
        private final int MAX = 1000001;

        Graph(int nodes, int k) {
            minList = new long[nodes][k + 1];
            for (int i = 0; i < nodes; i++) {
                Vertex v = new Vertex(i);
                vertexList.add(v);
                edgeList.put(i, new ArrayList<>());
                fathersEdgeList.put(i, new ArrayList<>());
                special.put(i, new ArrayList<>());
                Arrays.fill(minList[i], MAX);
            }
        }

        private long minMemo(int source, int k, int destination) {
            // System.out.println(2);
            long currentTime = System.currentTimeMillis();
            if (edgeList.get(source).isEmpty()) return 0;

            Edge next;
            int edges;
            PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.dis));
            queue.add(vertexList.get(source));
            while (!queue.isEmpty()) {
                //  System.out.println(System.currentTimeMillis());
                if (System.currentTimeMillis() - currentTime > 3500) {
                    return 0;
                }
                int cur = Objects.requireNonNull(queue.poll()).index;
                if (cur == destination) {
                    //out.println(minList[cur][k]);
                    return minList[cur][k];
                }
                if (edgeList.get(cur).isEmpty()) continue;
                edges = edgeList.get(cur).size();
                for (int i = 0; i < edges; i++) {
                    next = edgeList.get(cur).get(i);
                    minList[next.v.index][0] = Math.min(minList[next.v.index][0], minList[next.u.index][0] + next.w);
                    for (int j = 1; j <= k; j++) {
                        if (special.get(next.u.index).contains(next.v.index))
                            minList[next.v.index][j] = Math.min(minList[next.v.index][j], minList[next.u.index][j - 1]);
                        else
                            minList[next.v.index][j] = Math.min(minList[next.v.index][j], minList[next.u.index][j] + next.w);
                    }
                    vertexList.get(next.v.index).dis = minList[next.v.index][0] + minList[next.v.index][k];
                    queue.add(next.v);
                }
            }
            return minList[destination][k];
        }

        private long min(long l, long l1, long l2) {
            return Math.min(Math.min(l, l1), l2);
        }

        private long minPath(int source, int destination, int i) {

            //   System.out.println(1);
            if (source == destination) return 0;
            if (minList[destination][i] < MAX) return minList[destination][i];
            if (fathersEdgeList.get(destination).isEmpty()) return 0;
            int edges = fathersEdgeList.get(destination).size();

            Edge next;
            long min, tmp;
            next = fathersEdgeList.get(destination).get(0);


            if (i == 0) {
                min = minPath(source, next.u.index, 0) + next.w;
                for (int j = 1; j < edges; j++) {
                    next = fathersEdgeList.get(destination).get(j);
                    tmp = minPath(source, next.u.index, 0) + next.w;
                    if (min > tmp)
                        min = tmp;
                }
            } else {
                if (special.get(next.u.index).contains(next.v.index)) {
                    min = Math.min(minPath(source, next.u.index, i - 1)
                            , minPath(source, next.u.index, i) + next.w);
                } else min = minPath(source, next.u.index, i) + next.w;

                for (int j = 1; j < edges; j++) {
                    next = fathersEdgeList.get(destination).get(j);
                    if (special.get(next.u.index).contains(next.v.index)) {
                        tmp = Math.min(minPath(source, next.u.index, i - 1), minPath(source, next.u.index, i) + next.w);
                    } else tmp = minPath(source, next.u.index, i) + next.w;
                    if (min > tmp)
                        min = tmp;
                }
            }
            if (min < MAX) minList[destination][i] = min;
            return min;
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int cities = in.nextInt();
        int edges = in.nextInt();
        int special = in.nextInt();
        int k = in.nextInt();

        if (special < k) k = special;

        Graph graph = new Graph(cities, k);
        for (int i = 0; i < edges; i++) {
            Vertex u = graph.vertexList.get(in.nextInt() - 1);
            Vertex v = graph.vertexList.get(in.nextInt() - 1);
            int w = in.nextInt();
            graph.fathersEdgeList.get(v.index).add(new Edge(u, v, w));
            graph.edgeList.get(u.index).add(new Edge(u, v, w));
        }

        for (int i = 0; i < special; i++) {
            Vertex u = graph.vertexList.get(in.nextInt() - 1);
            Vertex v = graph.vertexList.get(in.nextInt() - 1);
            graph.special.get(u.index).add(v.index);
            graph.fathersEdgeList.get(v.index).add(new Edge(u, v, 1000001));
            graph.edgeList.get(u.index).add(new Edge(u, v, 1000001));
        }

        //long result = graph.minPath(in.nextInt() - 1, in.nextInt() - 1, k);
        //System.out.println(result);
        int source = in.nextInt() - 1;
        Arrays.fill(graph.minList[source], 0);

        int finalK = k;
        int des = in.nextInt() - 1;

        graph.minMemo(source, k, des);
        out.println(graph.minPath(source, des, finalK));
        out.close();
        //System.out.println(graph.minMemo(source, k, in.nextInt() - 1));
        // System.out.println(graph.minList[in.nextInt() - 1][k]);
    }

    static class Edge {
        private Vertex v;
        private Vertex u;
        private int w;

        Edge(Vertex u, Vertex v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class Vertex {
        private int index;
        private long dis;

        Vertex(int i) {
            index = i;
        }
    }

}
