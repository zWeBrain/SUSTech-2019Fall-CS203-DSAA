package lab9;


import java.util.*;

public class F {
    private static class Graph {
        private List<Vertex> vertexList = new ArrayList<>();
        private Map<Vertex, List<Edge>> edgeListMap = new HashMap<>();
        //private Set<Integer> visited = new HashSet<>();
    }

    static class Edge {
        private Vertex v;
        private Vertex u;

        Edge(Vertex u, Vertex v) {
            this.u = u;
            this.v = v;
        }
    }

    private static class Vertex {
        private long x;
        private long y;
        private long rid;
        private int time;
        private int index;
        private HashSet<Integer> fathers;


        Vertex(int i, long x, long y, long r, int t) {
            fathers = new HashSet<>();
            this.x = x;
            this.y = y;
            rid = r;
            time = t;
            index = i;
        }
    }

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int nodes = in.nextInt();
        Graph graph = new Graph();
        for (int i = 0; i < nodes; i++) {
            Vertex v = new Vertex(i, in.nextLong(), in.nextLong(), in.nextInt(), in.nextInt());
            graph.vertexList.add(v);
            graph.edgeListMap.put(v, new ArrayList<>());
        }
        Vertex tmp;
        Vertex flag;
        boolean[] visited = new boolean[nodes];
        //  boolean[] huanLi = new boolean[nodes];
        long sum = 0;
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));


        for (int i = 0; i < nodes; i++) {
            tmp = graph.vertexList.get(i);
            for (int j = 0; j < nodes; j++) {
                flag = graph.vertexList.get(j);
                if (i != j && isOK(tmp, flag)) {
                    flag.fathers.add(tmp.index);
                    graph.edgeListMap.get(tmp).add(new Edge(tmp, flag));
                    visited[j] = true;
                }
            }
        }

        ArrayList<List<Integer>> SCComponents = getSCComponents(graph);
        List<Integer> list;
        for (List<Integer> scc : SCComponents) {
            boolean f = false;
            if (scc.size() > 1) {
                list = scc;
                Vertex vertex;
                int min = Integer.MAX_VALUE;
                for (Integer integer : list) {
                    vertex = graph.vertexList.get(integer);
                    vertex.fathers.removeAll(list);
                    //System.out.println("ewq      "+vertex.fathers);
                    if (!vertex.fathers.isEmpty()) f = true;
                    min = Math.min(min, vertex.time);
                }
                if (!f)
                    sum += min;
            }
        }
        //  System.out.println(SCComponents);

       /* for (int i = 0; i < nodes; i++) {
            if (huanLi[i]) {
                cur(graph, huanLi, i);
            }
        }*/

        for (int i = 0; i < nodes; i++) {
            if (!visited[i]) {
                queue.add(graph.vertexList.get(i));
            }
            // visited[i] = !visited[i];
        }
        //visited = new boolean[nodes];
        while (!queue.isEmpty()) {
            tmp = queue.poll();
            /*if (!visited[tmp.index]) {
                sum += tmp.time;
                cur(graph, visited, tmp.index);
            }*/
            sum += tmp.time;
        }
        System.out.println(sum);
    }

    private static void cur(Graph graph, boolean[] booleans, int i) {
        List<Edge> list = graph.edgeListMap.get(graph.vertexList.get(i));
        int next;
        for (Edge edge : list) {
            next = edge.v.index;
            if (!booleans[next]) {
                booleans[next] = true;
                cur(graph, booleans, next);
            }
        }
    }

    private static boolean isOK(Vertex tmp, Vertex flag) {
        double dis = Math.sqrt((tmp.x - flag.x) * (tmp.x - flag.x) + (tmp.y - flag.y) * (tmp.y - flag.y));
        return tmp.rid >= dis;
    }

    private static ArrayList<List<Integer>> getSCComponents(Graph graph) {
        int[] preCount = new int[1];
        long[] low = new long[graph.vertexList.size()];
        boolean[] visited = new boolean[graph.vertexList.size()];
        Stack<Vertex> stack = new Stack<>();
        ArrayList<List<Integer>> sccComp = new ArrayList<>();

        for (int v = 0; v < graph.vertexList.size(); v++)
            if (!visited[v]) dfs(v, low, visited, stack, preCount, graph, sccComp);

        return sccComp;
    }

    public static void dfs(int v, long[] low, boolean[] visited, Stack<Vertex> stack, int[] preCount, Graph graph, ArrayList<List<Integer>> sccComp) {
        low[v] = preCount[0]++;
        visited[v] = true;
        stack.push(graph.vertexList.get(v));
        long min = low[v];
        for (Edge w : graph.edgeListMap.get(graph.vertexList.get(v))) {
            if (!visited[w.v.index])
                dfs(w.v.index, low, visited, stack, preCount, graph, sccComp);
            if (low[w.v.index] < min)
                min = low[w.v.index];
        }
        if (min < low[v]) {
            low[v] = min;
            return;
        }
        List<Integer> component = new ArrayList<>();
        int w;
        do {
            w = stack.pop().index;
            component.add(w);
            low[w] = graph.vertexList.size();
        } while (w != v);
        sccComp.add(component);
    }
}
