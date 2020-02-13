package lab7;

import java.io.*;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class A_stop {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
            out.flush();
        }
        out.close();
    }

    private static void operate() {
        int n = in.nextInt();
        int[][] tree = new int[n][2];
        for (int i = 0; i < n; i++) {
            tree[i][0] = in.nextInt();
            tree[i][1] = in.nextInt();
        }
        out.println(judge(n, tree) ? "YES" : "NO");
    }

    private static boolean judge(int n, int[][] tree) {
        // int depth = (int) (Math.log(n + 1) / Math.log(2));
        int[] nzeros = { n + 1 };
        loop(nzeros, tree, 1);
        return true;
    }

    private static boolean loop(int[] nzeros, int[][] tree, int key) {
        int left = tree[key - 1][0];
        nzeros[0]--;
        System.out.println(left + " " + nzeros[0]);
        int right = tree[key - 1][1];
        nzeros[0]--;
        System.out.println(right + " " + nzeros[0]);
        loop(nzeros, tree, left);
        loop(nzeros, tree, right);
        return false;
    }
}
/*
1
5
2 5

5 0
0 0
0 0
*/

/*
1
5
2 3
5 4
0 0
0 0
0 0
*/