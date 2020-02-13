package lab5;

import java.io.*;
import java.util.StringTokenizer;

public class B {

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

    private static boolean Rabin_Karp_search(String T, String P1, String P2, int d, int q) {
        int m = P1.length();
        int n = T.length();
        int i, j;
        int p1 = 0;
        int p2 = 0;
        int t = 0;
        int h = 1;
        boolean notfind = true;
        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < m; i++) {
            p1 = (d * p1 + P1.charAt(i)) % q;
            p2 = (d * p2 + P2.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }
        for (i = 0; i <= n - m; i++) {
            if (notfind) {
                if (p1 == t) {
                    for (j = 0; j < m; j++)
                        if (T.charAt(i + j) != P1.charAt(j))
                            break;
                    if (j == m)
                        notfind = false;
                }
            } else {
                if (p2 == t) {
                    for (j = 0; j < m; j++)
                        if (T.charAt(i + j) != P2.charAt(j))
                            break;
                    if (j == m)
                        return true;
                }
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
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
        /*String T = "Rabin–Karp string search algorithm: Rabin-Karp";
        String P = "string";
        int q = Integer.MAX_VALUE;
        int d = 26;
        long starttime = System.currentTimeMillis();
        System.out.println(Rabin_Karp_search(T, P, P, d, q));
        out.println(System.currentTimeMillis() - starttime);
        out.close();*/
    }

    private static boolean Rabin_Karp_search(String T, String P, int d, int q) {
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

    private static void operate() {
        int m = in.nextInt();
        int n = in.nextInt();
        String pattern = in.next();
        String txt = in.next();
        String[] ps = pattern.split("\\*");
        if (ps.length == 1) {
            String p2 = ps[0].trim();
            out.println(Rabin_Karp_search(txt, p2, 26, 2147483647) ? "YES" : "NO");
        } else if (ps[0].length() == 0) {
            out.println(Rabin_Karp_search(txt, ps[1], 26, 2147483647) ? "YES" : "NO");
        } else if (ps[1].length() == 0) {
            out.println(Rabin_Karp_search(txt, ps[0], 26, 2147483647) ? "YES" : "NO");
        } else {
            String p1 = ps[0].trim();
            String p2 = ps[1].trim();
            out.println(Rabin_Karp_search(txt, p1, p2, 26, 2147483647) ? "YES" : "NO");
        }

    }
}


