package lab5;


import java.io.*;
import java.util.StringTokenizer;

public class RK {
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

    private static void Rabin_Karp_search(String T, String P, int d, int q) {
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
                    out.println("Pattern found at index :" + i);
            }

            if (i < n - m) {
                t = (d * (t - T.charAt(i) * h) + T.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args) {
        String T = "Rabin–Karp string search algorithm: Rabin-Karp";
        String P = "abin";
        //99999989
        int q = 9997;
        int d = 26;
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Rabin_Karp_search(T, P, d, q);
        }
        out.println(System.currentTimeMillis() - starttime);
        out.close();
    }
}
