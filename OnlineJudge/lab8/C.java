package lab8;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class C {
    static class queueArr {
        private int[] a;
        private int front;
        private int rear;

        queueArr(int size) {
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

        Node add(int v) {
            Node tmp = new Node(v);
            if (this.next != null) {
                tmp.next = this.next;
                this.next = tmp;
            } else this.next = tmp;
            return tmp;
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

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int end = in.nextInt();
        int m = in.nextInt();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            nodes.add(new Node(-1));
        }
        int current = buildAdj(m, end, nodes);
       // printNodes(nodes);
        int[] judge = new int[current];
        out.println(getMinDistance(nodes, end, judge));
        out.close();
    }

    private static int getMinDistance(ArrayList<Node> nodes, int end, int[] judge) {
        queueArr q = new queueArr(judge.length + 1);
        judge[0] = 2;
        int[] dis = new int[judge.length];

        Node tmp = nodes.get(0);
        int i;
        while (tmp.next != null) {
            i = tmp.next.value;
            judge[i]++;
            dis[i] = 1;
            q.enqueue(i);
            if (i == end - 1) return dis[end - 1];
            tmp = tmp.next;
        }

        int next;
        while (!q.isEmpty()) {
            next = q.dequeue();
            judge[next]++;
            tmp = nodes.get(next);
            while (tmp.next != null) {
                i = tmp.next.value;
                if (judge[i] == 0) {
                    q.enqueue(i);
                    judge[i]++;
                    dis[i] = dis[next] + 1;
                    if (i == end - 1) return dis[end - 1];
                }
                tmp = tmp.next;
            }
        }
        return -1;
    }


    private static int buildAdj(int m, int end, ArrayList<Node> nodes) {
        int current = end;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            switch (w) {
                case 1:
                    nodes.get(u - 1).add(v - 1);
                    break;
                case 2:
                    nodes.add(new Node(-1));
                    nodes.get(u - 1).add(current);
                    nodes.get(current++).add(v - 1);
                    break;
            }
        }
        return current;
    }

    private static void printNodes(ArrayList<Node> nodes) {
        Node tmp;
        for (Node node : nodes) {
            tmp = node;
            while (tmp != null) {
                System.out.print(tmp.value + " ");
                tmp = tmp.next;
            }
            System.out.println();
        }
    }
}
