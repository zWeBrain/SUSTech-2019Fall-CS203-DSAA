package lab7;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class C_pq {
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
        }
        out.close();
    }

    private static void operate() {
        int len = in.nextInt();
        Queue<Long> q = new PriorityQueue<>();
        for (int i = 0; i < len; i++) {
            q.add(in.nextLong());
        }
        long sum = 0;
        long tmp;
        while (q.size() != 1) {
            tmp = q.poll() + q.poll();
            sum += tmp;
            q.add(tmp);
        }
        out.println(sum);
    }
}
