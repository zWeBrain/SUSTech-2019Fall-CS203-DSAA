package lab6;

import java.io.*;
import java.util.StringTokenizer;

public class A {
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
            int nodes = in.nextInt();
            int max = in.nextInt();
            if (nodes == 1 || max == 1) {
                out.println(1);
            } else if (nodes > max) {
                int h = getH(max, nodes);

                //bao le
                int a = (int)((long) (Math.pow(max, h) - 1) / (max - 1));
                out.println((long) (Math.pow(max, h) - nodes + a) / max + nodes - a);
            } else {
                out.println(nodes - 1);
            }

        }
        out.close();
    }

    private static int getH(int k, int n) {
        int h = 0;
        long a = 0;
        long len=1;
        while (a <= n) {
            a+=len;
            h++;
            len*=k;
        }
        return h - 1;
    }
}
