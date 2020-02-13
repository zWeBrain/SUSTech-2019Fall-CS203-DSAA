package lab8;

import org.jetbrains.annotations.Contract;

import java.io.PrintWriter;
import java.io.*;
import java.util.*;

public class E {
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

    static class Node {
        Node next;
        Node father;

        int value;
        int degree;

        @Contract(pure = true)
        Node(int v) {
            value = v;
        }

        void addNext(int v) {
            Node tmp = new Node(v);
            if (this.next != null) {
                tmp.next = this.next;
                this.next = tmp;
            } else this.next = tmp;
        }

        void addFather(int v) {
            Node tmp = new Node(v);
            if (this.father != null) {
                tmp.father = this.father;
                this.father = tmp;
            } else this.father = tmp;
            degree++;
        }

        void removeFather() {
            degree--;
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {
        int cities = in.nextInt();
        int edges = in.nextInt();
        int[] output = new int[cities];
        int flag = cities - 1;
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < cities; i++) {
            nodes.add(new Node(i));
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        queue.clear();
        buildAdj(edges, nodes, queue);
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            output[flag--] = tmp.value + 1;
            while (tmp.next != null) {
                nodes.get(tmp.next.value).removeFather();
                if (nodes.get(tmp.next.value).degree == 0)
                    queue.add(nodes.get(tmp.next.value));
                tmp = tmp.next;
            }
        }
        for (int value : output) {
            out.print(value + " ");
        }
        out.println();
    }

    private static void buildAdj(int edges, ArrayList<Node> nodes, PriorityQueue<Node> queue) {
        int u, v;
        boolean[] visited = new boolean[nodes.size()];

        for (int i = 0; i < edges; i++) {
            u = in.nextInt();
            v = in.nextInt();
            nodes.get(v - 1).addNext(u - 1);
            nodes.get(u - 1).addFather(v - 1);
            visited[u - 1] = true;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                nodes.get(i).degree = 0;
                // refresh(i, nodes, 0);
                queue.add(nodes.get(i));
            }
        }
    }

    private static void refresh(int i, ArrayList<Node> nodes, int degree) {
        Node tmp = nodes.get(i);
        while (tmp.next != null) {
            tmp = tmp.next;
            nodes.get(tmp.value).degree = Math.max(degree + 1, nodes.get(tmp.value).degree);
            refresh(tmp.value, nodes, tmp.degree);
        }
    }
}
