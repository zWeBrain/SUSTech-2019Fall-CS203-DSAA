package lab8;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class F {
    static class Node {
        private int x;
        private int y;
        private int h;
        private int value;
        private int max;
        private int fathers;

        Node(int x, int y, int h, int v) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.max = h;
            this.value = v;
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
        int m = in.nextInt();
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(new Node(in.nextInt(), in.nextInt(), in.nextInt(), i));
        }
        int[][] adj = new int[m][m];

        Node tmp;
        Node flag;

        boolean[] visited = new boolean[m];
        for (int i = 0; i < list.size(); i++) {
            tmp = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                flag = list.get(j);
                if (i != j && isOK(tmp, flag)) {
                    flag.fathers++;
                    adj[i][j] = 1;
                    visited[j] = true;
                }
            }
        }

        ConcurrentLinkedQueue<Node> queue = new ConcurrentLinkedQueue<>();
        long max = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                queue.add(list.get(i));
                max = Math.max(max, list.get(i).h);
            }
            visited[i] = !visited[i];

        }

        while (!queue.isEmpty()) {
            tmp = queue.poll();
            for (int i = 0; i < m; i++) {
                flag = list.get(i);
                if (flag != tmp && adj[tmp.value][i] == 1 && flag.fathers != 0) {
                    flag.fathers--;
                    flag.max = Math.max(flag.max, flag.h + tmp.max);
                    max = Math.max(max, flag.max);
                    if (flag.fathers == 0) queue.add(flag);
                }
            }
        }
        //printMatrix(adj);
        out.println(max);
    }

    private static boolean isOK(Node bottom, Node top) {
        if (bottom.x > top.x && bottom.y > top.y) return true;
        else return bottom.y > top.x && bottom.x > top.y;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                out.print(anInt + " ");
            }
            out.println();
        }
    }
}
