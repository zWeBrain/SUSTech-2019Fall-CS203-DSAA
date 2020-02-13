package lab8;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class D {
    static class queue {
        private int[] a;
        private int front;
        private int rear;

        queue(int size) {
            a = new int[size];
            front = 0;
            rear = 0;
        }

        void enqueue(int e) {
            if ((rear + 1) % a.length == front) {
                return;
            }
            a[rear] = e;
            rear = (rear + 1) % a.length;
        }

        int dequeue() {
            int e = a[front];
            front = (front + 1) % a.length;
            return e;
        }

        public boolean isEmpty() {
            return front == rear;
        }

    }

    static class Node {
        Node next;
        int value;

        Node(int v) {
            value = v;
        }

        void add(int v) {
            Node tmp = new Node(v);
            if (this.next != null) {
                tmp.next = this.next;
                this.next = tmp;
            } else this.next = tmp;
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
        int[] visited = new int[cities];
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < cities; i++) nodes.add(new Node(-1));
        buildAdj(edges, nodes);
        // printNodes(nodes);
        ArrayList<Integer> red = new ArrayList<>();
        ArrayList<Integer> black = new ArrayList<>();
        dyeing(nodes, red, black, visited);

        if (red.size() <= black.size()) {
            out.println(red.size());
            for (Integer integer : red) {
                out.print((integer + 1) + " ");
            }
            out.println();
        } else {
            out.println(black.size());
            for (Integer integer : black) {
                out.print((integer + 1) + " ");
            }
            out.println();
        }
        out.flush();
    }

    private static void dyeing(ArrayList<Node> nodes, ArrayList<Integer> red, ArrayList<Integer> black, int[] visited) {
        queue q = new queue(visited.length + 1);
        visited[0] = 2;
        red.add(0);
        Node tmp = nodes.get(0);
        int i;
        while (tmp.next != null) {
            i = tmp.next.value;
            visited[i]++;
            black.add(i);
            q.enqueue(i);
            tmp = tmp.next;
        }

        int next;
        while (!q.isEmpty()) {
            next = q.dequeue();
            visited[next]++;
            tmp = nodes.get(next);
            while (tmp.next != null) {
                i = tmp.next.value;
                if (visited[i] == 0) {
                    q.enqueue(i);
                    visited[i]++;
                    if (red.contains(next)) black.add(i);
                    else red.add(i);
                }
                tmp = tmp.next;
            }
        }
    }

    private static void buildAdj(int edges, ArrayList<Node> nodes) {
        for (int i = 0; i < edges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            nodes.get(u - 1).add(v - 1);
            nodes.get(v - 1).add(u - 1);
        }
    }

    /*private static void printNodes(ArrayList<Node> nodes) {
        Node tmp;
        for (Node node : nodes) {
            tmp = node;
            while (tmp != null) {
                System.out.print(tmp.value + " ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    }*/

}
