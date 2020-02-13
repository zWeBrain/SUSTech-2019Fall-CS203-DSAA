package lab5;

import java.io.*;
import java.util.StringTokenizer;

public class D {
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

    private static void GetNext(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen)
            if (k == -1 || p[j] == p[k])
                next[++j] = ++k;
            else
                k = next[k];
    }

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {
        String s = in.next();
        int[] next = new int[s.length() + 1];
        GetNext(s.toCharArray(), next);
        for (int i = 0; i < next.length; i++) {
            out.print(next[i]+" ");
        }
        out.println();
        int len = s.length() - next[s.length()];
        if (len == s.length()) out.println(len);
        else out.println((s.length() % len == 0) ? 0 : len - s.length() % len);
    }
}
