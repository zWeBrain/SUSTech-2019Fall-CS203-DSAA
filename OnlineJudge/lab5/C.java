package lab5;

import java.io.*;
import java.util.StringTokenizer;

public class C {
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

    private static boolean Rabin_Karp_search(String T, String P, int d, int q) {
        if (P.length() > T.length()) return false;
        int m = P.length();
        int n = T.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;
        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;
        for (i = 0; i < m; i++) {
            p = (d * p + P.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }
        for (i = 0; i <= n - m; i++) {

            if (p == t) {
                for (j = 0; j < m; j++)
                    if (T.charAt(i + j) != P.charAt(j))
                        break;

                if (j == m)
                    return true;
            }

            if (i < n - m) {
                t = (d * (t - T.charAt(i) * h) + T.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int test = in.nextInt();
        String s;
        String next;
        int count = 0;
        for (int i = 0; i < test; i++) {
            s = in.next();
            next = in.next();
            if (Rabin_Karp_search(next, s.substring(0, (s.length() - 1) / 3 + 1), 20, 101))
                count++;
        }
        out.println(count);
        out.close();
    }
}
