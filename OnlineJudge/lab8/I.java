package lab8;

import java.io.*;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;


public class I {
    static class Node {

        private int b;
        private long max;
        private int fathers;
        private ArrayList<Integer> next = new ArrayList<>();

        Node(int a, int b) {
            this.b = b;
            this.max = a;
        }

        void add(int next) {
            this.next.add(next);
        }
    }

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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {
        long sum = 0L;
        int nodes = in.nextInt();
        int edges = in.nextInt();
        ArrayList<Node> list = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            list.add(new Node(in.nextInt(), in.nextInt()));
        }

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        boolean[] visited = new boolean[nodes];
        buildAdj(edges, list, visited);

        for (int i = 0; i < list.size(); i++) {
            if (!visited[i]) queue.add(i);
        }

        ArrayList<Integer> tmp;
        Node next;
        Node that;
        int paths;

        while (!queue.isEmpty()) {
            that = list.get(queue.poll());
            tmp = that.next;
            paths = tmp.size();
            for (int i = 0; i < paths; i++) {
                int temp = tmp.get(i);
                next = list.get(temp);
                next.fathers--;
                list.get(temp).max = (list.get(temp).max + that.max) % MOD;
                if (next.fathers == 0) queue.add(temp);
                sum = (sum + that.max * list.get(temp).b) % MOD;
            }
        }
        out.println(sum % MOD);
    }

    private static void buildAdj(int edges, ArrayList<Node> nodes, boolean[] visited) {
        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            visited[v - 1] = true;
            nodes.get(u - 1).add(v - 1);
            nodes.get(v - 1).fathers++;
        }
    }

}
